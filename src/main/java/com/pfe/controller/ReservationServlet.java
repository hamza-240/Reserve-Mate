/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.controller;

import com.pfe.dao.*;
import com.pfe.model.Chambre;
import com.pfe.model.Client;
import com.pfe.model.Reservation;
import com.pfe.model.Tarif;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationServlet extends HttpServlet {
    static int fois = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           HttpSession session = req.getSession(false);
           if (session != null) {
               // recuperation des données
               String action = req.getParameter("action");
               String nombre_de_personne=req.getParameter("nombre_de_personne");
               String prix_total =req.getParameter("prix_total");
               String debutDate = req.getParameter("dateArrivee");
               String finDate = req.getParameter("dateDepart");
               String avance = req.getParameter("avance");
               String hotelId = req.getParameter("hotelId");

               if (action!=null  && debutDate!=null && finDate!=null && avance!=null && hotelId!=null && nombre_de_personne!=null && prix_total!=null ) {

                   LocalDate dateDebut = LocalDate.parse(debutDate);
                   LocalDate dateFin = LocalDate.parse(finDate);
                   if (dateDebut.isBefore(dateFin)) {
                       LocalDate currentDate = LocalDate.now();
                       float avanceFloat = Float.parseFloat(avance);
                       int id_reservation;
                       float prix_totalFloat = Float.parseFloat(prix_total);
                       int hotel_id = Integer.parseInt(hotelId);
                       int nbpersonne = Integer.parseInt(nombre_de_personne);
                       Map<String, Object> map;
                       Client client = (Client) session.getAttribute("client");
                       System.out.println(client.toString());
                       Reservation reservation = client.addReservation(currentDate,dateDebut,dateFin,avanceFloat,prix_totalFloat-avanceFloat,nbpersonne,prix_totalFloat,prix_totalFloat-avanceFloat<=0,null,0);
                       System.out.println("reservation");
                       if ("Reserver".equals(action)) {
                           Chambre chambre;
                           String chambreId = req.getParameter("chambreId");
                           if (chambreId != null) {
                               int chambre_id = Integer.parseInt(chambreId);
                               map = new HashMap<>();
                               map.put("id", chambre_id);
                               chambre = GetChambre.getChambreBy(map, 1, 1).getFirst();
                               if (chambre != null) {
                                   reservation.setHotel(chambre.getHotel());
                                   reservation.ajouterChambre(chambre);

                               }
                           }

                       } else if ("ReserverPlusieursChambre".equals(action)) {
                           String nombreChambre = req.getParameter("nombre");
                           List<Integer> chambreListID = new ArrayList<>();
                           List<Chambre> chambreList;
                           int chambreRecuperer = Integer.parseInt(nombreChambre);
                           // Recuperer les id des chambres
                           for (int i = 0; i < chambreRecuperer; i++) {
                               String chambreId = req.getParameter("chambreId"+i);
                               if (chambreId != null) {
                                   chambreListID.add(Integer.parseInt(chambreId));
                               }

                           }
                           // recuper les chambre
                           for(Integer id:chambreListID){
                               map = new HashMap<>();
                               map.put("id", id);
                               Chambre chambre= GetChambre.getChambreBy(map, 1, 1).getFirst();
                               if (chambre != null) {
                                   reservation.setHotel(chambre.getHotel());
                                   reservation.ajouterChambre(chambre);
                               }
                           }


                       }
                       // inseré la reservation
                       if(reservation!=null){
                           reservation.setClient(client);
                           System.out.println("id client : "+reservation.getClient().getId()+" id hotel"+reservation.getHotel().getId());
                           id_reservation=AjouterReservation.ajouterReservation(reservation);
                           if (id_reservation !=0){
                               // inser les id des chambre dans la tables
                               reservation.setId(id_reservation);
                               System.out.println("la reservation est ajouté par ID : "+id_reservation+" :)");
                             if (AjouterReservationChambre.ajouterReservationChambre(reservation)){
                                 // si l'insertion des chambre est faite par succues il faut genere une facture
                                 System.out.println("les chambres sont ajouté par ID : "+id_reservation+" :)");
                                 if (AjouterFacture.ajouterFacture(reservation)){
                                     System.out.println("la Facture est generé :) ");
                                     req.getRequestDispatcher("dashbordClient.jsp").forward(req, resp);
                                 }else
                                     System.out.println("la facture n'est pas ajouter :(");

                             }else
                                 System.out.println("les chambres ne sont  pas ajouté :(");

                           }else
                               System.out.println("La reservation est pas ajouté :(");

                       }

                   }else {
                       resp.sendRedirect("/reservation");
                   }
               }else
                   System.out.println("une attribut est null ");

           }else
               System.out.println("la session  est null ");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {

            // validation des données
            String id = req.getParameter("id_chambre");
            String action = req.getParameter("action");
            List<Chambre> chambre = null;
            List<Chambre> chambreList = null;
            Map<String, Object> map = null;
            if ("Reserver".equals(action)) {
                System.out.println("id chambre : "+id);
                if (id != null && !id.isEmpty()) {
                    int id_chambre = Integer.parseInt(req.getParameter("id_chambre"));
                    map = new HashMap<>();
                    map.put("id", id_chambre);
                    chambre = GetChambre.getChambreBy(map, 1,1);
                    map.put("id", (chambre.get(0).getTarif()).getIdTarif());
                    List<Tarif> tarifs = GetTarif.getTarif(map, 1);
                    if (tarifs.size() > 0 && tarifs != null)
                        chambre.getFirst().setTarif(tarifs.get(0));
                }
                if (chambre != null) {
                    req.setAttribute("chambre", chambre.get(0));
                    req.setAttribute("action", action);
                    req.getRequestDispatcher("reservation.jsp").forward(req, resp);
                } else {
                    System.out.println("chambre est null \n il y a une prebleme de recuperation des données");
                    req.setAttribute("alert", "true");
                    resp.sendRedirect("AllDisponibleChambre.jsp");
                }
            } else if ("Annuler".equals(action)) {
                session.removeAttribute("hotel");
                req.setAttribute("action", action);
                resp.sendRedirect("AllDisponibleChambre.jsp");

            }

        }
    }

}


