package com.example.project.Controller;

import com.example.project.Dao.DaoProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BannerHeader", value = "/BannerHeader")
public class BannerHeader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoProduct daoProduct = new DaoProduct();
        List<String[]> ListBanner = daoProduct.loadBannerForHome();

        if (!daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Top-Header").isEmpty()) {
            List<String[]> lBanner = daoProduct.findBannerWithPosition(ListBanner, "Trang chủ-Top-Header");

            String url = lBanner.get(0)[0];
            String imgSrc = lBanner.get(0)[2];
            String altText = lBanner.get(0)[3];

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                out.println("<html><head><title>Banner</title></head><body>");
                out.println("<a href=\"" + url + "\">");  // Đóng dấu ngoặc kép cho href
                out.println("<img src=\"Img/Banner/" + imgSrc + "\" alt=\"" + altText + "\" />");
                out.println("</a>");
                out.println("</body></html>");
            }
            daoProduct.close();
        }else{
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                out.println("<html><head><title>Banner</title></head><body>");
                out.println("<a href=\"#\">");
                out.println("<img src=\"Img/Banner/TH.jpg\" alt=\"No Banner\" />");
                out.println("</a>");
                out.println("</body></html>");
            }
            daoProduct.close();
        }
    }
}


