<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%@ page import="com.demo.entities.*" %>
<%@ page import="com.demo.models.*" %>

<%
    List<OrderDetails> orderDetails = (List<OrderDetails>) request.getAttribute("orderDetails");
    PetModel petModel = new PetModel();
%>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Chi tiết đơn hàng</h5>
                        <form method="post" action="${pageContext.request.contextPath}/admin/editOrderDetails">
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
                                        <%
                                            int index = 1;
                                            for (OrderDetails orderDetail : orderDetails) {
                                                Pets pet = petModel.findPetById(orderDetail.getPetId());
                                        %>
                                        <tr>
                                            <td scope="row"><%= index++ %></td>
                                            <td><%= pet.getPetName() %></td>
                                            <td>
                                                <input type="number" name="quantity_<%= orderDetail.getId() %>" value="<%= orderDetail.getQuantity() %>" required />
                                            </td>
                                            <td>
                                                <input type="number" name="money_<%= orderDetail.getId() %>" value="<%= orderDetail.getMoney() %>" required />
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="form-footer">
                                <button type="submit" class="btn btn-success">Cập nhật</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
