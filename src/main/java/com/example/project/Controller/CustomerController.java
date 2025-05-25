package com.example.project.Controller;

import com.example.project.Dao.CustomerDAO;
import com.example.project.Model.Address;
import com.example.project.Model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "customer", urlPatterns = {"/customer", "/customer/*"})
public class CustomerController extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all customers
                List<Customer> customers = customerDAO.getAllCustomers();

                // Get addresses for each customer
                for (Customer customer : customers) {
                    List<Address> addresses = customerDAO.getCustomerAddresses(customer.getCustomerID());
                    customer.getUser().setAddresses(addresses);
                }

                req.setAttribute("customers", customers);
                req.getRequestDispatcher("/Customer.jsp").forward(req, resp);
            } else {
                // Handle specific customer
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length >= 2) {
                    int customerId = Integer.parseInt(pathParts[1]);
                    Customer customer = customerDAO.getCustomerById(customerId);

                    if (customer != null) {
                        List<Address> addresses = customerDAO.getCustomerAddresses(customerId);
                        customer.getUser().setAddresses(addresses);
                        req.setAttribute("customer", customer);
                        req.getRequestDispatcher("/CustomerDetails.jsp").forward(req, resp);
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in CustomerController servlet: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.startsWith("/delete")) {
            try {
                int customerId = Integer.parseInt(req.getParameter("id"));
                boolean deleted = customerDAO.deleteCustomer(customerId);

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