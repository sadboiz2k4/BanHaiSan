//package com.example.project.Controller;
//
//import com.example.project.Dao.AddOrderDAO;
//import com.example.project.Dao.Dao;
//import com.example.project.Model.OrderStatus;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet(name = "addorder", urlPatterns = {"/addorder"})
//public class AddOrderController extends HttpServlet {
//    private AddOrderDAO orderDAO;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
////        orderDAO = new AddOrderDAO(new Dao());
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Forward to the add order form page
//        request.getRequestDispatcher("/AddOrder.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Set encoding for Vietnamese characters
//            request.setCharacterEncoding("UTF-8");
//
//            // Create OrderStatus object
//            OrderStatus order = new OrderStatus();
//
//            // Get orderId from form
//            String orderIdStr = request.getParameter("orderId");
//            if (orderIdStr != null && !orderIdStr.trim().isEmpty()) {
//                order.setOrderId(Integer.parseInt(orderIdStr));
//            } else {
//                throw new IllegalArgumentException("Order ID is required.");
//            }
//
//            // Set other order properties
//            order.setCustomerName(request.getParameter("customerName"));
//            order.setProductName(request.getParameter("productName"));
//
//            // Parse and set quantity
//            String quantityStr = request.getParameter("quantity");
//            if (quantityStr != null && !quantityStr.trim().isEmpty()) {
//                order.setQuantity(Integer.parseInt(quantityStr));
//            }
//
//            // Parse and set total amount
//            String totalPriceStr = request.getParameter("totalPrice");
//            if (totalPriceStr != null && !totalPriceStr.trim().isEmpty()) {
//                order.setTotalAmount(Integer.parseInt(totalPriceStr));
//            }
//
//            // Set status
//            String status = request.getParameter("status");
//            if (status != null && !status.trim().isEmpty()) {
//                // Convert status to proper format if needed
//                switch (status.toLowerCase()) {
//                    case "pending":
//                        order.setStatus("Pending");
//                        break;
//                    case "shipping":
//                        order.setStatus("Shipping");
//                        break;
//                    case "completed":
//                        order.setStatus("Completed");
//                        break;
//                    case "cancelled":
//                        order.setStatus("Cancelled");
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Invalid status value");
//                }
//            }
//
//            // Save to database
////            boolean success = orderDAO.AddOrderDAO(order);
//
////            if (success) {
////                // Redirect to order management page after successful addition
////                response.sendRedirect(request.getContextPath() + "/order-admin");
////            } else {
////                request.setAttribute("error", "Could not add order. Please try again.");
////                request.getRequestDispatcher("/AddOrder.jsp").forward(request, response);
////            }
////
////        } catch (IllegalArgumentException e) {
////            request.setAttribute("error", "Input data error: " + e.getMessage());
////            request.getRequestDispatcher("/AddOrder.jsp").forward(request, response);
////        } catch (Exception e) {
////            e.printStackTrace();
////            request.setAttribute("error", "An error occurred: " + e.getMessage());
////            request.getRequestDispatcher("/AddOrder.jsp").forward(request, response);
////        }
//    }
//}