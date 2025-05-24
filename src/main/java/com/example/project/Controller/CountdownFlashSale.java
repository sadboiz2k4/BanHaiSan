package com.example.project.Controller;

import com.example.project.Dao.DatabaseTimeManager;
import com.example.project.Model.Time;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(name = "CountdownFlashSale", value = "/CountdownFlashSale")
public class CountdownFlashSale extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DatabaseTimeManager databaseTimeManager = new DatabaseTimeManager();
        Time timeEndFlashSale = databaseTimeManager.loadTimePromotions("Flash Sale");
        Time timeNow = Time.convertLocalDatetimeToTime(now);

        int [] timeMinus = timeEndFlashSale.timeMinus(timeNow);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String jsonResponseTime = "{\"hours\":" + timeMinus[0] + ",\"minutes\":" + timeMinus[1] + ",\"seconds\":" + timeMinus[2] + "}";
        out.print(jsonResponseTime);
        out.flush();
        databaseTimeManager.close();
    }

    public void destroy() {
    }
}