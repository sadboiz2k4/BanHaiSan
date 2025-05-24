package com.example.project.Controller;

import com.example.project.Dao.OrderAdminDAO;
import com.example.project.Model.OrderStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "order-admin", urlPatterns = {"/order-admin", "/order-admin/*"})
public class OrderManagementController extends HttpServlet {
    private OrderAdminDAO orderAdminDao;

    @Override
    public void init() throws ServletException {
        super.init();
        orderAdminDao = new OrderAdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all orders
                List<OrderStatus> orders = orderAdminDao.getAllOrders();
                req.setAttribute("orders", orders);
                req.getRequestDispatcher("/OrderManagement.jsp").forward(req, resp);
            } else {
                // Handle specific order
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length >= 2) {
                    int orderId = Integer.parseInt(pathParts[1]);
                    OrderStatus order = orderAdminDao.getOrderById(orderId);

                    if (order != null) {
                        req.setAttribute("order", order);
                        req.getRequestDispatcher("/OrderManagement.jsp").forward(req, resp);
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in OrderController servlet: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.startsWith("/delete")) {
            try {
                int orderId = Integer.parseInt(req.getParameter("id"));
                boolean deleted = orderAdminDao.deleteOrder(orderId);

                if (deleted) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}