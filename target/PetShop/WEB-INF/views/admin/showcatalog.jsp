<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.demo.entities.*" %>
<%@ page import="com.demo.models.*" %>
<%@ page import="java.util.*" %>
<%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
List<Catalogs> catalogs = (List<Catalogs>) request.getAttribute("catalogs");
CategoryModel categoryModel = new CategoryModel();

if (session.getAttribute("admin-username") == null) {
    response.sendRedirect(request.getContextPath() + "/admin/login");
}
%>
<style>
.dataTables_wrapper .dataTables_paginate{
text-align:center;
margin: 10px;

}

    .dataTables_wrapper .dataTables_paginate .paginate_button {
        padding: 0.5rem 0.75rem;
        margin-left: 2px;
        border-radius: 0.25rem;
        font-size: 14px;
        line-height: 1.5;
        border: 1px solid #ccc;
        background-color: linear-gradient(45deg, #0c675e, #069e90);
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button:hover {
        background-color: green;
        color: #333;
        cursor: pointer;
    }

    .dataTables_wrapper .dataTables_paginate .paginate_button.current {
        background-color: #007bff;
        color: #fff;
        border-color: #007bff;
    }
</style>


<!-- Start header section -->
<div class="content-wrapper">
    <div class="container-fluid">
        <!--End Row-->
        <div class="row">
            <div class="col-lg-12">
                <button class="add-catalog">
                    <a href="${pageContext.request.contextPath}/admin/addcatalog">Thêm chuyên mục</a>
                </button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách chuyên mục</h5>
                        <div class="table-responsive">
                            <table id="catalogsTable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col" >#</th>
                                        <th scope="col" >Tên chuyên mục</th>
                                        <th scope="col" >Phân loại</th>
                                        <th scope="col" >Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(Catalogs catalog : catalogs) { %>
                                    <tr>
                                        <td scope="row"><%= catalog.getId() %></td>
                                        <td><%= catalog.getName() %></td>
                                        <td><%= categoryModel.findCategoryById(catalog.getCategoryId()).getName() %></td>
                                        <td>
                                            <button class="btn btn-danger">
                                                <a href="${pageContext.request.contextPath}/admin/chuyenmuc?action=delete&id=<%= catalog.getId() %>">Xóa</a>
                                            </button>
                                            <button class="btn btn-success">
                                                <a href="${pageContext.request.contextPath}/admin/editcatalog?id=<%= catalog.getId() %>">Sửa</a>
                                            </button>
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>

<script>
    $(document).ready(function () {
    	$('#catalogsTable').DataTable();
    });
</script>
