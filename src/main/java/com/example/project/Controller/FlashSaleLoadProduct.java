package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FlashSaleLoadProduct", value = "/FlashSaleLoadProduct")
public class FlashSaleLoadProduct extends HttpServlet {
    public void init() {
    };
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DaoProduct daoProduct = new DaoProduct();

        List<ProductTK> listProductTFSFull = daoProduct.loadProductPromotion("Flash Sale");
        List<ProductTK> listProductTFS = listProductTFSFull.size() > 15 ? listProductTFSFull.subList(0, 15) : listProductTFSFull;
        request.setAttribute("listProductFS", listProductTFS);
        daoProduct.close();
        try {
            request.getRequestDispatcher("/JspComForHome/flash-sale.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
    }
}