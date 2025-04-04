package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Allclient {
    public List<Client> getAll(){
        List<Client> clients =null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con= ConnexionDB.getConnexion();
            String sql="select * from client ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            clients = new ArrayList<Client>();
            while(rs.next()){
                Client client = new Client();
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setAdresse(rs.getString("adresse"));
                client.setVille(rs.getString("ville"));
                clients.add(client);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;

    }
}
