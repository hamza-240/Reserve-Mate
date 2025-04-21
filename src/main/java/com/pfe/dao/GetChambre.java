/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Chambre;
import com.pfe.model.Hotel;
import com.pfe.model.Reservation;
import com.pfe.model.Tarif;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetChambre {
    public static List<Chambre> getChambreBy(Map<String, Object> map,int choix,int cr) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Chambre> chambreList = null;
        if (map!=null){
            try {
                con = ConnexionDB.getConnexion();
                if (con != null) {
                    StringBuilder sql =new StringBuilder( "select * from chambre where 1=1 " ) ;
                    List<Object> values = new ArrayList<>();
                    if (cr>0) {


                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            sql.append(" AND ").append(entry.getKey()).append(" = ? ");
                            values.add(entry.getValue());
                        }
                    }
                    ps = con.prepareStatement(sql.toString());

                    for (int i=0;i<values.size();i++){
                        ps.setObject(i+1, values.get(i));

                    }
                    rs = ps.executeQuery();
                    if (rs.next()){
                        chambreList = new ArrayList<>();
                        do {
                            Chambre chambre = new Chambre();
                            Hotel hotel = new Hotel();
                            Tarif tarif = new Tarif();

                            hotel.setId(rs.getInt("hotel_id"));
                            tarif.setIdTarif(rs.getInt("tarif_id"));
                            chambre.setId(rs.getInt("id"));
                            chambre.setDisponible(rs.getBoolean("disponible"));
                            chambre.setNombre_personnes(rs.getInt("nombre_personnes"));
                            chambre.setTarif(tarif);
                            chambre.setHotel(hotel);
                            if (choix==1 && chambre.isDisponible() || choix==0 && !(chambre.isDisponible()) || choix==-1){
                                // ajouter just les chambres qui sont disponible "1"
                                // ou
                                // ajouter juste les chambre qui sont pas disponible "0"
                                // ajouter tous les chambres " -1 "
                                chambreList.add(chambre);
                            }


                        }while (rs.next());
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return chambreList;
    }
    public static boolean isPresent(int id_chambre,int status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder sql;
        if(id_chambre>0){
            try {
                con = ConnexionDB.getConnexion();
                if (con != null) {

                    if (status==-1){
                         sql =new StringBuilder( "select count(*)  from chambre where  id = ?");

                    }else
                         sql = new StringBuilder("select count(*) from chambre where  id = ? and disponible = ? ");

                        ps = con.prepareStatement(sql.toString());
                        ps.setInt(1, id_chambre);

                        if(status!=-1)
                            ps.setInt(2, status);

                    rs = ps.executeQuery();
                    if (rs.next()){
                        return rs.getInt(1)>0;
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static boolean isPresent(int chambre_id,int hotel_id,int status) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sql;
        if(chambre_id>0 && hotel_id>0){
            try {
                con = ConnexionDB.getConnexion();
                if (con != null) {

                    if (status==-1){
                        sql =new StringBuilder( "select count(*)  from chambre where  id = ? and hotel_id = ?");

                    }else
                        sql = new StringBuilder("select count(*) from chambre where  id = ? and hotel_id = ? and disponible = ? ");

                    ps = con.prepareStatement(sql.toString());
                    ps.setInt(1, chambre_id);
                    ps.setInt(2, hotel_id);
                    if(status!=-1)
                        ps.setInt(3, status);

                    rs = ps.executeQuery();
                    if (rs.next()){
                        return rs.getInt(1)>0;
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }
}
