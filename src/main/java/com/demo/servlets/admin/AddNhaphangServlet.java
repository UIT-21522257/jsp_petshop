package com.demo.servlets.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Catalogs;
import com.demo.entities.Pets;
import com.demo.entities.WarehouseInvoice;
import com.demo.models.CatalogModel;
import com.demo.models.PetModel;
import com.demo.models.WarehouseInvoiceModel;
import com.google.gson.Gson;

@WebServlet("/admin/addwarehouseinvoice")
public class AddNhaphangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNhaphangServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doGet_Index(request, response);
		} else if (action.equalsIgnoreCase("getCatalogs")) {
			doGet_GetCatalogs(request, response);
		} else if (action.equalsIgnoreCase("getPets")) {
			doGet_GetPets(request, response);
		}
	}

	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("p", "../admin/addwarehouseinvoice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	}

	protected void doGet_GetCatalogs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		CatalogModel catalogModel = new CatalogModel();
		List<Catalogs> catalogs = catalogModel.findAllByCategory(Integer.parseInt(categoryId));

		String json = new Gson().toJson(catalogs);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doGet_GetPets(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String catalogId = request.getParameter("catalogId");
		PetModel petModel = new PetModel();
		List<Pets> pets = petModel.findAllByCatalog(Integer.parseInt(categoryId), Integer.parseInt(catalogId));

		String json = new Gson().toJson(pets);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("addpet")) {
			doPost_Add(request, response);
		}
	}

	protected void doPost_Add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		Date tradeDate = new Date();
		String petId = request.getParameter("petId");

		WarehouseInvoice warehouseInvoice = new WarehouseInvoice();
		warehouseInvoice.setPetId(Integer.parseInt(petId));
		warehouseInvoice.setQuantity(Integer.parseInt(quantity));
		warehouseInvoice.setPrice(Double.parseDouble(price));
		warehouseInvoice.setTradeDate(tradeDate);
		warehouseInvoice.setStatus(false);

		WarehouseInvoiceModel warehouseInvoiceModel = new WarehouseInvoiceModel();
		if (warehouseInvoiceModel.create(warehouseInvoice)) {
			response.sendRedirect("quanlinhaphang");
		}
	}
}
