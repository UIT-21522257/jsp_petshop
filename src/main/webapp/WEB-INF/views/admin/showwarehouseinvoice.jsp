<%@page import="com.demo.entities.WarehouseInvoice"%>
<%@page import="com.demo.models.*"%>
<%@page import="com.demo.entities.*"%>
<%@page import="java.util.*"%>
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css">
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
List<WarehouseInvoice> warehouseInvoices = (List<WarehouseInvoice>) request.getAttribute("warehouseInvoices");
CategoryModel categoryModel = new CategoryModel();
List<Categorys> categorys = categoryModel.findAll();
CatalogModel catalogModel = new CatalogModel();
%>
<!-- Start header section -->
<div class="content-wrapper">
	<div class="container-fluid">

		<div class="row mt-3">
			<div class="col-lg-12">
				<button class="add-catalog">
					<a
						href="${pageContext.request.contextPath}/admin/addwarehouseinvoice">Thêm
						thú cưng vào kho</a>
				</button>
			</div>
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Hoạt động nhập hàng</h5>
						<table id="example" class="display" style="width: 100%">
							<thead>
								<tr>
									<th>#</th>
									<th>Id thú cưng</th>
									<th>Số lượng</th>
									<th>Đơn giá</th>
									<th>Tổng</th>
									<th>Ngày nhập</th>
									<th>Trạng thái</th>
									<th>Hành động</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (WarehouseInvoice warehouseInvoice : warehouseInvoices) {
								%>
								<tr>
									<td><%=warehouseInvoice.getId()%></td>
									<td><%=warehouseInvoice.getPetId()%></td>
									<td><%=warehouseInvoice.getQuantity()%></td>
									<td><%=warehouseInvoice.getPrice()%></td>
									<td><%=warehouseInvoice.getQuantity() * warehouseInvoice.getPrice()%></td>
									<td><%=warehouseInvoice.getTradeDate()%></td>
									<c:if test="<%=warehouseInvoice.isStatus() == true %>">
										<td>Đã xác nhận</td>
										<td>Hoàn thành nhập hàng</td>
									</c:if>
						
									<c:if test="<%= warehouseInvoice.isStatus() == false %>">
										<td>
										<button class="btn btn-danger">
												<a
													href="${pageContext.request.contextPath}/admin/editwarehouseinvoice?action=confirm&id=<%= warehouseInvoice.getId() %>">Chưa xác nhận</a>
									    </button>
									     </td>
									    <td>
									    <button class="btn btn-warning">
												<a
													href="${pageContext.request.contextPath}/admin/editwarehouseinvoice?id=<%= warehouseInvoice.getId() %>">Sửa </a>
									    </button>
									     <button class="btn btn-danger">
												<a
													href="${pageContext.request.contextPath}/admin/quanlinhaphang?action=remove&id=<%= warehouseInvoice.getId() %>">Xoá</a>
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