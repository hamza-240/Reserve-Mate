package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetClient {
    public static Client getClient(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Client client = null;
        try {
            con= ConnexionDB.getConnexion();
            if (con != null) {

                String sql = "select * from client where nom=? and password=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                /* client = new Client();
                   car peut être  la requête ne retourne rien mais tu cree une instance de client
                   donc objet il retourne pas null mais plutôt un reference sur une objet qui sa comportement
                   est null (selon le consructeur par defaut qui tu as cree  )
                   donc de preference de mettre cette instruction de creation de instance á interieur de la
                   condition d'ou il obligé de verifier si il y a une ligne tu vas cree cette instance sinon
                   tu fais rien
                 */
                if (rs.next()) {
                    client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setVille(rs.getString("ville"));
                    client.setNom(rs.getString("nom"));
                    client.setPrenom(rs.getString("prenom"));
                    client.setEmail(rs.getString("email"));
                    client.setDate_naissance(rs.getDate("date_naissance").toLocalDate());
                    client.setPassword(rs.getString("password"));

                }
            }else{
                System.out.println("Erreur : connexion null");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public static int getClientID(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            con= ConnexionDB.getConnexion();
            String sql = "select id from client where username=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                id=rs.getInt("id");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
    }

