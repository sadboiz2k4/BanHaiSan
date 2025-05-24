package com.example.project.Controller;

import com.example.project.Dao.AddressSuportShipDB;
import com.example.project.Model.Street;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StreetLoadSS", value = "/street-load-ss")
public class StreetLoadSS extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int wardId = Integer.parseInt(req.getParameter("wardId"));
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        AddressSuportShipDB addressDAO = new AddressSuportShipDB();
        List<Street> streets = addressDAO.getStreetsByWardId(wardId);
        if(streets == null){
            return;
        }
        out.println("<option value=''>-- Chọn Đường --</option>");
        for (Street street : streets) {
            out.printf("<option value='%d'>%s</option>",
                    street.getId(), street.getName());
        }
        addressDAO.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
