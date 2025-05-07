<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.demo.entities.Item"%>
<%@page import="com.demo.models.ItemModel"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<!-- Meta Tag -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name='copyright' content=''>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Title Tag  -->
<title>Petshop</title>
<!-- Favicon -->
<link rel="icon" type="image/png" href="images/favicon.png">
<!-- Web Font -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&display=swap"
	rel="stylesheet">

<!-- StyleSheet -->

<!-- Font Awasome -->
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/bootstrap.css">
<!-- Magnific Popup -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/magnific-popup.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/font-awesome.css">
<!-- Fancybox -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/jquery.fancybox.min.css">
<!-- Themify Icons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/themify-icons.css">
<!-- Nice Select CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/niceselect.css">
<!-- Animate CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/animate.css">
<!-- Flex Slider CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/flex-slider.min.css">
<!-- Owl Carousel -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/owl-carousel.css">
<!-- Slicknav -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/slicknav.min.css">

<!-- Eshop StyleSheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/responsive.css">

  <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
<script>
$(document).ready(function () {
	$('#datepicker').datepicker({
        dateFormat: 'yy-mm-dd'
    });
	$("#timkiem").autocomplete({
	    minLength: 0,
	    source: '${pageContext.request.contextPath}/autocomplete',
	    select: function( event, ui ) {
			
	      return false;
	    }
	  }).autocomplete( "instance" )._renderItem = function( ul, item ) {
		    return $( "<li style='background-color: white; list-style-type: none; width:350px;height:75px; position: relative; z-index: 2;'>" )
		      .append( "<a style='text-decoration: none; border:none; color: black; display: block; width: 100%; height: 100%;' href='${pageContext.request.contextPath}/petdetail?id=" + item.id + "'><div><img src='${pageContext.request.contextPath}/assets/user/images/anhcho/" + item.image + "' height='60' width='60'> &nbsp;" + item.petName + "</div></a>" )
		      .appendTo( ul );
	    };
});
	
</script>

