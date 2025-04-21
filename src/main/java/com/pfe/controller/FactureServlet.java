/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.controller;

import com.pfe.dao.GetFacture;
import com.pfe.model.Client;
import com.pfe.model.Facture;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FactureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Client client = (Client) session.getAttribute("client");
            if (client != null) {
                try {
                    List<Facture> factures = GetFacture.getFacturesByClient(client);
                    req.setAttribute("factures", factures);
                    req.getRequestDispatcher("factures.jsp").forward(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
