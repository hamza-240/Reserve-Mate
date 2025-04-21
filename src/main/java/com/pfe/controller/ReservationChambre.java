/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pfe.dao.GetChambre;
import com.pfe.dao.GetHotel;
import com.pfe.dao.GetTarif;
import com.pfe.model.Chambre;
import com.pfe.model.Hotel;
import com.pfe.model.Tarif;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


public class ReservationChambre extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {


            // validation de id recuperé
            int id_hotel = Integer.parseInt(request.getParameter("id"));
            Map<String, Object> map = new HashMap<String, Object>();
            List<Chambre> chambres = null;

            // recuperer tous les chambre de cette hotel
            if (id_hotel > 0)
                map.put("hotel_id", id_hotel);
            chambres = GetChambre.getChambreBy(map, 1,1);

            // recuperer  la tarif de chaque chambre


            map.remove("hotel_id");

            if (chambres != null && chambres.size() > 0) {


                for (Chambre c : chambres) {
                    map.put("id", (c.getTarif()).getIdTarif());
                    List<Tarif> tarifs = GetTarif.getTarif(map, 1);
                    System.out.println("haaa ana -----");
                    if (tarifs.size() > 0 && tarifs != null)
                        c.setTarif(tarifs.get(0));
                }
            }

            // ajouter les chambres selectionné au model pour utilser dans le jsp
            request.setAttribute("chambres", chambres);

            // recuperer les information sur l'hotel conserné

            List<Hotel> hotel = GetHotel.getHotelsBy(map, 0);
            request.setAttribute("hotel", hotel.get(0));
            session.setAttribute("hotel", hotel.get(0));

            map.remove("id");

            request.getRequestDispatcher("AllDisponibleChambre.jsp").forward(request, response);
        }
    }


    public void destroy() {
    }
}