package com.example.project.Controller;

import com.example.project.Model.Cart;
import com.example.project.Model.CartProduct;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateCart", value = "/update-cart")
public class UpdateCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        PrintWriter out = response.getWriter();
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String action = request.getParameter("action");

            if (action != null) {
                CartProduct product = cart.data.get(productId);

                if (product != null) {
                    int newQuantity = product.getQuantity();
                    if (action.equals("increase")) {
                        newQuantity++;
                    } else if (action.equals("decrease") && newQuantity > 1) {
                        newQuantity--;
                    }
                    cart.update(productId, newQuantity);
                    // Cập nhật lại tổng số lượng và tổng giá trị giỏ hàng
                    int totalQuantity = cart.getTotalQuantity();
                    double totalPrice = cart.getTotal();

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write("{\"newQuantity\": " + newQuantity + ", "
                            + "\"totalQuantity\": \"" + totalQuantity + "\", "
                            + "\"totalPrice\": \"" + totalPrice + "\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.write("{\"newQuantity\": 0, \"totalQuantity\": 0, \"totalPrice\": 0}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write("{\"newQuantity\": 0, \"totalQuantity\": 0, \"totalPrice\": 0}");
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("{\"newQuantity\": 0, \"totalQuantity\": 0, \"totalPrice\": 0}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"newQuantity\": 0, \"totalQuantity\": 0, \"totalPrice\": 0}");
        } finally {
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}