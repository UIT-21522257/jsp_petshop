<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/login__register.css">
<title>Đăng ký</title>
</head>
<body>
	<div class="container">
		<div class="registration form">
			<header>Đăng ký</header>
			<form
				action="${pageContext.request.contextPath}/login?action=register"
				method="post">
				<input type="text" name="username"
					placeholder="Vui lòng nhập Tên đăng nhập"> 
				<input
					type="text" name="email" placeholder="Vui lòng nhập Email">
				<input type="password" name="password"
					placeholder="Vui lòng nhập Mật khẩu"> 
					<input type="password" name="password"
					placeholder="Vui lòng nhập lại Mật khẩu"> 
					<input type="submit" class="button" value="Đăng ký">
			</form>
			<div class="signup">
				<span class="signup">Bạn đã có tài khoản? <a
					href="${pageContext.request.contextPath}/login?action=login">Đăng nhập</a>
				</span>
			</div>
		</div>
	</div>
</body>
</html>