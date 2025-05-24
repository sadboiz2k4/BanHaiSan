//package com.example.project.Controller;
//
//import com.example.project.Dao.DaoProduct;
//import com.example.project.Dao.HandlerOrderDB;
//import com.example.project.Model.OrderDetailTK;
//import com.example.project.Model.ProductLarge;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@WebServlet(name = "ShoppingCartLoad", value = "/shopping-cart")
//public class ShoppingCartLoad extends HttpServlet {
//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        if(session.getAttribute("userID") == null){
//            System.out.println("userID = 0");
//            resp.sendRedirect("login");
//            return;
//        }
//        List<OrderDetailTK> listOrderDetail = (List<OrderDetailTK>) req.getSession().getAttribute("shoppingCart");
//        HandlerOrderDB handlerOrderDB = new HandlerOrderDB();
//
//        List<ProductLarge> listProduct = new ArrayList<>();
//        HashMap<Integer,Integer> mapProductIDWQuantity = new HashMap<>();
//        DaoProduct daoProduct = new DaoProduct();
//        int total = 0;
//        int totalQuantity = 0;
//        for(OrderDetailTK od : listOrderDetail){
//            System.out.println(od.getProductId()+"..........");
//            listProduct.add(daoProduct.getProductByID(od.getProductId()));
//            mapProductIDWQuantity.put(od.getProductId(), od.getQuantity());
//            total += od.getPriceProduct()*od.getQuantity();
//            totalQuantity += od.getQuantity();
//        }
//        req.setAttribute("listProduct", listProduct);
//        req.setAttribute("mapProductIDWQuantity", mapProductIDWQuantity);
//        req.setAttribute("total", total);
//        req.setAttribute("totalQuantity", totalQuantity);
//        handlerOrderDB.close();
//        daoProduct.close();
//        req.getRequestDispatcher("shopping-cart-page.jsp").forward(req, resp);
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//    }
//}
