package com.demo.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.entities.Pets;
import com.demo.models.PetModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int PAGE_SIZE = 12;

    public HomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            doGetIndex(request, response);
        } else if (action.equals("filterByCategory")) {
            doGetCategory(request, response);
        }
    }

    protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("p", "../user/main.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
    }

    protected void doGetCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PetModel petModel = new PetModel();
        String categoryId = request.getParameter("idcategory");
        String pageParam = request.getParameter("page");
        int page = pageParam != null ? Integer.parseInt(pageParam) : 1; // Default to page 1 if not provided

        List<Pets> pets;
        int total;

        try {
            int idCategory = Integer.parseInt(categoryId);
            int offset = (page - 1) * PAGE_SIZE; // Use PAGE_SIZE constant
            pets = petModel.findAllByCategoryWithPagination(idCategory, offset, PAGE_SIZE);
            total = petModel.getTotalCountByCategory(idCategory);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Log exception
            pets = new ArrayList<>();
            total = 0;
        }

        // Create JSON response
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.add("pets", new Gson().toJsonTree(pets));
        jsonResponse.addProperty("total", total);
        jsonResponse.addProperty("page", page);
        jsonResponse.addProperty("pageSize", PAGE_SIZE);
        jsonResponse.addProperty("totalPages", (int) Math.ceil(total / (double) PAGE_SIZE)); // Calculate total pages

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonResponse.toString());
        printWriter.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}