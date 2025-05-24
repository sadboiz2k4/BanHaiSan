package com.example.project.Controller;

import com.example.project.Dao.AddCustomerDAO;
import com.example.project.Dao.Dao;
import com.example.project.Model.Address;
import com.example.project.Model.Customer;
import com.example.project.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "addcustomer", urlPatterns = {"/addcustomer"})
public class AddCustomerController extends HttpServlet {
    private AddCustomerDAO customerDAO;
    private static final Logger LOGGER = Logger.getLogger(AddCustomerController.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        customerDAO = new AddCustomerDAO(new Dao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/AddCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // Tạo đối tượng User với các thông tin cơ bản
            User user = new User();
            user.setEmail(request.getParameter("email"));
            user.setPhoneNumber(request.getParameter("phoneNumber"));

            // Xử lý status (rank) và point
            String status = request.getParameter("status");
            String point = request.getParameter("point");

            if (status != null && !status.isEmpty()) {
                user.setRank(Integer.parseInt(status));
            } else {
                user.setRank(0); // Default rank
            }

            if (point != null && !point.isEmpty()) {
                user.setPoint(Integer.parseInt(point));
            } else {
                user.setPoint(0); // Default point
            }

            // Xử lý địa chỉ
            List<Address> addresses = new ArrayList<>();
            String streetAddress = request.getParameter("address");
            if (streetAddress != null && !streetAddress.trim().isEmpty()) {
                Address address = new Address();
                address.setStreet(streetAddress);
                address.setWardOrCommune(request.getParameter("wardOrCommune"));
                address.setDistrict(request.getParameter("district"));
                address.setProvinceOrCity(request.getParameter("provinceOrCity"));
                address.setPrimary(true);
                addresses.add(address);
            }
            user.setAddresses(addresses);

            // Tạo đối tượng Customer
            Customer customer = new Customer();
            String customerID = request.getParameter("customerID");

            if (customerID != null && !customerID.trim().isEmpty()) {
                customer.setCustomerID(Integer.parseInt(customerID.replace("#KH", "")));
            } else {
                throw new IllegalArgumentException("Mã khách hàng không hợp lệ.");
            }

            // Xử lý tên
            String fullName = request.getParameter("fullname");
            if (fullName != null && !fullName.trim().isEmpty()) {
                String[] nameParts = fullName.split("\\s+");
                if (nameParts.length > 1) {
                    customer.setFirstname(nameParts[nameParts.length - 1]);
                    customer.setLastname(String.join(" ",
                            Arrays.copyOfRange(nameParts, 0, nameParts.length - 1)));
                } else {
                    customer.setFirstname(fullName);
                    customer.setLastname("");
                }
            }

            // Xử lý ngày sinh
            String dateStr = request.getParameter("dateOfBirth");
            if (dateStr != null && !dateStr.isEmpty()) {
                customer.setDateOfBirth(Date.valueOf(dateStr));
            }

            customer.setUser(user);

            // Lưu vào database
            boolean success = customerDAO.addCustomer(customer, user);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/customer");
            } else {
                request.setAttribute("error", "Không thể thêm khách hàng. Vui lòng thử lại.");
                request.getRequestDispatcher("/AddCustomer.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Lỗi dữ liệu đầu vào: ", e);
            request.setAttribute("error", "Lỗi dữ liệu đầu vào: " + e.getMessage());
            request.getRequestDispatcher("/AddCustomer.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định: ", e);
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("/AddCustomer.jsp").forward(request, response);
        }
    }
}