package com.demo.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Users;
import com.demo.models.AddressModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/adduser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
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
		if (action == null) {
			doGet_Index(request, response);
		}
	}

	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("p", "../admin/adduser.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("addUser")) {
			doPost_AddUser(request, response);
		}
	}

	protected void doPost_AddUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel userModel = new UserModel();
		Users user = new Users();
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		AddressModel addressModel = new AddressModel();
		user.setFullName(new String(fullName.getBytes("ISO-8859-1"), "UTF-8"));
		user.setEmail(new String(email.getBytes("ISO-8859-1"), "UTF-8"));
		user.setPhoneNumber(new String(phoneNumber.getBytes("ISO-8859-1"), "UTF-8"));
//		user.setAddress(new String(address.getBytes("ISO-8859-1"), "UTF-8"));
		user.setStatus(true);
		user.setRoleId(2);
		user.setUserName(new String(username.getBytes("ISO-8859-1"), "UTF-8"));
		String passwordBcrypt = BCrypt.hashpw(password, BCrypt.gensalt());
		user.setPassword(new String(passwordBcrypt.getBytes("ISO-8859-1"), "UTF-8"));
		if (userModel.isExistEmail(email)) {
	        request.getSession().setAttribute("message", "Email đã tồn tại");
	        response.sendRedirect("quanliuser");
	    } else {
	        if (userModel.createByAdmin(user)) {
	            request.getSession().setAttribute("message", "Tạo thành công");
	            response.sendRedirect("quanliuser");
	        } else {
	            request.getSession().setAttribute("message", "Tạo thất bại");
	            response.sendRedirect("quanliuser");
	        }
	    }
	}
}
