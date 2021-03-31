package com.stage.innovatieve_parkeergarage.Objects;

public class Betaaltarief {

    //Attributen specificeren
    private int betaaltarief_Id;
    private String betaaltarief_Type;
    private Double betaaltarief_Waarde;
    private Parkeergarage betaaltatief_Parkeergarage;

    //Om een Betaaltarief object aan te maken
    public Betaaltarief(int betaaltarief_Id, String betaaltarief_Type, Double betaaltarief_Waarde, Parkeergarage betaaltatief_Parkeergarage) {
        this.betaaltarief_Id = betaaltarief_Id;
        this.betaaltarief_Type = betaaltarief_Type;
        this.betaaltarief_Waarde = betaaltarief_Waarde;
        this.betaaltatief_Parkeergarage = betaaltatief_Parkeergarage;
    }

    //geeft betaaltarief id terug
    public int getBetaaltarief_Id() {
        return betaaltarief_Id;
    }

    // geeft betaaltarief type terug
    public String getBetaaltarief_Type() {
        return betaaltarief_Type;
    }

    //geeft betaaltarief waarde terug
    public Double getBetaaltarief_Waarde() {
        return betaaltarief_Waarde;
    }

    //geeft object parkeergarage van het betaaltarief terug
    public Parkeergarage getBetaaltatief_Parkeergarage() {
        return betaaltatief_Parkeergarage;
    }
}
