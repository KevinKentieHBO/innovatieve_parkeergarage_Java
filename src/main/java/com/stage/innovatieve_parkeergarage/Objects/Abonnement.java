package com.stage.innovatieve_parkeergarage.Objects;

public class Abonnement {

    //Attributen voor het Abonnement object
    private int id;
    private Abonnement_Type abonnement_type;
    private int tijdseenheid;
    private Double jaartarief;

    //Constructor voor het abonnement
    public Abonnement(int id, Abonnement_Type abonnement_type, int tijdseenheid, Double jaartarief) {
        this.id = id;
        this.abonnement_type = abonnement_type;
        this.tijdseenheid = tijdseenheid;
        this.jaartarief = jaartarief;
    }

    //Getters en setters voor abonnement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonnement_Type getAbonnement_type() {
        return abonnement_type;
    }

    public void setAbonnement_type(Abonnement_Type abonnement_type) {
        this.abonnement_type = abonnement_type;
    }

    public int getTijdseenheid() {
        return tijdseenheid;
    }

    public void setTijdseenheid(int tijdseenheid) {
        this.tijdseenheid = tijdseenheid;
    }

    public double getJaartarief() {
        return jaartarief;
    }

    public void setJaartarief(Double jaartarief) {
        this.jaartarief = jaartarief;
    }
}
