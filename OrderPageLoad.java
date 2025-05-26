package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Dao.HandlerAddressDB;
import com.example.project.Dao.HandlerPayment;
import com.example.project.Dao.HandlerShip;
import com.example.project.Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "OrderPageLoad", value = "/order-page-load")
public class OrderPageLoad extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("userID") == null){
            System.out.println("userID = 0");
            resp.sendRedirect("login");
            return;
        }
        int userID = (int) session.getAttribute("userID");
        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        DaoProduct daoProduct = new DaoProduct();

        // address primary
        AddressUser addressPrimary = handlerAddressDB.getAddressPrimary(userID);
        System.out.println(addressPrimary);
        req.setAttribute("addressPrimary", addressPrimary);

        //Primary payment
        HandlerPayment handlerPayment = new HandlerPayment();
        Payment paymentPrimary = handlerPayment.getPrimaryPayment();
        req.setAttribute("paymentPrimary", paymentPrimary);

        //List address of user
        List<AddressUser> listAddressUser = handlerAddressDB.getAllAddressUser(userID);
        req.setAttribute("listAddressUser", listAddressUser);

        //List order details
        List<OrderDetailTK> listOrderDetail = (List<OrderDetailTK>) session.getAttribute("shoppingCart");

        //List product
        HashMap<Integer,Integer> mapProductIDWQuantity = new HashMap<>();
        List<ProductLarge> listProduct = new ArrayList<>();
        for(OrderDetailTK od : listOrderDetail){
            listProduct.add(daoProduct.getProductByID(od.getProductId()));
            mapProductIDWQuantity.put(od.getProductId(), od.getQuantity());
        }
        req.setAttribute("mapProductIDWQuantity", mapProductIDWQuantity);
        req.setAttribute("listProduct", listProduct);

        int totalProduct = 0;
        for(OrderDetailTK od : listOrderDetail){
            totalProduct += od.getQuantity();
        }
        req.setAttribute("totalProduct", totalProduct);

        //List shipping method
        HandlerShip handlerShip = new HandlerShip();
        List<Ship> listShip = null;
        try {
            listShip = handlerShip.getAllShip();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("listShip", listShip);
        req.setAttribute("shipPrimary", handlerShip.getPrimaryShip());
        //List payment method
        List<Payment> listPayment = handlerPayment.getAllPayment();
        req.setAttribute("listPayment", listPayment);

        //total price
        int totalPrice = 0;
        for(OrderDetailTK od : listOrderDetail){
            totalPrice += daoProduct.getProductByID(od.getProductId()).getPrice() * od.getQuantity();
        }
        req.setAttribute("totalPrice", totalPrice);
        //cost ship
        Ship ship = handlerShip.getPrimaryShip();
        //discount ship
        int discountShip = 0;
        req.setAttribute("discountShip", discountShip);
        //discount total price
        int discount = 0;
        for(OrderDetailTK od : listOrderDetail){
            discount += daoProduct.getProductByID(od.getProductId()).getDiscountPrice() * od.getQuantity();
        }
        req.setAttribute("discount", discount);
        //total price after discount
        int finalTotal = totalPrice - discount;
        req.setAttribute("finalTotal", finalTotal);

        handlerPayment.close();
        handlerAddressDB.close();
        handlerShip.close();
        req.getRequestDispatcher("order-page.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
