package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import com.example.project.Model.ProductLarge;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "searchSortPrice", value = "/search-sort-price")
public class SearchSortPrice extends HttpServlet {
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isUp = Boolean.parseBoolean(request.getParameter("isUp"));
        HttpSession session = request.getSession();
        String valueSearch = request.getParameter("valueSearch");
        int currentPage = Integer.parseInt(request.getParameter("currentPage") != null ? request.getParameter("currentPage"):"1");
        DaoProduct daoProduct = new DaoProduct();
        int totalProductPerPage = 20;
        List<ProductLarge> listProduct = (List<ProductLarge>) session.getAttribute("listProduct");
        if(isUp){
            listProduct.sort(Comparator.comparing(ProductLarge::getPrice));
        }else{
            listProduct.sort(Comparator.comparing(ProductLarge::getPrice).reversed());
        }
        int totalProduct  = listProduct.size();
        List<ProductLarge> listProductForPage = listProduct.subList((currentPage-1)*totalProductPerPage,Math.min(currentPage*totalProductPerPage,totalProduct));
        System.out.println(totalProduct);
        System.out.println(listProductForPage.size());
        for (ProductLarge product : listProductForPage) {
            System.out.println("l"+product);
        }
        int totalPage = totalProduct % totalProductPerPage ==0 ? (int)totalProduct/totalProductPerPage:(int)totalProduct/totalProductPerPage+1;
        request.setAttribute("ListProductSearch", listProductForPage);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        daoProduct.close();

        try {
            request.getRequestDispatcher("/JspComForHome/search-product.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}
