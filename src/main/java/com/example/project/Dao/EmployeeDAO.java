package com.example.project.Dao;

import com.example.project.Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAO {
    private final Dao dao;

    public EmployeeDAO() {
        this.dao = new Dao();
    }

    public List<Employee> getAllEmployees() throws SQLException {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT DISTINCT e.*, " +
                    "a.Street, a.WardOrcommune, a.District, a.ProvinceOrCity, " +
                    "av.ImgURL, av.AltText " +
                    "FROM Employees e " +
                    "LEFT JOIN AddressEmployees ae ON e.OSID = ae.OSID " +
                    "LEFT JOIN Address a ON ae.AddressID = a.AddressID " +
                    "LEFT JOIN AvatarUsers av ON e.EmployeeID = av.UserID AND av.IsActive = 1 " +
                    "GROUP BY e.EmployeeID";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeID");
                if (!employeeMap.containsKey(employeeId)) {
                    employeeMap.put(employeeId, extractEmployeeFromResultSet(rs));
                }
            }
            return new ArrayList<>(employeeMap.values());
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT e.*, " +
                    "a.Street, a.WardOrcommune, a.District, a.ProvinceOrCity, " +
                    "av.ImgURL, av.AltText " +
                    "FROM Employees e " +
                    "LEFT JOIN AddressEmployees ae ON e.OSID = ae.OSID " +
                    "LEFT JOIN Address a ON ae.AddressID = a.AddressID " +
                    "LEFT JOIN AvatarUsers av ON e.EmployeeID = av.UserID AND av.IsActive = 1 " +
                    "WHERE e.EmployeeID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractEmployeeFromResultSet(rs);
            }
            return null;
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt("EmployeeID"));
        employee.setOSID(rs.getInt("OSID"));
        employee.setFirstName(rs.getString("FirstName"));
        employee.setLastName(rs.getString("LastName"));
        employee.setPosition(rs.getString("Position"));
        employee.setEmail(rs.getString("Email"));
        employee.setPhoneNumber(rs.getString("PhoneNumber"));
        employee.setBirthDate(rs.getDate("DateOfBirth"));
        boolean bGender = rs.getBoolean("Gender");

        // Map gender to "Nam" or "Nữ"
        if (bGender) {
            employee.setGender("Nam");
        } else {
            employee.setGender("Nữ");
        }
        // Build address string only if Street is not null
        String street = rs.getString("Street");
        String ward = rs.getString("WardOrcommune");
        String district = rs.getString("District");
        String province = rs.getString("ProvinceOrCity");

        if (street != null && ward != null && district != null && province != null) {
            String address = String.format("%s, %s, %s, %s",
                    street.trim(),
                    ward.trim(),
                    district.trim(),
                    province.trim()
            );
            // Only set if we have a complete address
            if (!address.contains("null")) {
                employee.setAddress(address);
            }
        }

        return employee;
    }

    public boolean deleteEmployee(int employeeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            int result = ps.executeUpdate();
            return result > 0;
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}