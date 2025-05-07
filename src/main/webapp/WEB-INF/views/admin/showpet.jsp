<%@page import="com.demo.models.*"%>
<%@page import="com.demo.entities.*"%>
<%@page import="com.demo.models.CatalogModel"%>
<%@page import="com.demo.models.CategoryModel"%>
<%@ page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
List<Pets> pets = (List<Pets>) request.getAttribute("pets");
CategoryModel categoryModel = new CategoryModel();
CatalogModel catalogModel = new CatalogModel();

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
        <div class="row mt-3">
            <div class="col-lg-12">
                <button class="add-catalog">
                    <a href="${pageContext.request.contextPath}/admin/addsanpham">Thêm thú cưng</a>
                </button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách thú cưng</h5>
                        <div class="table-responsive">
                            <table id="dataTable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Tên thú cưng</th>
                                        <th scope="col">kích thước</th>
                                        <th scope="col">Giới tính</th>
                                        <th scope="col">Thông tin cơ bản</th>
                                        <th scope="col">Thông tin cụ thể</th>
                                        <th scope="col">Xuất xứ</th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Giá</th>
                                        <th scope="col">Ngày sinh</th>
                                        <th scope="col">Ảnh</th>
                                        <th scope="col">Loại</th>
                                        <th scope="col">Chuyên mục</th>
                                        <th scope="col">Hành động</th>
                                        <th scope="col">Bình luận</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int i = 1;
                                    for (Pets pet : pets) {
                                    %>
                                    <tr>
                                        <td><%=i%></td>
                                        <td><%=pet.getPetName()%></td>
                                        <td><%=pet.getPetType()%></td>
                                        <td><%=pet.getPetGender()%></td>
                                        <td><%=pet.getDescription()%></td>
                                        <td><%=pet.getDetail()%></td>
                                        <td><%=pet.getMade()%></td>
                                        <td><%=pet.getAmount()%></td>
                                        <td><%=pet.getMoney()%></td>
                                        <td><%=pet.getPetBirthday()%></td>
                                        <td>
                                            <img src="${pageContext.request.contextPath}/assets/user/images/pet/<%=pet.getImage()%>" alt="Ảnh thú cưng" width="100" height="100">
                                        </td>
                                        <td><%=categoryModel.findCategoryById(pet.getCategoryId()).getName()%></td>
                                        <td><%=catalogModel.findCatalogById(pet.getCatalogId()).getName()%></td>
                                        <td>
                                            <button class="btn btn-danger">
                                                <a href="${pageContext.request.contextPath}/admin/danhsachsanpham?action=delete&id=<%= pet.getId() %>">Xóa</a>
                                            </button>
                                            <button class="btn btn-success">
                                                <a href="${pageContext.request.contextPath}/admin/editsanpham?id=<%= pet.getId() %>">Sửa</a>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="btn btn-warning">
                                                <a href="${pageContext.request.contextPath}/admin/quanlibinhluan?id=<%= pet.getId() %>">Danh sách bình luân</a>
                                            </button>
                                        </td>
                                    </tr>
                                    <% i++; } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function() {
        $('#dataTable').DataTable();
    });
</script>
