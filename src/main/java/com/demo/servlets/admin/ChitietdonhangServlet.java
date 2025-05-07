package com.demo.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.OrderDetails;
import com.demo.models.OrderDetailModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/chitietdonhang")
public class ChitietdonhangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChitietdonhangServlet() {
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
		}else if(action.equalsIgnoreCase("chitietdonhang")) {
			doGet_Chitietdonhang(request, response);
		}
	}

	protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("p", "../admin/showorderdetail.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	}
	protected void doGet_Chitietdonhang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		System.out.println(id);
		int idOrder = Integer.parseInt(id);
		OrderDetailModel orderDetailsModel = new OrderDetailModel();
		List<OrderDetails> orderDetails =  orderDetailsModel.findAllByOrderId(idOrder);
		System.out.println(orderDetails);
		if(orderDetails != null) {
			request.getSession().setAttribute("orderdetails", orderDetails);
			response.sendRedirect(request.getContextPath() + "/admin/chitietdonhang");			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
