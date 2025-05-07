package com.demo.servlets.user;

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
 * Servlet implementation class CachnuoiServlet
 */
@WebServlet("/changepassword")
public class Change_PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Change_PasswordServlet() {
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
		if (action == null|| action.equalsIgnoreCase("changepassword")) {
			doGet_ChangePassword(request, response);
		}

	}

	protected void doGet_ChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/change_password.jsp").forward(request, response);
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
		} else if (action.equalsIgnoreCase("changepassword")) {
			doPost_ChangePassword(request, response);

		}
	}

	protected void doPost_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
	}

	protected void doPost_ChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String oldPass = request.getParameter("oldpass");
		String newPass = request.getParameter("newpass");
		UserModel userModel = new UserModel();
		Users users =( Users)request.getSession().getAttribute("user");
		if (users != null) {
			if (BCrypt.checkpw(oldPass, users.getPassword())) {
				users.setPassword(BCrypt.hashpw(newPass, BCrypt.gensalt()));
				if (userModel.update(users)) {
					response.sendRedirect("login");
				}
			} else {
				request.getSession().setAttribute("msg", "Mat khau cu khong dung! Vui long thu lai");
				response.sendRedirect("changepassword");
			}
		} 
		// TODO Auto-generated method stub
	}

}
