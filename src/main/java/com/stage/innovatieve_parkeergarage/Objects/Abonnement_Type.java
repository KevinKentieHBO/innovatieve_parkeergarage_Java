package com.stage.innovatieve_parkeergarage.Objects;

public class Abonnement_Type {

    //Attributen object vaststellen:
    private int id;
    private String naam;
    private String beschrijving;

    //Constructor voor Abonnement_Type
    public Abonnement_Type(int id, String naam, String beschrijving) {
        this.id = id;
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    //Getters en setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
}
