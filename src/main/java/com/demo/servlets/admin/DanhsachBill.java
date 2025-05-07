package com.demo.servlets.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Bills;
import com.demo.entities.Log;
import com.demo.entities.Pets;
import com.demo.entities.WarehouseInvoice;
import com.demo.models.BillModel;
import com.demo.models.LogModel;
import com.demo.models.OrderModel;
import com.demo.models.PetModel;
import com.demo.models.WarehouseInvoiceModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/quanlibill")
public class DanhsachBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DanhsachBill() {
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
			BillModel billModel = new BillModel();
			List<Bills> bills = billModel.findAll();
			request.setAttribute("bills", bills);
			request.setAttribute("p", "../admin/showbill.jsp");
			request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("confirm")) {
			doGet_Confirm(request, response);
		}
	}

	protected void doGet_Confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		BillModel billModel = new BillModel();
	    Bills bill = billModel.findBillById(id);
	    bill.setStatus(true);
	    if(billModel.update(bill)) {
	    	response.sendRedirect("quanlibill");	    	
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
