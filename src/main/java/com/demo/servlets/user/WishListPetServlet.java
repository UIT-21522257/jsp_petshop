package com.demo.servlets.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.entities.Log;
import com.demo.entities.Pets;
import com.demo.entities.Users;
import com.demo.helpers.ConfigIP;
import com.demo.helpers.IPAddressUtil;
import com.demo.models.LogModel;
import com.demo.models.PetModel;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/wishlistpet")
public class WishListPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WishListPetServlet() {
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
			doGetIndex(request, response);
		} else if (action.equalsIgnoreCase("wishlist")) {
			doGet_Wishlist(request, response);
		} else if (action.equalsIgnoreCase("remove")) {
			doGet_Remove(request, response);
		}
	}

	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("p", "../user/wishlist-pet.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
	}

	protected void doGet_Wishlist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		PetModel petModel = new PetModel();
		LogModel logModel = new LogModel();
		Users user =(Users) request.getSession().getAttribute("user");
		Pets pet = petModel.findPetById(id);
		if (request.getSession().getAttribute("wishlistpets") == null) {
			List<Pets> pets = new ArrayList<>();
			pets.add(pet);
			logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "warning", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Xem sản phẩm yêu thích", "Thêm sản phẩm " + petModel.findPetById(id).getPetName()+" vào danh sách yêu thích", user.getId()));
			request.getSession().setAttribute("wishlistpets", pets);
		} else {
			List<Pets> pets = (List<Pets>) request.getSession().getAttribute("wishlistpets");
			if (petModel.checkExist(id, pets) == -1) {
				pets.add(pet);
				logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "warning", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Xem sản phẩm yêu thích", "Thêm sản phẩm " + petModel.findPetById(id).getPetName()+" vào danh sách yêu thích", user.getId()));
				request.getSession().setAttribute("wishlistpets", pets);
			}else {
				request.getSession().setAttribute("wishlistpets", pets);
			}
		}
            response.sendRedirect("wishlistpet");
	}

	protected void doGet_Remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Pets> pets = (List<Pets>) request.getSession().getAttribute("wishlistpets");
		PetModel petModel = new PetModel();
		LogModel logModel = new LogModel();
		Users user =(Users) request.getSession().getAttribute("user");
		logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "warning", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Xem sản phẩm yêu thích", "Xoá sản phẩm " + petModel.findPetById(id).getPetName()+" khỏi danh sách yêu thích", user.getId()));
		pets.remove(id);
		request.getSession().setAttribute("msg", "Đã xóa thú cưng khỏi danh sách yêu thích");
		request.getSession().setAttribute("wishlistpets", pets);
		response.sendRedirect("wishlistpet");
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
