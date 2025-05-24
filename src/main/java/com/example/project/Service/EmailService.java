package com.example.project.Service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        String fromEmail = "oseanseafood@gmail.com";
        String password = "vdlejxprzwfltcji";
        //vdle jxpr zwfl tcji
        // Cấu hình SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*");
        // Xác thực tài khoản
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(body);

        // Gửi email
        Transport.send(message);
    }

    public static void main(String[] args) throws MessagingException {
        EmailService.sendEmail("22130231@st.hcmuaf.edu.vn", "Bị report", "Out game");
    }
}
