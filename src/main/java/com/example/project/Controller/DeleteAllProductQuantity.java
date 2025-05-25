package com.example.project.Controller;

import com.example.project.Model.OrderDetailTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeleteAllProductQuantity", value = "/delete-all-product-quantity")
public class DeleteAllProductQuantity extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderDetailTK> shoppingCart = (List<OrderDetailTK>) req.getSession().getAttribute("shoppingCart");
        if(shoppingCart == null){
            return;
        }
        int productID = Integer.parseInt(req.getParameter("productID"));
        int[] isExist = OrderDetailTK.isExistProductID(shoppingCart, productID);
        if(isExist[0] == 0){
            return;
        }else{
            shoppingCart.remove(isExist[2]);
        }
        PrintWriter out = resp.getWriter();
        out.println("success");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
