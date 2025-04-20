package com.pfe.controller;

import com.pfe.dao.GetHotel;
import com.pfe.model.Hotel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

public class RechercheHotel extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = 0;
        Map<String, Object> mapRechercheHotel = new HashMap<>();
        List<Hotel> hotels =null;
        String debutDate = req.getParameter("debutDate");
        String finDate = req.getParameter("finDate");
        if(!(req.getParameter("etoiles").isEmpty()) || !(req.getParameter("nom").isEmpty()) || !(req.getParameter("ville").isEmpty())) {

            int etoille = Integer.parseInt(req.getParameter("etoiles"));
            String nom = req.getParameter("nom");
            String ville = req.getParameter("ville");

            if (nom != null && !(nom.isEmpty())) {
                i++;
                mapRechercheHotel.put("nom", nom);
            }
            if (ville != null && !(ville.isEmpty())) {
                i++;
                mapRechercheHotel.put("ville", ville);
            }
            if (etoille > 0) {
                i++;
                mapRechercheHotel.put("etoiles", etoille);
            }
        }


        hotels = GetHotel.getHotelsBy(mapRechercheHotel,i);

        req.setAttribute("hotels", hotels);
        HttpSession session = req.getSession(false);
        if(debutDate != null && !(debutDate.isEmpty())) {
            session.setAttribute("debutDate", debutDate);
        }
        if(finDate != null && !(finDate.isEmpty())) {
            session.setAttribute("finDate", finDate);
        }
        req.getRequestDispatcher("AllHotels.jsp").forward(req, resp);



    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("rechercheHotel.jsp");
    }
}
