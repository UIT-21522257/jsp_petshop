<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
<!-- Add Bootstrap CSS if needed -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
</head>
<body class="js">
	<!-- Breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="bread-inner">
						<ul class="bread-list">
							<li><a href="${pageContext.request.contextPath }/home">Trang
									chủ<i class="ti-arrow-right"></i>
							</a></li>
							<li class="active"><a
								href="${pageContext.request.contextPath }/cart">Giỏ hàng</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumbs -->

	<!-- Shopping Cart -->
	<div class="shopping-cart section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<span style="color: red">${empty msg1 ? "" : msg1}</span>
				</div>
				<div class="col-12">
					<!-- Shopping Summery -->
					<table class="table shopping-summery">
						<thead>
							<tr class="main-hading">
								<th>Thú cưng</th>
								<th>Tên</th>
								<th class="text-center">Giá</th>
								<th class="text-center">Số lượng</th>
								<th class="text-center">Tổng</th>
								<th class="text-center"><i class="ti-trash remove-icon"></i>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="total" value="0"></c:set>
							<c:forEach var="item" items="${sessionScope.cart }" varStatus="i">
								<c:set var="total"
									value="${total + item.pet.money * item.quantity }"></c:set>
								<tr>
									<td class="image" data-title="No"><img
										src="${pageContext.request.contextPath}/assets/user/images/anhcho/${item.pet.image}"
										alt="#" /></td>
									<td class="product-des" data-title="Description">
										<p class="product-name">
											<a href="#">${item.pet.petName}</a>
										</p>
										<p class="product-des">${item.pet.description}</p>
									</td>
									<td class="price" data-title="Price"><span>${item.pet.money}
											triệu đồng</span></td>
									<td class="qty" data-title="Qty"
										style="text-align: center; font-size: 14px;">
										<!-- Input Order -->
										<div class="input-group">
											<button class="btn btn-sm btn-minus" data-index="${i.index}"
												data-action="minus">
												<i class="fa-solid fa-minus"></i>
											</button>
											<span class="quantity">${item.quantity}</span>
											<button class="btn btn-sm btn-plus" data-index="${i.index}"
												data-action="plus">
												<i class="fa-solid fa-plus"></i>
											</button>
										</div> <!--/ End Input Order -->
									</td>
									<td class="total-amount" data-title="Total"><span
										id="totalAmount-${i.index}">${item.pet.money * item.quantity}
											triệu đồng </span></td>

									<td class="action" data-title="Remove"><a
										href="${pageContext.request.contextPath }/cart?action=removeToCart&index=${i.index}">
											<i class="ti-trash remove-icon"></i>
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--/ End Shopping Summery -->
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<!-- Total Amount -->
					<div class="total-amount">
						<div class="row">
							<div class="col-lg-8 col-md-5 col-12">
								<div class="left">
								<!--  
									<div class="coupon">
										<form action="#" target="_blank">
											<input name="Coupon" placeholder="Nhập mã giảm giá" />
											<button class="btn">Áp dụng</button>
										</form>
									</div>
									<div class="checkbox">
										<label class="checkbox-inline" for="2"> <input
											name="news" id="2" type="checkbox" /> Giao hàng (+100 nghìn
											đồng)
										</label>
									</div>
									-->
								</div>
							</div>
							<div class="col-lg-4 col-md-7 col-12">
								<div class="right">
									<ul>
										<li>Tổng giỏ hàng<span id="totalAmount">${total }
												triệu đồng</span></li>
										<li class="last">Tổng<span id="totalAmount-discount">${total }
												triệu đồng</span></li>
									</ul>
									<div class="button5">
										<a href="${pageContext.request.contextPath }/checkout"
											class="btn btn-success">Thanh toán</a> <a
											href="${pageContext.request.contextPath }/home"
											class="btn btn-success">Tiếp tục mua hàng</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--/ End Total Amount -->
				</div>
			</div>
		</div>
	</div>
	<!--/ End Shopping Cart -->

	<!-- Add jQuery and AJAX script -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					// Xử lý sự kiện khi nhấn nút tăng số lượng
					$('.btn-plus').click(function() {
						var index = $(this).data('index');
						updateQuantity(index, 'plus');
					});

					// Xử lý sự kiện khi nhấn nút giảm số lượng
					$('.btn-minus').click(function() {
						var index = $(this).data('index');
						updateQuantity(index, 'minus');
					});

					// Hàm gửi yêu cầu AJAX để cập nhật số lượng
					function updateQuantity(index, action) {
					    $.ajax({
					        type: 'GET',
					        url: '${pageContext.request.contextPath}/cart',
					        data: {
					            action: action,
					            index: index
					        },
					        dataType: 'json',
					        success: function(data) {
					            // Cập nhật số lượng và tổng tiền trên giao diện
					            var cart = data;
					            for (var i = 0; i < cart.length; i++) {
					                var item = cart[i];
					                $('.qty').eq(i).find('.quantity').text(item.quantity);
					                $('#totalAmount-' + i).text((item.pet.money * item.quantity) + ' triệu đồng');
					            }
					            // Cập nhật tổng giỏ hàng
					            var total = 0;
					            for (var i = 0; i < cart.length; i++) {
					                total += cart[i].pet.money * cart[i].quantity;
					            }
					            $('#totalAmount').text(total + ' triệu đồng');
					            $('#totalAmount-discount').text(total + ' triệu đồng');
					        },
					        error: function(jqXHR, textStatus, errorThrown) {
					            console.log('Lỗi khi gửi yêu cầu AJAX: ' + textStatus, errorThrown);
					        }
					    });
					}

				});
	</script>

</body>
</html>
