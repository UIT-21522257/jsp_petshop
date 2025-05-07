<%@page import="com.demo.models.OrderDetailModel"%>
<%@page import="com.demo.entities.OrderDetails"%>
<%@page import="com.demo.models.AddressModel"%>
<%@page import="com.demo.models.OrderModel"%>
<%@page import="com.demo.models.BillModel"%>
<%@page import="com.demo.entities.Users"%>
<%@page import="com.demo.entities.Orders"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tình trạng đơn hàng</title>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.7/css/dataTables.dataTables.css">
<style>
/* Lớp phủ toàn màn hình */
.overlay {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Màu nền nửa trong suốt */
    z-index: 998; /* Đảm bảo lớp phủ ở trên các phần tử khác nhưng dưới hộp thoại */
}

#btnClose {
    float: right;
    margin: 5px;
    background: transparent;
    border: none;
    font-size: 25px;
    cursor: pointer;
}
</style>
</head>
<body>
<section class="container_status">
<h3 style="text-align:center; position: relative;">Đơn hàng của bạn</h3>
<% 
OrderDetailModel orderDetailModel = new OrderDetailModel();
OrderModel orderModel = new OrderModel();
AddressModel addressModel = new AddressModel();
Users user = (Users) request.getSession().getAttribute(("user"));
List<Orders> orders =(List<Orders>) orderModel.findAllByUserId(user.getId());
BillModel billModel = new BillModel();
int z =0;
int w =0;
%>
<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th>Ghi chú</th>
                <th>Tổng tiền</th>
                <th>Địa chỉ</th>
                <th>Thời gian đặt</th>
                <th>Trạng thái đơn hàng</th>
                <th>Phương thức thanh toán</th>
                <th>Trạng thái thanh toán</th>
                <th>Hoá đơn</th>
            </tr>
        </thead>
        <tbody>
            <% for(int i=0; i< orders.size(); i++){ 
            	++z;
            %>
            <tr data-id="<%= orders.get(i).getId() %>" style="cursor: pointer;" class="clickTR">
                <td><%= z %></td>
                <td>${sessionScope.user.fullName }</td>
                <td><%= orders.get(i).getPhoneNumber() %></td>
                <td><%= orders.get(i).getEmail() %></td>
                <td><%= orders.get(i).getNote() %></td>
                <td><%= orders.get(i).getTotalMoney() %> triệu đồng</td>
                <td><%= addressModel.findAddressById(orders.get(i).getAddressId()).getAddress()+", "+ addressModel.findAddressById(orders.get(i).getAddressId()).getWard()+", "+addressModel.findAddressById(orders.get(i).getAddressId()).getDistrict()+", "+addressModel.findAddressById(orders.get(i).getAddressId()).getCountry() %></td>
                <td><%= orders.get(i).getOrderDate() %></td>
                <td><%= orders.get(i).getStatus() == 1? "Đang xác nhận": "Chưa xác nhận"%></td>
                <c:if test="<%= billModel.findBillByOrderId(orders.get(i).getId()).getPaymentMethod() == 2 && orders.get(i).getStatus() == 1  %>">
                <td>Thanh toán bằng VNPay</td>
                <td>Đã thanh toán</td>
                <td>
                <button class="btn btn-danger">
						<a href="${pageContext.request.contextPath}/showbill?id=<%= orders.get(i).getId() %>">In hoá đơn</a>
				</button>
                </td>
                </c:if>
                <c:if test="<%= billModel.findBillByOrderId(orders.get(i).getId()).getPaymentMethod() == 2 && orders.get(i).getStatus() == 0  %>">
                <td>Thanh toán bằng VNPay</td>
                <td>Đã thanh toán</td>
                <td>
                </td>
                </c:if>
                <c:if test="<%= billModel.findBillByOrderId(orders.get(i).getId()).getPaymentMethod() == 1 && billModel.findBillByOrderId(orders.get(i).getId()).isStatus() == true%>">
                 <td>Thanh toán khi nhận hàng</td>
                 <td>Đã thanh toán</td>
                <td>
                <button class="btn btn-danger">
						<a href="${pageContext.request.contextPath}/showbill?id=<%= orders.get(i).getId() %>">In hoá đơn</a>
				</button>
                </td>
                </c:if>
                <c:if test="<%= billModel.findBillByOrderId(orders.get(i).getId()).getPaymentMethod() == 1 && billModel.findBillByOrderId(orders.get(i).getId()).isStatus() == false%>">
                 <td>Thanh toán khi nhận hàng</td>
                 <td>Chưa thanh toán</td>
                <td>
                Thanh toán khi nhận hàng xong để In hoá đơn
                </td>
                </c:if>
            </tr>       
                <% } %>
                </tbody>
    </table>
    <!-- Lớp phủ -->
    <div id="dialog-overlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 998;"></div>
     <div id="dialog-message" title="Download complete" style="display: none; margin:70px 180px; z-index:999;">
     <button id="btnClose" style="float: right;margin:5px; background: transparent; border: none; font-size: 25px; cursor: pointer;">
        <i class="fas fa-times"></i>
    </button>
     <div class="order-form" style="height: 360px; width: 720px;">
				<h3>Chi tiết đơn hàng</h3>	
			<table id="table" class="display" style="width:100%">
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên thú cưng</th>
                <th>Kích thước</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Tổng tiền</th>
            </tr>
        </thead>
        <tbody>

            <tr>
                <td>STT</td>
                <td>Tên thú cưng</td>
                <td>Kích thước</td>
                <td>Số lượng </td>
                <td>Đơn giá (triệu đồng)</td>
                <td>Tổng tiền (triệu đồng)</td>
            </tr>    

          </tbody> 
            </table>
			</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
