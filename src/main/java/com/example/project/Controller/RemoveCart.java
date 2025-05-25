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

@WebServlet(name = "RemoveCart", value = "/remove-cart")
public class RemoveCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid = -1;
        try {
            pid = Integer.parseInt(request.getParameter("productID"));

        } catch (
                NumberFormatException e) {
            response.sendRedirect("show-cart");
            return;
        }
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            response.sendRedirect("show-cart");
            return;
        }
        cart.remove(pid);
        session.setAttribute("cart", cart);
        response.sendRedirect("show-cart");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
