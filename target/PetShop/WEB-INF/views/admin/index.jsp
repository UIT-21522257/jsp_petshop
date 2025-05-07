<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.demo.entities.Orders"%>
<%@page import="com.mysql.cj.x.protobuf.MysqlxCrud.Order"%>
<%@page import="com.demo.models.UserModel"%>
<%@page import="com.demo.models.OrderModel"%>
<%@page import="com.demo.models.OrderDetailModel"%>
<%@page import="com.google.api.client.http.HttpRequest"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma" , "no-cache");
  response.setHeader("Expires" , "0");
 
  
  if (session.getAttribute("admin-username") == null){
	  response.sendRedirect(request.getContextPath() + "/admin/login"); 
  }
//Lấy các thuộc tính từ request
 Double total = (Double) request.getAttribute("total");
 Integer dog = (Integer) request.getAttribute("dog");
 Integer cat = (Integer) request.getAttribute("cat");
 Integer other = (Integer) request.getAttribute("other");
 Integer totalOrders = (Integer) request.getAttribute("totalOrders");
 Integer totalProductsSold = (Integer) request.getAttribute("totalProductsSold");
 
 String startDate = request.getParameter("startDate");
 String endDate = request.getParameter("endDate");

 // DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");
 // String formattedNumber = decimalFormatter.format(total) + " triệu đồng";
%>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
$(function() {
   "use strict";

   // Chart 2
   var ctx = document.getElementById("chart2").getContext('2d');
   var myChart = new Chart(ctx, {
       type: 'doughnut',
       data: {
           labels: ["Chó", "Mèo", "Thú cưng khác"],
           datasets: [{
               backgroundColor: [
                   "#ffffff",
                   "rgba(255, 255, 255, 0.70)",
                   "rgba(255, 255, 255, 0.50)"
               ],
               data: [${dog}, ${cat}, ${other}],
               borderWidth: [0, 0, 0]
           }]
       },
       options: {
           maintainAspectRatio: false,
           legend: {
               position: "bottom",
               display: false,
               labels: {
                   fontColor: '#ddd',
                   boxWidth: 15
               }
           },
           tooltips: {
               displayColors: false
           }
       }
   });

   // Datepicker
   $("#startDate").datepicker({ dateFormat: 'yy-mm-dd' });
   $("#endDate").datepicker({ dateFormat: 'yy-mm-dd' });
   $("#loadDefault").click(function() {
       $("#startDate").val('');
       $("#endDate").val('');
   });
});
</script>

<!-- Start header section -->
<div class="content-wrapper">
   <div class="container-fluid">
       <!-- Form để chọn khoảng thời gian -->
       <form id="filterForm" method="get" action="${pageContext.request.contextPath}/admin/home">
           <label for="startDate">Từ ngày:</label>
           <input type="text" id="startDate" name="startDate" value="${startDate }" required>
           <label for="endDate">Đến ngày:</label>
           <input type="text" id="endDate" name="endDate" value="${endDate}" required>
           <button type="submit">Lọc</button>
            <button type="button" id="loadDefault">Load</button>
       </form>
       <div class="card mt-3">
           <div class="card-content">
               <div class="row row-group m-0">
                   <div class="col-12 col-lg-6 col-xl-3 border-light">
                       <div class="card-body">
                           <h5 class="text-white mb-0">${totalOrders} <span class="float-right"><i class="fa fa-shopping-cart"></i></span></h5>
                           <div class="progress my-3" style="height:3px;">
                               <div class="progress-bar" style="width:100%"></div>
                           </div>
                           <p class="mb-0 text-white small-font">Đơn hàng</p>
                       </div>
                   </div>
                   <div class="col-12 col-lg-6 col-xl-3 border-light">
                       <div class="card-body">
                           <h5 class="text-white mb-0">${total} triệu đồng</h5>
                           <div class="progress my-3" style="height:3px;">
                               <div class="progress-bar" style="width:100%"></div>
                           </div>
                           <p class="mb-0 text-white small-font">Tổng thu nhập</p>
                       </div>
                   </div>
                   <div class="col-12 col-lg-6 col-xl-3 border-light">
                       <div class="card-body">
                           <h5 class="text-white mb-0"><%= (new UserModel().findAll().size()) - 1%><span class="float-right"><i class="fa fa-user"></i></span></h5>
                           <div class="progress my-3" style="height:3px;">
                               <div class="progress-bar" style="width:100%"></div>
                           </div>
                           <p class="mb-0 text-white small-font">Người dùng</p>
                       </div>
                   </div>
                   <div class="col-12 col-lg-6 col-xl-3 border-light">
                       <div class="card-body">
                           <h5 class="text-white mb-0">${totalProductsSold} <span class="float-right"><i class="fa fa-envira"></i></span></h5>
                           <div class="progress my-3" style="height:3px;">
                               <div class="progress-bar" style="width:100%"></div>
                           </div>
                           <p class="mb-0 text-white small-font">Bán ra</p>
                       </div>
                   </div>
               </div>
           </div>
       </div>
       <div class="row">
           <div class="col-12 col-lg-4 col-xl-4">
               <div class="card">
                   <div class="card-header">Danh mục bán chạy</div>
                   <div class="card-body">
                       <div class="chart-container-2">
                           <canvas id="chart2"></canvas>
                       </div>
                   </div>
                   <div class="table-responsive">
                       <table class="table align-items-center">
                           <tbody>
                               <tr>
                                   <td><i class="fa fa-circle text-white mr-2"></i>Chó</td>
                                   <td>${dog }</td>
                                   <td>${Double.isNaN(dog / (dog + cat + other) * 100) ? 0:(dog / (dog + cat + other) * 100) } %</td>
                               </tr>
                               <tr>
                                   <td><i class="fa fa-circle text-light-1 mr-2"></i>Mèo</td>
                                   <td>${cat}</td>
                                   <td>${Double.isNaN(cat / (dog + cat + other) * 100) ? 0:(cat / (dog + cat + other) * 100) } %</td>
                               </tr>
                               <tr>
                                   <td><i class="fa fa-circle text-light-1 mr-2"></i>Thú cưng khác</td>
                                   <td>${other}</td>
                                   <td>${Double.isNaN(other / (dog + cat + other) * 100) ? 0:(other / (dog + cat + other) * 100)} %</td>
                               </tr>
                           </tbody>
                       </table>
                   </div>
               </div>
           </div>
       </div>
   </div>
</div>

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