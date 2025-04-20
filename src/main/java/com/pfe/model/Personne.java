package com.pfe.model;

import java.time.LocalDate;
import java.util.Date;

public class Personne {
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected LocalDate date_naissance;
    protected String email;
    protected String ville;
    public Personne(String nom,String prenom,String adresse,LocalDate date_naissance,String email,String ville) {
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.date_naissance=date_naissance;
        this.email=email;
        this.ville=ville;

    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public LocalDate getDate_naissance() {
        return date_naissance;
    }
    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public Personne() {}

}
