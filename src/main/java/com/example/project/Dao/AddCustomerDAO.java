package com.example.project.Dao;

import com.example.project.Model.Address;
import com.example.project.Model.Customer;
import com.example.project.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCustomerDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private final Dao dao;
    private static final Logger LOGGER = Logger.getLogger(AddCustomerDAO.class.getName());

    public AddCustomerDAO(Dao dao) {
        this.dao = dao;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT c.customer_id, c.firstname, c.lastname, c.date_of_birth, " +
                "u.user_id, u.email, u.phone_number, u.rank, u.point, " +
                "a.address_id, a.street, a.ward_or_commune, a.district, a.province_or_city, au.is_primary " +
                "FROM Customers c " +
                "JOIN Users u ON c.user_id = u.user_id " +
                "LEFT JOIN Address_Users au ON u.user_id = au.user_id " +
                "LEFT JOIN Address a ON au.address_id = a.address_id " +
                "ORDER BY c.customer_id";

        try {
            conn = dao.getConn();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            int currentCustomerId = -1;
            Customer currentCustomer = null;
            User currentUser = null;

            while (rs.next()) {
                int customerId = rs.getInt("customer_id");

                if (currentCustomerId != customerId) {
                    currentUser = new User();
                    currentUser.setUserID(rs.getInt("user_id"));
                    currentUser.setEmail(rs.getString("email"));
                    currentUser.setPhoneNumber(rs.getString("phone_number"));
                    currentUser.setRank(rs.getInt("rank"));
                    currentUser.setPoint(rs.getInt("point"));
                    currentUser.setAddresses(new ArrayList<>());

                    currentCustomer = new Customer();
                    currentCustomer.setCustomerID(customerId);
                    currentCustomer.setFirstname(rs.getString("firstname"));
                    currentCustomer.setLastname(rs.getString("lastname"));
                    currentCustomer.setDateOfBirth(rs.getDate("date_of_birth"));
                    currentCustomer.setUser(currentUser);
                    currentCustomer.setUserID(currentUser.getUserID());

                    customers.add(currentCustomer);
                    currentCustomerId = customerId;
                }

                if (rs.getObject("address_id") != null) {
                    Address address = new Address();
                    address.setAddressID(rs.getInt("address_id"));
                    address.setStreet(rs.getString("street"));
                    address.setWardOrCommune(rs.getString("ward_or_commune"));
                    address.setDistrict(rs.getString("district"));
                    address.setProvinceOrCity(rs.getString("province_or_city"));
                    address.setPrimary(rs.getBoolean("is_primary"));
                    currentUser.getAddresses().add(address);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving customers", e);
        } finally {
            closeResources();
        }
        return customers;
    }

    public boolean addCustomer(Customer customer, User user) {
        boolean success = false;
        String getUserIdQuery = "SELECT MAX(user_id) + 1 AS next_id FROM Users";
        int nextUserId = 1;

        try {
            conn = dao.getConn();
            conn.setAutoCommit(false);

            // Get next user_id
            ps = conn.prepareStatement(getUserIdQuery);
            rs = ps.executeQuery();
            if (rs.next()) {
                nextUserId = rs.getInt("next_id");
            }
            user.setUserID(nextUserId);
            customer.setUserID(nextUserId);

            // Insert User with rank and point
            String userQuery = "INSERT INTO Users (user_id, email, phone_number, rank, point) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(userQuery);
            ps.setInt(1, nextUserId);
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setInt(4, user.getRank());
            ps.setInt(5, user.getPoint());

            int userRowsAffected = ps.executeUpdate();

            if (userRowsAffected > 0) {
                // Insert Customer
                String customerQuery = "INSERT INTO Customers (customer_id, user_id, firstname, lastname, date_of_birth) " +
                        "VALUES (?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(customerQuery);
                ps.setInt(1, customer.getCustomerID());
                ps.setInt(2, nextUserId);
                ps.setString(3, customer.getFirstname());
                ps.setString(4, customer.getLastname());
                ps.setDate(5, customer.getDateOfBirth());

                int customerRowsAffected = ps.executeUpdate();

                // Insert Addresses
                if (customerRowsAffected > 0 && user.getAddresses() != null && !user.getAddresses().isEmpty()) {
                    for (Address address : user.getAddresses()) {
                        String addressQuery = "INSERT INTO Address (street, ward_or_commune, district, province_or_city) " +
                                "VALUES (?, ?, ?, ?)";
                        ps = conn.prepareStatement(addressQuery, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, address.getStreet());
                        ps.setString(2, address.getWardOrCommune());
                        ps.setString(3, address.getDistrict());
                        ps.setString(4, address.getProvinceOrCity());

                        ps.executeUpdate();
                        rs = ps.getGeneratedKeys();

                        if (rs.next()) {
                            int addressId = rs.getInt(1);
                            String addressUserQuery = "INSERT INTO Address_Users (address_id, user_id, is_primary) " +
                                    "VALUES (?, ?, ?)";
                            ps = conn.prepareStatement(addressUserQuery);
                            ps.setInt(1, addressId);
                            ps.setInt(2, nextUserId);
                            ps.setBoolean(3, address.isPrimary());
                            ps.executeUpdate();
                        }
                    }
                }
                success = true;
            }

            if (success) {
                conn.commit();
                LOGGER.info("Customer added successfully with ID: " + customer.getCustomerID());
            } else {
                conn.rollback();
                LOGGER.warning("Failed to add customer with ID: " + customer.getCustomerID());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding customer", e);
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error rolling back transaction", ex);
            }
        } finally {
            closeResources();
        }
        return success;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error closing resources", e);
        }
    }
}