<%@ page import="java.util.List" %>
<%@ page import="com.demo.entities.*" %>
<%@ page import="com.demo.models.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
CategoryModel categoryModel = new CategoryModel();
List<Categorys> categorys = categoryModel.findAll();
CatalogModel catalogModel = new CatalogModel();
List<Catalogs> catalogs = catalogModel.findAll();
%>

  <!-- Start header section -->


    <div class="content-wrapper">
      <div class="container-fluid">
        <div class="row mt-3">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
                <div class="card-title">Thêm sản phẩm</div>
                <hr>
                <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/addsanpham?action=addpet">


                  <div class="form-group">
                    <label for="input-1">Tên sản phẩm</label>
                    <input type="text" class="form-control" id="input-1" placeholder="Tên sản phẩm" name="petName">
                  </div>
                  <div class="form-group">
                    <label for="input-2">Kích thước</label>
                   <div>
                            <select class="form-control valid" id="input-2" name="petType" required aria-invalid="false">
                                <option value="Lớn">Lớn</option>
                                <option value="Nhỏ">Nhỏ</option>
                            </select>
                        </div>
                  </div>
                  <div class="form-group">
                    <label for="input-3">Giới tính</label>
                   <div>
                            <select class="form-control valid" id="input-8" name="petGender" required aria-invalid="false">
                                <option value="Đực">Đực</option>
                                <option value="Cái">Cái</option>
                            </select>
                        </div>
                  </div>
                  <div class="form-group">
                    <label for="input-4">Thông tin cơ bản</label>
                    <input type="text" class="form-control" id="input-4" placeholder="Thông tin cơ bản" name="description">
                  </div>
                  <div class="form-group">
                    <label for="input-5">Thông tin cụ thể</label>
                    <input type="text" class="form-control" id="input-5" placeholder="Thông tin cụ thể" name="detail">
                  </div>
                  <div class="form-group">
                    <label for="input-6">Xuất xứ</label>
                    <input type="text" class="form-control" id="input-6" placeholder="Xuất xứ" name="made">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Số lượng</label>
                    <input type="text" class="form-control" id="input-7" placeholder="Số lượng" name="amount">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Giá (triệu đồng)</label>
                    <input type="text" class="form-control" id="input-8" placeholder="Giá" name="money">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Ngày sinh</label>
                    <input id="datepicker" type="text" class="form-control" placeholder="Ngày sinh" name="petBirthday">
                  </div>
                  <div class="form-group">
                    <label for="input-1">Ảnh</label>
                    <input type="file" name="files" multiple="multiple" required accept="image/*" class="form-control" id="input-9" placeholder="Ảnh" >
                  </div>
                  <!-- Profile picture image-->
                <div id="pictures" style="max-height: 300px; overflow: auto ;">
                </div>
                  <script>
                  $(document).ready(function(){
                    var src = [];
                      $('#input-9').change( function(event) {
                        if(event.target.files.length > 4){
                           alert('Chỉ được chọn tối đa 4 tấm hình');
                           location.reload();
                        } else {
                          for (var i = 0; i <  event.target.files.length; i++) {
                        console.log(event.target.files[i].name);
                        src[i] = URL.createObjectURL(event.target.files[i]);
                        $('<img src="' + src[i] + '" height="200" style="margin-top: 5px"/> ').appendTo('#pictures');
                        console.log(src[i]);
                      }
                        }
                    
                      
                       
                });
                    
                   /*  $('<p>Thành phần p</p>').appendTo('#div'); */
                   
                  });
                </script>
                    <div class="form-group">
                        <label for="input-2">Loại</label>
                         <div>
	                    <select class="form-control valid" id="input-10" name="categoryname" required aria-invalid="false">
	                    <% for(Categorys categoryPet: categorys){ %>
                            <option value="<%=categoryPet.getId()%>"><%= categoryPet.getName() %></option>
                            <% } %>                           
	                    </select>
	                  </div>
                    </div>
                    <div class="form-group">
                        <label for="input-2">Chuyên mục</label>
                        <div>
                            <select class="form-control valid" id="input-11" name="catalogname" aria-invalid="false">
                                 <% for(Catalogs catalog: catalogs){ %>
                            <option value="<%=catalog.getId()%>"><%= catalog.getName() %></option>
                            <% } %> 
                            </select>
                        </div>
                    </div>

               <div class="form-footer">
                    <button class="btn btn-danger"><i class="fa fa-times"></i><a href="${pageContext.request.contextPath}/admin/danhsachsanpham">Hủy</a></button>
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
<script>
		var date = new Date();

		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		if (month < 10) month = "0" + month;
		if (day < 10) day = "0" + day;

		var today = year + "-" + month + "-" + day;


		document.getElementById('the-date').value = today;
</script>

