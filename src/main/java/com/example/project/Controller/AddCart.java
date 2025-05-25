package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Cart;
import com.example.project.Model.Product;
import com.example.project.Model.ProductTK;
import com.example.project.Service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCart", value = "/add-cart")
public class AddCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            DaoProduct dp = new DaoProduct();
            ProductTK pid = dp.getById(Integer.parseInt(request.getParameter("productID")));
            HttpSession session = request.getSession(true);

            if (session.getAttribute("userID") == null) {
                out.write("{\"status\": \"login\"}");
                out.flush();
                return;
            }
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) cart = new Cart();
            cart.add(pid);
            session.setAttribute("cart", cart);

            out.write("{\"status\": \"success\", \"message\": \"Sản phẩm đã được thêm vào giỏ hàng!\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"status\": \"error\", \"message\": \"ID sản phẩm không hợp lệ!\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"status\": \"error\", \"message\": \"Lỗi server xảy ra!\"}");
        } finally {
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