</head>
<body class="js">
	<!-- Start chung -->
	<!-- Preloader -->
	<div class="preloader">
		<div class="preloader-inner">
			<div class="preloader-icon">
				<span></span> <span></span>
			</div>
		</div>
	</div>
	<!-- End Preloader -->

	<!-- Header -->
	<header class="header shop">
		<!-- Topbar -->
		<div class="topbar">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-12 col-12">
						<!-- Top Left -->
						<div class="top-left">
							<ul class="list-main">
								<li><i class="ti-headphone-alt"></i>0835169543</li>
								<li><i class="ti-email"></i>petshop@gmail.com</li>
							</ul>
						</div>
						<!--/ End Top Left -->
					</div>
					<div class="col-lg-8 col-md-12 col-12">
						<!-- Top Right -->
						<div class="right-content">
							<div class="list-main-right">
								<span><i class="ti-location-pin"></i>Linh
									Xuân, Tp.Thủ Đức</span> 
								<div class="list-main-right-login">
									<c:if test="${sessionScope.user ==null }">
										<span class="header-icon-label"> <i
											class="ti-power-off"></i><a
											href="${pageContext.request.contextPath}/login">Đăng
												nhập/Đăng ký</a>
										</span>
									</c:if>
									<c:if test="${sessionScope.user !=null }">
										<a href="${pageContext.request.contextPath}/thongtintaikhoan">
											<span class="header-icon-label"> <i
												class="ti-power-off"></i> ${sessionScope.user.fullName }
										</span>
											<div class="dropdown-content">
												<a
													href="${pageContext.request.contextPath}/personalinformation">Tài
													khoản của tôi</a> <a
													href="${pageContext.request.contextPath}/changepassword?action=changepassword">Đổi
													mật khẩu</a> <a
													href="${pageContext.request.contextPath}/orderstatus">Thông
													tin đơn hàng</a> <a
													href="${pageContext.request.contextPath}/login?action=logout">Đăng
													xuất</a>
											</div>
										</a>
									</c:if>
								</div>
							</div>
						</div>
						<!-- End Top Right -->
					</div>
				</div>
			</div>
		</div>
		<!-- End Topbar -->
		<div class="middle-inner">
			<div class="container">
				<div class="row">
					<div class="col-lg-2 col-md-2 col-12">
						<!-- Logo -->
						<div class="logo">
							<a href="index.html"><img
								src="${pageContext.request.contextPath}/assets/user/images/logopetshop.jpg" alt="logo" style="width:80px; height:80px;" /></a>
						</div>
						<!--/ End Logo -->
					</div>
					<div class="col-lg-8 col-md-7 col-12">
					<form action="${pageContext.request.contextPath }/showlistsearch" method="GET">
						<div class="search-bar-top">
							<div class="search-bar">
								<span style="margin-right:30px;">Tìm kiếm</span>
									<input name="search" id="timkiem" placeholder="Nhập tên thú cưng....."
										type="search" />
									<button class="btnn">
										<i class="ti-search"></i>
									</button>
								
							</div>
						</div>
						</form>
					</div>
					<div class="col-lg-2 col-md-3 col-12">
						<div class="right-bar">
							<!-- Search Form -->
							<div class="sinlge-bar">
								<a href="${pageContext.request.contextPath }/wishlistpet" class="single-icon"><i
									class="fa fa-heart-o" aria-hidden="true"></i></a>
							</div>
							<div class="sinlge-bar">
								<a href="${pageContext.request.contextPath}/personalinformation" class="single-icon"><i
									class="fa fa-user-circle-o" aria-hidden="true"></i></a>
							</div>
							<%
							HttpSession session2 = request.getSession();
							List<Item> cart = new ArrayList<>();
							if (session2.getAttribute("cart") != null) {
								cart = (List<Item>) session2.getAttribute("cart");
							}
							int i = -1;
							%>
							<div class="sinlge-bar shopping">
								<a href="${pageContext.request.contextPath}/cart"
									class="single-icon"><i class="ti-bag"></i>
									<span class="total-count"><%=cart == null ? 0 : cart.size()%></span></a>

								<!-- Shopping Item -->
								<div class="shopping-item">
									<div class="dropdown-cart-header">
										<span><%=cart == null ? 0 : cart.size()%></span> <a
											href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
									</div>
									<ul class="shopping-list">
										<%
										for (Item item : cart) {
										%>
										<li><a
											href="${pageContext.request.contextPath }/cart?action=removeToCart&index=<%= ++i %>"
											class="remove" title="Remove this item"><i
												class="fa fa-remove"></i></a> <a class="cart-img" href="#"><img
												src="${pageContext.request.contextPath}/assets/user/images/anhcho/<%= item.getPet().getImage() %>"
												alt="#" /></a>
											<h4>
												<a href="#"><%=item.getPet().getPetName()%></a>
											</h4>
											<p class="quantity">
												<%=item.getQuantity()%>
												- <span class="amount"><%=item.getPet().getMoney() * item.getQuantity()%>
													triệu đồng</span>
											</p></li>
										<%
										}
										%>
									</ul>
									<div class="bottom">
										<div class="total">
											<span>Tổng</span> <span class="total-amount"><%=ItemModel.total(cart)%>
												triệu đồng</span>
										</div>
										<a href="${pageContext.request.contextPath}/checkout"
											class="btn animate">Thanh toán</a>
									</div>
								</div>
								<!--/ End Shopping Item -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Header Inner -->
		<div class="header-inner">
			<div class="container">
				<div class="cat-nav-head">
					<div class="row">
						<div class="col-lg-3">
							<div class="all-category">
								<h3 class="cat-heading">
									<i class="fa fa-bars" style="margin-right:3px;"></i>DANH MỤC
								</h3>
							</div>
						</div>
						<div class="col-lg-9 col-12">
							<div class="menu-area">
								<!-- Main Menu -->
								<nav class="navbar navbar-expand-lg">
									<div class="navbar-collapse">
										<div class="nav-inner">
											<ul class="nav main-menu menu navbar-nav">
												<li class="active"><a
													href="${pageContext.request.contextPath}/home">Trang
														chủ</a></li>
												<li><a
													href="${pageContext.request.contextPath}/shopgrid">Thú
														cưng</a></li>
												<li><a href="#">Shop<i
														class="ti-angle-down"></i><span class="new">Mới</span></a>
													<ul class="dropdown">
														<li><a
															href="${pageContext.request.contextPath}/shopgrid">Lọc</a></li>
														<li><a href="${pageContext.request.contextPath}/cart">Giỏ
																hàng</a></li>
														<li><a
															href="${pageContext.request.contextPath}/checkout">Thanh
																toán</a></li>
													</ul></li>
												<li><a
													href="${pageContext.request.contextPath}/contact">Liên
														hệ</a></li>
											</ul>
										</div>
									</div>
								</nav>
								<!--/ End Main Menu -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/ End Header Inner -->
	</header>
	<!--/ End Header -->
	<!-- End chung -->

	<!-- End chung -->
	<jsp:include page="${p}"></jsp:include>
	<!-- Start Footer Area -->
	<footer class="footer">
		<!-- Footer Top -->
		<div class="footer-top section">
			<div class="container">
				<div class="row">
					<div class="col-lg-5 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer about">
							<div class="logo">
								<a href="index.html"><img
									src="${pageContext.request.contextPath}/assets/user/images/logopetshop.jpg" alt="#" /></a>
							</div>
							<p class="text">Đó là protein, không phải cuộc đua, là cực
								hình, không cần thiết Anh ấy là một cầu thủ bóng đá tuyệt vời.
								Đó là một ngày cuối tuần. Vì Chúa của tôi ai là người phát
								triển, là người vận chuyển, dễ đau buồn, sợ hãi.</p>
							<p class="call">
								Thắc mắc? Gọi cho chúng tôi 24/7<span><a
									href="tel:123456789">0835169543</a></span>
							</p>
						</div>
						<!-- End Single Widget -->
					</div>
					<div class="col-lg-2 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer links">
							<h4>Thông tin</h4>
							<ul>
								<li><a href="#">Về chúng tôi</a></li>
								<li><a href="#">Câu hỏi</a></li>
								<li><a href="#">Điều khoản & điều kiện</a></li>
								<li><a href="#">Liên hệ</a></li>
								<li><a href="#">Giúp đỡ</a></li>
							</ul>
						</div>
						<!-- End Single Widget -->
					</div>
					<div class="col-lg-2 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer links">
							<h4>Dịch vụ khách hàng</h4>
							<ul>
								<li><a href="#">Phương thức thanh toán</a></li>
								<li><a href="#">Hoàn tiền</a></li>
								<li><a href="#">Trả hàng</a></li>
								<li><a href="#">Giao hàng</a></li>
								<li><a href="#">Chính sách bảo mật</a></li>
							</ul>
						</div>
						<!-- End Single Widget -->
					</div>
					<div class="col-lg-3 col-md-6 col-12">
						<!-- Single Widget -->
						<div class="single-footer social">
							<h4>Liên lạc</h4>
							<!-- Single Widget -->
							<div class="contact">
								<ul>
									<li>Chung cư HT Pearl</li>
									<li>Linh Xuân, Tp.Thủ Đức</li>
									<li>petshop@gmail.com</li>
									<li>0835169543</li>
								</ul>
							</div>
							<!-- End Single Widget -->
							<ul>
								<li><a href="https://www.facebook.com/truong.lechi.902/"><i
										class="ti-facebook"></i></a></li>
								<li><a href="https://www.tiktok.com/@lechitruong23211"><i
										class="ti-twitter"></i></a></li>
								<li><a href="#"><i class="ti-flickr"></i></a></li>
								<li><a href="https://www.instagram.com/emg_dniw/"><i
										class="ti-instagram"></i></a></li>
							</ul>
						</div>
						<!-- End Single Widget -->
					</div>
				</div>
			</div>
		</div>
		<!-- End Footer Top -->
		<div class="copyright">
			<div class="container">
				<div class="inner">
					<div class="row">
						<div class="col-lg-6 col-12">
							<div class="left">
								<p>
									Copyright © 2020 <a href="http://www.wpthemesgrid.com"
										target="_blank">PetShop</a> - All
									Rights Reserved.
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- /End Footer Area -->

	<!-- Jquery -->
	<%-- <script
		src="${pageContext.request.contextPath}/assets/user/js/jquery.min.js"></script> --%>
	<script
		src="${pageContext.request.contextPath}/assets/user/js/jquery-migrate-3.0.0.js"></script>
