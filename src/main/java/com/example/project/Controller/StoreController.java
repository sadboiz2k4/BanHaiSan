package com.example.project.Controller;

import com.example.project.Dao.StoreDAO;
import com.example.project.Dao.Dao;
import com.example.project.Model.Store;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "store-system", value = "/store-system")
public class StoreController extends HttpServlet {
    private StoreDAO storeDAO;
    
    @Override
    public void init() throws ServletException {
        storeDAO = new StoreDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Store> stores = storeDAO.getAllStores();
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("/StoreSystem.jsp").forward(request, response);
    }
}