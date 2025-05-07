package com.demo.servlets.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.models.UserModel;
import com.demo.entities.Users;

@WebServlet("/admin/quanliuser")
public class QuanliuserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QuanliuserServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            doGet_Index(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            doGet_Delete(request, response);
        }
    }

    protected void doGet_Index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String filterStatus = request.getParameter("filterStatus");

        UserModel userModel = new UserModel();
        List<Users> users;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp startDate = null;
        Timestamp endDate = null;

        try {
            if (startDateStr != null && endDateStr != null && !startDateStr.isEmpty() && !endDateStr.isEmpty()) {
                Date start = sdf.parse(startDateStr);
                Date end = sdf.parse(endDateStr);
                startDate = new Timestamp(start.getTime());
                endDate = new Timestamp(end.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDate != null && endDate != null) {
            users = userModel.findUsersByDateRangeAndStatus(startDate, endDate, filterStatus);
        } else {
            users = userModel.findAllRole(2); // Assuming 2 represents regular users
        }

        request.setAttribute("users", users);
        request.setAttribute("p", "../admin/user.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout/admin.jsp").forward(request, response);
    }


    protected void doGet_Delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        int idUser = Integer.parseInt(id);
        UserModel userModel = new UserModel();
        if (userModel.delete(idUser)) {
            response.sendRedirect("quanliuser");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to doGet to handle filtering
        doGet(request, response);
    }
}
