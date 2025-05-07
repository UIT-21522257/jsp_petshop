<%@page import="com.demo.models.OrderModel"%>
<%@page import="com.demo.models.BillModel"%>
<%@page import="com.demo.entities.Bills"%>
<%@page import="java.util.*"%>
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css">
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
List<Bills> bills = (List<Bills>) request.getAttribute("bills");
OrderModel orderModel = new OrderModel();
%>
<!-- Start header section -->
<div class="content-wrapper">
	<div class="container-fluid">

		<div class="row mt-3">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Hoá đơn người dùng</h5>
						<table id="example" class="display" style="width: 100%">
							<thead>
								<tr>
									<th>#</th>
									<th>Id người dùng</th>
									<th>Id đơn hàng</th>
									<th>Chi tiết đơn hàng</th>
									<th>Tổng tiền</th>
									<th>Phương thức thanh toán</th>
									<th>Thời gian</th>
									<th>Trạng thái</th>
								</tr>
							</thead>
							<tbody>
							<%
							for (Bills bill : bills) {
							%>
								<tr>
									<td><%= bill.getId()%></td>
									<td><%= orderModel.findOrderById(bill.getOrderId()).getUserId()%></td>
									<td><%= bill.getOrderId()%></td>
									<td>
                         <button class="btn btn-danger">
												<a href="${pageContext.request.contextPath}/admin/donhang?id=<%= bill.getOrderId() %>">Chi tiết</a>
						</button>
                        </td> 
									<td><%= orderModel.findOrderById(bill.getOrderId()).getTotalMoney() %> triệu đồng</td>
									<td><%= bill.getPaymentMethod() == 0?"Thanh toán bằng ngân hàng":bill.getPaymentMethod() == 1?"Thanh toán khi nhận hàng":"Thanh toán bằng paypal"%></td>
									<td><%= bill.getCreateDate()%></td>
									<c:if test="<%= bill.isStatus() == true %>">
										<td>Đã thanh toán</td>
									</c:if>
						
						<c:if test="<%= bill.isStatus() == false %>">
										 <td>
                         <button class="btn btn-danger">
												<a href="${pageContext.request.contextPath}/admin/quanlibill?action=confirm&id=<%= bill.getId() %>">Chưa thanh toán</a>
						</button>
                        </td>  
						</c:if> 	

								</tr>
								<%
								}
								%>
							<tbody>
							
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="overlay toggle-menu"></div>
	</div>
</div>
  <script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script>
new DataTable('#example');
</script>