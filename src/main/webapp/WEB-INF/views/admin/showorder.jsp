<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.demo.entities.*" %>
<%@ page import="com.demo.models.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma" , "no-cache");
  response.setHeader("Expires" , "0");
  
  
  if (session.getAttribute("admin-username") == null){
	  response.sendRedirect(request.getContextPath() + "/admin/login");
  }
  List<Orders> orders = (List<Orders>)request.getAttribute("orders");
  UserModel userModel = new UserModel();
  AddressModel addressModel = new AddressModel();
  %>
  <!-- Start header section -->
    <div class="content-wrapper">
      <div class="container-fluid">
        <!--End Row-->


        <div class="row">
 
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Danh sách đơn hàng</h5>
                <div class="table-responsive">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                       <th scope="col">Tài khoản User</th>
                        <th scope="col">Họ Tên</th>
                        <th scope="col">Email</th>
                        <th scope="col">SĐT</th>
                         <th scope="col">Địa chỉ</th>
                         <th scope="col">Ghi chú</th>
                         <th scope="col">Tổng tiền</th>
                          <th scope="col">Ngày tạo</th>
                          <th scope="col">Danh sách sản phẩm</th>
                          <th scope="col">Hành động</th>
                         <th scope="col">Trạng thái</th>
                        
                      </tr>
                    </thead>
                    <tbody>
                    
                    <% for(Orders order: orders){ %>
                      <tr>
                        <td scope="row"><%= order.getId() %></td>
                         <td><%= order.getUserId() %></td>
                         <td><%= userModel.findUserById(order.getUserId()).getFullName() %></td>
                         <td><%= order.getEmail() %></td>
                         <td><%= order.getPhoneNumber() %></td>
                         <td><%= addressModel.findAddressById(order.getAddressId()).getAddress()+", "+ addressModel.findAddressById(order.getAddressId()).getWard()+", "+addressModel.findAddressById(order.getAddressId()).getDistrict()+", "+addressModel.findAddressById(order.getAddressId()).getCountry() %></td>
                         <td><%= order.getNote() %></td>
                         <td><%= order.getTotalMoney() %> triệu đồng</td>
                          <td><%= order.getOrderDate() %>  </td>
                          <td>
                        <button class="btn btn-success"><i class="fa"></i><a href="${pageContext.request.contextPath}/admin/chitietdonhang?action=chitietdonhang&id=<%= order.getId() %>">Chi tiết</a></button>
                        </td> 
                        <c:if test="<%= order.getStatus() == 1 %>">
										<td>Hoàn thành đơn hàng</td>
										<td>Đã xác nhận</td>
									</c:if>
						
						<c:if test="<%= order.getStatus() == 0 %>">
						<td>
						<button class="btn btn-danger">
												<a href="${pageContext.request.contextPath}/admin/editorder?id=<%= order.getId() %>">Sửa</a>
						</button>					
						</td>
										 <td>
                         <button class="btn btn-danger">
												<a href="${pageContext.request.contextPath}/admin/editorder?action=confirm&id=<%= order.getId() %>">Chưa Xác nhận</a>
						</button>
                        </td>  
						</c:if> 				
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