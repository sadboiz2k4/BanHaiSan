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
import java.util.List;

@WebServlet(name = "AddressUserPage", value = "/address-user-page")
public class AddressUserPage extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userID") == null){
            System.out.println("userID = 0");
            resp.sendRedirect("login");
            return;
        }
        int id = (int) session.getAttribute("userID");
        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        List<AddressUser> listAddressUser = handlerAddressDB.getAllAddressUser(id);
        req.setAttribute("listAddressUser", listAddressUser);
        handlerAddressDB.close();
        req.getRequestDispatcher("/JspComForPersonal/address.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
