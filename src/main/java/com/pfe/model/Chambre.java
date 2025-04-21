/**
 * Copyright (c) 2023 Votre Nom
 * MIT License
 */
package com.pfe.model;

public class Chambre  {
    private int id;
    private Reservation reservation;
    private Hotel hotel;
    private Tarif tarif;
    private boolean disponible;
    private int nombre_personnes;
    public Chambre() {}
    protected Chambre(Reservation reservation, Hotel hotel, Tarif tarif, boolean disponible, int nombre_personnes) {
        this.reservation = reservation;
        this.hotel = hotel;
        this.tarif = tarif;
        this.disponible = disponible;
        this.nombre_personnes = nombre_personnes;
    }
    public Reservation getReservation() {
        return reservation;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public Tarif getTarif() {
        return tarif;
    }
    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
    public boolean isDisponible() {
        return disponible;

    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public int getNombre_personnes() {
        return nombre_personnes;
    }
    public void setNombre_personnes(int nombre_personnes) {
        this.nombre_personnes = nombre_personnes;
    }



}
