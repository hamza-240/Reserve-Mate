package com.pfe.model;

import java.util.ArrayList;
import java.util.List;

public class Tarif {
    private int idTarif;
    private String description;
    private List<Chambre> chambres;
    private  float tarif;
    public Tarif() {}
    public Tarif( float tarif,String description) {
        this.tarif = tarif;
        this.description = description;
        chambres = new ArrayList<Chambre>();
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Chambre> getChambres() {
        return chambres;
    }
    public int getIdTarif() {
        return idTarif;
    }
    public void setIdTarif(int idTarif) {
        this.idTarif = idTarif;
    }
    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }
    public float getTarif() {
        return tarif;
    }
    public void setTarif(float tarif) {
        this.tarif = tarif;
    }
    public void addChambre(Chambre chambre) {
        chambres.add(chambre);
    }
}