<%-- 	<script
		src="${pageContext.request.contextPath}/assets/user/js/jquery-ui.min.js"></script> --%>
	<!-- Popper JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/popper.min.js"></script>
	<!-- Bootstrap JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/bootstrap.min.js"></script>
	<!-- Color JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/colors.js"></script>
	<!-- Slicknav JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/slicknav.min.js"></script>
	<!-- Owl Carousel JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/owl-carousel.js"></script>
	<!-- Magnific Popup JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/magnific-popup.js"></script>
	<!-- Waypoints JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/waypoints.min.js"></script>
	<!-- Countdown JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/finalcountdown.min.js"></script>
	<!-- Nice Select JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/nicesellect.js"></script>
	<!-- Flex Slider JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/flex-slider.js"></script>
	<!-- ScrollUp JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/scrollup.js"></script>
	<!-- Onepage Nav JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/onepage-nav.min.js"></script>
	<!-- Easing JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/easing.js"></script>
	<!-- Active JS -->
	<script
		src="${pageContext.request.contextPath}/assets/user/js/active.js"></script>
	<!-- Jquery -->
<!-- 	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
		integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
	<script>
                                    var country = document.getElementById("country");
                                    var districts = document.getElementById("district");
                                    var wards = document.getElementById("ward");
                                    var Parameter = {
                                        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                                        method: "GET",
                                        responseType: "application/json",
                                    };
                                    var promise = axios(Parameter);
                                    promise.then(function (result) {
                                        renderCity(result.data);
                                    });

                                    function renderCity(data) {
                                        for (const x of data) {
                                            country.options[country.options.length] = new Option(x.Name, x.Name);
                                        }
                                        country.onchange = function () {
                                            district.length = 1;
                                            ward.length = 1;
                                            if (this.value != "") {
                                                const result = data.filter(n => n.Name === this.value);

                                                for (const k of result[0].Districts) {
                                                    district.options[district.options.length] = new Option(k.Name, k.Name);
                                                }
                                            }
                                        };
                                        district.onchange = function () {
                                            ward.length = 1;
                                            const dataCity = data.filter((n) => n.Name === country.value);
                                            if (this.value != "") {
                                                const dataWards = dataCity[0].Districts.filter(n => n.Name === this.value)[0].Wards;

                                                for (const w of dataWards) {
                                                    wards.options[wards.options.length] = new Option(w.Name, w.Name);
                                                }
                                            }
                                        };
                                    }
                                </script>
</body>
</html>