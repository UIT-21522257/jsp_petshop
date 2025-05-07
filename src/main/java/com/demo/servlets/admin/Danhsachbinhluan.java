package com.demo.servlets.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Comments;
import com.demo.entities.Log;
import com.demo.entities.Pets;
import com.demo.entities.WarehouseInvoice;
import com.demo.models.CommentModel;
import com.demo.models.LogModel;
import com.demo.models.PetModel;
import com.demo.models.WarehouseInvoiceModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/quanlibinhluan")
public class Danhsachbinhluan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Danhsachbinhluan() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");
	    if (action == null) {
	        int petId = Integer.parseInt(request.getParameter("id"));

	        CommentModel commentModel = new CommentModel();
	        List<Comments> comments = commentModel.findCommentByPet(petId); 

	        // Đặt danh sách comments vào request để chuyển đến view
	        request.setAttribute("comments", comments);
	        request.setAttribute("p", "../admin/showcomment.jsp");
	        request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
	    } else if (action.equalsIgnoreCase("remove")) {
	        doGet_Remove(request, response);
	    }
	}


	protected void doGet_Remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CommentModel commentModel = new CommentModel();
		commentModel.delete(id);
		response.sendRedirect("quanlibinhluan");

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
