package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetReservationChambre {
    public static boolean getReservationChambre(int reservation_id, int chambre_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if(reservation_id >0 && chambre_id >0) {
            try {
                con= ConnexionDB.getConnexion();
                String sql = "select count(*) from reservaation_chambre where reservation_id=? and chambre_id=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, reservation_id);
                ps.setInt(2, chambre_id);
                rs = ps.executeQuery();
                if(rs.next()) {
                    return rs.getInt(1)>0 ? true : false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
