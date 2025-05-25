package com.example.project.Controller;

import com.example.project.Model.OrderDetailTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "RemoveProductToShoppingCart", value = "/remove-product-to-shopping-cart")
public class RemoveProductToShoppingCart extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("productID"));
        HttpSession session = req.getSession();
        List<OrderDetailTK> shoppingCart = (List<OrderDetailTK>) session.getAttribute("shoppingCart");
        if(shoppingCart == null){
            return;
        }else{
            int [] isExist = OrderDetailTK.isExistProductID(shoppingCart,productID);
            if(isExist[0] == 0){
                return;
            }else{
                if(isExist[1] == 1){
                    shoppingCart.remove(isExist[2]);
                }else{
                    shoppingCart.get(isExist[2]).setQuantity(isExist[1]-1);
                }
            }

        }

        session.setAttribute("shoppingCart",shoppingCart);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}