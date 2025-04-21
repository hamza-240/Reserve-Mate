/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetEmployee {
    public static List<Employe> getAllEmployee(){
        List<Employe> employes = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con= ConnexionDB.getConnexion();
            String sql="select * from employe ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            employes = new ArrayList<>();
            while(rs.next()){
                Employe emp = new Employe();
                emp.setNom(rs.getString("nom"));
                emp.setPrenom(rs.getString("prenom"));
                emp.setEmail(rs.getString("email"));
                emp.setMatricule(rs.getInt("matricule"));
                emp.setAdresse(rs.getString("adresse"));
                employes.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employes;
    }
}
