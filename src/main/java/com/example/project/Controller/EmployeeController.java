package com.example.project.Controller;

import com.example.project.Dao.EmployeeDAO;
import com.example.project.Model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "employee", urlPatterns = {"/employee", "/employee/*"})
public class EmployeeController extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all employees
                List<Employee> employees = employeeDAO.getAllEmployees();
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("/EmployeeManagement.jsp").forward(req, resp);
            } else {
                // Handle specific employee
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length >= 2) {
                    int employeeId = Integer.parseInt(pathParts[1]);
                    Employee employee = employeeDAO.getEmployeeById(employeeId);

                    if (employee != null) {
                        req.setAttribute("employee", employee);
                        req.getRequestDispatcher("/EmployeeDetails.jsp").forward(req, resp);
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in EmployeeController servlet: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.startsWith("/delete")) {
            try {
                int employeeId = Integer.parseInt(req.getParameter("id"));
                boolean deleted = employeeDAO.deleteEmployee(employeeId);

                if (deleted) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        // Add other POST operations here (create, update, etc.)
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}