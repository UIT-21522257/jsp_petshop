package com.demo.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Users;
import com.demo.models.UserModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/edituser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			UserModel userModel = new UserModel();
			Users user = userModel.findUserById(id);
			request.setAttribute("user", user);
			request.setAttribute("p", "../admin/edituser.jsp");
			request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("edituser")) {
			doPost_EditUser(request, response);
		}
	}

	protected void doPost_EditUser(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int userId = Integer.parseInt(request.getParameter("id"));
	    UserModel userModel = new UserModel();
	    Users user = userModel.findUserById(userId);

	    String fullName = request.getParameter("fullname");
	    String phoneNumber = request.getParameter("phonenumber");
	    String password = request.getParameter("password");
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");

	    // Kiểm tra xem email đã tồn tại chưa
	    Users existingUser = userModel.findUserByEmail(email);
	    if (existingUser != null && existingUser.getId() != userId) {
	        // Nếu email đã tồn tại và không phải của user hiện tại, thông báo lỗi
	        request.getSession().setAttribute("msg-email", "Email đã tồn tại cho tài khoản khác");
	        response.sendRedirect("quanliuser"); 
	        return;
	    }

	    // Kiểm tra xem có thay đổi mật khẩu hay không
	    boolean isPasswordChanged = false;
	    if (password != null && !password.isEmpty()) {
	        isPasswordChanged = true;
	    }

	    // Cập nhật thông tin người dùng
	    user.setFullName(new String(fullName.getBytes("ISO-8859-1"), "UTF-8"));
	    user.setEmail(new String(email.getBytes("ISO-8859-1"), "UTF-8"));
	    user.setPhoneNumber(new String(phoneNumber.getBytes("ISO-8859-1"), "UTF-8"));
	    user.setStatus(true);
	    user.setRoleId(2);
	    user.setUserName(new String(username.getBytes("ISO-8859-1"), "UTF-8"));

	    // Nếu có thay đổi mật khẩu, thì mã hóa và cập nhật
	    if (isPasswordChanged) {
	        String passwordBcrypt = BCrypt.hashpw(password, BCrypt.gensalt());
	        user.setPassword(passwordBcrypt);
	    }

	    if (userModel.update(user)) {
	        request.getSession().setAttribute("message", "Cập nhật thành công");
	        response.sendRedirect("quanliuser");
	    } else {
	        request.getSession().setAttribute("message", "Cập nhật thất bại");
	        response.sendRedirect("quanliuser");
	    }
	}

}
