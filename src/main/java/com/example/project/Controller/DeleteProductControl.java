package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteControl", urlPatterns = {"/delete"})
public class DeleteProductControl extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pid = request.getParameter("pid");
        DaoProduct dao = new DaoProduct();
        dao.deleteImgProduct(pid);
        dao.deleteProduct(pid);
        response.sendRedirect("product-admin");
    }
}
