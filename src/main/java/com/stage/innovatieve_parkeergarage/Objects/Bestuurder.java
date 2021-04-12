package com.stage.innovatieve_parkeergarage.Objects;

import java.util.ArrayList;

public class Bestuurder {

    //Attributen van bestuurder
    private int bestuurder_Id;
    private String bestuurder_Naam;
    private String bestuurder_Geboortedatum;
    private ArrayList<Auto> mijnAutos;

    //Constructor om bestuurder object aan te maken
    public Bestuurder(int bestuurder_Id, String bestuurder_Naam, String bestuurder_Geboortedatum, ArrayList<Auto> mijnAutos) {
        this.bestuurder_Id = bestuurder_Id;
        this.bestuurder_Naam = bestuurder_Naam;
        this.bestuurder_Geboortedatum = bestuurder_Geboortedatum;
        this.mijnAutos = mijnAutos;
    }

    //Getters en setters
    public int getBestuurder_Id() {
        return bestuurder_Id;
    }

    public void setBestuurder_Id(int bestuurder_Id) {
        this.bestuurder_Id = bestuurder_Id;
    }

    public String getBestuurder_Naam() {
        return bestuurder_Naam;
    }

    public void setBestuurder_Naam(String bestuurder_Naam) {
        this.bestuurder_Naam = bestuurder_Naam;
    }

    public String getBestuurder_Geboortedatum() {
        return bestuurder_Geboortedatum;
    }

    public void setBestuurder_Geboortedatum(String bestuurder_Geboortedatum) {
        this.bestuurder_Geboortedatum = bestuurder_Geboortedatum;
    }

    public ArrayList<Auto> getMijnAutos() {
        return mijnAutos;
    }

    public void setMijnAutos(Auto auto) {
        mijnAutos.add(auto);
    }
}
