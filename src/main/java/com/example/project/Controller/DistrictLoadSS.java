package com.example.project.Controller;

import com.example.project.Dao.AddressSuportShipDB;
import com.example.project.Model.District;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DistrictLoadSS", value = "/district-load-ss")
public class DistrictLoadSS extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int provinceId = Integer.parseInt(req.getParameter("provinceId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        AddressSuportShipDB addressDAO = new AddressSuportShipDB();
        List<District> districts = addressDAO.getDistrictsByProvinceId(provinceId);
        if (districts == null) {
            return;
        }
        for (District district : districts) {
            System.out.println(district.getName());
        }
        out.println("<option value=''>-- Chọn Quận/Huyện --</option>");
        for (District district : districts) {
            out.printf("<option value='%d'>%s</option>",
                    district.getId(), district.getName());
        }
        addressDAO.close();

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
