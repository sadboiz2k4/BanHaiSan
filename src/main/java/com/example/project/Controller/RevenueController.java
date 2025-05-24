package com.example.project.Controller;

import com.example.project.Dao.RevenueDAO;
import com.example.project.Model.ChartData;
import com.example.project.Model.OrderStatus;
import com.example.project.Model.Product;
import com.example.project.Model.Revenue;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "revenue", urlPatterns = {"/revenue", "/revenue/*"})
public class RevenueController extends HttpServlet {
    private RevenueDAO revenueDAO;
    private static final Logger LOGGER = Logger.getLogger(RevenueController.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            revenueDAO = new RevenueDAO();
            LOGGER.info("RevenueDAO initialized successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error initializing RevenueDAO: {0}", e.getMessage());
            throw new ServletException("Failed to initialize RevenueDAO.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (revenueDAO == null) {
            LOGGER.severe("RevenueDAO is not initialized.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server configuration error.");
            return;
        }


        try {
            // Fetch data for revenue statistics
            Revenue revenueStats = revenueDAO.getRevenueStats();
            request.setAttribute("revenueStats", revenueStats);

            // Fetch best-selling products
            List<Product> bestSellers = revenueDAO.getBestSellingProducts();
            request.setAttribute("bestSellers", bestSellers);

            // Fetch recent orders
            List<OrderStatus> recentOrders = revenueDAO.getRecentOrders();
            request.setAttribute("orders", recentOrders);

            // Fetch monthly revenue chart data
            List<ChartData> chartData = revenueDAO.getMonthlyRevenueData();
            request.setAttribute("chartData", chartData);

            // Fetch out-of-stock products
            List<Product> outOfStockProducts = revenueDAO.getOutOfStockProducts();
            request.setAttribute("outOfStockProducts", outOfStockProducts);

            // Fetch order counts for chart
            List<Map<String, Object>> dataChart = new ArrayList<>();
            List<Integer> orderCounts = revenueDAO.allTotalPerMonth();
            List<Integer> annualRevenue = revenueDAO.annualRevenue();
            int month = 1;
            for (int item : revenueDAO.allTotalPerMonth()) {
                Map<String, Object> dataPoint = new HashMap<>();
                dataPoint.put("month","Tháng "+month); // "Tháng 1", "Tháng 2", ...
                dataPoint.put("revenue", annualRevenue.get(month-1)); // Doanh thu
                dataPoint.put("totalOrders", item); // Tổng đơn hàng
                dataChart.add(dataPoint);
                month++;
            }
            Gson gson = new Gson();
            String orderCountsJson = gson.toJson(dataChart);
            System.out.println(orderCountsJson);
            request.setAttribute("dataChart", orderCountsJson);


            if (revenueStats != null) {
                double totalOrders = revenueStats.getTotalOrders();
                double cancelledOrders = revenueStats.getCancelledOrders();
                double totalProducts = revenueStats.getTotalProducts();
                double outOfStockProductsCount = revenueStats.getOutOfStockProducts();

                double orderCompletionRate = (totalOrders > 0)
                        ? ((totalOrders - cancelledOrders) / totalOrders) * 100
                        : 0.0;
                double productAvailabilityRate = (totalProducts > 0)
                        ? ((totalProducts - outOfStockProductsCount) / totalProducts) * 100
                        : 0.0;

                request.setAttribute("orderCompletionRate", orderCompletionRate);
                request.setAttribute("productAvailabilityRate", productAvailabilityRate);
            }

            request.getRequestDispatcher("Revenue.jsp").forward(request, response);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error in RevenueController: {0}", e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching revenue data.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in RevenueController: {0}", e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }
}