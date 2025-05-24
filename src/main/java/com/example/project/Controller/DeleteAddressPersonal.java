package com.example.project.Controller;

import com.example.project.Dao.HandlerAddressDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteAddressPersonal", value = "/delete-address")
public class DeleteAddressPersonal extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("addressUserID"));
        HttpSession session = req.getSession();
        int userID = (int)session.getAttribute("userID");
        HandlerAddressDB handlerAddressDB = new HandlerAddressDB();
        handlerAddressDB.deleteAddress(id, userID);
        handlerAddressDB.close();
        PrintWriter out = resp.getWriter();
        out.println("success");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
