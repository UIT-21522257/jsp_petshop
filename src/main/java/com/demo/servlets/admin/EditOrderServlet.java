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
import com.demo.entities.OrderDetails;
import com.demo.entities.Orders;
import com.demo.entities.Pets;
import com.demo.entities.WarehouseInvoice;
import com.demo.models.CatalogModel;
import com.demo.models.CategoryModel;
import com.demo.models.OrderDetailModel;
import com.demo.models.OrderModel;
import com.demo.models.PetModel;
import com.demo.models.WarehouseInvoiceModel;
import com.google.gson.Gson;

@WebServlet("/admin/editorder")
public class EditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditOrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doGet_Index(request, response);
		} else if (action.equalsIgnoreCase("confirm")) {
			doGet_Confirm(request, response);
		}
	}

	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("p", "../admin/editorder.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("editorder")) {
			doPost_Edit(request, response);
		} 
	}

	protected void doGet_Confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		OrderModel orderModel = new OrderModel();
		Orders order = orderModel.findOrderById(id);
		OrderDetailModel orderDetailModel = new OrderDetailModel();
		List<OrderDetails> orderDetails = orderDetailModel.findAllByOrderId(id);
		order.setStatus(1);
		if (orderModel.update(order)) {
			for (OrderDetails orDetails : orderDetails) {
				PetModel petModel = new PetModel();
				Pets pet = petModel.findPetById(orDetails.getPetId());	
				pet.setAmount(pet.getAmount() - orDetails.getQuantity());
				if (petModel.update(pet)) {
					request.getSession().setAttribute("msg-order", "Đặt hàng thành công");
					System.out.println("Thanh cong");
					response.sendRedirect("donhang");
				} else {
					request.getSession().setAttribute("msg-order", "Đặt hàng không thành công");
					System.out.println("Khong Thanh cong");
					response.sendRedirect("donhang");
				}
			}
		}
	}

	protected void doPost_Edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		OrderModel orderModel = new OrderModel();
		Orders order = orderModel.findOrderById(id);
		OrderDetailModel orderDetailModel = new OrderDetailModel();
		List<OrderDetails> orderDetails = orderDetailModel.findAllByOrderId(id);
	}
}
