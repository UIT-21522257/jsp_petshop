<%@ page import="java.util.List" %>
<%@ page import="com.demo.entities.*" %>
<%@ page import="com.demo.models.*" %>
<%@ page import="com.google.gson.Gson" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
WarehouseInvoice warehouseInvoice = (WarehouseInvoice) request.getAttribute("warehouseInvoice");
PetModel petModel = new PetModel();
Pets pet = petModel.findPetById(warehouseInvoice.getPetId());

CategoryModel categoryModel = new CategoryModel();
List<Categorys> categorys = categoryModel.findAll();
CatalogModel catalogModel = new CatalogModel();
List<Catalogs> catalogs = catalogModel.findAllByCategory(pet.getCategoryId());
List<Pets> pets = petModel.findAllByCatalog(pet.getCategoryId(), pet.getCatalogId());
%>

<div class="content-wrapper">
  <div class="container-fluid">
    <div class="row mt-3">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="card-title">Chỉnh sửa hàng vào kho</div>
            <hr>
            <form method="post" action="${pageContext.request.contextPath}/admin/editwarehouseinvoice?action=editpet&id=<%= warehouseInvoice.getId() %>">
  <div class="form-group">
    <label for="categorySelect">Loại thú cưng</label>
    <select class="form-control valid" id="categorySelect" name="categoryId" required aria-invalid="false">
      <% for(Categorys categoryPet: categorys){ %>
        <option value="<%=categoryPet.getId()%>" <%= categoryPet.getId() == pet.getCategoryId() ? "selected" : "" %>><%= categoryPet.getName() %></option>
      <% } %>
    </select>
  </div>
  <div class="form-group">
    <label for="catalogSelect">Mục thú cưng</label>
    <select class="form-control valid" id="catalogSelect" name="catalogId" required aria-invalid="false">
      <% for(Catalogs catalog: catalogs){ %>
        <option value="<%=catalog.getId()%>" <%= catalog.getId() == pet.getCatalogId() ? "selected" : "" %>><%= catalog.getName() %></option>
      <% } %>
    </select>
  </div>
  <div class="form-group">
    <label for="petSelect">Tên thú cưng</label>
    <select class="form-control valid" id="petSelect" name="petId" required aria-invalid="false">
      <% for(Pets p: pets){ %>
        <option value="<%=p.getId()%>" <%= p.getId() == pet.getId() ? "selected" : "" %>><%= p.getPetName() %></option>
      <% } %>
    </select>
  </div>
  <div class="form-group">
    <label for="input-7">Số lượng</label>
    <input type="text" class="form-control" id="quantity" placeholder="Số lượng" name="quantity" value="<%= warehouseInvoice.getQuantity() %>">
  </div>
  <div class="form-group">
    <label for="input-8">Giá (triệu đồng)</label>
    <input type="text" class="form-control" id="price" placeholder="Giá" name="price" value="<%= warehouseInvoice.getPrice() %>">
  </div>
  <div class="form-footer">
    <button class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/quanlinhaphang">Hủy</a></button>
    <button type="submit" class="btn btn-success"><i class="fa fa-check-square-o"></i>Sửa</button>
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
      url: '${pageContext.request.contextPath}/getCatalogsByCategory',
      type: 'GET',
      data: { categoryId: categoryId },
      success: function(data) {
        var catalogs = JSON.parse(data);
        $('#catalogSelect').empty();
        $('#catalogSelect').append('<option value="">Chọn mục thú cưng</option>');
        catalogs.forEach(function(catalog) {
          $('#catalogSelect').append('<option value="' + catalog.id + '">' + catalog.name + '</option>');
        });
      }
    });
  });

  $('#catalogSelect').change(function() {
    var catalogId = $(this).val();
    $.ajax({
      url: '${pageContext.request.contextPath}/getPetsByCatalog',
      type: 'GET',
      data: { catalogId: catalogId },
      success: function(data) {
        var pets = JSON.parse(data);
        $('#petSelect').empty();
        $('#petSelect').append('<option value="">Chọn tên thú cưng</option>');
        pets.forEach(function(pet) {
          $('#petSelect').append('<option value="' + pet.id + '">' + pet.name + '</option>');
        });
      }
    });
  });

  // Trigger change event on categorySelect to load initial catalogs and pets
  $('#categorySelect').trigger('change');
});
</script>
