//package com.example.project.Controller;
//
//import com.example.project.Dao.HandlerOrderDB;
//import com.example.project.Model.Voucher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(name = "VoucherCheckOrder", value = "/voucher-check")
//public class VoucherCheckOrder extends HttpServlet {
//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String voucherCode = req.getParameter("voucherCode");
//        HttpSession session = req.getSession();
//        int userID = (int) session.getAttribute("userID");
//
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//
//        HandlerOrderDB handlerOrderDB = new HandlerOrderDB();
//        if(handlerOrderDB.checkVoucher(userID,voucherCode)) {
//            Voucher voucher = handlerOrderDB.getVoucher(voucherCode);
//            out.print("<span style='display:none' id = 'voucher-discount-type'>" + voucher.getDiscountType() + "</span>");
//            out.print("<span id = 'voucher-discount-amount'>" + voucher.getDiscountValue() + "</span>");
//        }else{
//            out.print("<span style='display:none' id = 'voucher-discount-type'>null</span>");
//            out.print("<span id = 'voucher-discount-amount'>0</span>");
//        }
//        handlerOrderDB.close();
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//    }
//}