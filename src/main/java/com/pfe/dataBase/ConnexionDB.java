package com.pfe.dataBase;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    private static String USER = "root";
    private static String PASS = "Benaissa2003!";
    private static String URL = "jdbc:mysql://localhost:3306/jee_pfe";
    public static Connection getConnexion(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("✅ Connexion à la base de données réussie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de downloads des pilotes :(");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion de base de données :(");
            throw new RuntimeException(e);
        }
        return con;
    }
}
