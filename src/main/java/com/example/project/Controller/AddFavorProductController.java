package com.example.project.Controller;

import com.example.project.Dao.FavoriteProductDao;
import com.example.project.Dao.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import java.io.IOException;

@WebServlet("/add-to-favorite")
public class AddFavorProductController extends HttpServlet {
    private final Dao dao = new Dao();
    private final FavoriteProductDao favoriteProductDao = new FavoriteProductDao(dao.getConn());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        HttpSession session = request.getSession(false);

        try {
            // Validate session and user ID
            if (session == null || session.getAttribute("userID") == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Please log in to add favorites.");
                jsonResponse.addProperty("requireLogin", true);
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            int userId = (int) session.getAttribute("userID");

            // Validate product ID
            String productIDStr = request.getParameter("productID");
            if (productIDStr == null || productIDStr.trim().isEmpty()) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Invalid product ID.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            int productId = Integer.parseInt(productIDStr);

            // Toggle favorite status
            if (favoriteProductDao.isProductFavorite(userId, productId)) {
                favoriteProductDao.removeFavoriteProduct(userId, productId);
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("action", "removed");
                jsonResponse.addProperty("message", "Removed from favorites.");
            } else {
                favoriteProductDao.addFavoriteProduct(userId, productId);
                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("action", "added");
                jsonResponse.addProperty("message", "Added to favorites.");
            }
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Error processing request: " + e.getMessage());
            e.printStackTrace();
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
