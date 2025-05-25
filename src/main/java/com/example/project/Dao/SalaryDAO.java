package com.example.project.Dao;

import com.example.project.Model.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {
    private final Dao dao;

    public SalaryDAO() {
        this.dao = new Dao();
    }

    public List<Salary> getAllSalaries() throws SQLException {
        List<Salary> salaryList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT e.EmployeeID, e.LastName, e.FirstName, e.Position, e.Gender, " +
                    "e.TotalSalary " +
                    "FROM Employees e " +
                    "ORDER BY e.EmployeeID";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                salaryList.add(extractSalaryFromResultSet(rs));
            }
            return salaryList;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public Salary getSalaryById(int employeeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "SELECT e.EmployeeID, e.LastName, e.FirstName, e.Position, e.Gender, " +
                    "e.TotalSalary " +
                    "FROM Employees e " +
                    "WHERE e.EmployeeID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractSalaryFromResultSet(rs);
            }
            return null;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public boolean updateSalary(Salary salary) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "UPDATE Employees SET TotalSalary = ? WHERE EmployeeID = ?";

            ps = conn.prepareStatement(sql);
            ps.setDouble(1, salary.getTotalSalary());
            ps.setInt(2, salary.getEmployeeId());

            return ps.executeUpdate() > 0;
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private Salary extractSalaryFromResultSet(ResultSet rs) throws SQLException {
        Salary salary = new Salary();
        salary.setEmployeeId(rs.getInt("EmployeeID"));
        salary.setEmployeeName(rs.getString("LastName") + " " + rs.getString("FirstName"));
        salary.setPosition(rs.getString("Position"));
        boolean bGender = rs.getBoolean("Gender");
        salary.setTotalSalary(rs.getInt("TotalSalary"));

        // Map gender to "Nam" or "Nữ"
        if (bGender) {
            salary.setGender("Nam");
        }else{
            salary.setGender("Nữ");
        }
        return salary;
    }

    public boolean deleteSalary(int employeeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dao.getConnection("jdbc:mysql://localhost/osdb", "root", "");
            String sql = "DELETE FROM Employees WHERE EmployeeID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);

            return ps.executeUpdate() > 0;
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}