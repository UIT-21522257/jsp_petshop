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
import com.demo.entities.Categorys;
import com.demo.entities.Pets;
import com.demo.entities.WarehouseInvoice;
import com.demo.models.CatalogModel;
import com.demo.models.CategoryModel;
import com.demo.models.PetModel;
import com.demo.models.WarehouseInvoiceModel;
import com.google.gson.Gson;

@WebServlet("/admin/editwarehouseinvoice")
public class EditNhaphangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditNhaphangServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doGet_Index(request, response);
		} else if (action.equals("getCatalogs")) {
			getCatalogs(request, response);
		} else if (action.equals("getPets")) {
			getPets(request, response);
		} else if (action.equalsIgnoreCase("confirm")) {
			doGet_Confirm(request, response);
		}
	}

	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WarehouseInvoiceModel warehouseInvoiceModel = new WarehouseInvoiceModel();
		String id = request.getParameter("id");
		int idPet = Integer.parseInt(id);
		WarehouseInvoice warehouseInvoice = warehouseInvoiceModel.findInvoiceByID(idPet);

		PetModel petModel = new PetModel();
		Pets pet = petModel.findPetById(warehouseInvoice.getPetId());
		CatalogModel catalogModel = new CatalogModel();
		Catalogs catalog = catalogModel.findCatalogById(pet.getCatalogId());
		CategoryModel categoryModel = new CategoryModel();
		Categorys category = categoryModel.findCategoryById(pet.getCategoryId());

		request.setAttribute("warehouseInvoice", warehouseInvoice);
		request.setAttribute("pet", pet);
		request.setAttribute("catalog", catalog);
		request.setAttribute("category", category);

		List<Categorys> categories = categoryModel.findAll();
		request.setAttribute("categories", categories);

		request.setAttribute("p", "../admin/editwarehouseinvoice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	}

	private void getCatalogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		CatalogModel catalogModel = new CatalogModel();
		List<Catalogs> catalogs = catalogModel.findAllByCategory(categoryId);
		String json = new Gson().toJson(catalogs);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void getPets(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		int catalogId = Integer.parseInt(request.getParameter("catalogId"));
		PetModel petModel = new PetModel();
		List<Pets> pets = petModel.findAllByCatalog(categoryId, catalogId);
		String json = new Gson().toJson(pets);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("editpet")) {
			doPost_Edit(request, response);
		} 
	}

	protected void doGet_Confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		WarehouseInvoiceModel warehouseInvoiceModel = new WarehouseInvoiceModel();
		WarehouseInvoice warehouseInvoice = warehouseInvoiceModel.findInvoiceByID(id);
		warehouseInvoice.setStatus(true);
		if (warehouseInvoiceModel.update(warehouseInvoice)) {
			PetModel petModel = new PetModel();
			Pets pet = petModel.findPetById(warehouseInvoice.getPetId());
			pet.setAmount(pet.getAmount() + warehouseInvoice.getQuantity());
			if (petModel.update(pet)) {
				request.getSession().setAttribute("msg-invoice", "Nhập hàng thành công");
				response.sendRedirect("quanlinhaphang");
			} else {
				request.getSession().setAttribute("msg-invoice", "Nhập hàng không thành công");
				response.sendRedirect("quanlinhaphang");
			}
		}
	}

	protected void doPost_Edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		WarehouseInvoiceModel warehouseInvoiceModel = new WarehouseInvoiceModel();
		WarehouseInvoice warehouseInvoice = warehouseInvoiceModel.findInvoiceByID(id);
		String petId = request.getParameter("petId");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		Date tradeDate = new Date();
		warehouseInvoice.setPetId(Integer.parseInt(petId));
		warehouseInvoice.setQuantity(Integer.parseInt(quantity));
		warehouseInvoice.setPrice(Double.parseDouble(price));
		warehouseInvoice.setTradeDate(tradeDate);
		warehouseInvoice.setStatus(false);
		if (warehouseInvoiceModel.update(warehouseInvoice)) {
			response.sendRedirect("quanlinhaphang");
		}
	}
}
