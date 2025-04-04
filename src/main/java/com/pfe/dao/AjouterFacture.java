package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Facture;
import com.pfe.model.Reservation;

import java.sql.*;
import java.time.LocalDate;

public class AjouterFacture {
    public static boolean ajouterFacture(Reservation reservation) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        if (reservation != null) {
            try {
                con = ConnexionDB.getConnexion();
                if (con != null) {
                    String sql= "insert into facture values(NULL,?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, reservation.getId());
                    ps.setFloat(2, reservation.getPrix());
                    ps.setDate(3, Date.valueOf(LocalDate.now()));
                    if(reservation.checkConfirmed())
                        ps.setString(4,"payée");
                   else {
                       if (reservation.getAvance()==0)
                           ps.setString(4,"Non payée");
                       else
                           ps.setString(4,"En Attente");
                    }
                   result = ps.executeUpdate();


                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result > 0 ? true : false;
    }
}
