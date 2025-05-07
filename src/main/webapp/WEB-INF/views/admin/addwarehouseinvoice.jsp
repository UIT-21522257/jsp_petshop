<%@ page import="java.util.List" %>
<%@ page import="com.demo.entities.*" %>
<%@ page import="com.demo.models.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
CategoryModel categoryModel = new CategoryModel();
List<Categorys> categorys = categoryModel.findAll();
%>

<div class="content-wrapper">
  <div class="container-fluid">
    <div class="row mt-3">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="card-title">Thêm hàng vào kho</div>
            <hr>
            <form method="post" action="${pageContext.request.contextPath}/admin/addwarehouseinvoice?action=addpet">
              <div class="form-group">
                <label for="input-1">Loại thú cưng</label>
                <div>
                  <select class="form-control valid" id="categorySelect" name="categoryname" required aria-invalid="false">
                    <% for(Categorys categoryPet: categorys){ %>
                      <option value="<%=categoryPet.getId()%>"><%= categoryPet.getName() %></option>
                    <% } %>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="input-1">Mục thú cưng</label>
                <div>
                  <select class="form-control valid" id="catalogSelect" name="catalogname" required aria-invalid="false">
                    <!-- Options will be populated based on category selection -->
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="input-1">Tên thú cưng</label>
                <div>
                  <select class="form-control valid" id="petSelect" name="petId" required aria-invalid="false">
                    <!-- Options will be populated based on catalog selection -->
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="input-1">Số lượng</label>
                <input type="text" class="form-control" id="quantity" placeholder="Số lượng" name="quantity">
              </div>
              <div class="form-group">
                <label for="input-1">Giá (triệu đồng)</label>
                <input type="text" class="form-control" id="price" placeholder="Giá" name="price">
              </div>
              <div class="form-footer">
                <button class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/quanlinhaphang">Hủy</a></button>
                <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i> Thêm</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="overlay toggle-menu"></div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
  $('#categorySelect').change(function() {
    var categoryId = $(this).val();
    $.ajax({
      type: 'GET',
      url: '${pageContext.request.contextPath}/admin/addwarehouseinvoice?action=getCatalogs',
      data: { categoryId: categoryId },
      success: function(response) {
        var catalogSelect = $('#catalogSelect');
        catalogSelect.empty();
        response.forEach(function(catalog) {
          var option = $('<option></option>').attr('value', catalog.id).text(catalog.name);
          catalogSelect.append(option);
        });
      },
      error: function() {
        alert('Error retrieving catalogs');
      }
    });
  });

  $('#catalogSelect').change(function() {
    var categoryId = $('#categorySelect').val();
    var catalogId = $(this).val();
    $.ajax({
      type: 'GET',
      url: '${pageContext.request.contextPath}/admin/addwarehouseinvoice?action=getPets',
      data: { categoryId: categoryId, catalogId: catalogId },
      success: function(response) {
        var petSelect = $('#petSelect');
        petSelect.empty();
        response.forEach(function(pet) {
          var option = $('<option></option>').attr('value', pet.id).text(pet.petName);
          petSelect.append(option);
        });
      },
      error: function() {
        alert('Error retrieving pets');
      }
    });
  });
});
</script>
