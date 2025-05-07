<%@page import="java.util.ArrayList"%>
<%@page import="com.demo.entities.Item"%>
<%@page import="com.demo.entities.Users"%>
<%@page import="com.demo.models.ItemModel"%>
<%@page import="com.demo.models.AddressModel"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thanh toán</title>
<style>
/* Định dạng màu của hình tròn khi checked */
.checkbox-in input[type="checkbox"]:checked + span:before {
  background-color: blue; /* Thay đổi màu thành màu xanh khi checked */
}

/* Định dạng màu của hình tròn khi không được chọn */
.checkbox-in input[type="checkbox"] + span:before {
  background-color: #ccc; /* Màu mặc định của hình tròn khi không checked */
}

/* Ẩn checkbox */
.checkbox-in input[type="checkbox"] {
  display: none;
}

/* Hiển thị hình tròn */
.checkbox-in input[type="checkbox"] + span:before {
  content: "";
  display: inline-block;
  width: 16px;
  height: 16px;
  border: 1px solid #333;
  border-radius: 50%;
  margin-right: 5px;
}
.shop.checkout .single-widget .checkbox label::before{
display:none;
}

/* Định dạng màu của hình tròn khi hover */
.checkbox-in input[type="checkbox"] + span:hover:before {
  background-color: green; /* Thay đổi màu thành màu xanh khi hover */
}
</style>

