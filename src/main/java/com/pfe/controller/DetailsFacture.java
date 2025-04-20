package com.pfe.controller;

import com.pfe.dao.GetFacture;
import com.pfe.dao.GetHotel;
import com.pfe.dao.GetReservation;
import com.pfe.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsFacture extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String facture_id = request.getParameter("id");

            if (facture_id != null && !(facture_id.isEmpty())) {
                int facture_id_int = Integer.parseInt(facture_id);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("id", facture_id_int);
                List<Facture> factureList= GetFacture.getFactures(map);
                int id_reservation= factureList.getFirst().getReservation().getId();
                Reservation reservation = GetReservation.getReservationById(id_reservation);
                int id_hotel = reservation.getHotel().getId();
                map.clear();
                map.put("id", id_hotel);
                Hotel hotel = GetHotel.getHotelsBy(map,1).getFirst();
                Map<Chambre, Tarif> mapChambre = GetReservation.getChambreTarif(reservation);
                request.setAttribute("hotel", hotel);
                request.setAttribute("reservation", reservation);
                request.setAttribute("facture", factureList.getFirst());
                request.setAttribute("chambre", mapChambre);
                request.getRequestDispatcher("factureDetails.jsp").forward(request, response);

            }
        }
    }
}
