package com.example.project.Controller;

import com.example.project.Dao.HandlerUserDB;
import com.example.project.Model.CustomerTK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/change-password")
public class ChangePassword extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/JspComForPersonal/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String reNewPassword = req.getParameter("reNewPassword");
        if (newPassword.equals(reNewPassword)) {
            // check password
            int userID = (int) req.getSession().getAttribute("userID");
            HandlerUserDB handlerUserDB = new HandlerUserDB();
            CustomerTK customerTK = handlerUserDB.getInforUser(userID);
            boolean checkPassword = handlerUserDB.checkPassword(userID,password);
            if(reNewPassword.equals(password)){
                resp.sendRedirect(req.getContextPath() + "/LoadPersonal");
            }
            // update password
            if(checkPassword){
                handlerUserDB.updatePassword(customerTK.getEmail(),newPassword);
                handlerUserDB.close();
                resp.sendRedirect(req.getContextPath() + "/LoadPersonal");
            } else {
                req.setAttribute("error", "Mật khẩu không đúng");
                req.getRequestDispatcher("/JspComForPersonal/change-password.jsp").forward(req, resp);
            }
            // redirect to home
        } else {
            req.setAttribute("error", "Mật khẩu mới không khớp");
            req.getRequestDispatcher("/JspComForPersonal/change-password.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
