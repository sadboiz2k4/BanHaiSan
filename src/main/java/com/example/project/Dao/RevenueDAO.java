package com.example.project.Dao;

import com.example.project.Model.ChartData;
import com.example.project.Model.OrderStatus;
import com.example.project.Model.Product;
import com.example.project.Model.Revenue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueDAO {
    private final Dao dao = new Dao();

    public RevenueDAO() {

    }
    Connection conn = dao.getConn();
    public Revenue getRevenueStats() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Revenue revenue = new Revenue();

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");

            String statsQuery = "SELECT " +
                    "(SELECT COUNT(*) FROM Employees) as total_employees, " +
                    "(SELECT COUNT(*) FROM Products) as total_products, " +
                    "(SELECT COUNT(*) FROM Orders) as total_orders, " +
                    "(SELECT SUM(FinalAmount) FROM Payments) as total_income, " +
                    "(SELECT COUNT(*) FROM Products p " +
                    "INNER JOIN Inventorys i ON p.ProductID = i.ProductID " +
                    "WHERE i.Stock = 0 OR i.Status = 'Hết hàng') as out_of_stock_products";

            ps = conn.prepareStatement(statsQuery);
            rs = ps.executeQuery();

            if (rs.next()) {
                revenue.setTotalEmployees(rs.getInt("total_employees"));
                revenue.setTotalProducts(rs.getInt("total_products"));
                revenue.setTotalOrders(rs.getInt("total_orders"));
                revenue.setTotalIncome(rs.getDouble("total_income"));
                revenue.setOutOfStockProducts(rs.getInt("out_of_stock_products"));
                revenue.setNewEmployees(0);
            }

            return revenue;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    public List<Integer> allTotalPerMonth() throws SQLException {
        List<Integer> totalPerMonth = new ArrayList<>();
        for (int t = 1; t <= 12; t++) {
            totalPerMonth.add(totalOrderPerMonth(t));
        }
        return totalPerMonth;
    }
    public int totalOrderPerMonth(int month) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as total FROM ORDERS WHERE MONTH(CreateDate) = ?;");
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");

        }
        return 0;
    }
    public int monthlyRevenue(int month) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT SUM(FinalAmount) as total FROM Payments WHERE OrderID IN (SELECT OrderID FROM Orders WHERE MONTH(CreateDate) = ?);");
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }
    public List<Integer> annualRevenue() throws SQLException {
        List<Integer> totalPerYear = new ArrayList<>();
        for (int t = 1; t <= 12; t++) {
            totalPerYear.add(monthlyRevenue(t));
        }
        return totalPerYear;
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(new RevenueDAO().annualRevenue());
    }
    public List<Product> getBestSellingProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            // Updated column aliases to match JSP property names
            String query = "SELECT p.ProductID as productId, p.NameProduct as name, p.Price as price, " +
                    "c.Name as category, SUM(od.Quantity) as total_sold " +
                    "FROM Products p " +
                    "INNER JOIN OrderDetails od ON p.ProductID = od.ProductID " +
                    "INNER JOIN Categories c ON p.CategoriesID = c.CategoriesID " +
                    "INNER JOIN Orders o ON od.OrderID = o.OrderID " +
                    "WHERE o.Status = 'Completed' " +
                    "GROUP BY p.ProductID, p.NameProduct, p.Price, c.Name " +
                    "ORDER BY total_sold DESC LIMIT 10";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                // Updated to use the new column aliases
                product.setProductID(rs.getInt("productId"));
                product.setNameProduct(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setCategoryName(rs.getString("category"));
                products.add(product);
            }

            return products;
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
            String query = "SELECT o.OrderID, CONCAT(c.FirstName, ' ', c.LastName) as customer_name, " +
                    "p.NameProduct as product_name, od.Quantity, pay.FinalAmount as total_amount " +
                    "FROM Orders o " +
                    "INNER JOIN Customers c ON o.UserID = c.UserID " +
                    "INNER JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                    "INNER JOIN Products p ON od.ProductID = p.ProductID " +
                    "INNER JOIN Payments pay ON o.OrderID = pay.OrderID " +
                    "ORDER BY o.OrderID DESC LIMIT 10";  // Changed to OrderID for sorting

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderStatus order = new OrderStatus();
                order.setOrderId(rs.getInt("OrderID"));
                order.setCustomerName(rs.getString("customer_name"));
                order.setProductName(rs.getString("product_name"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setTotalAmount(rs.getInt("total_amount"));
                orders.add(order);
            }

            return orders;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    public List<Product> getOutOfStockProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String query = "SELECT p.ProductID, p.NameProduct, p.Price, i.Stock, c.Name as CategoryName " +
                    "FROM Products p " +
                    "INNER JOIN Inventorys i ON p.ProductID = i.ProductID " +
                    "INNER JOIN Categories c ON p.CategoriesID = c.CategoriesID " +
                    "WHERE i.Stock = 0 OR i.Status = 'Hết hàng'";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setNameProduct(rs.getString("NameProduct"));
                product.setPrice(rs.getInt("Price"));
                product.setStock(rs.getInt("Stock"));
                product.setCategoryName(rs.getString("CategoryName"));
                products.add(product);
            }

            return products;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    public List<ChartData> getMonthlyRevenueData() throws SQLException {
        List<ChartData> chartData = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String query = "SELECT MONTH(o.CreateDate) as month, " +
                    "COUNT(DISTINCT o.OrderID) as total_orders, " +
                    "SUM(p.FinalAmount) as revenue " +
                    "FROM Orders o " +
                    "INNER JOIN Payments p ON o.OrderID = p.OrderID " +
                    "WHERE YEAR(o.CreateDate) = YEAR(CURRENT_DATE) " +
                    "GROUP BY MONTH(o.CreateDate) " +
                    "ORDER BY month";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                ChartData data = new ChartData();
                data.setMonth(rs.getString("month"));
                data.setTotalOrders(rs.getInt("total_orders"));
                data.setRevenue(rs.getDouble("revenue"));
                chartData.add(data);
            }

            return chartData;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}