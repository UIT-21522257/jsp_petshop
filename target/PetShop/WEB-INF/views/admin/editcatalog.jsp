<%@ page import="java.util.List"%>
<%@ page import="com.demo.entities.*"%>
<%@ page import="com.demo.models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
CategoryModel categoryModel = new CategoryModel();
List<Categorys> categorys = categoryModel.findAll();
Catalogs catalog = (Catalogs) request.getAttribute("catalog");
%>
<!-- Start header section -->
<div class="content-wrapper">
	<div class="container-fluid">

		<div class="row mt-3">
			<div class="col-lg-8">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Sửa chuyên mục</div>
						<hr>

						<form action="${pageContext.request.contextPath}/admin/editcatalog?action=editcatalog&id=<%= catalog.getId() %>"
							method="post">
							<div class="form-group">
								<label for="input-1">ID</label> <input type="text"
									class="form-control" readonly id="input-1" placeholder="ID"
									name="id" value="<%=catalog.getId()%>">
							</div>
							<div class="form-group">
								<label for="input-1">Tên chuyên mục</label> <input type="text"
									class="form-control" id="input-1" placeholder="Tên chuyên mục"
									name="name" value="<%=catalog.getName()%>">
							</div>
							<div class="form-group">
								<label for="input-2">Phân loại</label>
								<div>
									<select class="form-control valid" id="input-6"
										name="categoryid" required aria-invalid="false">
										<%
										for (Categorys categoryPet : categorys) {
										%>
										<option value="<%=categoryPet.getId()%>"><%=categoryPet.getName()%></option>
										<%
										}
										%>
									</select>
								</div>
							</div>
							<div class="form-footer">
								<button class="btn btn-danger">
									<a href="${pageContext.request.contextPath}/admin/cate/list">Hủy</a>
								</button>

								<button type="submit" class="btn btn-success">Cập nhật</button>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
		<div class="overlay toggle-menu"></div>
	</div>
</div>