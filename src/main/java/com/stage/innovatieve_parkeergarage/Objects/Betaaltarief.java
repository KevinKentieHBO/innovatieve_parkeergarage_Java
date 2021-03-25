package com.stage.innovatieve_parkeergarage.Objects;

public class Betaaltarief {
    private int betaaltarief_Id;
    private String betaaltarief_Type;
    private Double betaaltarief_Waarde;
    private Parkeergarage betaaltatief_Parkeergarage;

    public Betaaltarief(int betaaltarief_Id, String betaaltarief_Type, Double betaaltarief_Waarde, Parkeergarage betaaltatief_Parkeergarage) {
        this.betaaltarief_Id = betaaltarief_Id;
        this.betaaltarief_Type = betaaltarief_Type;
        this.betaaltarief_Waarde = betaaltarief_Waarde;
        this.betaaltatief_Parkeergarage = betaaltatief_Parkeergarage;
    }

    public int getBetaaltarief_Id() {
        return betaaltarief_Id;
    }

    public String getBetaaltarief_Type() {
        return betaaltarief_Type;
    }

    public Double getBetaaltarief_Waarde() {
        return betaaltarief_Waarde;
    }

    public Parkeergarage getBetaaltatief_Parkeergarage() {
        return betaaltatief_Parkeergarage;
    }
}
