package com.pfe.dao;

import com.pfe.dataBase.ConnexionDB;
import com.pfe.model.Tarif;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetTarif {
    public static List<Tarif> getTarif(Map<String,Object> map, int p) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> values=null;
        List<Tarif> tarifs=null;
        if(map!=null) {
            try {
                con= ConnexionDB.getConnexion();
                if (con != null) {
                    StringBuilder sql=new StringBuilder("select * from tarif where 1=1 ");
                    values=new ArrayList<Object>();
                    if (p>0){
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            sql.append(" and "+entry.getKey()+"=? ");
                            values.add(entry.getValue());
                        }
                    }
                    ps=con.prepareStatement(sql.toString());
                    for(int i=1;i<=values.size();i++) {
                        ps.setObject(i,values.get(i-1));
                    }
                    rs=ps.executeQuery();
                    if(rs.next()) {
                        tarifs=new ArrayList<>();
                        do {
                            Tarif tarif=new Tarif();
                            tarif.setIdTarif(rs.getInt("id"));
                            tarif.setTarif(rs.getFloat("prix"));
                            tarif.setDescription(rs.getString("description"));
                            tarifs.add(tarif);
                        }while(rs.next());


                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return tarifs;
    }
}
