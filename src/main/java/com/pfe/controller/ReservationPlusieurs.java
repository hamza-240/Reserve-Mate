/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.controller;

import com.pfe.dao.GetChambre;
import com.pfe.dao.GetTarif;
import com.pfe.model.Chambre;
import com.pfe.model.Tarif;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationPlusieurs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int nombre = Integer.parseInt(req.getParameter("nombre_chambre"));
        req.setAttribute("nombre", nombre);
        req.setAttribute("id_hotel",Integer.parseInt(req.getParameter("id_hotel")));
        req.getRequestDispatcher("reservationPlusieurs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {

            // validation des données
            String action = req.getParameter("action");
            List<Chambre> chambre = null;
            List<Chambre> chambreList = null;
            Map<String, Object> map = null;
            List<Tarif> tarifChambre;
            if ("ReserverPlusieursChambre".equals(action)) {

                // validation de données recuperer par la page reservationPlusieurs.jsp
                String nombre_recupere = req.getParameter("nombre_chambre");
                int id_hotel = Integer.parseInt(req.getParameter("id_hotel"));

                List<Integer> tabVar = null;
                if (nombre_recupere != null && !nombre_recupere.isEmpty()) {
                    int nombre_chambre = Integer.parseInt(nombre_recupere);
                    tabVar = new ArrayList<>();
                    for (int i = 0; i < nombre_chambre; i++) {
                        String chambreID = req.getParameter("chambre" + i);
                        if (chambreID != null && !chambreID.isEmpty()) {
                            tabVar.add(Integer.parseInt(chambreID));
                        }
                    }

                    // donc on a un tableau de id des chambre la prochaine etape est de recuper les infor sur les chambre

                    // Vérification si on a bien récupéré des chambres
                    if (tabVar.isEmpty()) {
                        throw new IllegalArgumentException("Aucun ID de chambre valide récupéré.");
                    }
                    chambreList = new ArrayList<>();


                    for (int id_chambre : tabVar) {
                        map = new HashMap<>();
                        if (GetChambre.isPresent(id_chambre,id_hotel,1)) {// si le chambre exist

                            map.put("id", id_chambre);
                            map.put("hotel_id", id_hotel);
                            chambre = GetChambre.getChambreBy(map, 1, 1);
                            map.remove("hotel_id");

                            if (  chambre != null && !chambre.isEmpty()) {
                                map.put("id", chambre.get(0).getTarif().getIdTarif());
                                tarifChambre = GetTarif.getTarif(map, 1);
                                if (tarifChambre != null && !tarifChambre.isEmpty()) {
                                    chambre.get(0).setTarif(tarifChambre.get(0));
                                }
                                chambreList.add(chambre.get(0));
                            } else {
                                // si il n y aucun ligne
                                resp.sendRedirect("dashbordClient.jsp");
                                return;
                            }
                        }else {
                            // si il n y aucun ligne
                            resp.sendRedirect("dashbordClient.jsp");
                            return;
                        }
                    }
                    req.setAttribute("chambreList", chambreList);
                    req.setAttribute("action", action);
                    req.getRequestDispatcher("reservation.jsp").forward(req, resp);


                }

            }
        }
    }
}
