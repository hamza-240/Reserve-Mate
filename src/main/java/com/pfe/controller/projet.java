package com.pfe.controller;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class projet
{


    public class Université {
        private String nom;
        private String adresse;
        private List<Faculté> facultés;

        public Université(String nom, String adresse) {
            this.nom = nom;
            this.adresse = adresse;
            this.facultés = new ArrayList<>();
        }

        public void ajouterFaculté(Faculté faculté) {
            facultés.add(faculté);
        }

        public List<Faculté> getFacultés() {
            return facultés;
        }
    }

    public class Faculté {
        private String nom;
        private String département;
        private List<Département> départements;

        public Faculté(String nom, String département) {
            this.nom = nom;
            this.département = département;
            this.départements = new ArrayList<>();
        }

        public void ajouterDépartement(Département département) {
            départements.add(département);
        }

        public List<Département> getDépartements() {
            return départements;
        }
    }

    public class Département {
        private String nom;
        private String code;
        private List<Professeur> professeurs;

        public Département(String nom, String code) {
            this.nom = nom;
            this.code = code;
            this.professeurs = new ArrayList<>();
        }

        public void ajouterProfesseur(Professeur professeur) {
            professeurs.add(professeur);
        }

        public List<Professeur> getProfesseurs() {
            return professeurs;
        }
    }

    public class Professeur {
        private String nom;
        private String spécialité;
        private List<Cours> cours;

        public Professeur(String nom, String spécialité) {
            this.nom = nom;
            this.spécialité = spécialité;
            this.cours = new ArrayList<>();
        }

        public void enseignerCours(Cours cours) {
            this.cours.add(cours);
        }
    }

    public class Cours {
        private String titre;
        private Integer crédits;
        private Salle salle;
        private List<Etudiant> étudiants;

        public Cours(String titre, Integer crédits) {
            this.titre = titre;
            this.crédits = crédits;
            this.étudiants = new ArrayList<>();
        }

        public void ajouterEtudiant(Etudiant étudiant) {
            étudiants.add(étudiant);
            étudiant.inscrireCours(this);
        }

        public void setSalle(Salle salle) {
            this.salle = salle;
        }
    }

    public class Etudiant {
        private String nom;
        private String matricule;
        private List<Cours> cours;
        private List<Double> notes;

        public Etudiant(String nom, String matricule) {
            this.nom = nom;
            this.matricule = matricule;
            this.cours = new ArrayList<>();
            this.notes = new ArrayList<>();
        }

        public void inscrireCours(Cours cours) {
            if (!this.cours.contains(cours)) {
                this.cours.add(cours);
            }
        }
    }

    public class Salle {
        private String numéro;
        private Integer capacité;

        public Salle(String numéro, Integer capacité) {
            this.numéro = numéro;
            this.capacité = capacité;
        }

        // Getters et setters
        public String getNuméro() {
            return numéro;
        }

        public Integer getCapacité() {
            return capacité;
        }
    }
}
