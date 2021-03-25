package com.stage.innovatieve_parkeergarage.Objects;

public class Parkeerplaats {
    private int parkeerplaats_Id;
    private Parkeergarage parkeerplaats_Parkeergarage;
    private int parkeerplaats_Laag;
    private int Parkeerplaats_Locatie;

    public Parkeerplaats(int parkeerplaats_Id, Parkeergarage parkeerplaats_Parkeergarage, int parkeerplaats_Laag, int parkeerplaats_Locatie) {
        this.parkeerplaats_Id = parkeerplaats_Id;
        this.parkeerplaats_Parkeergarage = parkeerplaats_Parkeergarage;
        this.parkeerplaats_Laag = parkeerplaats_Laag;
        Parkeerplaats_Locatie = parkeerplaats_Locatie;
    }

    public int getParkeerplaats_Id() {
        return parkeerplaats_Id;
    }

    public Parkeergarage getParkeerplaats_Parkeergarage() {
        return parkeerplaats_Parkeergarage;
    }

    public int getParkeerplaats_Laag() {
        return parkeerplaats_Laag;
    }

    public int getParkeerplaats_Locatie() {
        return Parkeerplaats_Locatie;
    }
}
