package com.demo.servlets.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.models.OrderDetailModel;
import com.demo.models.OrderModel;
import com.demo.entities.Orders;

@WebServlet("/admin/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số ngày từ request
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày phù hợp
        Date startDate = null;
        Date endDate = null;

        try {
            if (startDateStr != null && endDateStr != null) {
                startDate = sdf.parse(startDateStr);
                endDate = sdf.parse(endDateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        OrderDetailModel orderDetailModel = new OrderDetailModel();
        OrderModel orderModel = new OrderModel();

        double total = 0.0;
        int dog = 0;
        int cat = 0;
        int other = 0;
        int totalOrders = 0;
        int totalProductsSold = 0;

        if (startDate != null && endDate != null) {
            // Lọc đơn hàng theo khoảng thời gian
            List<Orders> orders = orderModel.findOrdersByDateRange(new java.sql.Timestamp(startDate.getTime()), new java.sql.Timestamp(endDate.getTime()));
            totalOrders = orders.size();

            for (Orders order : orders) {
                if (order.getStatus() != 3) {
                    total += order.getTotalMoney();
                    totalProductsSold += orderDetailModel.getQuantityByOrderId(order.getId());
                    
                    dog += orderDetailModel.quantityByCategory(order.getId(), 1);
                    cat += orderDetailModel.quantityByCategory(order.getId(), 2);
                    other += orderDetailModel.quantityByCategory(order.getId(), 3);
                }
            }
        } else {
            // Nếu không có khoảng thời gian, lấy tất cả dữ liệu
            List<Orders> orders = orderModel.findAll();
            totalOrders = orders.size();

            for (Orders order : orders) {
                if (order.getStatus() != 3) {
                    total += order.getTotalMoney();
                    totalProductsSold += orderDetailModel.getQuantityByOrderId(order.getId());
                    
                    dog += orderDetailModel.quantityByCategory(order.getId(), 1);
                    cat += orderDetailModel.quantityByCategory(order.getId(), 2);
                    other += orderDetailModel.quantityByCategory(order.getId(), 3);
                }
            }
        }
        request.setAttribute("startDate", startDateStr);
        request.setAttribute("endDate", endDateStr);
        request.setAttribute("total", total);
        request.setAttribute("dog", dog);
        request.setAttribute("cat", cat);
        request.setAttribute("other", other);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalProductsSold", totalProductsSold);
        request.setAttribute("p", "../admin/index.jsp");

        request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
