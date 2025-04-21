/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.model;

import java.time.LocalDate;
import java.util.Date;

public class Facture {
    private int id;
    private LocalDate date_facture;
    private float montant;
    private int nb_jours;
    private String etat;
    private Employe employe;
    private Client client;
    private Reservation reservation;
    public Facture(int id,LocalDate date_facture,String etat, float montant, int nb_jours , Employe employe, Client client) {
        this.id = id;
        this.employe = employe;
        this.client = client;
        this.date_facture = date_facture;
        this.montant = montant;
        this.nb_jours = nb_jours;
        this.etat = etat;


    }

    public Facture() {

    }

    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate_facture() {
        return date_facture;
    }
    public void setDate_facture(LocalDate date_facture) {
        this.date_facture = date_facture;
    }
    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;

    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public int getNb_jours() {
        return nb_jours;
    }
    public void setNb_jours(int nb_jours) {
        this.nb_jours = nb_jours;
    }
    public Employe getEmploye() {
        return employe;

    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }


}
