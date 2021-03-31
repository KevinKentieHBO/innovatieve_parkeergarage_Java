package com.stage.innovatieve_parkeergarage.Objects;

public class Parkeergarage {

    //Attributen specificeren
    private int parkeergarage_Id;
    private String parkeergarage_Naam;
    private String parkeergarage_Locatie;
    private int parkeergarage_Parkeerlagen;
    private int parkeergarage_Aantal_Plaatsen;
    private String parkeergarage_Opening;
    private String parkeergarage_Sluiting;

    //Constructor voor het aanmaken van een parkeergarage object
    public Parkeergarage(int parkeergarage_Id, String parkeergarage_Naam, String parkeergarage_Locatie, int parkeergarage_Parkeerlagen, int parkeergarage_Aantal_Plaatsen, String parkeergarage_Opening, String parkeergarage_Sluiting) {
        this.parkeergarage_Id = parkeergarage_Id;
        this.parkeergarage_Naam = parkeergarage_Naam;
        this.parkeergarage_Locatie = parkeergarage_Locatie;
        this.parkeergarage_Parkeerlagen = parkeergarage_Parkeerlagen;
        this.parkeergarage_Aantal_Plaatsen = parkeergarage_Aantal_Plaatsen;
        this.parkeergarage_Opening = parkeergarage_Opening;
        this.parkeergarage_Sluiting = parkeergarage_Sluiting;
    }

    //geeft parkeergarage id terug
    public int getParkeergarage_Id() {
        return parkeergarage_Id;
    }

    //geeft parkeergarage naam terug
    public String getParkeergarage_Naam() {
        return parkeergarage_Naam;
    }

    //geeft parkeergarage locatie terug
    public String getParkeergarage_Locatie() {
        return parkeergarage_Locatie;
    }

    //geeft parkeergarage parkeerlagen terug
    public int getParkeergarage_Parkeerlagen() {
        return parkeergarage_Parkeerlagen;
    }

    //geeft parkeergarage aantal verdiepingen terug
    public int getParkeergarage_Aantal_Plaatsen() {
        return parkeergarage_Aantal_Plaatsen;
    }

    //geeft parkeergarage openingstijd terug
    public String getParkeergarage_Opening() {
        return parkeergarage_Opening;
    }

    //geeft parkeergarage sluitingstijd terug
    public String getParkeergarage_Sluiting() {
        return parkeergarage_Sluiting;
    }
}