</head>
<body class="js">
	<!-- Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">
							<li><a href="${pageContext.request.contextPath }/home">Trang chủ<i
									class="ti-arrow-right"></i></a></li>
							<li class="active"><a href="blog-single.html">Thanh toán</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->

	<!-- Start Checkout -->
	<section class="shop checkout section">
		<%
		AddressModel addressModel = new AddressModel();
		Users user = (Users) request.getSession().getAttribute("user");
		%>
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-12">
					<div class="checkout-form">
						<h2>Thanh toán của bạn</h2>
						<p>Vui lòng điền thông tin</p>
						<!-- Form -->
						<form class="form" method="post"
							action="${pageContext.request.contextPath }/checkout?action=dathang">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-12">
									<div class="form-group">
										<label>Họ và tên<span>*</span></label> <input type="text"
											name="fullName" placeholder="" required="required"
											value="${sessionScope.user.fullName }" />
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-12">
									<div class="form-group">
										<label>Số điện thoại<span>*</span></label> <input
											class="form-control"
											value="${sessionScope.user.phoneNumber }" name="phoneNumber"
											id="inputPhone" pattern="(\(\+[0-9]{2}\)|0)([0-9]{9,10})"
											type="tel" placeholder="Nhập số điện thoại của bạn" required>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-12">
									<div class="form-group">
										<label>Email<span>*</span></label> <input class="form-control"
											name="email"
											pattern="[a-z0-9._%+\-]+@[a-z0-9.\-]+\.[a-z]{2,}$"
											id="inputEmailAddress" type="email" placeholder="Nhập email"
											value="${sessionScope.user.email }">
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-12">
									<div class="form-group">
										<label for="country">Tỉnh/Thành Phố<span>*</span></label> <select
											class="select_option" name="country_checkout" id="country">
											<option id=""
												value="<%=addressModel.findAddressByIdUser(user.getId()).getCountry()%>"><%=addressModel.findAddressByIdUser(user.getId()).getCountry()%></option>
										</select>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-12">
									<div class="form-group">
										<label for="district">Quận/Huyện<span>*</span></label> <select
											class="select_option" name="district_checkout" id="district">
											<option id=""
												value="<%=addressModel.findAddressByIdUser(user.getId()).getDistrict()%>"><%=addressModel.findAddressByIdUser(user.getId()).getDistrict()%></option>
										</select>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-12">
									<div class="form-group">
										<label for="ward">Xã/Phường<span>*</span></label> <select
											class="select_option" name="ward_checkout" id="ward">
											<option id=""
												value="<%=addressModel.findAddressByIdUser(user.getId()).getWard()%>"><%=addressModel.findAddressByIdUser(user.getId()).getDistrict()%></option>
										</select>
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label>Địa chỉ cụ thể(Số nhà/Ấp/Thôn)<span>*</span>
										</label> <input type="text" name="address_checkout"
											placeholder="Nhập địa chỉ cụ thể"
											value="<%=addressModel.findAddressByIdUser(user.getId()).getAddress()%>"
											required="required" />
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label>Ghi chú khác(*)<span>*</span>
										</label> <textarea id="checkout-mess" cols="20" rows="5" name="note"
										placeholder="Điền các thông tin cần ghi chú vào đây"></textarea>
									</div>
								</div>
							</div>

							<!--/ End Form -->
					</div>
				</div>
				<%
				HttpSession session2 = request.getSession();
				List<Item> cart = (List<Item>) session2.getAttribute("cart");
				ItemModel itemModel = new ItemModel();
				%>
				<div class="col-lg-4 col-12">
					<div class="order-details">
						<!-- Order Widget -->
						<div class="single-widget">
							<h2>Tổng giỏ hàng</h2>
							<div class="content">
								<ul>
									 <li>Tổng hàng<span id="totalAmount"><%= itemModel.total(cart) %></span> (triệu đồng)</li>
									 <input type="hidden" name="totalhidden" value="<%= itemModel.total(cart) %>" />
    <!--  <li>(+) Giao hàng<span id="shippingFee">0.1</span> (triệu đồng)</li>  --> 
      <li class="last">Tổng<span class="finalAmount"><%= itemModel.total(cart) %></span> (triệu đồng)</li>
								</ul>
							</div>
						</div>
						<!--/ End Order Widget -->
						<!-- Order Widget -->
						<div class="single-widget">
        <h2>Phương thức thanh toán</h2>
        <div class="content" style="margin:10px 25px;">
            <div class="radio">       
                <input type="radio" id="paymentMethod2" name="payment_method" value="1">
                <label for="paymentMethod2">Thanh toán khi nhận hàng</label><br>
                
                <input type="radio" id="paymentMethod3" name="payment_method" value="2">
                <label for="paymentMethod3">Thanh toán VNPay(Sau khi đặt hàng)</label><br>
            </div>
        </div>
    </div>

    <!-- Nút Đặt hàng -->
    <div class="single-widget get-button">
        <div class="content">
            <div class="button">
                <button id="btnDatHang" class="btn" type="submit">Đặt hàng</button>
            </div>
        </div>
    </div>
				</form>
			</div>
		</div>
	</section>
	<!--/ End Checkout -->

	<!-- Start Shop Services Area  -->
	<section class="shop-services section home">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-rocket"></i>
						<h4>Free shiping</h4>
						<p>Orders over $100</p>
					</div>
					<!-- End Single Service -->
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-reload"></i>
						<h4>Free Return</h4>
						<p>Within 30 days returns</p>
					</div>
					<!-- End Single Service -->
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-lock"></i>
						<h4>Sucure Payment</h4>
						<p>100% secure payment</p>
					</div>
					<!-- End Single Service -->
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<!-- Start Single Service -->
					<div class="single-service">
						<i class="ti-tag"></i>
						<h4>Best Peice</h4>
						<p>Guaranteed price</p>
					</div>
					<!-- End Single Service -->
				</div>
			</div>
		</div>
	</section>
	<!-- End Shop Services -->

	<!-- Start Shop Newsletter  -->
	<section class="shop-newsletter section">
		<div class="container">
			<div class="inner-top">
				<div class="row">
					<div class="col-lg-8 offset-lg-2 col-12">
						<!-- Start Newsletter Inner -->
						<div class="inner">
							<h4>Newsletter</h4>
							<p>
								Subscribe to our newsletter and get <span>10%</span> off your
								first purchase
							</p>
							<form action="mail/mail.php" method="get" target="_blank"
								class="newsletter-inner">
								<input name="EMAIL" placeholder="Your email address" required=""
									type="email" />
								<button class="btn">Subscribe</button>
							</form>
						</div>
						<!-- End Newsletter Inner -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Shop Newsletter -->
	<script>
	document.addEventListener('DOMContentLoaded', function() {
		  var checkboxes = document.querySelectorAll('.single-widget input[type="checkbox"]');
		  
		  checkboxes.forEach(function(checkbox) {
		    checkbox.addEventListener('change', function() {
		      
		      var methodId = 'method' + this.id;
		      var methodDiv = document.getElementById(methodId);
		      
		      if (this.checked) {
		        methodDiv.style.display = 'block';
		        checkboxes.forEach(function(cb) {
		          var otherMethodId = 'method' + cb.id;
		          var otherMethodDiv = document.getElementById(otherMethodId);
		          
		          if (cb !== checkbox && otherMethodDiv) {
		            otherMethodDiv.style.display = 'none';
		            cb.checked = false; 
		          }
		        });
		      } else {
		        methodDiv.style.display = 'none';
		      }
		    });
		    if (checkbox.checked) {
		      var methodId = 'method' + checkbox.id;
		      var methodDiv = document.getElementById(methodId);
		      methodDiv.style.display = 'block';
		    }
		  });
		});
	</script>
	<script>
document.addEventListener('DOMContentLoaded', function() {
    var form = document.querySelector('form'); // Chọn form của bạn
    var radios = document.querySelectorAll('input[name="payment_method"]');
    var submitButton = document.getElementById('btnDatHang');

    submitButton.addEventListener('click', function(event) {
        var isChecked = Array.from(radios).some(radio => radio.checked);
        if (!isChecked) {
            alert('Vui lòng chọn phương thức thanh toán!');
            event.preventDefault(); // Ngăn chặn gửi form
        }
    });
});
</script>
</body>
</html>