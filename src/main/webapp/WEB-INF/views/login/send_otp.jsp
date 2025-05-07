<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/login__register.css">
    <title>Petshop|Quên Mật khẩu</title>
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
          <header>Gữi mã OTP</header>
          
          <form action="${pageContext.request.contextPath}/forgotpassword?action=sendotp" method="post">
         <span style="color: red"> <%= msg1 == null ? "" : msg1 %>
				</span>
            <input type="text" name="email" placeholder="Vui lòng nhập Email">
            <a href="${pageContext.request.contextPath}/login?action=register">Bạn muốn đăng ký?</a>
            <input type="submit" class="button" value="Gửi mã">
          </form>
        </div>
      </div>
</body>
</html>