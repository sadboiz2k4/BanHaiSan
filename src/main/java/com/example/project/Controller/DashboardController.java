package com.example.project.Controller;

import com.example.project.Dao.DashboardDAO;
import com.example.project.Model.Customer;
import com.example.project.Model.DashboardStats;
import com.example.project.Model.OrderStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "dashboard", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {
    private DashboardDAO dashboardDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        dashboardDAO = new DashboardDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get dashboard statistics
            DashboardStats stats = dashboardDAO.getDashboardStats();
            request.setAttribute("stats", stats);

            // Get recent orders
            List<OrderStatus> recentOrders = dashboardDAO.getRecentOrders();
            request.setAttribute("recentOrders", recentOrders);

            // Get recent customers
            List<Customer> recentCustomers = dashboardDAO.getRecentCustomers();
            request.setAttribute("recentCustomers", recentCustomers);

            // Get monthly revenue data for chart
            Map<String, Double> monthlyRevenue = dashboardDAO.getMonthlyRevenue();
            request.setAttribute("monthlyRevenue", monthlyRevenue);

            request.getRequestDispatcher("Dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            System.out.println("Error in DashboardController: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
