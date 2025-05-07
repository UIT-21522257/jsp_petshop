<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/login__register.css">
<title>Pet Shop|Nhập mật khẩu mới</title>
</head>
<body>
	<div class="overlay__pass hidden"></div>
	<div class="container">
		<% 
		HttpSession session2 = request.getSession();
		String msg = (String) (session2.getAttribute("msg"));
		String msg1 = msg;
		session2.removeAttribute("msg");
		%>
		<div class="login form">
			<header>Nhập mật khẩu mới</header>
			<form
				action="${pageContext.request.contextPath}/forgotpassword?action=forgotpassword"
				method="post">
				<span style="color: red"> <%=msg1 == null ? "" : msg1%></span> 
				<div class="mb-3">
                      <label class="small mb-1" for="inputPassword">Mật khẩu mới</label>
                      <input class="form-control" name="newpass" id="inputPassword"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,}" type="password" placeholder="Nhập mật khẩu mới">
                  </div>
                  <div class="mb-3">
                    <label class="small mb-1" for="inputUsername">Xác nhận mật khẩu</label>
                    <input class="form-control" name="newpass2" id="inputConfirmPassword" type="password" placeholder="Nhập lại mật khẩu của bạn">
                  </div>
                  <script>
                    var password = document.getElementById("inputPassword")
                      , confirm_password = document.getElementById("inputConfirmPassword");

                    function validatePassword(){
                      if(password.value != confirm_password.value) {
                        confirm_password.setCustomValidity("Passwords Don't Match");
                      } else {
                        confirm_password.setCustomValidity('');
                      }
                    }

                    password.onchange = validatePassword;
                    confirm_password.onkeyup = validatePassword;
                  </script>
				<a	href="./change_password.html">Đổi mật khẩu</a> <input type="submit"
					class="button change_password" value="Xác nhận">
			</form>
			<div class="signup">
				<span class="signup">Chưa có tài khoản? <a
					href="${pageContext.request.contextPath}/login?action=register">Đăng
						ký</a>
				</span>
			</div>
		</div>
	</div>

	<div class="pass-notify hidden">
		<span class="signup">Đổi mật khẩu thành công! <input
			type="submit" class="button login_button" value="Đăng nhập">
	</div>
</body>
</html>