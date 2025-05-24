package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "LoadProductFromCategories", value = "/loadProductFromCategories")
public class LoadProductFromCategories extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int  category = Integer.parseInt(req.getParameter("category"));
        DaoProduct  daoProduct = new DaoProduct();
        System.out.println(category);
        List<ProductTK> listProduct = new ArrayList<>();
        listProduct = daoProduct.loadProductCategories(category);
        System.out.println(listProduct);
        String categoryStr = daoProduct.getNameCatergoryByID(category);
        req.setAttribute("category", categoryStr);
        req.setAttribute("listProduct", listProduct);
        req.getRequestDispatcher("catergory.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
