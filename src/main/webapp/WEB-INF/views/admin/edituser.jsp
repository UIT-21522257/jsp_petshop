<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.demo.models.UserModel"%>
<%@page import="com.demo.entities.Users"%>
<!-- Start header section -->
<%
Users user = (Users) request.getAttribute("user");
%>
<div class="content-wrapper">
	<div class="container-fluid">

		<div class="row mt-3">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Sửa User</div>
						<hr>
						<form
							action="${pageContext.request.contextPath}/admin/edituser?action=edituser&id=<%= user.getId() %>"
							method="post">
							<div class="form-group">
								<label for="input-1">Họ tên</label> <input type="text"
									class="form-control" id="input-1" placeholder="Họ tên"
									value="<%=user.getFullName()%>" name="fullname">
							</div>
							<div class="form-group">
								<label for="input-3">Số điện thoại</label> <input type="text"
									class="form-control" id="input-2" placeholder="Số điện thoại"
									value="<%=user.getPhoneNumber()%>" name="phonenumber">
							</div>
							<div class="form-group">
								<label for="input-3">Email</label> <input type="text"
									class="form-control" id="input-3" placeholder="Email"
									value="<%=user.getEmail()%>" name="email">
							</div>
							<div class="form-group">
								<label for="input-3">Tài khoản</label> <input type="text"
									class="form-control" id="input-4" placeholder="Tài khoản"
									value="<%=user.getUserName()%>" name="username">
							</div>
							
							<div class="form-group">
								<label for="input-3">Mật khẩu(Đã được mã hoá)</label> <input type="text"
									class="form-control" id="input-5" placeholder="Mật khẩu"
									value="<%=user.getPassword() %>" name="password">
							</div>
							<div class="form-group">
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