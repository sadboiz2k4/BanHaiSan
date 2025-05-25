package com.example.project.Controller;

import com.example.project.Dao.AddEmployeeDAO;
import com.example.project.Dao.Dao;
import com.example.project.Model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;

@WebServlet(name = "addEmployee", urlPatterns = {"/addEmployee"})
public class AddEmployeeController extends HttpServlet {
    private AddEmployeeDAO addEmployeeDAO;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        addEmployeeDAO = new AddEmployeeDAO(new Dao());
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Get the next available employee ID
            int nextEmployeeId = addEmployeeDAO.getNextEmployeeId();
            request.setAttribute("nextEmployeeId", nextEmployeeId);
            request.getRequestDispatcher("/AddEmployee.jsp").forward(request, response);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "list":
                try {
                    var employees = addEmployeeDAO.getAllEmployees();
                    String jsonEmployees = gson.toJson(employees);
                    response.getWriter().write(jsonEmployees);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write(gson.toJson(
                            new ApiResponse(false, "Error loading list: " + e.getMessage())));
                }
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(gson.toJson(
                        new ApiResponse(false, "Invalid action parameter")));
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try {
            Employee employee = createEmployeeFromRequest(request);
            String employeeIdStr = request.getParameter("EmployeeId");
            boolean isUpdate = false;

            if (employeeIdStr != null && !employeeIdStr.isEmpty()) {
                employee.setEmployeeID(Integer.parseInt(employeeIdStr));
                isUpdate = true;
            }

            boolean success;
            if (isUpdate) {
                success = addEmployeeDAO.updateEmployee(employee);
            } else {
                success = addEmployeeDAO.addEmployee(employee);
            }

            if (success) {
                String message = isUpdate ? "Cập nhật nhân viên thành công!" : "Thêm nhân viên thành công!";
                String jsonResponse = gson.toJson(new ApiResponse(true, message));
                response.getWriter().write(jsonResponse);
            } else {
                String action = isUpdate ? "cập nhật" : "thêm";
                String jsonResponse = gson.toJson(new ApiResponse(false, "Không thể " + action + " nhân viên!"));
                response.getWriter().write(jsonResponse);
            }

        } catch (Exception e) {
            String errorResponse = gson.toJson(
                    new ApiResponse(false, "Lỗi xử lý: " + e.getMessage()));
            response.getWriter().write(errorResponse);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Employee createEmployeeFromRequest(HttpServletRequest request) throws Exception {
        Employee employee = new Employee();

        // Set full name (combining first and last name)
        String fullName = request.getParameter("fullName");
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ và tên không được để trống");
        }
        String[] nameParts = fullName.trim().split("\\s+", 2);
        employee.setFirstName(nameParts[0]);
        employee.setLastName(nameParts.length > 1 ? nameParts[1] : "");

        // Set email
        String email = request.getParameter("email");
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        employee.setEmail(email);

        // Set phone number
        String phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber == null || !phoneNumber.matches("^[0-9]{10,11}$")) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }
        employee.setPhoneNumber(phoneNumber);

        // Set other fields
        employee.setGender(request.getParameter("gender"));
        employee.setPosition(request.getParameter("position"));
        employee.setAddress(request.getParameter("address"));

        // Set birth date
        String birthDateStr = request.getParameter("birthDate");
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(birthDateStr);
            employee.setBirthDate(new Date(parsedDate.getTime()));
        }

        return employee;
    }

    private static class ApiResponse {
        private final boolean success;
        private final String message;

        public ApiResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}