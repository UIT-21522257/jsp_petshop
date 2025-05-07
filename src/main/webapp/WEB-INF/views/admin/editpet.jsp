<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.demo.entities.*"%>
<%@ page import="com.demo.models.*"%>
<%@ page import="java.util.*"%>


<!-- Start header section -->
<%
CategoryModel categoryModel = new CategoryModel();
List<Categorys> categorys = categoryModel.findAll();
CatalogModel catalogModel = new CatalogModel();
List<Catalogs> catalogs = catalogModel.findAll();
Pets pet = (Pets) request.getAttribute("pet");
%>
<div class="content-wrapper">
	<div class="container-fluid">
		<div class="row mt-3">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Sửa sản phẩm</div>
						<hr>
						<form method="post"
							action="${pageContext.request.contextPath}/admin/editsanpham?action=editpet&id=<%= pet.getId() %>">

							<div class="form-group">
								<label for="input-1">Mã sản phẩm</label> <input type="text"
									class="form-control" readonly="readonly" id="input-1"
									placeholder="Mã sản phẩm" name="id" value="<%= pet.getId() %>">
							</div>

							<div class="form-group">
								<label for="input-1">Tên sản phẩm</label> <input type="text"
									class="form-control" id="input-2" placeholder="Tên sản phẩm"
									name="petName" value="<%= pet.getPetName() %>">
							</div>
							 <div class="form-group">
                    <label for="input-2">Kích thước</label>
                   <div>
                            <select class="form-control valid" id="input-3" name="petType" required aria-invalid="false">
                                <option value="Lớn">Lớn</option>
                                <option value="Nhỏ">Nhỏ</option>
                            </select>
                        </div>
                  </div>
                  <div class="form-group">
                    <label for="input-3">Giới tính</label>
                   <div>
                            <select class="form-control valid" id="input-4" name="petGender" required aria-invalid="false">
                                <option value="Đực">Đực</option>
                                <option value="Cái">Cái</option>
                            </select>
                        </div>
                  </div>
                  <div class="form-group">
                    <label for="input-4">Thông tin cơ bản</label>
                    <input type="text" class="form-control" id="input-5" placeholder="Thông tin cơ bản" name="description" value="<%= pet.getDescription() %>">
                  </div>
                  <div class="form-group">
                    <label for="input-5">Thông tin cụ thể</label>
                    <input type="text" class="form-control" id="input-6" placeholder="Thông tin cụ thể" name="detail" value="<%= pet.getDetail() %>" >
                  </div>
                  <div class="form-group">
                    <label for="input-6">Xuất xứ</label>
                    <input type="text" class="form-control" id="input-7" placeholder="Xuất xứ" name="made" value="<%= pet.getMade() %>">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Số lượng</label>
                    <input type="text" class="form-control" id="input-8" placeholder="Số lượng" name="amount" value="<%= pet.getAmount() %>">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Giá (triệu đồng)</label>
                    <input type="text" class="form-control" id="input-9" placeholder="Giá" name="money" value="<%= pet.getMoney() %>">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Ngày sinh</label>
                    <input type="text" class="form-control" id="createDate" placeholder="Ngày sinh" name="createDate" value="<%= pet.getPetBirthday() %>">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Ảnh</label>
                    <input type="text" class="form-control" id="input-10" placeholder="Ảnh" name="image" value="<%= pet.getImage() %>">
                  </div>
                    <div class="form-group">
                        <label for="input-2">Loại</label>
                         <div>
	                    <select class="form-control valid" id="input-11" name="categoryname" required aria-invalid="false">
	                    <% for(Categorys categoryPet: categorys){ %>
                            <option value="<%=categoryPet.getId()%>"><%= categoryPet.getName() %></option>
                            <% } %>                           
	                    </select>
	                  </div>
                    </div>
                    <div class="form-group">
                        <label for="input-2">Chuyên mục</label>
                        <div>
                            <select class="form-control valid" id="input-12" name="catalogname" aria-invalid="false">
                                 <% for(Catalogs catalog: catalogs){ %>
                            <option value="<%=catalog.getId()%>"><%= catalog.getName() %></option>
                            <% } %> 
                            </select>
                        </div>
                    </div>

							<div class="form-footer">
								<button class="btn btn-danger">
									<a href="${pageContext.request.contextPath}/danhsachsanpham">Hủy</a>
								</button>

								<button type="submit" class="btn btn-success">Cập nhật</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="overlay toggle-menu"></div>
	</div>
</div>