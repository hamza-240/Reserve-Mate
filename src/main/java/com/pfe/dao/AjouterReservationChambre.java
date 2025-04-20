package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Chambre;
import com.pfe.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjouterReservationChambre {
    public static boolean ajouterReservationChambre(Reservation r) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if(r!=null) {
            con= ConnexionDB.getConnexion();
             try {
                // il faut Ajouté tous les chambres de cette reservation
                 String sql = "insert into reservation_chambre values(?,?)";
                 for (Chambre c : r.getChambres()) {
                     ps = con.prepareStatement(sql);
                     ps.setInt(1, r.getId());
                     ps.setInt(2,c.getId());
                     if (ps.executeUpdate()<=0) {
                         return false;
                     }
                 }
                 return true;
            } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
        }
        return false;
    }
}
