package com.pfe.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
    private int id;
    private LocalDate date_reserved;
    private LocalDate date_depart;
    private LocalDate date_arrive;
    private float avance;
    private float reste;
    private int nombre_person;
    private float prix;
    private boolean confirmed;
    private Client client;
    private List<Chambre> chambres;
    private Hotel hotel;
    //private Facture facture;

    public Reservation() {}
    private Reservation(LocalDate date_reserved, LocalDate date_depart, LocalDate date_arrive, float avance, float reste,
                       int nombre_person, float prix, boolean confirmed, Client client,
                       Hotel hotel, int id) {
        this.date_reserved = date_reserved;
        this.date_depart = date_depart;
        this.date_arrive = date_arrive;
        this.avance = avance;
        this.reste = reste;
        this.nombre_person = nombre_person;
        this.prix = prix;
        this.confirmed = confirmed;
        this.client = client;
        this.chambres = new ArrayList<Chambre>();
        this.hotel = hotel;
        this.id = id;
    }

    public LocalDate getDate_reserved() {
        return date_reserved;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setDate_reserved(LocalDate date_reserved) {
        this.date_reserved = date_reserved;
    }
    public LocalDate getDate_depart() {
        return date_depart;
    }
    public void setDate_depart(LocalDate date_depart) {
        this.date_depart = date_depart;
    }
    public LocalDate getDate_arrive() {
        return date_arrive;
    }
    public void setDate_arrive(LocalDate date_arrive) {
        this.date_arrive = date_arrive;
    }
    public float getAvance() {
        return avance;
    }
    public void setAvance(float avance) {
        if (this.prix-(this.avance+avance)>0){
            this.avance+=avance;
            this.reste=this.prix-this.avance;

        }
        else {
            this.avance=prix;
            this.reste=0;

        }
    }
    public float getReste() {
        return reste;
    }
    public void setReste(float reste) {
        this.reste = reste;
    }
    public int getNombre_person() {
        return nombre_person;
    }
    public void setNombre_person(int nombre_person) {
        this.nombre_person = nombre_person;

    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;

    }
    public boolean isConfirmed() {
        return confirmed;
    }
    public boolean checkConfirmed() {
        if(this.reste==0 && this.prix<=this.avance){
            return true;
        }else
            return false;
    }
    public void setConfirmed(boolean confirmed) {
        if (confirmed && this.reste > 0) {
            this.confirmed = false; // Ne pas confirmer au lieu de lever une exception
        } else {
            this.confirmed = confirmed;
        }
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public List<Chambre> getChambres() {
        return chambres;
    }
    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }
    public void ajouterChambre(Chambre chambre) {
        if (!chambre.isDisponible()) {
            throw new IllegalStateException("La chambre " + chambre.getId() + " n'est pas disponible !");
        }
        this.chambres.add(chambre);
        chambre.setDisponible(false); // Marquer la chambre comme réservée
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    static Reservation creerReservation(LocalDate date_reserved, LocalDate date_depart, LocalDate date_arrive, float avance, float reste,
                                        int nombre_person, float prix, boolean confirmed, Client client,
                                        Hotel hotel, int id) {
        return new Reservation(date_reserved,date_depart,date_arrive,avance,reste,nombre_person,prix,confirmed,client,hotel,id);
    }





}
