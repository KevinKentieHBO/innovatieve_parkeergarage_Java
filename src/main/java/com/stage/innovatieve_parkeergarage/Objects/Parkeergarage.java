package com.stage.innovatieve_parkeergarage.Objects;

public class Parkeergarage {
    private int parkeergarage_Id;
    private String parkeergarage_Naam;
    private String parkeergarage_Locatie;
    private int parkeergarage_Parkeerlagen;
    private int parkeergarage_Aantal_Plaatsen;
    private String parkeergarage_Opening;
    private String parkeergarage_Sluiting;

    public Parkeergarage(int parkeergarage_Id, String parkeergarage_Naam, String parkeergarage_Locatie, int parkeergarage_Parkeerlagen, int parkeergarage_Aantal_Plaatsen, String parkeergarage_Opening, String parkeergarage_Sluiting) {
        this.parkeergarage_Id = parkeergarage_Id;
        this.parkeergarage_Naam = parkeergarage_Naam;
        this.parkeergarage_Locatie = parkeergarage_Locatie;
        this.parkeergarage_Parkeerlagen = parkeergarage_Parkeerlagen;
        this.parkeergarage_Aantal_Plaatsen = parkeergarage_Aantal_Plaatsen;
        this.parkeergarage_Opening = parkeergarage_Opening;
        this.parkeergarage_Sluiting = parkeergarage_Sluiting;
    }

    public int getParkeergarage_Id() {
        return parkeergarage_Id;
    }

    public String getParkeergarage_Naam() {
        return parkeergarage_Naam;
    }

    public String getParkeergarage_Locatie() {
        return parkeergarage_Locatie;
    }

    public int getParkeergarage_Parkeerlagen() {
        return parkeergarage_Parkeerlagen;
    }

    public int getParkeergarage_Aantal_Plaatsen() {
        return parkeergarage_Aantal_Plaatsen;
    }

    public String getParkeergarage_Opening() {
        return parkeergarage_Opening;
    }

    public String getParkeergarage_Sluiting() {
        return parkeergarage_Sluiting;
    }
}
