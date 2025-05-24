package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Cart;
import com.example.project.Model.ProductTK;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCartDetail", value = "/add-cart-detail")
public class AddCartDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            int productId = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            DaoProduct dp = new DaoProduct();
            ProductTK pid = dp.getById(productId);
            HttpSession session = request.getSession(true);

            if (session.getAttribute("userID") == null) {
                out.write("{\"status\": \"login\"}");
                return;
            }

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) cart = new Cart();

            cart.add(pid, quantity);

            session.setAttribute("cart", cart);
            out.write("{\"status\": \"success\", \"message\": \"Đã thêm sản phẩm vào giỏ hàng!\"}");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"status\": \"error\", \"message\": \"Dữ liệu không hợp lệ!\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"status\": \"error\", \"message\": \"Lỗi server xảy ra!\"}");
        } finally {
            out.flush();
            out.close();
        }
    }
}