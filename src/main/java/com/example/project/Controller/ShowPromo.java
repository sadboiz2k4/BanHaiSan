package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowPromo", value = "/ShowPromo")
public class ShowPromo extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        DaoProduct daoProduct = new DaoProduct();
        String valueSearch = request.getParameter("valueSearch");
        List<String> listPromo = daoProduct.findPromoList(valueSearch);
        System.out.println(listPromo);
        request.setAttribute("listPromo",listPromo);
        daoProduct.close();
        request.getRequestDispatcher("/JspComForHome/list-promo.jsp").forward(request,response);
    }

    public void destroy() {
    }
}