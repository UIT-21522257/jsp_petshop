package com.demo.servlets.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Address;
import com.demo.entities.GoogleUser;
import com.demo.entities.Log;
import com.demo.entities.Users;
import com.demo.helpers.ConfigIP;
import com.demo.helpers.GoogleLogin;
import com.demo.helpers.IPAddressUtil;
import com.demo.helpers.MailHelper;
import com.demo.helpers.RandomStringHelper;
import com.demo.models.AddressModel;
import com.demo.models.LogModel;
import com.demo.models.RoleModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logingoogle")
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginGoogleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	// LoginGoogleServlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String code = request.getParameter("code");
	        AddressModel addressModel = new AddressModel();
	        LogModel logModel = new LogModel();
	        if (code == null || code.isEmpty()) {
	            RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
	            dis.forward(request, response);
	        } else {
	            String accessToken = GoogleLogin.getToken(code);
	            GoogleUser googlePojo = GoogleLogin.getUserInfo(accessToken);
	            
	            UserModel userModel = new UserModel();
	            Users user = userModel.findUserByEmail(googlePojo.getEmail());
	            if(user != null) {
	                request.getSession().setAttribute("user", user);    
	                response.sendRedirect("home");
	            } else {
	                user = new Users();
	                user.setEmail(googlePojo.getEmail());
	                user.setUserName(googlePojo.getName());
	                user.setRoleId(2);
	                user.setSecurityCode(RandomStringHelper.generateRandomString(6));
	                user.setBirthday(new Date());
	                user.setStatus(true);
	                userModel.create(user);
	                
	                // Kiểm tra xem user có được tạo thành công hay không
	                Users newUser = userModel.findUserByEmail(googlePojo.getEmail());
	                if (newUser != null) {
	                    int userId = newUser.getId();
	                    
	                    // Tạo địa chỉ cho user mới
	                    Address address = new Address("", "", "", "", userId);
	                    addressModel.create(address);
	                    
	                    request.getSession().setAttribute("user", newUser); 
	                    logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Chưa đăng nhập", "Đã đăng nhập bằng google thành công", user.getId()));
	                    response.sendRedirect("home");
	                } else {
	                    // Xử lý trường hợp user không được tạo thành công
	                    request.setAttribute("errorMessage", "Đăng nhập bằng Google thất bại. Vui lòng thử lại.");
	                    RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
	                    dis.forward(request, response);
	                }
	            }
	        }
	    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
}
