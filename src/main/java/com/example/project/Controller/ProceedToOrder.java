//package com.example.project.Controller;
//
//import com.example.project.Dao.HandlerOrderDB;
//import com.example.project.Model.OrderDetailTK;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//@WebServlet(name = "ProceedToOrder", value = "/proceed-to-order")
//public class ProceedToOrder extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        int idAddress = Integer.parseInt(req.getParameter("idAddress"));
//        int idShip = Integer.parseInt(req.getParameter("idShip"));
//        int idPayment = Integer.parseInt(req.getParameter("idPayment"));
//        String massage = req.getParameter("massage");
//        String voucher = req.getParameter("voucherCode");
//
//        HttpSession session = req.getSession();
//        int userID = (int) session.getAttribute("userID");
//        if(userID==0){
//            resp.sendRedirect("login.jsp");
//        }
//        List <OrderDetailTK> listOrderDetail = (List<OrderDetailTK>) session.getAttribute("shoppingCart");
//
//        HandlerOrderDB handlerOrderDB = new HandlerOrderDB();
//        handlerOrderDB.proceedToOrder(userID,idAddress,idShip,idPayment,massage,voucher, listOrderDetail);
//        handlerOrderDB.close();
//
//        PrintWriter out = resp.getWriter();
//        out.write("success");
//        out.flush();
//    }
//}
