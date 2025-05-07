<%@ page import="com.demo.models.LogModel" %>
<%@ page import="com.demo.entities.Log" %>
<%@ page import="java.util.*" %>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css">
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
List<Log> logs = (List<Log>) request.getAttribute("logs");
%>
<style>
.log-info {
    background-color: #dff0d8; /* Màu xanh nhạt cho thông tin */
    color: #3c763d; /* Màu xanh lá cho chữ */
}

.log-warning {
    background-color: #fcf8e3; /* Màu vàng nhạt cho cảnh báo */
    color: #8a6d3b; /* Màu vàng nâu cho chữ */
}

.log-error {
    background-color: #f2dede; /* Màu đỏ nhạt cho lỗi */
    color: #a94442; /* Màu đỏ cho chữ */
}
</style>
<!-- Start header section -->
<div class="content-wrapper">
    <div class="container-fluid">
        <div class="row mt-3">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Hoạt động người dùng</h5>
                        <table id="example" class="display" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Id người dùng</th>
                                    <th>Ip</th>
                                    <th>Cấp độ</th>
                                    <th>Quốc gia</th>
                                    <th>Thời gian</th>
                                    <th>Giá trị trước</th>
                                    <th>Giá trị sau</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="log" items="${logs}">
                                    <tr>
                                        <td>${log.id}</td>
                                        <td>${log.userId}</td>
                                        <td>${log.ip}</td>
                                        <c:choose>
                                            <c:when test="${log.level eq 'info'}">
                                                <td class="log-info">${log.level}</td>
                                            </c:when>
                                            <c:when test="${log.level eq 'warning'}">
                                                <td class="log-warning">${log.level}</td>
                                            </c:when>
                                            <c:when test="${log.level eq 'error'}">
                                                <td class="log-error">${log.level}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${log.level}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td>${log.national}</td>
                                        <td>${log.time}</td>
                                        <td>${log.beforeValue}</td>
                                        <td>${log.afterValue}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script>
    new DataTable('#example');
</script>
