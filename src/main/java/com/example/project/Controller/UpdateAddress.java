package com.example.project.Controller;

import com.example.project.Dao.HandlerAddressDB;
import com.example.project.Model.AddressUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UpdateAddress", value = "/edit-address")
public class UpdateAddress extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        int idUser = (int) session.getAttribute("idUser");
//        if(idUser == 0){
//            resp.sendRedirect("login.jsp");
//            return;
//        }

        int id = Integer.parseInt(req.getParameter("addressUserID"));

        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        AddressUser au = handlerAddressDB.getAddressUserForID(2, id);
        req.setAttribute("address", au);
        handlerAddressDB.close();
        req.getRequestDispatcher("update-address.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        HttpSession session = req.getSession();
        int idUser = (int) session.getAttribute("idUser");
        if(idUser == 0){
            resp.sendRedirect("login.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String name =req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String province = req.getParameter("province");
        String district = req.getParameter("district");
        String ward = req.getParameter("ward");
        String street = req.getParameter("street");
        boolean isPrimary = Boolean.parseBoolean(req.getParameter("default-address"));
        String type = req.getParameter("addressType");

        handlerAddressDB.updateAddress(id, name, phone, address, province, district, ward, street, isPrimary, type);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
