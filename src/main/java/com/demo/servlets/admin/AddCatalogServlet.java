package com.demo.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.entities.Catalogs;
import com.demo.entities.Users;
import com.demo.models.CatalogModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/addcatalog")
public class AddCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCatalogServlet() {
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
		request.setAttribute("p", "../admin/addcatalog.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("addcatalog")) {
			doPost_AddCatalog(request, response);
		}
	}

	protected void doPost_AddCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CatalogModel catalogModel = new CatalogModel();
		Catalogs catalog = new Catalogs();
		String catalogName = request.getParameter("catalogname");
		String categoryName = request.getParameter("categoryname");
		catalog.setCategoryId(Integer.parseInt(categoryName));
		catalog.setName(new String(catalogName.getBytes("ISO-8859-1"), "UTF-8"));
		if (catalogModel.create(catalog)) {
			request.getSession().setAttribute("message", "Tao thanh cong");
			response.sendRedirect("chuyenmuc");
		} else {
			request.getSession().setAttribute("message", "Tao that bai");
			response.sendRedirect("chuyenmuc");
		}
	}

}
