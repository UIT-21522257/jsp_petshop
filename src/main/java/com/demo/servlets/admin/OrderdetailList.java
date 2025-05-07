package com.demo.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.OrderDetails;
import com.demo.entities.Orders;
import com.demo.entities.Pets;
import com.demo.models.OrderModel;
import com.demo.models.PetModel;

@WebServlet("/admin/orderdetaillist")
public class OrderdetailList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    OrderModel orderModel = new OrderModel();
    PetModel petModel = new PetModel();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<OrderDetails> orderdetailList = orderModel.getAllOrderdetails(); 
		request.setAttribute("orderdetailList", orderdetailList);
		List<Pets> pets = new ArrayList<Pets>();
		for(OrderDetails orderdetail: orderdetailList)
		{
			Pets pet = new Pets();
			pet = petModel.findPetById(orderdetail.getId());
			pets.add(pet);
		}
		request.setAttribute("pets", pets);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/show-orderdetail.jsp"); 
		dispatcher.forward(request, response); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle post requests if needed
    }
}
