package com.example.project.Controller;

import com.example.project.Dao.AddSalaryDAO;
import com.example.project.Dao.Dao;
import com.example.project.Model.Salary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "addsalary", urlPatterns = {"/addsalary"})
public class AddSalaryController extends HttpServlet {
    private AddSalaryDAO salaryDAO;
    private static final Logger LOGGER = Logger.getLogger(AddSalaryController.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        salaryDAO = new AddSalaryDAO(new Dao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/AddSalary.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // Create new Salary object
            Salary salary = new Salary();

            // Get and validate employee name
            String employeeName = request.getParameter("employeeName");
            if (employeeName == null || employeeName.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên nhân viên không được để trống.");
            }
            salary.setEmployeeName(employeeName.trim());

            // Get and validate gender
            String gender = request.getParameter("gender");
            if (gender == null || gender.trim().isEmpty()) {
                throw new IllegalArgumentException("Giới tính không được để trống.");
            }
            salary.setGender(gender);

            // Get and validate position
            String position = request.getParameter("position");
            if (position == null || position.trim().isEmpty()) {
                throw new IllegalArgumentException("Chức vụ không được để trống.");
            }
            salary.setPosition(position);

            // Get and validate total salary
            String totalSalaryStr = request.getParameter("baseSalary");
            if (totalSalaryStr == null || totalSalaryStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Tiền lương không được để trống.");
            }
            try {
                int totalSalary = Integer.parseInt(totalSalaryStr);
                if (totalSalary < 0) {
                    throw new IllegalArgumentException("Tiền lương không được âm.");
                }
                salary.setTotalSalary(totalSalary);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Tiền lương phải là số.");
            }

            // Save to database
            boolean success = salaryDAO.AddSalaryDAO(salary);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/salary");
            } else {
                request.setAttribute("error", "Không thể thêm thông tin lương. Vui lòng thử lại.");
                request.getRequestDispatcher("/AddSalary.jsp").forward(request, response);
            }

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Lỗi dữ liệu đầu vào: ", e);
            request.setAttribute("error", "Lỗi dữ liệu đầu vào: " + e.getMessage());
            request.getRequestDispatcher("/AddSalary.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định: ", e);
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("/AddSalary.jsp").forward(request, response);
        }
    }
}