/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Reservation;

import java.sql.*;

public class AjouterReservation {
    public static int ajouterReservation(Reservation r) {
        if (r == null) {
            System.out.println("Une attribut est null");
            return 0;
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnexionDB.getConnexion();
            if (con != null) {
                String sql = "INSERT INTO reservation VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                // Vérification des valeurs null
                ps.setDate(1, r.getDate_reserved() != null ? Date.valueOf(r.getDate_reserved()) : null);
                ps.setDate(2, r.getDate_arrive() != null ? Date.valueOf(r.getDate_arrive()) : null);
                ps.setDate(3, r.getDate_depart() != null ? Date.valueOf(r.getDate_depart()) : null);
                ps.setFloat(4, r.getAvance());
                ps.setFloat(5, r.getReste());
                ps.setInt(6, r.getNombre_person());
                ps.setFloat(7, r.getPrix());
                ps.setBoolean(8, r.isConfirmed());
                ps.setInt(9, r.getClient().getId());
                ps.setInt(10, r.getHotel().getId());

                int res = ps.executeUpdate();

                if (res > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
