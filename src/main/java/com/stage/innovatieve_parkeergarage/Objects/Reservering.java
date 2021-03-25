package com.stage.innovatieve_parkeergarage.Objects;

public class Reservering {
    private int reservering_Id;
    private Parkeerplaats reservering_Parkeerplaats;
    private String reservering_Begintijd;
    private String reservering_Eindtijd;
    private Auto reservering_Auto;

    public Reservering(int reservering_Id, Parkeerplaats reservering_Parkeerplaats, String reservering_Begintijd, String reservering_Eindtijd, Auto reservering_Auto) {
        this.reservering_Id = reservering_Id;
        this.reservering_Parkeerplaats = reservering_Parkeerplaats;
        this.reservering_Begintijd = reservering_Begintijd;
        this.reservering_Eindtijd = reservering_Eindtijd;
        this.reservering_Auto = reservering_Auto;
    }

    public int getReservering_Id() {
        return reservering_Id;
    }

    public Parkeerplaats getReservering_Parkeerplaats() {
        return reservering_Parkeerplaats;
    }

    public String getReservering_Begintijd() {
        return reservering_Begintijd;
    }

    public String getReservering_Eindtijd() {
        return reservering_Eindtijd;
    }

    public Auto getReservering_Auto() {
        return reservering_Auto;
    }
}
