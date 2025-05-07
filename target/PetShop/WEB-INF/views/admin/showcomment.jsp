<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ page import="java.util.*" %>
	<%@ page import="com.demo.entities.*" %>
	<%@ page import="com.demo.models.*" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%
	CommentModel commentModel = new CommentModel();
	PetModel petModel = new PetModel();
	UserModel userModel = new UserModel();
	List<Comments> comments =(List<Comments>) request.getSession().getAttribute("comments");
	int x = 0;
	%>
	 <!-- Start header section -->
    <div class="content-wrapper">
      <div class="container-fluid">
        <!--End Row-->


        <div class="row">
 
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Chi tiết bình luận</h5>
                <div class="table-responsive">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Tên người dùng</th>
                        <th scope="col">Tên thú cưng</th>
                        <th scope="col">Bình luận</th>
                        <th scope="col">Thời gian</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:if test="${sessionScope.comments == null }">
                     <% for(Comments comment: commentModel.findAll()){ 
                     x++;
                     %>
                      <tr>
                        <td scope="row"><%= x %></td>
                        <td><%= userModel.findUserById(comment.getUserId()).getFullName()%></td>
                        <td><%= petModel.findPetById(comment.getPetId()).getPetName() %></td>
                        <td><%= comment.getNote() %></td>
                        <td><%= comment.getCreateDate() %></td>
                     </tr>
                    <% } %>
                    
                     </c:if>
                     <c:if test="${sessionScope.comments != null }">
                     <% for(int i = 0; i< comments.size(); i++){ 
                     %>
                      <tr>
                        <td scope="row"><%= (i+1) %></td>
                        <td><%= userModel.findUserById(comments.get(i).getUserId()).getFullName()%></td>
                        <td><%= petModel.findPetById(comments.get(i).getPetId()).getPetName() %></td>
                        <td><%= comments.get(i).getNote() %></td>
                        <td><%= comments.get(i).getCreateDate() %></td>
                     </tr>
                    <% } %>
                     </c:if>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
	 