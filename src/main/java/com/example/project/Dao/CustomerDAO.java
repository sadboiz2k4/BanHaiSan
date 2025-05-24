package com.example.project.Dao;

import com.example.project.Model.Address;
import com.example.project.Model.Customer;
import com.example.project.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final Dao dao;

    public CustomerDAO() {
        this.dao = new Dao();
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            // Sửa câu query để chỉ lấy thông tin khách hàng và user
            String sql = "SELECT c.CustomerID, c.FirstName, c.LastName, c.DateOfBirth, c.Gender, " +
                    "u.UserID, u.Email, u.PhoneNumber, u.Username, u.Status,u.Rank ,u.Point " +
                    "FROM Customers c " +
                    "JOIN Users u ON c.UserID = u.UserID";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                // Lấy địa chỉ cho mỗi khách hàng
                List<Address> addresses = getCustomerAddresses(customer.getCustomerID());
                customer.getUser().setAddresses(addresses);
                customers.add(customer);
            }
            return customers;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public Customer getCustomerById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");

            String sql = "SELECT c.CustomerID, c.FirstName, c.LastName, c.DateOfBirth, c.Gender, " +
                    "u.UserID, u.Email, u.PhoneNumber, u.Username, u.Status,u.Rank, u.Point " +
                    "FROM Customers c " +
                    "JOIN Users u ON c.UserID = u.UserID " +
                    "WHERE c.CustomerID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                // Lấy địa chỉ cho khách hàng
                List<Address> addresses = getCustomerAddresses(id);
                customer.getUser().setAddresses(addresses);
                return customer;
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public List<Address> getCustomerAddresses(int customerId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Address> addresses = new ArrayList<>();

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");

            String sql = "SELECT a.*, au.IsPrimary " +
                    "FROM Address a " +
                    "JOIN AddressUsers au ON a.AddressID = au.AddressID " +
                    "JOIN Users u ON au.UserID = u.UserID " +
                    "JOIN Customers c ON u.UserID = c.UserID " +
                    "WHERE c.CustomerID = ? " +
                    "ORDER BY au.IsPrimary DESC";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                addresses.add(extractAddressFromResultSet(rs));
            }
            return addresses;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
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

        User user = new User();
        user.setUserID(rs.getInt("UserID"));
        user.setEmail(rs.getString("Email"));
        user.setPhoneNumber(rs.getString("PhoneNumber"));
        user.setUsername(rs.getString("Username"));
        user.setStatus(rs.getString("Status"));
        user.setRank(rs.getInt("Rank"));
        user.setPoint(rs.getInt("Point"));
        user.setAddresses(new ArrayList<>()); // Khởi tạo danh sách địa chỉ

        customer.setUser(user);
        return customer;
    }

    private Address extractAddressFromResultSet(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setAddressID(rs.getInt("AddressID"));
        address.setStreet(rs.getString("Street"));
        address.setWardOrCommune(rs.getString("WardOrCommune"));
        address.setDistrict(rs.getString("District"));
        address.setProvinceOrCity(rs.getString("ProvinceOrCity"));
        address.setPrimary(rs.getBoolean("IsPrimary"));
        return address;
    }

    public boolean deleteCustomer(int customerId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            conn.setAutoCommit(false);

            // Xóa từ AddressUsers trước
            String deleteAddressUsers = "DELETE au FROM AddressUsers au " +
                    "JOIN Users u ON au.UserID = u.UserID " +
                    "JOIN Customers c ON u.UserID = c.UserID " +
                    "WHERE c.CustomerID = ?";
            ps = conn.prepareStatement(deleteAddressUsers);
            ps.setInt(1, customerId);
            ps.executeUpdate();
            ps.close();

            // Sau đó xóa từ Customers
            String deleteCustomer = "DELETE FROM Customers WHERE CustomerID = ?";
            ps = conn.prepareStatement(deleteCustomer);
            ps.setInt(1, customerId);
            int result = ps.executeUpdate();

            conn.commit();
            return result > 0;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}