<script>
$(document).ready(function () {
    $('#example tbody .clickTR').click(function(){
        $('#dialog-overlay').css('display', 'block');
        $('#dialog-message').css('display', 'block');
        var orderId = $(this).attr('data-id');
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/dialogmessage',
            data: { 
                orderId: orderId
            },
            success: function(data){
                var s = '';
                var orderdetails = data.orderdetails;
                var pets = data.pets;
                for (var i = 0; i < orderdetails.length; i++) {
                    s += '<tr>';
                    s += '<td>' + (i + 1) + '</td>'; 
                    s += '<td>' + pets[i].petName + '</td>';
                    s += '<td>' + pets[i].petType + '</td>';
                    s += '<td>' + orderdetails[i].quantity + '</td>';
                    s += '<td>' + orderdetails[i].money + ' (triệu đồng)</td>';
                    s += '<td>' + orderdetails[i].money * orderdetails[i].quantity + ' (triệu đồng)</td>';
                    s += '</tr>';
                }
                $('#table tbody').html(s);

                // Khởi tạo DataTable
                $('#table').DataTable();
            }
        });
    });


 // Xử lý sự kiện bấm nút Đóng
    $('#btnClose').click(function() {
        $('#dialog-message').css('display', 'none');
        $('#dialog-overlay').css('display', 'none');
    });
});
</script>

</section>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}
#dialog-message{
position:absolute;
top: 224px;
left:324px;
}

.order-form {
	width: 80%;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h3 {
	text-align: center;
}

.product-row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.quantity {
	width: 40px;
	text-align: center;
}

.total {
	font-weight: bold;
}

.discount-code {
	margin-top: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.payment-options {
	margin-top: 20px;
}

.checkout-button {
	background-color: red;
	padding: 10px;
	cursor: pointer;
	color: white;
	border: none;
	border-radius: 5px;
}

@media ( max-width : 768px) {
	.order-form {
		width: 80%;
	}
}

.payment-options {
	margin-top: 20px;
	text-align: center;
}

.payment-options button {
	background-color: #4CAF50;
	color: white;
	padding: 10px 10px;
	margin: 10px 10px;
	border: none;
	border-radius: 20px;
	cursor: pointer;
	font-size: 16px;
}

.payment-options button:hover {
	background-color: #45a049;
}

.discount-code {
	margin-top: 20px;
	text-align: center;
}

.checkout-button {
	background-color: #e74c3c;
	color: white;
	padding: 15px 30px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
	transition: background-color 0.3s ease;
}

.checkout-button:hover {
	background-color: #c0392b;
}

.container {
	max-width: 1080px;
	margin: 0 auto;
	text-align: center;
}

form {
	margin-bottom: 100px;
}

select {
	padding: 10px;
	margin: 3px;
	width: 100%;
	box-sizing: border-box;
}

#result {
	font-size: 18px;
	color: #333;
}

/* General Styles */
.checkout-form-list {
	margin-bottom: 20px;
}

.checkout-form-list label {
	display: block;
	font-weight: bold;
	margin-bottom: 8px;
}

.checkout-form-list input, .checkout-form-list select,
	.checkout-form-list textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

/* Different Address Section */
.different-address {
	margin-top: 30px;
}

#ship-box-info {
	display: none;
	/* Hide the different address section initially */
}

/* Buttons and Additional Styles */
.checkout-button {
	background-color: #e74c3c;
	color: white;
	padding: 15px 30px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
	transition: background-color 0.3s ease;
}

.checkout-button:hover {
	background-color: #c0392b;
}

/* Responsive Styles */
@media screen and (max-width: 768px) {
	.row {
		flex-direction: column;
	}
	.checkout-form-list {
		width: 100%;
	}
}
.checkout-area {
            padding-top: 60px;
            padding-bottom: 30px;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .col-lg-6 {
            width: 48%; 
            margin-bottom: 20px; 
        }
</style>
    <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
<script>
new DataTable('#example');
new DataTable('#example2');
</script>
</body>
</html>