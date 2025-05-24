package com.example.project.Dao;

import com.example.project.Model.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSalaryDAO {
    private final Dao dao;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Connection conn = null;

    public AddSalaryDAO(Dao dao) {
        this.dao = dao;
    }

    public boolean AddSalaryDAO(Salary salary) {
        boolean success = false;
        String query = "INSERT INTO Salaries (employee_name, gender, position, total_salary, is_paid) VALUES (?, ?, ?,? , ?, ?)";

        try {
            conn = dao.getConn();
            ps = conn.prepareStatement(query);

            ps.setString(1, salary.getEmployeeName());
            ps.setString(2, salary.getGender());
            ps.setString(3, salary.getPosition());
            ps.setInt(4,salary.getTotalSalary());
            ps.setDouble(5, salary.getTotalSalary());

            int rowsAffected = ps.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}