<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ page import="com.demo.entities.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Admin Pet Shop Website</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link href="${pageContext.request.contextPath}/assets/admin/css/pace.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/assets/admin/js/pace.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/admin/plugins/summernote/dist/summernote-bs4.css" />
<link rel="icon" href="${pageContext.request.contextPath}/assets/admin/images/favicon.ico" type="image/x-icon">
<%--  <link href="${pageContext.request.contextPath}/assets/admin/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet" />--%>
<link href="${pageContext.request.contextPath}/assets/admin/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/admin/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/admin/css/animate.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/admin/css/icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/assets/admin/css/sidebar-menu.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/admin/css/app-style.css" rel="stylesheet" />


<%
Users admin =(Users) request.getSession().getAttribute("user");

%>
<script>
$(document).ready(function () {
	$('#datepicker').datepicker({
        dateFormat: 'yy-mm-dd'
    });
});
	
</script>
</head>
<body>
<body class="bg-theme bg-theme1">


  <!-- start loader -->
  <div id="pageloader-overlay" class="visible incoming">
    <div class="loader-wrapper-outer">
      <div class="loader-wrapper-inner">
        <div class="loader"></div>
      </div>
    </div>
  </div>
  
 <div id="wrapper">
  <div id="sidebar-wrapper" data-simplebar="" data-simplebar-auto-hide="true">
      <div class="brand-logo">
        <a href="${pageContext.request.contextPath}/admin/homepage">
          <img src="${pageContext.request.contextPath}/assets/admin/images/logo-icon.png" class="logo-icon" alt="logo icon">
          <h5 class="logo-text">Admin PetShop Website</h5>
        </a>
      </div>
      <ul class="sidebar-menu do-nicescrol">
        <li class="sidebar-header">MENU ADMIN</li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/home">
            <i class="zmdi zmdi-view-dashboard"></i> <span>Tổng quan</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/quanliuser">
            <i class="zmdi zmdi-accounts"></i> <span>Quản lí User</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/chuyenmuc">
            <i class="zmdi zmdi-grid"></i> <span>Chuyên Mục</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/danhsachsanpham">
            <i class="zmdi zmdi-widgets"></i> <span>Danh Sách Sản Phẩm</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/quanlilog">
            <i class="zmdi zmdi-shopping-cart"></i> <span>Quản lí Log</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/quanlibill">
            <i class="zmdi zmdi-shopping-cart"></i> <span>Quản lí Hoá đơn</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/quanlinhaphang">
            <i class="zmdi zmdi-shopping-cart"></i> <span>Quản lí Nhập Hàng</span>
          </a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/admin/quanlitonkho">
            <i class="zmdi zmdi-shopping-cart"></i> <span>Quản lí Tồn Kho</span>
          </a>
        </li>
      </ul>
    </div>
    <header class="topbar-nav">
      <nav class="navbar navbar-expand fixed-top">
        <ul class="navbar-nav mr-auto align-items-center">
          <li class="nav-item">
            <a class="nav-link toggle-menu" href="javascript:void();">
              <i class="icon-menu menu-icon"></i>
            </a>
          </li>
        </ul>
		
        <ul class="navbar-nav align-items-center right-nav-link">
          <li class="nav-item">
            <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" data-toggle="dropdown" href="#">
              <span class="user-profile"><img src="${pageContext.request.contextPath}/assets/admin/images/avatar-admin.png" class="img-circle"
                  alt="user avatar"></span>
            </a>
            <ul class="dropdown-menu dropdown-menu-right">
              <li class="dropdown-item user-details">
                <a href="javaScript:void();">
                  <div class="media">
                    <div class="avatar"><img class="align-self-start mr-3" src="${pageContext.request.contextPath}/assets/admin/images/avatar-admin.png"
                        alt="user avatar"></div>
                    <div class="media-body">
                      <h6 class="mt-2 user-title">Petshopwebsite</h6>
                	<b> Admin:  <%= admin.getFullName() %></b>                         
                    </div>
                  </div>
                </a>
              </li>   
              <li class="dropdown-divider"></li>
			 <li class="dropdown-item"><i class="icon-power mr-2"></i><a href="${pageContext.request.contextPath}/login?action=logout"> Đăng xuất</a></li>		
            </ul>
          </li>
        </ul>
      </nav>
    </header>
    <div class="clearfix"></div>
    <%-- phan than giao dien --%>
    	<!-- section main -->
	<jsp:include page="${p }"></jsp:include>
    <a href="javaScript:void();" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>
    <div class="right-sidebar">
      <div class="switcher-icon">
        <i class="zmdi zmdi-settings zmdi-hc-spin"></i>
      </div>
      <div class="right-sidebar-content">
        <p class="mb-0">Màu nền admin</p>
        <hr>
        <ul class="switcher">
          <li id="theme1"></li>
          <li id="theme2"></li>
          <li id="theme3"></li>
          <li id="theme4"></li>
          <li id="theme5"></li>
          <li id="theme6"></li>
        </ul>
        <p class="mb-0">Màu nền gradient</p>
        <hr>
        <ul class="switcher">
          <li id="theme7"></li>
          <li id="theme8"></li>
          <li id="theme9"></li>
          <li id="theme10"></li>
          <li id="theme11"></li>
          <li id="theme12"></li>
          <li id="theme13"></li>
          <li id="theme14"></li>
          <li id="theme15"></li>
        </ul>
      </div>
    </div>
<%-- <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.min.js"></script>--%>
  <script src="${pageContext.request.contextPath}/assets/admin/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/admin/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/admin/plugins/simplebar/js/simplebar.js"></script>
  <script src="${pageContext.request.contextPath}/assets/admin/js/sidebar-menu.js"></script>
<%--  <script src="${pageContext.request.contextPath}/assets/admin/js/jquery.loading-indicator.js"></script>--%>
  <script src="${pageContext.request.contextPath}/assets/admin/js/app-script.js"></script>
  <script src="${pageContext.request.contextPath}/assets/admin/plugins/Chart.js/Chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/admin/js/index.js"></script>
  <script src="${pageContext.request.contextPath}/assets/admin/plugins/summernote/dist/summernote-bs4.min.js"></script>

</body>
</html>