package com.example.project.Controller;

import com.example.project.Dao.SalaryDAO;
import com.example.project.Model.Salary;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "salary", urlPatterns = {"/salary", "/salary/*"})
public class SalaryController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SalaryController.class.getName());
    private SalaryDAO salaryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        salaryDAO = new SalaryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all salaries - Fixed method name here
                List<Salary> salaryList = salaryDAO.getAllSalaries();
                request.setAttribute("salaryList", salaryList);
                request.getRequestDispatcher("/Salary.jsp").forward(request, response);
            } else {
                // Rest of the code remains the same
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length >= 2) {
                    int employeeId = Integer.parseInt(pathParts[1]);
                    Salary salary = salaryDAO.getSalaryById(employeeId);

                    if (salary != null) {
                        request.setAttribute("salary", salary);
                        request.getRequestDispatcher("/Salary.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in SalaryController", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // Rest of the class implementation remains the same
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo != null) {
                switch (pathInfo) {
                    case "/update":
                        updateSalary(request, response);
                        break;
                    case "/delete":
                        deleteSalary(request, response);
                        break;
                    default:
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing POST request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void updateSalary(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Salary salary = extractSalaryFromRequest(request);
            boolean updated = salaryDAO.updateSalary(salary);

            if (updated) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating salary", e);
            throw new ServletException("Error updating salary", e);
        }
    }

    private void deleteSalary(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int employeeId = Integer.parseInt(request.getParameter("id"));
            boolean deleted = salaryDAO.deleteSalary(employeeId);

            if (deleted) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting salary", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Salary extractSalaryFromRequest(HttpServletRequest request) {
        Salary salary = new Salary();
        salary.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
        salary.setTotalSalary(Integer.parseInt(request.getParameter("totalSalary")));
        return salary;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}