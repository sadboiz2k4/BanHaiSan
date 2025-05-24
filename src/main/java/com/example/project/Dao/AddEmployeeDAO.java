package com.example.project.Dao;

import com.example.project.Model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeDAO {
    private final Dao dao;

    public AddEmployeeDAO(Dao dao) {
        this.dao = dao;
    }

    public int getNextEmployeeId() {
        Connection conn = null;
        try {
            conn = dao.getConn();
            String query = "SELECT MAX(OSID) + 1 as nextId FROM Employees";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int nextId = rs.getInt("nextId");
                    return nextId > 0 ? nextId : 1; // Return 1 if table is empty
                }
                return 1; // Default to 1 if no results
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 1; // Return 1 in case of error
        } finally {
            closeConnection(conn);
        }
    }

    // Add the new method to get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dao.getConn();
            String query = """
                
                SELECT e.*, a.Street, a.WardOrcommune, a.District, a.ProvinceOrCity 
                FROM Employees e 
                LEFT JOIN AddressEmployees ae ON e.OSID = ae.OSID 
                LEFT JOIN Address a ON ae.AddressID = a.AddressID
                ORDER BY e.OSID
                """;

            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setOSID(rs.getInt("OSID"));
                    employee.setFirstName(rs.getString("FirstName"));
                    employee.setLastName(rs.getString("LastName"));
                    employee.setPosition(rs.getString("Position"));
                    employee.setEmail(rs.getString("Email"));
                    employee.setPhoneNumber(rs.getString("PhoneNumber"));
                    employee.setBirthDate(rs.getDate("DateOfBirth"));
                    employee.setGender(rs.getString("Gender"));

                    // Combine address parts
                    String address = String.format("%s, %s, %s, %s",
                            nullToEmpty(rs.getString("Street")),
                            nullToEmpty(rs.getString("WardOrcommune")),
                            nullToEmpty(rs.getString("District")),
                            nullToEmpty(rs.getString("ProvinceOrCity"))
                    ).replaceAll(", $", ""); // Remove trailing comma if any

                    employee.setAddress(address);
                    employees.add(employee);
                }
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list in case of error
        } finally {
            closeConnection(conn);
        }
    }

    private String nullToEmpty(String str) {
        return str == null ? "" : str.

    trim();
    }
    public boolean addEmployee(Employee employee) {
        Connection conn = null;
        boolean success = false;

        try {
            conn = dao.getConn();
            if (conn == null) {
                throw new SQLException("Unable to establish database connection");
            }

            conn.setAutoCommit(false);  // Start transaction

            // Insert into Employees table
            String employeeQuery = "INSERT INTO Employees (OSID, FirstName, LastName, Position, Email, PhoneNumber, DateOfBirth, Gender, TotalSalary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(employeeQuery)) {
                setEmployeeParameters(ps, employee);
                ps.executeUpdate();
            }

            // Handle address
            int addressId = handleAddress(conn, employee, true);

            // Insert into AddressEmployees table
            insertAddressEmployee(conn, employee.getOSID(), addressId);

            conn.commit();  // Commit transaction
            success = true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback on error
                } catch (SQLException ex) {
                    // Log rollback error but don't throw it
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            success = false;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);  // Reset auto-commit
                    conn.close();  // Close connection
                } catch (SQLException e) {
                    // Log closing error but don't throw it
                    e.printStackTrace();
                }
            }
        }

    return success;
    }
    public boolean updateEmployee(Employee employee) {
        Connection conn = null;
        PreparedStatement psEmployee = null;
        boolean success = false;

        try {
            // Lấy connection từ pool
            conn = dao.getConn();
            if (conn == null) {
                throw new SQLException("Unable to establish database connection");
            }

            // Bắt đầu transaction
            conn.setAutoCommit(false);

            // Update thông tin employee
            String employeeQuery = "UPDATE Employees SET FirstName = ?, LastName = ?, Position = ?, " +
                    "Email = ?, PhoneNumber = ?, DateOfBirth = ?, Gender = ? " +
                    "WHERE OSID = ?";

            psEmployee = conn.prepareStatement(employeeQuery);
            setEmployeeParameters(psEmployee, employee);
            psEmployee.setInt(8, employee.getOSID());
            psEmployee.executeUpdate();

            // Xử lý cập nhật địa chỉ
            String[] addressParts = parseAddress(employee.getAddress());
            int addressId = getAddressId(conn, employee.getOSID());

            if (addressId > 0) {
                // Update địa chỉ hiện có
                try (PreparedStatement psAddress = conn.prepareStatement(
                        "UPDATE Address SET Street = ?, WardOrcommune = ?, District = ?, ProvinceOrCity = ? WHERE AddressID = ?")) {
                    setAddressParameters(psAddress, addressParts);
                    psAddress.setInt(5, addressId);
                    psAddress.executeUpdate();
                }

                // Cập nhật ngày cập nhật
                try (PreparedStatement psDate = conn.prepareStatement(
                        "UPDATE AddressEmployees SET LastUpdateDate = CURRENT_DATE WHERE OSID = ? AND AddressID = ?")) {
                    psDate.setInt(1, employee.getOSID());
                    psDate.setInt(2, addressId);
                    psDate.executeUpdate();
                }
            }

            // Commit transaction nếu mọi thứ OK
            conn.commit();
            success = true;

        } catch (SQLException e) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (psEmployee != null) {
                    psEmployee.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    private void setEmployeeParameters(PreparedStatement ps, Employee employee) throws SQLException {
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setString(3, employee.getPosition());
        ps.setString(4, employee.getEmail());
        ps.setString(5, employee.getPhoneNumber());
        ps.setDate(6, employee.getBirthDate());
        ps.setString(7, employee.getGender());
    }
    private int handleAddress(Connection conn, Employee employee, boolean isNew) throws SQLException {
        String[] addressParts = parseAddress(employee.getAddress());

        if (isNew) {
            return insertAddress(conn, addressParts);
        } else {
            int addressId = getAddressId(conn, employee.getOSID());
            if (addressId > 0) {
                updateAddress(conn, addressId, addressParts);
            }
            return addressId;
        }
    }

    private int insertAddress(Connection conn, String[] addressParts) throws SQLException {
        String addressQuery = "INSERT INTO Address (Street, WardOrcommune, District, ProvinceOrCity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(addressQuery, Statement.RETURN_GENERATED_KEYS)) {
            setAddressParameters(ps, addressParts);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("Failed to get generated address ID");
            }
        }
    }

    private void updateAddress(Connection conn, int addressId, String[] addressParts) throws SQLException {
        String addressQuery = "UPDATE Address SET Street = ?, WardOrcommune = ?, District = ?, ProvinceOrCity = ? WHERE AddressID = ?";
        try (PreparedStatement ps = conn.prepareStatement(addressQuery)) {
            setAddressParameters(ps, addressParts);
            ps.setInt(5, addressId);
            ps.executeUpdate();
        }
    }

    private void setAddressParameters(PreparedStatement ps, String[] addressParts) throws SQLException {
        for (int i = 0; i < 4; i++) {
            ps.setString(i + 1, addressParts[i]);
        }
    }

    private void insertAddressEmployee(Connection conn, int osid, int addressId) throws SQLException {
        String addressEmpQuery = "INSERT INTO AddressEmployees (OSID, AddressID, CreateDate, LastUpdateDate) VALUES (?, ?, CURRENT_DATE, CURRENT_DATE)";
        try (PreparedStatement ps = conn.prepareStatement(addressEmpQuery)) {
            ps.setInt(1, osid);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    private void updateAddressEmployeeDate(Connection conn, int osid, int addressId) throws SQLException {
        String updateDateQuery = "UPDATE AddressEmployees SET LastUpdateDate = CURRENT_DATE WHERE OSID = ? AND AddressID = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateDateQuery)) {
            ps.setInt(1, osid);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    private int getAddressId(Connection conn, int osid) throws SQLException {
        String query = "SELECT AddressID FROM AddressEmployees WHERE OSID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, osid);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("AddressID") : -1;
            }
        }
    }

    private String[] parseAddress(String address) {
        String[] parts = address.split(",");
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            result[i] = i < parts.length ? parts[i].trim() : "";
        }
        return result;
    }

    private void handleTransactionError(Connection conn, SQLException e) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);  // Reset auto-commit to true
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }