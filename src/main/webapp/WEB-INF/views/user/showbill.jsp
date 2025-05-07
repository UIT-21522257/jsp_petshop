<%@page import="com.demo.models.OrderModel"%>
<%@page import="com.demo.models.BillModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.demo.entities.*"%>
<%@page import="com.demo.models.OrderModel"%>
<%@page import="com.demo.models.AddressModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hóa Đơn Thanh Toán</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #FAFAFA;
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        * {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        .page {
            width: 21cm;
            overflow: hidden;
            min-height: 297mm;
            padding: 2.5cm;
            margin-left: auto;
            margin-right: auto;
            background: white;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }
        .subpage {
            padding: 1cm;
            border: 5px red solid;
            height: 237mm;
            outline: 2cm #FFEAEA solid;
        }
        @page {
            size: A4;
            margin: 0;
        }
        button {
            width: 100px;
            height: 24px;
        }
        .header {
            overflow: hidden;
        }
        .logo {
            background-color: #FFFFFF;
            text-align: left;
            float: left;
        }
        .company {
            padding-top: 24px;
            text-transform: uppercase;
            background-color: #FFFFFF;
            text-align: right;
            float: right;
            font-size: 16px;
        }
        .title {
            text-align: center;
            position: relative;
            color: #0000FF;
            font-size: 24px;
            top: 1px;
        }
        .footer-left {
            text-align: center;
            text-transform: uppercase;
            padding-top: 24px;
            position: relative;
            height: 150px;
            width: 50%;
            color: #000;
            float: left;
            font-size: 12px;
            bottom: 1px;
        }
        .footer-right {
            text-align: center;
            text-transform: uppercase;
            padding-top: 24px;
            position: relative;
            height: 150px;
            width: 50%;
            color: #000;
            font-size: 12px;
            float: right;
            bottom: 1px;
        }
        .TableData {
            background: #ffffff;
            font: 11px;
            width: 100%;
            border-collapse: collapse;
            font-family: Verdana, Arial, Helvetica, sans-serif;
            font-size: 12px;
            border: thin solid #d3d3d3;
        }
        .TableData th {
            background: rgba(0, 0, 255, 0.1);
            text-align: center;
            font-weight: bold;
            color: #000;
            border: solid 1px #ccc;
            height: 24px;
        }
        .TableData tr {
            height: 24px;
            border: thin solid #d3d3d3;
        }
        .TableData tr td {
            padding-right: 2px;
            padding-left: 2px;
            border: thin solid #d3d3d3;
        }
        .TableData tr:hover {
            background: rgba(0, 0, 0, 0.05);
        }
        .TableData .cotSTT {
            text-align: center;
            width: 10%;
        }
        .TableData .cotTenSanPham {
            text-align: left;
            width: 40%;
        }
        .TableData .cotHangSanXuat {
            text-align: left;
            width: 20%;
        }
        .TableData .cotGia {
            text-align: right;
            width: 120px;
        }
        .TableData .cotSoLuong {
            text-align: center;
            width: 50px;
        }
        .TableData .cotSo {
            text-align: right;
            width: 120px;
        }
        .TableData .tong {
            text-align: right;
            font-weight: bold;
            text-transform: uppercase;
            padding-right: 4px;
        }
        .TableData .cotSoLuong input {
            text-align: center;
        }
        @media print {
            @page {
                margin: 0;
                border: initial;
                border-radius: initial;
                width: initial;
                min-height: initial;
                box-shadow: initial;
                background: initial;
                page-break-after: always;
            }
        }
    </style>
</head>
<body onload="window.print();">
    <div id="page" class="page">
        <div class="header">
            <div class="logo"><img src="${pageContext.request.contextPath}/assets/user/images/logopetshop.jpg" alt="Logo PetShop" style="width:60px; height:60px;"/></div>
            <div class="company">C.Ty TNHH PetShop</div>
        </div>
        <br/>
        <div class="title">
            HÓA ĐƠN THANH TOÁN
            <br/>
            -------oOo-------
        </div>
        <br/>
        <br/>
        <table class="TableData">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Tổng tiền</th>
                    <th>Phương thức thanh toán</th>
                    <th>Trạng thái thanh toán</th>
                </tr>
            </thead>
           <tbody>
             <tr>
                <td>1</td>
                <td>${sessionScope.user.fullName}</td>
                <td>
                    ${address.getAddress()}, 
                    ${address.getWard()}, 
                    ${address.getDistrict()}, 
                    ${address.getCountry()}
                </td>
                <td>${order.getTotalMoney()} triệu đồng</td>
                <td>${bill.getPaymentMethod() == 2? "Thanh toán bằng VNPay":"Thanh toán khi nhận hàng"}</td>
                <td>${order.getStatus() == 1? "Đã xác nhận":"Chưa xác nhận"}</td>
            </tr> 
</tbody>


        </table>
        <div class="footer-left"> Tp.HCM, ngày 16 tháng 12 năm 2014<br/>
            Khách hàng </div>
        <div class="footer-right"> Tp.HCM, ngày 16 tháng 12 năm 2014<br/>
            Nhân viên </div>
    </div>
</body>
</html>
