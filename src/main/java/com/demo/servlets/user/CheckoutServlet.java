package com.demo.servlets.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.entities.Address;
import com.demo.entities.Bills;
import com.demo.entities.Item;
import com.demo.entities.OrderDetails;
import com.demo.entities.Orders;
import com.demo.entities.Pets;
import com.demo.entities.Users;
import com.demo.models.AddressModel;
import com.demo.models.BillModel;
import com.demo.models.ItemModel;
import com.demo.models.OrderDetailModel;
import com.demo.models.OrderModel;
import com.demo.models.PetModel;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doGetIndex(request, response);
		}
	}

	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("p", "../user/checkout.jsp");
		request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("dathang".equalsIgnoreCase(action)) {
			doPost_Dathang(request, response);
		}
	}

	protected void doPost_Dathang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Item> cart = (List<Item>) session.getAttribute("cart");

		if (user == null || cart == null || cart.isEmpty()) {
			session.setAttribute("error-checkout", "Vui lòng đăng nhập và thêm sản phẩm vào giỏ hàng.");
			response.sendRedirect("cart");
			return;
		}

		// Nhận dữ liệu từ form và xử lý encoding
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String country = new String(request.getParameter("country_checkout").getBytes("ISO-8859-1"), "UTF-8");
		String district = new String(request.getParameter("district_checkout").getBytes("ISO-8859-1"), "UTF-8");
		String ward = new String(request.getParameter("ward_checkout").getBytes("ISO-8859-1"), "UTF-8");
		String addressStr = new String(request.getParameter("address_checkout").getBytes("ISO-8859-1"), "UTF-8");
		String note = new String(request.getParameter("note").getBytes("ISO-8859-1"), "UTF-8");
		String paymentMethod = request.getParameter("payment_method");

		ItemModel itemModel = new ItemModel();
		AddressModel addressModel = new AddressModel();
		OrderModel orderModel = new OrderModel();
		OrderDetailModel orderDetailModel = new OrderDetailModel();
		PetModel petModel = new PetModel();
		BillModel billModel = new BillModel();

		// Kiểm tra số lượng sản phẩm
		for (Item item : cart) {
			Pets pet = petModel.findPetById(item.getPet().getId());
			if (pet.getAmount() < item.getQuantity()) {
				session.setAttribute("error-checkout", "Sản phẩm " + pet.getPetName() + " đã hết hàng hoặc không đủ số lượng.");
				response.sendRedirect("cart");
				return;
			}
		}

		// Kiểm tra hoặc tạo địa chỉ mới
		Address orderAddress;
		Address existingAddress = addressModel.findAddressByIdUser(user.getId());
		if (existingAddress != null &&
				existingAddress.getCountry().equalsIgnoreCase(country) &&
				existingAddress.getDistrict().equalsIgnoreCase(district) &&
				existingAddress.getWard().equalsIgnoreCase(ward) &&
				existingAddress.getAddress().equalsIgnoreCase(addressStr)) {
			orderAddress = existingAddress;
		} else {
			orderAddress = new Address(country, district, ward, addressStr, user.getId());
			if (!addressModel.create(orderAddress)) {
				response.sendRedirect("checkout");
				return;
			}
			orderAddress = addressModel.findAddressByIdUser(user.getId());
		}

		// Tạo đơn hàng
		Orders newOrder = new Orders(phoneNumber, email, note, new Timestamp(new Date().getTime()), itemModel.total(cart), 0, user.getId(), orderAddress.getId(),  orderAddress.getId() );
		if (!orderModel.create(newOrder)) {
			response.sendRedirect("checkout");
			return;
		}

		session.removeAttribute("cart");
		int orderId = orderModel.getLastOrder().getId();

		// Tạo hóa đơn
		Bills bill = new Bills();
		bill.setOrderId(orderId);
		bill.setPaymentMethod("2".equals(paymentMethod) ? 2 : 1);
		bill.setCreateDate(new Timestamp(new Date().getTime()));
		bill.setStatus(false);

		if (!billModel.create(bill)) {
			response.sendRedirect("checkout");
			return;
		}

		// Tạo chi tiết đơn hàng và cập nhật số lượng thú cưng
		for (Item item : cart) {
			OrderDetails detail = new OrderDetails(orderId, item.getQuantity(), item.getPet().getId(), item.getPet().getMoney());
			if (orderDetailModel.create(detail)) {
				Pets pet = petModel.findPetById(item.getPet().getId());
				pet.setAmount(pet.getAmount() - item.getQuantity());
				petModel.update(pet);
			}
		}

		// Chuyển hướng thanh toán
		if ("1".equals(paymentMethod)) {
			response.sendRedirect("orderstatus");
		} else {
			int totalAmountInDong = (int) (itemModel.total(cart) * 1_000_000);
			session.setAttribute("totalhidden", totalAmountInDong);
			response.sendRedirect("payment");
		}
	}
}
