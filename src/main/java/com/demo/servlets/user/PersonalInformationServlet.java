package com.demo.servlets.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.demo.entities.Address;
import com.demo.entities.Log;
import com.demo.entities.Users;
import com.demo.helpers.ConfigIP;
import com.demo.helpers.IPAddressUtil;
import com.demo.helpers.UploadFileHelper;
import com.demo.models.AddressModel;
import com.demo.models.LogModel;
import com.demo.models.UserModel;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/personalinformation")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 10
)
public class PersonalInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			doGetIndex(request, response);
		}
	}
	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("p", "../user/personal-information.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("update")) {
			doPost_Update(request, response);
		}
	}
	protected void doPost_Update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogModel logModel = new LogModel();
	    // Set character encoding
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    UserModel userModel = new UserModel();
	    AddressModel addressModel = new AddressModel();
	    Users user = (Users) request.getSession().getAttribute("user");

	    String fullName = request.getParameter("fullName");
	    String birthdayString = request.getParameter("birthday");
	    String gender = request.getParameter("gender");
	    String phoneNumber = request.getParameter("phoneNumber");
	    String country = request.getParameter("country");
	    String district = request.getParameter("district");
	    String ward = request.getParameter("ward");
	    String userAddress = request.getParameter("address");
	    
	    Part file = request.getPart("file");

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    // Xử lý avatar
	    String avatar = user.getImage();
	    if (file != null && file.getSize() > 0) {
	        avatar = UploadFileHelper.uploadFile("assets/user/images", request, file);
	        user.setImage(avatar);
	    } else {
	        // Nếu không có file được tải lên, giữ nguyên avatar hiện tại
	        user.setImage(avatar);
	    }

	    try {
	        user.setFullName(new String(fullName.getBytes("ISO-8859-1"), "UTF-8"));
	        Date birthday = dateFormat.parse(birthdayString);
	        String formattedBirthday = dateFormat.format(birthday);
	        user.setBirthday(birthday);
	        user.setGender(new String(gender.getBytes("ISO-8859-1"), "UTF-8"));
	        user.setPhoneNumber(new String(phoneNumber.getBytes("ISO-8859-1"), "UTF-8"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    Address userAddressObj = addressModel.findAddressByIdUser(user.getId());
	    userAddressObj.setCountry(new String(country.getBytes("ISO-8859-1"), "UTF-8"));
	    userAddressObj.setDistrict(new String(district.getBytes("ISO-8859-1"), "UTF-8"));
	    userAddressObj.setWard(new String(ward.getBytes("ISO-8859-1"), "UTF-8"));
	    userAddressObj.setAddress(new String(userAddress.getBytes("ISO-8859-1"), "UTF-8"));

	    if (userModel.update(user) && addressModel.update(userAddressObj)) {
	        request.getSession().removeAttribute("user");
	        request.getSession().setAttribute("user", userModel.findUserById(user.getId()));
	        request.getSession().setAttribute("msg-if", "Cập nhật thành công");
	        logModel.create(new Log(IPAddressUtil.getPublicIPAddress(), "info", ConfigIP.ipconfig(request).getCountryLong(), new Timestamp(new Date().getTime()), "Xem thông tin người dùng", "Cập nhật thông tin người dùng thành công", user.getId()));
	    } else {
	        request.getSession().setAttribute("msg-if", "Cập nhật thất bại");
	    }
	    
	    response.sendRedirect("personalinformation");
	}


}