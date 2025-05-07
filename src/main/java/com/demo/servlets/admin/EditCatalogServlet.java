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
@WebServlet("/admin/editcatalog")
public class EditCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCatalogServlet() {
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
			String id = request.getParameter("id");
			int idCatalog = Integer.parseInt(id);
			CatalogModel catalogModel = new CatalogModel();
			request.setAttribute("catalog", catalogModel.findCatalogById(idCatalog));
			request.setAttribute("p", "../admin/editcatalog.jsp");
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
		if (action.equalsIgnoreCase("editcatalog")) {
			doPost_EditCatalog(request, response);
		}
	}

	protected void doPost_EditCatalog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int idCatalog = Integer.parseInt(id);
		String catalogName = request.getParameter("name");
		String categoryId = request.getParameter("categoryid");
		int idCategory = Integer.parseInt(categoryId);
		CatalogModel catalogModel = new CatalogModel();
		Catalogs catalog = catalogModel.findCatalogById(idCatalog);
		catalog.setName(catalogName);
		catalog.setCategoryId(idCategory);
		if (catalogModel.update(catalog)) {
			response.sendRedirect("chuyenmuc");
		}
	}

}
