<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Đăng nhập</title>
<script>
	function startCountdown(seconds) {
		var countdownElement = document.getElementById('countdown');
		var interval = setInterval(
				function() {
					if (seconds <= 0) {
						clearInterval(interval);
						countdownElement.innerHTML = "Bạn có thể đăng nhập lại.";
					} else {
						countdownElement.innerHTML = "Bạn có thể đăng nhập lại sau "
								+ seconds + " giây.";
						seconds--;
					}
				}, 1000);
	}

	window.onload = function() {
		var error = "${error}";
		if (error && error.includes("chờ")) {
			var seconds = parseInt(error.match(/(\d+) giây/)[1]);
			startCountdown(seconds);
		} else if (error) {
			document.getElementById('countdown').innerHTML = error;
		}
	};
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/login__register.css">
</head>
<body>
	<div class="container">
		<div class="login form">
			<header>Đăng nhập</header>
			<div id="countdown" style="color: red;">
				<c:if test="${not empty error}">
					<p style="color: red;">${error}</p>
				</c:if>
			</div>
			<form action="${pageContext.request.contextPath}/login?action=login"
				method="post" class="login">
				
				</span> <input type="text" name="username"
					placeholder="Vui lòng nhập Username"> <input
					type="password" name="password"
					placeholder="Vui lòng nhập Mật khẩu">
				<div style="display: flex; justify-content: space-between;">
					<a
						href="${pageContext.request.contextPath}/forgotpassword?action=sendotp">Quên
						mật khẩu</a>
				</div>
				<input type="submit" class="button" value="Đăng nhập">
			</form>
			<div class="signup">
				<span class="signup">Chưa có tài khoản? <a
					href="${pageContext.request.contextPath}/login?action=register">Đăng
						ký</a>
				</span>
			</div>
			<a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid

&redirect_uri=http://localhost:8080/PetShop/logingoogle

&response_type=code

&client_id=349360172934-997frvlao1s88f7ji7f87tqdt4mocilo.apps.googleusercontent.com

&approval_prompt=force
			">Login bằng Google</a>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/assets/user/javascript/style_javascript.js"></script>
</body>
</html>