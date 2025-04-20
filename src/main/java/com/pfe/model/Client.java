package com.pfe.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends Personne{
    private int id;
    String password;
    private List<Facture> factures;
    private List<Reservation> reservations;
   public Client(String nom, String prenom,  String adresse,LocalDate date_naissance, String mail,String ville) {
       super(nom,prenom,adresse,date_naissance,mail,ville);
       factures = new ArrayList<Facture>();
       reservations = new ArrayList<Reservation>();
   }

   public String getPassword() {
       return password;
   }
   public void setPassword(String password) {
       this.password = password;
   }


   public Client() {
       super();
       factures = new ArrayList<Facture>();
       reservations = new ArrayList<Reservation>();
   }
   public int getId() {
       return id;
   }
   public void setId(int id) {
       this.id = id;
   }

    @Override
    public String toString() {
        return "Client [id=" + id + ", nom =" +nom +"prenom ="+prenom;
    }

    public List<Facture> getFactures() {
       return factures;
   }
   public void setFactures(List<Facture> factures) {
       this.factures = factures;
   }
   public List<Reservation> getReservations() {
       return reservations;
   }
   public void setReservations(List<Reservation> reservations) {
       this.reservations = reservations;
   }
   public void addFacture(Facture facture) {
       factures.add(facture);
   }
   public Reservation addReservation(LocalDate date_reserved, LocalDate date_depart, LocalDate date_arrive, float avance, float reste,
                                     int nombre_person, float prix, boolean confirmed, Hotel hotel, int id_reservation) {
           Reservation reservation =Reservation.creerReservation(date_reserved,date_depart,date_arrive,avance,reste,nombre_person,prix,confirmed,this,hotel,id_reservation);
           reservations.add(reservation);
           return reservation;

   }


}
