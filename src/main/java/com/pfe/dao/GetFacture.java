package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Client;
import com.pfe.model.Facture;
import com.pfe.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetFacture {
    public static List<Facture> getFacturesByClient(Client client) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Facture> factures = null;

        try {
            con= ConnexionDB.getConnexion();
            if (con != null) {
                String sql = "SELECT * \n" +
                        "FROM facture\n" +
                        "INNER JOIN reservation ON reservation.id = facture.reservation_id\n" +
                        "WHERE reservation.client_id = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1,client.getId());
                rs = ps.executeQuery();
                if(rs.next()) {
                    factures = new ArrayList<>();
                    do {
                        Facture facture = new Facture();
                        facture.setDate_facture(rs.getDate("date_facture").toLocalDate());
                        facture.setMontant(rs.getFloat("montant_total"));
                        facture.setEtat(rs.getString("etat"));
                        facture.setId(rs.getInt("id"));
                        factures.add(facture);

                    }while (rs.next());

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return factures;
    }

    public static List<Facture> getFactures(Map<String,Object>map){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Facture> factures = null;
        List<Object> values=null;
        if (map!=null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    StringBuilder sql = new StringBuilder("select * from facture where 1=1 ");
                    if (!map.isEmpty()) {
                        values = new ArrayList<>();
                        for (Map.Entry<String,Object> entry : map.entrySet()) {
                            sql.append(" and ").append(entry.getKey()).append(" = ?");
                            values.add(entry.getValue());
                        }


                    }
                    ps = con.prepareStatement(sql.toString());
                    for(Object value : values) {
                        ps.setObject(1,value);
                    }
                    rs = ps.executeQuery();
                    if(rs.next()) {
                        factures = new ArrayList<>();
                        do {
                            Reservation reservation = new Reservation();
                            Facture facture = new Facture();
                            facture.setDate_facture(rs.getDate("date_facture").toLocalDate());
                            facture.setMontant(rs.getFloat("montant_total"));
                            reservation.setId(rs.getInt("reservation_id"));
                            facture.setReservation(reservation);
                            facture.setEtat(rs.getString("etat"));
                            facture.setId(rs.getInt("id"));
                            factures.add(facture);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return factures;
    }

}
