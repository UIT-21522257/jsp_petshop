package com.demo.servlets.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Log;
import com.demo.entities.Users;
import com.demo.helpers.ConfigIP;
import com.demo.helpers.IPAddressUtil;
import com.demo.helpers.MailHelper;
import com.demo.helpers.RandomStringHelper;
import com.demo.models.LogModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class CachnuoiServlet
 */
@WebServlet("/forgotpassword")
public class Forgot_PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forgot_PasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null || action.equalsIgnoreCase("sendotp")) {
			doGet_SendOtp(request, response);
		} else if (action.equalsIgnoreCase("verifyotp")) {
			doGet_VerifyOtp(request, response);
		} else if (action.equalsIgnoreCase("forgotpassword")) {
			doGet_ForgotPassword(request, response);
		}

	}

	protected void doGet_SendOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/send_otp.jsp").forward(request, response);
	}

	protected void doGet_VerifyOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/verify_otp.jsp").forward(request, response);
	}

	protected void doGet_ForgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/forgot_password.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doGet_SendOtp(request, response);
		} else if (action.equalsIgnoreCase("sendotp")) {
			doPost_SendOtp(request, response);
		} else if (action.equalsIgnoreCase("verifyotp")) {
			doPost_VerifyOtp(request, response);
		} else if (action.equalsIgnoreCase("forgotpassword")) {
			doPost_ForgotPassword(request, response);
		}
	}

	protected void doPost_SendOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		System.out.println(email);
		UserModel userModel = new UserModel();
		LogModel logModel = new LogModel();
		Users user = userModel.findUserByEmail(email);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userforgot", user);
			String otp = RandomStringHelper.generateRandomString(6);
			MailHelper.MailHelper(email, "Lấy lại mật khẩu của bạn", "Đây là mã xác thực để lấy lại mật khẩu của bạn /n"
					+ otp + "/n Vui Lòng không cung cấp cho bất kì ai");
			session.setAttribute("otp", otp);
			logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Người dùng vào gmail", "Người dùng nhận được OTP từ mail", user.getId()));
			response.sendRedirect("forgotpassword?action=verifyotp");
		} else {
			request.getSession().setAttribute("msg", "Khong tim thay email!");
			response.sendRedirect("forgotpassword");
		}
	}

	protected void doPost_VerifyOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// day la code nhap vao
		HttpSession session = request.getSession();
		LogModel logModel = new LogModel();
		String otp = request.getParameter("otp");
		Users user = (Users) session.getAttribute("userforgot");
		// day la code khi lay ma
		String verifyOtp = (String) session.getAttribute("otp");
		if(otp.equalsIgnoreCase("")) {
			session.setAttribute("msg", "Vui long nhap ma xac thuc");
		}
		if(otp.equals(verifyOtp)) {
			logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Người dùng nhập mã OTP", "Nhập đúng mã OTP", user.getId()));
			response.sendRedirect("forgotpassword?action=forgotpassword");
		}else {
			logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "warning", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Người dùng nhập mã OTP", "Nhập sai mã OTP", user.getId()));
		session.setAttribute("msg", "Ma xac thuc khong dung, vui long kiem tra lai");
		response.sendRedirect("forgotpassword?action=verifyotp");
		}

	}

	protected void doPost_ForgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

            HttpSession session = request.getSession();
            UserModel userModel = new UserModel();
            LogModel logModel = new LogModel();
            Users user = (Users) session.getAttribute("userforgot");
            if(user != null) {
            	String password = request.getParameter("newpass");
            	String password2 = request.getParameter("newpass2");
            	if(password.equals(password2)) {
            		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            		if(userModel.update(user)) {
            			logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Người dùng vào trang đổi mật khẩu", "Người dùng đổi mật khẩu thành công", user.getId()));
            			response.sendRedirect("login");
            		}
            	}else {
            		logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "warning", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Người dùng vào trang đổi mật khẩu", "Người dùng nhập sai mật khẩu", user.getId()));
            		session.setAttribute("msg", "Mat khau moi khong trung khop! Vui long thu lai");
            		response.sendRedirect("forgotpassword?action=forgotpassword");
            	}
            }
	}

}
