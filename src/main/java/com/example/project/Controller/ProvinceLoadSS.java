package com.example.project.Controller;

import com.example.project.Dao.AddressSuportShipDB;
import com.example.project.Model.Province;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProvinceLoadSS", value = "/province-load-ss")
public class ProvinceLoadSS extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        AddressSuportShipDB addressDAO = new AddressSuportShipDB();
        List<Province> provinces = addressDAO.getAllProvinces();
        if(provinces == null){
            return;
        }
        out.println("<option value=''>-- Chọn Tỉnh/Thành phố --</option>");
        for (Province province : provinces) {
            out.printf("<option value='%d'>%s</option>",
                    province.getId(), province.getName());
        }
        addressDAO.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
