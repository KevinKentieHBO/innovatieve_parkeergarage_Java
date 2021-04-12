package com.stage.innovatieve_parkeergarage.Objects;

public class Betaling {

    //Attributen van betaling object
    private int betaling_Id;
    private Double betaling_Bedrag;
    private String betaling_Datum;
    private String betaling_Tijd;
    private Account betaling_Account;

    //Constructor om een betaling object aan te maken
    public Betaling(int betaling_Id, Double betaling_Bedrag, String betaling_Datum, String betaling_Tijd, Account betaling_Account) {
        this.betaling_Id = betaling_Id;
        this.betaling_Bedrag = betaling_Bedrag;
        this.betaling_Datum = betaling_Datum;
        this.betaling_Tijd = betaling_Tijd;
        this.betaling_Account = betaling_Account;
    }

    //Getters
    public int getBetaling_Id() {
        return betaling_Id;
    }

    public Double getBetaling_Bedrag() {
        return betaling_Bedrag;
    }

    public String getBetaling_Datum() {
        return betaling_Datum;
    }

    public String getBetaling_Tijd() {
        return betaling_Tijd;
    }

    public Account getBetaling_Account() {
        return betaling_Account;
    }
}
