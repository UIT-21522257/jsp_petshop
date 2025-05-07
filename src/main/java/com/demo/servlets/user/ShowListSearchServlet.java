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

import com.demo.entities.Pets;
import com.demo.models.PetModel;
import com.google.gson.Gson;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/showlistsearch")
public class ShowListSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowListSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            doGetIndex(request, response);
        }
    }

    protected void doGetIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("search");
        List<Pets> pets = new ArrayList<>();
        if (keyword != null && !keyword.isEmpty()) {
            PetModel petModel = new PetModel();
            pets = petModel.findByKeyword(keyword);
        }
        request.setAttribute("pets", pets);
        request.setAttribute("p", "../user/showlistsearch.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
