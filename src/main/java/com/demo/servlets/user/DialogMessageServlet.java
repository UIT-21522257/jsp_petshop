package com.demo.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.entities.OrderDetails;
import com.demo.entities.Pets;
import com.demo.models.OrderDetailModel;
import com.demo.models.OrderModel;
import com.demo.models.PetModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/dialogmessage")
public class DialogMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DialogMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		OrderDetailModel orderDetailModel = new OrderDetailModel();
		OrderModel orderModel = new OrderModel();
		int orderId =Integer.parseInt(request.getParameter("orderId")) ;
		System.out.println(request.getParameter("orderId"));
		List<Pets> pets  = new ArrayList<Pets>();
		for (OrderDetails orderDetail : orderDetailModel.findAllByOrderId(orderId)) {
			pets.add(new PetModel().findPetById(orderDetail.getPetId()));	
		}
		Gson gson = new Gson();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("pets", pets);
		data.put("orderdetails", orderDetailModel.findAllByOrderId(orderId));
		printWriter.print(gson.toJson(data));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
