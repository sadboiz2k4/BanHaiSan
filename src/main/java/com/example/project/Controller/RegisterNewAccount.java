package com.example.project.Controller;

import com.example.project.Service.EmailService;
import com.example.project.Dao.HandlerSupport;
import com.example.project.Dao.HandlerUserDB;
import com.example.project.Model.Time;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "RegisterNewAccount", value = "/register-new-account")
public class RegisterNewAccount extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("username");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String cPass = req.getParameter("confirmPassword");

        HandlerUserDB handlerUserDB = new HandlerUserDB();

        if(handlerUserDB.checkUsernameExist(uname)){
            req.setAttribute("error", "Tên đăng nhập đã tồn tại");
            req.getRequestDispatcher("register-new-account.jsp").forward(req, resp);
            return;
        }
        if(!pass.equals(cPass)){
            req.setAttribute("error", "Mật khẩu không khớp");
            req.getRequestDispatcher("register-new-account.jsp").forward(req, resp);
            return;
        }
        else{
            // Kiểm tra email đã tồn tại trong DB chưa
            if(handlerUserDB.checkEmailExist(email)){
                req.setAttribute("error", "Email đã tồn tại");
                req.getRequestDispatcher("register-new-account.jsp").forward(req, resp);
                return;
            }else{
                // Đăng kí
                String otp = HandlerSupport.generateRandomCode(6);
                try {
                    EmailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                req.getSession().setAttribute("email", email);
                req.getSession().setAttribute("otp", otp);
                req.getSession().setAttribute("uname", uname);
                req.getSession().setAttribute("pass", pass);
                req.getSession().setAttribute("timeCreateOTPRegis", Time.convertLocalDatetimeToTime(LocalDateTime.now()));
                req.getSession().setAttribute("typeCheckOTP", "register");
                req.getRequestDispatcher("check-otp.jsp").forward(req, resp);
            }

        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
