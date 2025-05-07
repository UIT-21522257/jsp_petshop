<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ page import="java.util.*" %>
	<%@ page import="com.demo.entities.*" %>
	<%@ page import="com.demo.models.*" %>
	<%
	
	List<OrderDetails> orderDetails =(List<OrderDetails>) request.getSession().getAttribute("orderdetails");
	OrderDetailModel orderDetailModel = new OrderDetailModel();
	OrderModel orderModel = new OrderModel();
	PetModel petModel = new PetModel();
	
	%>
	 <!-- Start header section -->
    <div class="content-wrapper">
      <div class="container-fluid">
        <!--End Row-->


        <div class="row">
 
          <div class="col-lg-12">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Chi tiết đơn hàng</h5>
                <div class="table-responsive">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th scope="col">STT</th>
                       <th scope="col">Tên thú cưng</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Thành tiền</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                     <% for(OrderDetails orderDetail: orderDetails){ %>
                      <tr>
                        <td scope="row"><%= orderDetail.getId() %></td>
                         <td><%= petModel.findPetById(orderDetail.getPetId()).getPetName() %></td>
                        <td><%= 1 %></td>
                        <td><%= orderDetail.getMoney() %></td>
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
	 