package com.pfe.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int id;
    List<Reservation> reservations;
    List<Chambre> chambres;
    private int etoilles;
    private String nom;
    private String ville;
    public Hotel() {}
    public Hotel(String nom,int etoilles, String ville) {
        this.etoilles=etoilles;
        this.nom = nom;
        this.ville = ville;
        reservations = new ArrayList<Reservation>();
        chambres = new ArrayList<>();
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Chambre> getChambres() {
        return chambres;
    }
    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }
   public int getEtoilles() {
        return etoilles;
   }
   public void setEtoilles(int etoilles) {
        this.etoilles = etoilles;
   }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    public void addChambre(Reservation reservation,  Tarif tarif, boolean disponible, int nombre_personnes) {
        Chambre chambre = new Chambre(reservation, this, tarif, disponible, nombre_personnes);
        chambres.add(chambre);
    }


}
