package com.stage.innovatieve_parkeergarage.Objects;

public class Account {

    //attributen voor een account
    private int account_Id;
    private Bestuurder account_Bestuurder;
    private String account_Gebruikersnaam;
    private String account_Email;
    private String account_Wachtwoord;
    private Double account_Saldo;
    private String account_Token;

    //Constructor voor het account object
    public Account(int account_Id, Bestuurder account_Bestuurder, String account_Gebruikersnaam, String account_Email, String account_Wachtwoord, Double account_Saldo) {
        this.account_Id = account_Id;
        this.account_Bestuurder = account_Bestuurder;
        this.account_Gebruikersnaam = account_Gebruikersnaam;
        this.account_Email = account_Email;
        this.account_Wachtwoord = account_Wachtwoord;
        this.account_Saldo = account_Saldo;
    }

    //Getters
    public int getAccount_Id() {
        return account_Id;
    }

    public Bestuurder getAccount_Bestuurder() {
        return account_Bestuurder;
    }

    public String getAccount_Gebruikersnaam() {
        return account_Gebruikersnaam;
    }

    public String getAccount_Email() {
        return account_Email;
    }

    public String getAccount_Wachtwoord() {
        return account_Wachtwoord;
    }

    public Double getAccount_Saldo() {
        return account_Saldo;
    }

    public String getAccount_Token() {
        return account_Token;
    }

    public void setAccount_Token(String account_Token) {
        this.account_Token = account_Token;
    }
}
