package com.demo.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.demo.models.PetModel; // Thay thế bằng tên lớp bạn dùng để quản lý pets
import com.demo.entities.Pets;

import java.util.List;

@WebServlet("/shopgrid")
public class ShopGridServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShopGridServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String category = request.getParameter("category");
         String price = request.getParameter("price");
         String limitParam = request.getParameter("limit");
         int limit = limitParam != null ? Integer.parseInt(limitParam) : 9; // Mặc định 9 sản phẩm nếu không có limit

         PetModel petModel = new PetModel();
         List<Pets> pets;

         if (category == null && price == null) {
             // Không có lọc, hiển thị tất cả sản phẩm
             pets = petModel.findAllByLimit(9);
         } else {
             // Có lọc, gọi phương thức findAllByFilter
             pets = petModel.findAllByFilter(
                 category != null ? category : "all", 
                 price != null ? price : "", 
                 limit
             );
         }

         request.setAttribute("pets", pets);
        request.setAttribute("p", "../user/shop-grid.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layout/user.jsp").forward(request, response);
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
