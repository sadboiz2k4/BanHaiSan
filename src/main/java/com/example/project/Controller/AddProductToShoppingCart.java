package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.OrderDetailTK;
import com.example.project.Model.ProductLarge;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProductToShoppingCart", value = "/add-product-to-shopping-cart")
public class AddProductToShoppingCart extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PrintWriter out = resp.getWriter();
            int productID = Integer.parseInt(req.getParameter("productID"));

            HttpSession session = req.getSession();
            DaoProduct daoProduct = new DaoProduct();
            ProductLarge product = daoProduct.getProductByID(productID);

            int discountPrice = product.getDiscountPrice();

            if (session.getAttribute("userID") == null) {
                out.write("login");
                out.flush();
                return;
            }
            List<OrderDetailTK> shoppingCart = (List<OrderDetailTK>) session.getAttribute("shoppingCart");
            if (shoppingCart == null) {
                shoppingCart = new ArrayList<>();
                shoppingCart.add(new OrderDetailTK(discountPrice, 1, productID));
            } else {
                int[] isExist = OrderDetailTK.isExistProductID(shoppingCart, productID);
                if (isExist[0] == 1) {
                    OrderDetailTK orderDetail = shoppingCart.get(isExist[2]);
                    orderDetail.setQuantity(isExist[1] + 1);
                    shoppingCart.set(isExist[2], orderDetail);
                } else {

                    shoppingCart.add(new OrderDetailTK(discountPrice, 1, productID));
                }

            }
            System.out.println(shoppingCart);
            session.setAttribute("shoppingCart", shoppingCart);
            resp.setContentType("text/plain");
            out.print("success");
            out.flush();
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid parameters");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Server error occurred");
        }
    }
}