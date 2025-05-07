<%@page import="com.demo.entities.Categorys"%>
<%@page import="com.demo.models.CatalogModel"%>
<%@page import="com.demo.models.CategoryModel"%>
<%@page import="com.demo.models.PetModel"%>
<%@page import="com.demo.entities.Pets"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
    CategoryModel categoryModel = new CategoryModel();
    PetModel petModel = new PetModel();
    int currentPage = 1; // Default page
    int pageSize = 12; // Number of items per page
    int categoryId = 1; // Default category id
    int totalPages = 0;
    
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }
    if (request.getParameter("categoryId") != null) {
        categoryId = Integer.parseInt(request.getParameter("categoryId"));
    }
    
    // Retrieve paginated pets
    List<Pets> petsList = petModel.findPaginatedByCategory(categoryId, currentPage, pageSize);
    totalPages = petModel.getTotalPagesByCategory(categoryId, pageSize); // Total pages based on the number of pets
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<style>
/* Styles for pagination container */
.pagination-container {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.pagination-container ul {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
}

.pagination-container li {
    margin: 0 5px;
}

.pagination-container .page-link {
    display: inline-block;
    padding: 10px 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
    text-decoration: none;
    color: #007bff;
    background-color: #fff;
    cursor: pointer;
}

.pagination-container .page-item.active .page-link {
    background-color: #007bff;
    color: white;
}

.pagination-container .page-link:hover {
    background-color: #ddd;
}
</style>
</head>
<body class="js">
<!-- Slider Area -->
    <section class="hero-slider">
      <div class="single-slider">
        <div class="container">
          <div class="row no-gutters">
            <div class="col-lg-9 offset-lg-3 col-12">
              <div class="text-inner">
                <div class="row">
                  <div class="col-lg-7 col-12">
                    <div class="hero-text">
                      <h1><span>GIẢM TỚI 50% </span>Chó Alaska</h1>
                      <p>
                        Một trong những loài được yêu thích nhất <br />
                        Có lông rậm, kích thước to lớn <br />
                        và mạnh mẽ .
                      </p>
                      <div class="button">
                        <a href="#" class="btn">Mua ngay!</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--/ End Slider Area -->

    <!-- Start Small Banner  -->
    <section class="small-banner section">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-4 col-md-6 col-12">
            <div class="single-banner">
              <img src="${pageContext.request.contextPath}/assets/user/images/anhdaidienalaska.jpg" alt="#" />
              <div class="content">
                <p>Danh sách chó</p>
                <h3>
                  Được xem <br />
                  nhiều nhất
                </h3>
                <a href="#">Khám phá ngay</a>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-12">
            <div class="single-banner">
              <img src="${pageContext.request.contextPath}/assets/user/images/anhdaidienmeo.jpg" alt="#" />
              <div class="content">
                <p>Danh mục thú cưng</p>
                <h3>
                  Mới nhất <br />
                  2023
                </h3>
                <a href="#">Khám phá ngay</a>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-12">
            <div class="single-banner tab-height">
              <img src="${pageContext.request.contextPath}/assets/user/images/anhdaidienchim.jpg" alt="#" />
              <div class="content">
                <p>Giảm thần tốc</p>
                <h3>
                  Mùa hè <br />
                  Giảm tới <span>40%</span>
                </h3>
                <a href="#">Khám phá ngay</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Small Banner -->

    <!-- Start Product Area -->
    <div class="product-area section">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <h2>Thú cưng hot</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="product-info">
                    <div class="nav-main">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <% 
                        for(Categorys category : categoryModel.findAll()){
                        %>
                            <li class="nav-item">
                                <button class="filterByCategory" id="<%= category.getId() %>"><%= category.getName() %></button>
                            </li> 
                            <%
                            }
                            %>
                        </ul>
                    </div>
                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                    <script>
                    $(document).ready(function() {
                        function loadPets(categoryId, page) {
                            $.ajax({
                                type: 'GET',
                                url: '${pageContext.request.contextPath}/home',
                                dataType: 'json',
                                data: {
                                    action: 'filterByCategory',
                                    idcategory: categoryId,
                                    page: page
                                },
                                success: function(response) {
                                    var pets = response.pets;
                                    var totalPages = response.totalPages;
                                    var html = '';
                                    for (var i = 0; i < pets.length; i++) {
                                        html += '<div class="col-xl-3 col-lg-4 col-md-4 col-12">';
                                        html += '<div class="single-product">';
                                        html += '<div class="product-img">';
                                        html += '<a href="${pageContext.request.contextPath}/petdetail?id=' + pets[i].id + '">';
                                        html += '<img class="default-img" src="${pageContext.request.contextPath}/assets/user/images/pet/' + pets[i].image + '" alt="#" />';
                                        html += '<img class="hover-img" src="${pageContext.request.contextPath}/assets/user/images/pet/' + pets[i].image + '" alt="#" />';
                                        html += '</a>';
                                        html += '<div class="button-head">';
                                        html += '<div class="product-action">';
                                        html += '<a data-toggle="modal" data-target="#exampleModal" title="Quick View" href="${pageContext.request.contextPath}/petdetail?id=' + pets[i].id + '"><i class="ti-eye"></i><span>Xem chi tiết</span></a>';
                                        html += '<a title="Wishlist" href="${pageContext.request.contextPath}/wishlistpet?action=wishlist&id=' + pets[i].id + '"><i class="ti-heart"></i><span>Thêm vào yêu thích</span></a>';
                                        html += '</div>';
                                        html += '<div class="product-action-2">';
                                        html += '<a title="Add to cart" href="${pageContext.request.contextPath}/cart?action=addToCart&id=' + pets[i].id + '">Thêm vào giỏ hàng</a>';
                                        html += '</div>';
                                        html += '</div>';
                                        html += '</div>';
                                        html += '<div class="product-content">';
                                        html += '<h3><a href="${pageContext.request.contextPath}/petdetail?id=' + pets[i].id + '">' + pets[i].petName + '</a></h3>';
                                        html += '<div class="product-price">';
                                        html += '<span>' + pets[i].money + ' triệu đồng</span>';
                                        html += '</div>';
                                        html += '</div>';
                                        html += '</div>';
                                        html += '</div>';
                                    }
                                    $('#product-container').html(html);
                                    updatePagination(page, totalPages);
                                },
                                error: function(jqXHR, textStatus, errorThrown) {
                                    console.log('Error:', textStatus, errorThrown);
                                }
                            });
                        }

                        function updatePagination(currentPage, totalPages) {
                            var paginationHtml = '';
                            if (totalPages > 1) {
                                paginationHtml += '<ul>';
                                if (currentPage > 1) {
                                    paginationHtml += '<li class="page-item"><button class="page-link" data-page="' + (currentPage - 1) + '">&laquo; Prev</button></li>';
                                }
                                for (var i = 1; i <= totalPages; i++) {
                                    paginationHtml += '<li class="page-item ' + (i === currentPage ? 'active' : '') + '">';
                                    paginationHtml += '<button class="page-link" data-page="' + i + '">' + i + '</button>';
                                    paginationHtml += '</li>';
                                }
                                if (currentPage < totalPages) {
                                    paginationHtml += '<li class="page-item"><button class="page-link" data-page="' + (currentPage + 1) + '">Next &raquo;</button></li>';
                                }
                                paginationHtml += '</ul>';
                            }
                            $('#pagination-container').html(paginationHtml);
                        }

                        function changePage(pageNumber) {
                            var categoryId = $('.filterByCategory.active').attr('id');
                            loadPets(categoryId, pageNumber);
                        }

                        $(document).on('click', '.filterByCategory', function() {
                            $('.filterByCategory').removeClass('active');
                            $(this).addClass('active');
                            var categoryId = $(this).attr('id');
                            loadPets(categoryId, 1);
                        });

                        $(document).on('click', '.page-link', function() {
                            var page = $(this).data('page');
                            changePage(page);
                        });

                        // Initial load
                        loadPets(1, 1);
                    });
                    </script>
                    <div id="product-container" class="row">
                        <!-- Product items will be loaded here by JavaScript -->
                    </div>
                    <div id="pagination-container" class="pagination-container">
                        <!-- Pagination buttons will be loaded here by JavaScript -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Product Area -->
</body>
</html>