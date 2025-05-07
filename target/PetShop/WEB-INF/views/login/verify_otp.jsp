<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/user/css/login__register.css">
<title>Xac thuc tai khoan</title>
</head>
<body>
	 <div class="container">
	 <%
			HttpSession session2 = request.getSession();
			String msg = (String)(session2.getAttribute("msg"));
			String msg1 = msg;
			session2.removeAttribute("msg");
			%>
    <div class="login form">
      <header>Nhap ma xac thuc</header>
      <form action="${pageContext.request.contextPath}/forgotpassword?action=verifyotp" method="post">
      <span style="color: red"> <%= msg1 == null ? "" : msg1 %>
				</span>
        <input type="text" name="otp" placeholder="Vui lòng nhập mã">
        <input type="submit" class="button" value="Xác thực">
      </form>
      <div class="signup">
        <span class="signup">Chưa có tài khoản?
         <a href="${pageContext.request.contextPath}/login?action=register">Đăng ký</a>
        </span>
      </div>
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/assets/user/javascript/style_javascript.js"></script>
</body>
</html>