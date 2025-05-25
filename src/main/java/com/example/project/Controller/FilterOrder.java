package com.example.project.Controller;

import com.example.project.Dao.HandlerOrderDB;
import com.example.project.Model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "FilterOrder", value = "/filter-order")
public class FilterOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        HttpSession session = req.getSession();
        int id = (int)session.getAttribute("userID");
        HandlerOrderDB handlerOrderDB = new HandlerOrderDB();
        if(status.equals("Tất cả")){
            req.setAttribute("listOrder", handlerOrderDB.getAllOrder(id));
            for (Order order : handlerOrderDB.getAllOrder(id)) {
                System.out.println(order.getOrderId());
            }
            req.getRequestDispatcher("/JspComForPersonal/order-comp.jsp").forward(req, resp);
        }else{
        req.setAttribute("listOrder", handlerOrderDB.filterStatusOrder(2,status));
        req.getRequestDispatcher("/JspComForPersonal/order-comp.jsp").forward(req, resp);
        }
        handlerOrderDB.close();


    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
