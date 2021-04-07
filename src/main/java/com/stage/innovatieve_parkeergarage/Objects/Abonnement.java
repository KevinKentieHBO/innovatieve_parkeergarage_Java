package com.stage.innovatieve_parkeergarage.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Abonnement {

    //Attributen voor het Abonnement object
    private int id;
    private Abonnement_Type abonnement_type;
    private int tijdseenheid;
    private Double jaartarief;
    private String begindatum;
    private String einddatum;
    private Parkeergarage parkeergarage;

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

    public String getBegindatum() {
        return begindatum;
    }

    public void setBegindatum(String begindatum) {
        this.begindatum = begindatum;
    }

    public String getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(String einddatum) {
        this.einddatum = einddatum;
    }

    public Parkeergarage getParkeergarage() {
        return parkeergarage;
    }

    public void setParkeergarage(Parkeergarage parkeergarage) {
        this.parkeergarage = parkeergarage;
    }

    public String isAbonnementActief(){
        try{
            Date einddatumDate = new SimpleDateFormat("dd-MM-yyyy").parse(einddatum);
            Date begindatumDate = new SimpleDateFormat("dd-MM-yyyy").parse(begindatum);
            Date huidigeDatum = new Date();

            if (einddatumDate.after(huidigeDatum) && begindatumDate.before(huidigeDatum)){
                return "Actief";
            }else{
                return "Verlopen";
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
