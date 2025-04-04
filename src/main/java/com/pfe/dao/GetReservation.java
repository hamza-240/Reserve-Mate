package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GetReservation {
    public static List<Reservation> getAllReservation() {
        List<Reservation> reservations=null;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con= ConnexionDB.getConnexion();
            if (con!=null){
                String sql="select * from reservation";
                ps=con.prepareStatement(sql);
                rs=ps.executeQuery();

                if(rs.next()){

                     reservations = new ArrayList<Reservation>();
                    do {
                        Client client=new Client();
                        Hotel hotel=new Hotel();
                        Reservation reservation=new Reservation();
                        reservation.setId(rs.getInt(1));
                        reservation.setDate_reserved(rs.getDate(2).toLocalDate());
                        reservation.setDate_depart(rs.getDate(3).toLocalDate());
                        reservation.setDate_arrive(rs.getDate(4).toLocalDate());
                        reservation.setAvance(rs.getInt(5));
                        reservation.setReste(rs.getInt(6));
                        reservation.setNombre_person(rs.getInt(7));
                        reservation.setPrix(rs.getInt(8));
                        reservation.setConfirmed(rs.getBoolean(9));
                        hotel.setId(rs.getInt(10));
                        client.setId(rs.getInt(11));
                        reservation.setHotel(hotel);
                        reservation.setClient(client);
                        reservations.add(reservation);


                    }while(rs.next());
                }

            }
        } catch (SQLException e) {

            System.out.println("Erreur de recuperation des reservations");
            throw new RuntimeException(e);

        }finally {

            // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées

            try {

                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();

            } catch (SQLException e) {

                System.out.println("Erreur de fermeture de connexion");
                e.printStackTrace();  // Gérer l'exception de fermeture

            }

        }
        return reservations;
    }
    public static Reservation getReservationById(int id) {

        Reservation reservation=null;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            con= ConnexionDB.getConnexion();
            if (con!=null){
                String sql="select * from reservation where id=? ";
                ps=con.prepareStatement(sql);
                ps.setInt(1, id);
                rs=ps.executeQuery();

                if(rs.next()){

                    reservation=new Reservation();
                    Hotel hotel=new Hotel();
                    Client client=new Client();
                    reservation.setId(rs.getInt(1));
                    reservation.setDate_reserved(rs.getDate(2).toLocalDate());
                    reservation.setDate_depart(rs.getDate(3).toLocalDate());
                    reservation.setDate_arrive(rs.getDate(4).toLocalDate());
                    reservation.setAvance(rs.getFloat("avance"));
                    reservation.setReste(rs.getInt(6));
                    reservation.setNombre_person(rs.getInt(7));
                    reservation.setPrix(rs.getInt(8));
                    reservation.setConfirmed(rs.getBoolean(9));
                    hotel.setId(rs.getInt(10));
                    client.setId(rs.getInt(11));
                    reservation.setHotel(hotel);
                    reservation.setClient(client);

                }

            }
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
        finally {
            // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Erreur de fermeture de connexion");
                e.printStackTrace();  // Gérer l'exception de fermeture
            }
        }
        return reservation;
    }

    public static List<Reservation> getAllReservationByIdClient(int idClient) {
        List<Reservation> reservations=null;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con= ConnexionDB.getConnexion();
            if (con!=null){
                String sql="select * from reservation where client_id=? ";
                ps=con.prepareStatement(sql);
                ps.setInt(1, idClient);
                rs=ps.executeQuery();
                if(rs.next()){
                    reservations = new ArrayList<Reservation>();
                    do {
                        Client client=new Client();
                        Hotel hotel=new Hotel();
                        Reservation reservation=new Reservation();
                        reservation.setId(rs.getInt(1));
                        reservation.setDate_reserved(rs.getDate(2).toLocalDate());
                        reservation.setDate_depart(rs.getDate(3).toLocalDate());
                        reservation.setDate_arrive(rs.getDate(4).toLocalDate());
                        reservation.setAvance(rs.getInt(5));
                        reservation.setReste(rs.getInt(6));
                        reservation.setNombre_person(rs.getInt(7));
                        reservation.setPrix(rs.getInt(8));
                        reservation.setConfirmed(rs.getBoolean(9));
                        hotel.setId(rs.getInt(10));
                        client.setId(rs.getInt(11));
                        reservation.setHotel(hotel);
                        reservation.setClient(client);
                        reservations.add(reservation);
                    }while (rs.next());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Erreur de fermeture de connexion");
                e.printStackTrace();  // Gérer l'exception de fermeture
            }
        }
        return reservations;
    }

    public static Map<Chambre, Tarif> getChambreTarif(Reservation reservation){
        Map<Chambre, Tarif> chambreTarif=null;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        if (reservation!=null) {
            int id_reservation=reservation.getId();
            try {
                con= ConnexionDB.getConnexion();
                if (con!=null){
                    String sql="SELECT *\n" +
                            "FROM reservation_chambre rc\n" +
                            "INNER JOIN chambre c ON c.id = rc.chambre_id\n" +
                            "INNER JOIN tarif t ON t.id = c.tarif_id\n" +
                            "WHERE rc.reservation_id = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, id_reservation);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        chambreTarif=new HashMap<Chambre, Tarif>();
                        do {
                            Hotel hotel=new Hotel();
                            Tarif tarif=new Tarif();
                            Chambre chambre=new Chambre();
                            chambre.setId(rs.getInt(3));
                            hotel.setId(rs.getInt(4));
                            chambre.setHotel(hotel);
                            tarif.setIdTarif(rs.getInt(5));
                            chambre.setTarif(tarif);
                            chambre.setDisponible(rs.getBoolean(6));
                            chambre.setNombre_personnes(rs.getInt(7));
                            tarif.setIdTarif(rs.getInt(8));
                            tarif.setTarif(rs.getFloat(9));
                            tarif.setDescription(rs.getString(10));
                            chambreTarif.put(chambre,tarif);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return chambreTarif;
    }
}
