package com.demo.servlets.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Address;
import com.demo.entities.Log;
import com.demo.entities.Users;
import com.demo.helpers.ConfigIP;
import com.demo.helpers.IPAddressUtil;
import com.demo.helpers.LoginAttemptTracker;
import com.demo.helpers.MailHelper;
import com.demo.helpers.RandomStringHelper;
import com.demo.models.AddressModel;
import com.demo.models.LogModel;
import com.demo.models.RoleModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null || action.equalsIgnoreCase("login")) {
			doGet_Login(request, response);
		} else if (action.equalsIgnoreCase("logout")) {
			doGet_Logout(request, response);
		} else if (action.equalsIgnoreCase("register")) {
			doGet_Register(request, response);
		} else if (action.equalsIgnoreCase("verify")) {
			doGet_Verify(request, response);
		} else if (action.equalsIgnoreCase("message")) {
			doGet_Message(request, response);
		}

	}
//
	protected void doGet_Login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);

	}

	protected void doGet_Message(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/message.jsp").forward(request, response);

	}

// xac thuc bang email
	protected void doGet_Verify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String securityCode = request.getParameter("securityCode");
		UserModel userModel = new UserModel();
		Users user = userModel.findUserByUserName(username);
		if (user.getSecurityCode().equalsIgnoreCase(securityCode) && user.getUserName().equalsIgnoreCase(username)) {
				user.setStatus(true);	
				if (userModel.update(user)) {
					LogModel logModel = new LogModel();
					logModel.create(new Log(IPAddressUtil.getPublicIPAddress(),"info",ConfigIP.ipconfig(request).getCountryLong(),new Timestamp(new Date().getTime()), "Đang đăng ký", "Đã đăng ký tài khoản thành công", user.getId()));
					response.sendRedirect("login");
				}	
		} else {
			request.getSession().setAttribute("error", "Xác thực không thành công");
		    response.sendRedirect("login?action=message");
		    return;
		}

	}

	protected void doGet_Logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users user =(Users) request.getSession().getAttribute("user");
		LogModel logModel = new LogModel();
		logModel.create(new Log(IPAddressUtil.getPublicIPAddress(),"info",ConfigIP.ipconfig(request).getCountryLong(),new Timestamp(new Date().getTime()), "Đã đăng nhập", "Đã thoát", user.getId()));
		request.getSession().removeAttribute("user");
		response.sendRedirect("login");

	}

	protected void doGet_Register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/register.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doPost_Index(request, response);
		} else if (action.equalsIgnoreCase("login")) {
			doPost_Login(request, response);
		} else if (action.equalsIgnoreCase("register")) {
			doPost_Register(request, response);
		} 
	}

	protected void doPost_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
	}

	protected void doPost_Login(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    // Check if the user is locked
	    if (LoginAttemptTracker.isLocked(username)) {
	        long secondsRemaining = LoginAttemptTracker.getLockoutTimeRemaining(username);
	        request.getSession().setAttribute("error", "Bạn đã nhập sai quá nhiều lần. Vui lòng chờ " + secondsRemaining + " giây.");
	        response.sendRedirect("login");
	        return;
	    }

	    UserModel userModel = new UserModel();
	    AddressModel addressModel = new AddressModel();
	    LogModel logModel = new LogModel();
	    Users user = userModel.findUserByUserName(username);

	    if (userModel.checkLogin(username, password)) {
	        LoginAttemptTracker.loginSuccessful(username); 

	        logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Chưa đăng nhập", "Đã đăng nhập", user.getId()));
	        if (user.getRoleId() == 1) {
	            request.getSession().setAttribute("user", userModel.findUserByUserName(username));
	            response.sendRedirect("admin/home");
	        } else {
	            if (addressModel.findAddressByIdUser(userModel.findUserByUserName(username).getId()) == null) {
	                addressModel.create(new Address("", "", "", "", userModel.findUserByUserName(username).getId()));
	            }
	            request.getSession().setAttribute("user", userModel.findUserByUserName(username));
	            response.sendRedirect("home");
	        }
	    } else {
	        LoginAttemptTracker.loginFailed(username); // Increment failed attempts on login failure
	        long secondsRemaining = LoginAttemptTracker.getLockoutTimeRemaining(username);
	        if (secondsRemaining > 0) {
	            request.getSession().setAttribute("error", "Bạn đã nhập sai quá nhiều lần. Vui lòng chờ " + secondsRemaining + " giây.");
	            logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "error", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Đang đăng nhập", "Đăng nhập sai thông tin nhiều lần", user.getId()));
	        } else {
	            request.getSession().setAttribute("error", "Tài khoản hoặc mật khẩu không đúng!");
	            logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "warning", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Đang đăng nhập", "Đăng nhập sai thông tin", user.getId()));
	        }
	        response.sendRedirect("login");
	    }
	}
	

	protected void doPost_Register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String securityCode = RandomStringHelper.generateRandomString(6);
		UserModel userModel = new UserModel();
		Users user = new Users();
		user.setUserName(username);
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
		user.setEmail(email);
		user.setSecurityCode(securityCode);
		user.setRoleId(2);
		user.setStatus(false);
		user.setBirthday(new java.util.Date());
		if (userModel.create(user)) {
			MailHelper.MailHelper(email, "Xác thực tài khoản của bạn",
					"Bạn hãy bấm vào " + "<a href='http://localhost:8080/PetShop/login?action=verify&username="
							+ username + "" + "&securityCode=" + securityCode
							+ "'>liên kết</a> này để xác thực tài khoản");
			response.sendRedirect("login?action=message");
		} else {
			request.getSession().setAttribute("error", "Tạo tài khoản không thành công");
		    response.sendRedirect("login?action=message");
		}

	}

}
