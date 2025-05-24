package com.example.project.Controller;

import com.example.project.Dao.AddressSuportShipDB;
import com.example.project.Model.Ward;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "WardLoadSS", value = "/ward-load-ss")
public class WardLoadSS extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int districtId = Integer.parseInt(req.getParameter("districtId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        AddressSuportShipDB addressDAO = new AddressSuportShipDB();
        List<Ward> wards = addressDAO.getWardsByDistrictId(districtId);
        if(wards == null){
            return;
        }
        out.println("<option value=''>-- Chọn Phường/Xã --</option>");
        for (Ward ward : wards) {
            out.printf("<option value='%d'>%s</option>",
                    ward.getId(), ward.getName());
        }
        addressDAO.close();
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
