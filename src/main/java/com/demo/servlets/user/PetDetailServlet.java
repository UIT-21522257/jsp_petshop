package com.demo.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Comments;
import com.demo.entities.Log;
import com.demo.entities.Pets;
import com.demo.entities.Users;
import com.demo.helpers.ConfigIP;
import com.demo.helpers.IPAddressUtil;
import com.demo.models.CommentModel;
import com.demo.models.LogModel;
import com.demo.models.PetModel;
import com.demo.models.UserModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class PetDetailServlet
 */
@WebServlet("/petdetail")
public class PetDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			doGetIndex(request, response);
		} else if(action.equalsIgnoreCase("addcomment")) {
			doGet_AddComment(request, response);
		}
	}
	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PetModel petModel = new PetModel();
		String id = request.getParameter("id");
		Pets pet = petModel.findPetById(Integer.parseInt(id));
		LogModel logModel = new LogModel();
		Users user =(Users) request.getSession().getAttribute("user");
		logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Chưa xem sản phẩm", "Xem chi tiết sản phẩm " + pet.getPetName(), user.getId()));
		request.setAttribute("pet", pet);
		request.setAttribute("p", "../user/pet-detail.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
	}
	protected void doGet_AddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		int petId = Integer.parseInt(request.getParameter("petId"));
		PetModel petModel = new PetModel();
		Pets pet = petModel.findPetById(petId);
		LogModel logModel = new LogModel();
		Users user =(Users) request.getSession().getAttribute("user");
		logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Xem sản phẩm", "Để lại bình luận về " + pet.getPetName(), user.getId()));
		CommentModel commentModel = new CommentModel();
		UserModel userModel = new UserModel();
		PrintWriter printWriter = response.getWriter();
		Gson gson = new Gson();
	    Map<String, Object> data= new HashMap<>();
	    data.put("comments", commentModel.newComment(petId));
	    data.put("users", userModel.findUsersByPetId(petId));
		printWriter.print(gson.toJson(data));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
          if(action.equalsIgnoreCase("addcomment")) {
			doPost_AddComment(request, response);
		}
	}
	protected void doPost_AddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String petID = request.getParameter("petId");
		String userID = request.getParameter("userId");
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("login");
		}
		int petId = Integer.parseInt(petID);
		int userId = Integer.parseInt(userID);
		String note = request.getParameter("note");
		System.out.println(note);
		CommentModel commentModel = new CommentModel();
		Comments comment = new Comments();
		comment.setPetId(petId);
		comment.setUserId(userId);
		comment.setNote(new String(note.getBytes("ISO-8859-1"), "UTF-8"));
		comment.setCreateDate(new Timestamp(new Date().getTime()));
		if(commentModel.create(comment)) {
			response.sendRedirect("petdetail?id="+petId);
		} else {
			response.sendRedirect("login");
		}
		
	}

}
