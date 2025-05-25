package com.example.project.Dao;

import com.example.project.Model.Customer;
import com.example.project.Model.DashboardStats;
import com.example.project.Model.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {
    private final Dao dao;

    public DashboardDAO() {
        this.dao = new Dao();
    }

    public DashboardStats getDashboardStats() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DashboardStats stats = new DashboardStats();

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");

            String query = "SELECT " +
                    "(SELECT COUNT(*) FROM Customers) as total_customers, " +
                    "(SELECT COUNT(*) FROM Products) as total_products, " +
                    "(SELECT COUNT(*) FROM Orders WHERE MONTH(CreateDate) = MONTH(CURRENT_DATE)) as total_orders, " +
                    "(SELECT COUNT(*) FROM Products p " +
                    "INNER JOIN Inventorys i ON p.ProductID = i.ProductID " +
                    "WHERE i.Stock < 10) as low_stock_products, " +
                    "(SELECT SUM(FinalAmount) FROM Payments WHERE MONTH(CreateDate) = MONTH(CURRENT_DATE)) as total_revenue, " +
                    "(SELECT COUNT(*) FROM Orders WHERE Status = 'pending') as pending_orders, " +
                    "(SELECT AVG(Rating) FROM Reviews) as average_rating";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                stats.setTotalCustomers(rs.getInt("total_customers"));
                stats.setTotalProducts(rs.getInt("total_products"));
                stats.setTotalOrders(rs.getInt("total_orders"));
                stats.setLowStockProducts(rs.getInt("low_stock_products"));
                stats.setTotalIncome(rs.getInt("total_revenue"));
                stats.setPendingOrders(rs.getInt("pending_orders"));
                stats.setAverageRating(rs.getDouble("average_rating"));
            }

            return stats;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public List<OrderStatus> getRecentOrders() throws SQLException {
        List<OrderStatus> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String query = "SELECT o.OrderID, " +
                    "CONCAT(c.FirstName, ' ', c.LastName) as customer_name, " +
                    "p.FinalAmount as total_amount, o.Status as status " +
                    "FROM Orders o " +
                    "INNER JOIN Customers c ON o.UserID = c.UserID " +
                    "INNER JOIN Payments p ON o.OrderID = p.OrderID " +
                    "ORDER BY o.CreateDate DESC LIMIT 3";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderStatus order = new OrderStatus();
                order.setOrderId(rs.getInt("OrderID"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }

            return orders;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public List<Customer> getRecentCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String query = "SELECT c.CustomerID, c.FirstName, c.LastName, " +
                    "c.DateOfBirth, c.Gender " +
                    "FROM Customers c " +
                    "INNER JOIN Users u ON c.UserID = u.UserID " +
                    "ORDER BY u.CreateDate DESC LIMIT 3";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setFirstname(rs.getString("FirstName"));
                customer.setLastname(rs.getString("LastName"));
                customer.setDateOfBirth(rs.getDate("DateOfBirth"));
                boolean bGender = rs.getBoolean("Gender");

                // Map gender to "Nam" or "Nữ"
                if (bGender) {
                    customer.setGender("Nam");
                } else {
                    customer.setGender("Nữ");
                }
                customers.add(customer);
            }

            return customers;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public Map<String, Double> getMonthlyRevenue() throws SQLException {
        Map<String, Double> monthlyRevenue = new LinkedHashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String query = "SELECT DATE_FORMAT(p.CreateDate, '%M') as month, " +
                    "SUM(p.FinalAmount) as revenue " +
                    "FROM Payments p " +
                    "WHERE p.CreateDate >= DATE_SUB(CURRENT_DATE, INTERVAL 6 MONTH) " +
                    "GROUP BY DATE_FORMAT(p.CreateDate, '%M') " +
                    "ORDER BY p.CreateDate DESC";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                monthlyRevenue.put(rs.getString("month"), rs.getDouble("revenue"));
            }

            return monthlyRevenue;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}