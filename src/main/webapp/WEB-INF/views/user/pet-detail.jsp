<%@page import="com.demo.models.PicturePetModel"%>
<%@page import="com.demo.models.CommentModel"%>
<%@page import="com.demo.models.CatalogModel"%>
<%@page import="com.demo.models.CategoryModel"%>
<%@page import="com.demo.models.PetModel"%>
<%@page import="com.demo.models.UserModel"%>
<%@page import="com.demo.entities.Pets"%>
<%@page import="com.demo.entities.Comments"%>
<%@page import="com.demo.entities.Categorys"%>
<%@page import="com.demo.entities.PicturePets"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết thú cưng</title>
</head>
<body class="js">
<!-- Breadcrumbs -->
    <div class="breadcrumbs">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <div class="bread-inner">
              <ul class="bread-list">
                <li>
                  <a href="index1.html"
                    >Trang chủ<i class="ti-arrow-right"></i
                  ></a>
                </li>
                <li class="active">
                  <a href="pet-detail.html">Chi tiết thú cưng</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- Start Blog Single -->
    <section class="blog-single section">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-12">
            <div class="blog-single-main">
            <%
            int id =Integer.parseInt(request.getParameter("id"));
            Pets pet = (Pets) request.getAttribute("pet");
            PetModel petModel = new PetModel();
            CatalogModel catalogModel = new CatalogModel();
            CategoryModel categoryModel = new CategoryModel();
            CommentModel commentModel = new CommentModel();
            UserModel userModel = new UserModel();
            PicturePetModel picturePetModel = new PicturePetModel();
            List<PicturePets> picPets =(List<PicturePets>) picturePetModel.findPostImageByPetID(id);
            
            %>
              <div class="row">
                <div class="col-12">
                  <div class="image">
                    <img
                      src="${pageContext.request.contextPath}/assets/user/images/pet/<%= pet.getImage() %>"
                      alt="#"
                      id="mainImage"
                    />
                    <div class="image-thumbnail">
                    <% for(PicturePets pic : picPets){
                    	%>
                      <div class="image">
                        <img
                          src="${pageContext.request.contextPath }/assets/user/images/pet/<%=pic.getImage() %>"
                          alt=""
                          onclick="changeImage(this)"
                        />
                      </div>
                      <% } %>
                    </div>
                  </div>
                  <div class="blog-detail">
                    <h2 class="blog-title"><%= pet.getPetName() %></h2>
                    <div class="blog-meta">
                      <span class="author">
                        <a href="#"><i class="fa fa-user"></i>Số lượng: <%= pet.getAmount() %></a
                        ><a href="#"><i class="fa fa-user"></i>Lượt xem: 1</a
                        ><a href="#"><i class="fa fa-calendar"></i><%= petModel.ageByPet(pet.getPetBirthday().toString())  %></a
                        ><a href="#"
                          ><i class="fa fa-comments"></i>Bình luận(<%= commentModel.findCommentByPet(pet.getId()).size() %>)</a
                        ></span
                      >
                      <a href="${pageContext.request.contextPath }/cart?action=addToCart&id=<%= pet.getId() %>" class="blog-meta-buy" type="button">
                        <i class="ti-bag"></i>
                        Thêm vào giỏ hàng
                      </a>
                    </div>
                    <div class="content">
                      <div class="content-infomation">
                        <div class="content-infomation-child">
                          <span>Giới tính: <%= pet.getPetGender() %> </span>
                          <span>Kích thước: <%= pet.getPetType() %> </span>
                        </div>
                        <div class="content-infomation-child">
                          <span>Xuất xứ: <%= pet.getMade() %> </span>
                          <span>Chủng loại: <%= catalogModel.findCatalogById(pet.getCatalogId()).getName() %>  </span>
                        </div>
                      </div>
                      <blockquote>
                        <i class="fa fa-quote-left"></i> Mô tả: <%= pet.getDetail() %>
                      </blockquote>
                    </div>
                  </div>
                  <div class="share-social">
                    <div class="row">
                      <div class="col-12">
                        <div class="content-tags">
                          <h4>Thẻ:</h4>
                          <ul class="tag-inner">
                          <% 
                     for(Categorys category : categoryModel.findAll()){
                     %>
                            <li><a href="#"><%= category.getName() %></a></li>
                            <%
                   }
                            %>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
				<div class="col-12">
				    <div class="comments">
				        <h3 class="comment-title" id="comment-size">Bình luận(<c:out value="${fn:length(commentModel.findCommentByPet(pet.id))}"/>)</h3>
				        <!-- Single Comment -->
				        <div id="list-comment">
				            <c:forEach var="comment" items="${commentModel.findCommentByPet(pet.id)}">
				                <div class="single-comment">
				                    <img src="${pageContext.request.contextPath}/assets/user/images/${userModel.findUserById(comment.userId).image}" style="width:80px; height:80px;" alt="Anh dai dien" />
				                    <div class="content">
				                        <h4><c:out value="${userModel.findUserById(comment.userId).fullName}"/><span><c:out value="${comment.createDate}"/></span></h4>
				                        <p><c:out value="${fn:escapeXml(comment.note)}"/></p>
				                        <div class="button">
				                            <a href="#" class="btn"><i class="fa fa-reply" aria-hidden="true"></i>Phản hồi</a>
				                        </div>
				                    </div>
				                </div>
				            </c:forEach>
				        </div>
				        <!-- End Single Comment -->
				    </div>
				</div>
                  <script>
                  $(document).ready(function(){
                	  var petId = $('#petId').val();
                	  var s = '';
                	  $.ajax({
                		  type:'GET',
                		  url:'${pageContext.request.contextPath}/petdetail',
                		  data:{
                			  action:'loadComment',
                			  petId:petId
                		  },  
                		  success:function(data){
                			  var users = data.users;
                			  var comments = data.comments;
                			  for(var i=0;i<comments.length;i++){
                				 s+= '<div class="single-comment">'; 
                				 s+= '<img src="'+ users[i].image  +'" style="width:80px; height:80px;" alt="#" />'; 
                				 s+= '<div class="content">'; 
                				 s+= ' <h4>'+ users[i].fullName +'<span>+'comments[i].createDate'+</span></h4>'; 
                				 s+= '<p>'+ comments[i].note +'</p>'; 
                				 s+= '<div class="button">'; 
                				 s+= '<a href="#" class="btn">'; 
                				 s+= '<i class="fa fa-reply" aria-hidden="true"></i>Phản hồi'; 
                				 s+= '</a>'; 
                				 s+= '</div>'; 
                				 s+= '</div>'; 
                				 s+= '</div>'; 
                			  }
                			  $('#list-comment').html(s);
                			  $('#comment-size').html('Bình luận('+ comments.length +')');
                		  }
                	  });
                  });
                  </script>
                </div>
                <div class="col-12">
                  <div class="reply">
                    <div class="reply-head">
                      <h2 class="reply-title">Để lại bình luận</h2>
                      <!-- Comment Form -->
                      <form class="form" action="${pageContext.request.contextPath }/petdetail?action=addcomment" method="post">
                        <div class="row">
                        <input type="hidden" id="petId" name="petId" value="<%= id%>">
                        <c:if test="${sessionScope.user != null}">
                          <input type="hidden" name="userId" value="${sessionScope.user.id }">                      
                          <div class="col-12">
                            <div class="form-group">
                              <label>Bình luận của bạn<span>*</span></label>
                              <textarea name="note"
                                name="message"
                                placeholder="" style="row: 20; col:20;"
                              ></textarea>
                            </div>
                          </div>
                          <div class="col-12">
                            <div class="form-group button">
                              <button type="submit" class="btn">
                                Đăng bình luận
                              </button>
                            </div>
                          </div>
                          </c:if>
                          <c:if test="${sessionScope.user == null }">
                          <span>Bạn chưa đăng nhập, vui lòng đăng nhập để gửi bình luận!</span>
                          </c:if>
                        </div>
                      </form>
                      <!-- End Comment Form -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-12">
            <div class="main-sidebar">
              <!-- Single Widget -->
              <div class="single-widget search">
                <div class="form">
                  <input type="email" placeholder="Tìm kiếm..." />
                  <a class="button" href="#"><i class="fa fa-search"></i></a>
                </div>
              </div>
              <!--/ End Single Widget -->
              <!-- Single Widget -->
              <div class="single-widget category">
                <h3 class="title">Danh mục thú cưng</h3>
                <ul class="categor-list">
                 <% 
                     for(Categorys category : categoryModel.findAll()){
                     %>
                  <li><a href=""><%= category.getName() %></a></li>
                   <% 
                     }
                     %>
                </ul>
              </div>
              <!--/ End Single Widget -->
              <!-- Single Widget -->
              <div class="single-widget recent-post">
                <h3 class="title">Bài đăng gần đây</h3>
                <!-- Single Post -->
                <div class="single-post">
                  <div class="image">
                    <img src="https://via.placeholder.com/100x100" alt="#" />
                  </div>
                  <div class="content">
                    <h5>
                      <a href="#">Top 10 Beautyful Women Dress in the world</a>
                    </h5>
                    <ul class="comment">
                      <li>
                        <i class="fa fa-calendar" aria-hidden="true"></i>Jan 11,
                        2020
                      </li>
                      <li>
                        <i class="fa fa-commenting-o" aria-hidden="true"></i>35
                      </li>
                    </ul>
                  </div>
                </div>
                <!-- End Single Post -->
                <!-- Single Post -->
                <div class="single-post">
                  <div class="image">
                    <img src="https://via.placeholder.com/100x100" alt="#" />
                  </div>
                  <div class="content">
                    <h5>
                      <a href="#">Top 10 Beautyful Women Dress in the world</a>
                    </h5>
                    <ul class="comment">
                      <li>
                        <i class="fa fa-calendar" aria-hidden="true"></i>Mar 05,
                        2019
                      </li>
                      <li>
                        <i class="fa fa-commenting-o" aria-hidden="true"></i>59
                      </li>
                    </ul>
                  </div>
                </div>
                <!-- End Single Post -->
                <!-- Single Post -->
                <div class="single-post">
                  <div class="image">
                    <img src="https://via.placeholder.com/100x100" alt="#" />
                  </div>
                  <div class="content">
                    <h5>
                      <a href="#">Top 10 Beautyful Women Dress in the world</a>
                    </h5>
                    <ul class="comment">
                      <li>
                        <i class="fa fa-calendar" aria-hidden="true"></i>June
                        09, 2019
                      </li>
                      <li>
                        <i class="fa fa-commenting-o" aria-hidden="true"></i>44
                      </li>
                    </ul>
                  </div>
                </div>
                <!-- End Single Post -->
              </div>
              <!--/ End Single Widget -->
              <!-- Single Widget -->
              <!--/ End Single Widget -->
              <!-- Single Widget -->
              <div class="single-widget side-tags">
                <h3 class="title">Tags</h3>
                <ul class="tag">
                  <li><a href="#">business</a></li>
                  <li><a href="#">wordpress</a></li>
                  <li><a href="#">html</a></li>
                  <li><a href="#">multipurpose</a></li>
                  <li><a href="#">education</a></li>
                  <li><a href="#">template</a></li>
                  <li><a href="#">Ecommerce</a></li>
                </ul>
              </div>
              <!--/ End Single Widget -->
              <!-- Single Widget -->
              <div class="single-widget newsletter">
                <h3 class="title">Newslatter</h3>
                <div class="letter-inner">
                  <h4>
                    Subscribe & get news <br />
                    latest updates.
                  </h4>
                  <div class="form-inner">
                    <input type="email" placeholder="Enter your email" />
                    <a href="#">Submit</a>
                  </div>
                </div>
              </div>
              <!--/ End Single Widget -->
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--/ End Blog Single -->
</body>
</html>