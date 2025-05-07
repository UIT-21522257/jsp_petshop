package com.demo.servlets.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Part;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entities.Pets;
import com.demo.entities.PicturePets;
import com.demo.entities.Users;
import com.demo.helpers.UploadFileHelper;
import com.demo.models.PetModel;
import com.demo.models.PicturePetModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/admin/addsanpham")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 10
)
public class AddsanphamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddsanphamServlet() {
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
			PetModel petModel = new PetModel();
			List<Pets> pets = petModel.findAll();
			request.setAttribute("p", "../admin/addpet.jsp");
			request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("addpet")) {
			doPost_AddPet(request, response);
		}
	}
	protected void doPost_AddPet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PetModel petModel = new PetModel();
		Pets pet = new Pets();
		PicturePetModel picturePetModel = new PicturePetModel();
		List<Part> parts = (List<Part>) request.getParts();
		String petName = request.getParameter("petName");
		String petType = request.getParameter("petType");
		String petGender = request.getParameter("petGender");
		String description = request.getParameter("description");
		String detail = request.getParameter("detail");
		String made = request.getParameter("made");
		String amount = request.getParameter("amount");
		String money = request.getParameter("money");
		String petBirthday = request.getParameter("petBirthday");
		String categoryname = request.getParameter("categoryname");
		String catalogname = request.getParameter("catalogname");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String picturePets = UploadFileHelper.uploadFile("assets/user/images/pet", request, parts.get(9));
		System.out.println(picturePets);
		pet.setPetName(new String(petName.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setPetType(new String(petType.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setPetGender(new String(petGender.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setDescription(new String(description.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setDetail(new String(detail.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setMade(new String(made.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setAmount(Integer.parseInt(amount));
		pet.setStatus(true);
		try {
			pet.setMoney(Double.parseDouble(money));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
            Date birthday = dateFormat.parse(petBirthday);
            pet.setPetBirthday(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
		pet.setImage(new String(picturePets.getBytes("ISO-8859-1"), "UTF-8"));
		pet.setCategoryId(Integer.parseInt(categoryname));
		pet.setCatalogId(Integer.parseInt(catalogname));
		if (petModel.create(pet)) {
			for (int i = 9; i < 13; i++) {
				PicturePets picturePets2 = new PicturePets();
				picturePets2.setImage(UploadFileHelper.uploadFile("assets/user/images/pet", request, parts.get(i)));
				picturePets2.setPetId(petModel.lastPets().getId());
				picturePets2.setCreated(new Date());
				if(picturePetModel.create(picturePets2)) {
					System.out.println("Thêm ảnh pet thành công");
				}else {
					System.out.println("Thêm ảnh pet không thành công");
				}
			}
			request.getSession().setAttribute("message", "Tạo thành công");
			response.sendRedirect("danhsachsanpham");
		} else {
			request.getSession().setAttribute("message", "Tạo thất bại");
			response.sendRedirect("danhsachsanpham");
		}
	}
}
