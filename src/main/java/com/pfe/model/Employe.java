package com.pfe.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employe extends Personne{
    private int matricule;
    private String fonction;
    private List<Facture> factures;


    public Employe(String nom, String prenom, LocalDate date_naissance, String mail, String ville, String adresse, int matricule, String fonction) {
        super(nom,prenom,adresse,date_naissance,mail,ville);
        this.matricule = matricule;
        this.fonction = fonction;
        factures = new ArrayList<Facture>();

    }
    public Employe(){
        super();
    }
    public String getFonction() {
        return fonction;
    }
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public List<Facture> getFactures() {
        return factures;
    }
    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public void addFacture(Facture facture) {
        factures.add(facture);

    }
    public void removeFacture(Facture facture) {
        factures.remove(facture);
    }

    public int getMatricule() {
        return matricule;
    }
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }



}
