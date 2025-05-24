package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.Cart;
import com.example.project.Model.Product;
import com.example.project.Model.ProductTK;
import com.example.project.Service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCart", value = "/add-cart")
public class AddCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
