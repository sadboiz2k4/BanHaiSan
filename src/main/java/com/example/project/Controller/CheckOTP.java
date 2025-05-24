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
import jakarta.servlet.http.HttpSession;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "CheckOTP", value = "/check-otp")
public class CheckOTP extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = (String) req.getSession().getAttribute("typeCheckOTP");
        if (type == null) {
            req.setAttribute("errorOTP", "Lỗi xác thực: Không có loại yêu cầu OTP.");
            req.getRequestDispatcher("check-otp.jsp").forward(req, resp);
            return;
        }
        // Xử lý gửi lại OTP
        if (req.getParameter("resendOTP") != null) {
            String email = (String) req.getSession().getAttribute("email");
            String newOTP = HandlerSupport.generateRandomCode(6);
            req.getSession().setAttribute("otp", newOTP);
            Time otpCreateAt = new Time(LocalDateTime.now());
            req.getSession().setAttribute("OTPForgetPassCreateAt", otpCreateAt);
            try {
                EmailService.sendEmail(email, "Mã OTP mới", "Mã OTP của bạn là: " + newOTP);
                req.setAttribute("successOTP", "Mã OTP mới đã được gửi đến email của bạn.");
            } catch (MessagingException e) {
                req.setAttribute("errorOTP", "Lỗi khi gửi email OTP.");
            }
            req.getRequestDispatcher("check-otp.jsp").forward(req, resp);
            return;
        }

        if (type.equals("forget")) {
            String otp = req.getParameter("passcode");
            String otpSession = (String) req.getSession().getAttribute("otp");
            Time otpCreateAt = (Time) req.getSession().getAttribute("OTPForgetPassCreateAt");
            if (otp.equals(otpSession)) {
                if (otpCreateAt.timeMinusToSecond(Time.convertLocalDatetimeToTime(LocalDateTime.now())) <= 900) {
                    String newPass = HandlerSupport.generateRandomCode(6);
                    String email = (String) req.getSession().getAttribute("email");
                    try {
                        EmailService.sendEmail(email, "New Password", "Your new password is: " + newPass);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                    HandlerUserDB handlerUserDB = new HandlerUserDB();
                    handlerUserDB.updatePassword(email, newPass);
                    handlerUserDB.close();
                }
            } else {
                req.setAttribute("errorOTP", "Mã OTP không đúng hoặc đã hết hạn");
                req.getRequestDispatcher("check-otp.jsp").forward(req, resp);
            }
        }
        if (type.equals("register")) {
            String otp = req.getParameter("passcode");
            String otpSession = (String) req.getSession().getAttribute("otp");
            Time otpCreateAt = (Time) req.getSession().getAttribute("timeCreateOTPRegis");
            if (otp.equals(otpSession)) {
                if (otpCreateAt.timeMinusToSecond(Time.convertLocalDatetimeToTime(LocalDateTime.now())) <= 900) {
                    String uname = (String) req.getSession().getAttribute("uname");
                    String email = (String) req.getSession().getAttribute("email");
                    String pass = (String) req.getSession().getAttribute("pass");
                    HandlerUserDB handlerUserDB = new HandlerUserDB();
                    handlerUserDB.registerNewAccount(uname, email, pass);
                    req.getSession().removeAttribute("pass");
                    req.getSession().removeAttribute("otp");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("errorOTP", "Mã OTP không đúng hoặc đã hết hạn");
                req.getRequestDispatcher("check-otp.jsp").forward(req, resp);
            }
        }
        if (type.equals("updateInfor")) {
            String otp = req.getParameter("passcode");
            String otpSession = (String) req.getSession().getAttribute("otp");
            Time otpCreateAt = (Time) req.getSession().getAttribute("OTPForgetPassCreateAt");
            String email = req.getParameter("emailI");
            String lastName = req.getParameter("lastName");
            String firstName = req.getParameter("firstName");
            boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
            String birthDay = req.getParameter("dob");
            String phone = req.getParameter("phone");
            if (otp.equals(otpSession)) {
                if (otpCreateAt.timeMinusToSecond(Time.convertLocalDatetimeToTime(LocalDateTime.now())) <= 900) {
                    HttpSession session = req.getSession();
                    int id = (int) session.getAttribute("userID");
                    HandlerUserDB handlerUserDB = new HandlerUserDB();

                    handlerUserDB.updateInforUser(id, email, phone, firstName, lastName, gender, birthDay);
                    handlerUserDB.close();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
