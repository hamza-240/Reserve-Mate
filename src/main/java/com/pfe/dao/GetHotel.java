package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GetHotel {
    public static Hotel getHotels(Hotel hotel) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (hotel != null) {
            try {
                con= ConnexionDB.getConnexion();
                String sql="select * from hotel where nom=? and ville=? and etoilles=?";
                ps= con.prepareStatement(sql);
                ps.setString(1, hotel.getNom());
                ps.setString(2, hotel.getVille());
                ps.setInt(3,hotel.getEtoilles());
                rs=ps.executeQuery();
                if (rs.next()) {
                    hotel.setNom(rs.getString("nom"));
                    hotel.setVille(rs.getString("ville"));
                    hotel.setEtoilles(rs.getInt("etoilles"));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotel;
    }
    public  static List<Hotel> getAllHotelsByNomVille(String nom, String ville) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (nom != null && ville != null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    String sql="select * from hotel where nom=? and ville=?";
                    ps=con.prepareStatement(sql);
                    ps.setString(1, nom);
                    ps.setString(2, ville);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do{
                            Hotel hotel = new Hotel();
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoilles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotels;
    }

    public static List<Hotel> getAllHotelsByVille(String ville) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (ville != null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    String sql="select * from hotel where  ville=?";
                    ps=con.prepareStatement(sql);

                    ps.setString(1, ville);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do{
                            Hotel hotel = new Hotel();
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoilles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotels;


    }

    public static List<Hotel> getAllHotelsByEtoilles(int etoilles) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (etoilles > 0) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    String sql="select * from hotel where etoilles=?";
                    ps=con.prepareStatement(sql);
                    ps.setInt(1, etoilles);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do {
                            Hotel hotel = new Hotel();
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoilles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotels;
    }

    public static List<Hotel> getAllHotelsByNom(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (nom != null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    String sql="select * from hotel where nom=?";
                    ps=con.prepareStatement(sql);
                    ps.setString(1, nom);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do {
                            Hotel hotel = new Hotel();
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoilles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotels;
    }
    public static List<Hotel> getAllHotelsByNomEtoilles(String nom,int et) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (nom != null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    String sql="select * from hotel where nom=? and etoilles=?";
                    ps=con.prepareStatement(sql);
                    ps.setString(1, nom);
                    ps.setInt(2, et);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do {
                            Hotel hotel = new Hotel();
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoilles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotels;
    }

    public static List<Hotel> getAllHotelsByVilleEtoilles(String ville,int et) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (ville != null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    String sql="select * from hotel where ville=? and etoilles=?";
                    ps=con.prepareStatement(sql);
                    ps.setString(1, ville);
                    ps.setInt(2, et);
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do {
                            Hotel hotel = new Hotel();
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoilles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }
        return hotels;
    }

    public static List<Hotel> getHotelsBy(Map<String , Object> mapHotel,int p) {
        /*
         p : est une variavle de validation c-a-d est il va eslection tout ou bien selon le  nom ou les etoilles...
         p = 0 --> selection tous
         p <> 0 --> selection seleon les champs retourner
         */
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Hotel> hotels = null;
        if (mapHotel != null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    StringBuilder sql = new StringBuilder("select * from hotel where 1=1 ");
                    List<Object> values = new ArrayList<>();
                    if (p>0){

                    for (Map.Entry<String , Object> entry : mapHotel.entrySet()) {
                        sql.append(" and ").append(entry.getKey()).append("=?");
                        values.add(entry.getValue());
                    }
                    }
                    ps=con.prepareStatement(sql.toString());

                    for (int i=0;i<values.size();i++) {
                        ps.setObject(i+1, values.get(i));

                    }
                    rs=ps.executeQuery();
                    if (rs.next()) {
                        hotels = new ArrayList<>();
                        do {
                            Hotel hotel = new Hotel();
                            hotel.setId(rs.getInt("id"));
                            hotel.setNom(rs.getString("nom"));
                            hotel.setVille(rs.getString("ville"));
                            hotel.setEtoilles(rs.getInt("etoiles"));
                            hotels.add(hotel);
                        }while (rs.next());
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                // Fermer les ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println("Erreur de fermeture de connexion");
                    e.printStackTrace();  // Gérer l'exception de fermeture
                }
            }
        }

        return hotels;
    }


}
