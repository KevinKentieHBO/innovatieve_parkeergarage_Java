package com.stage.innovatieve_parkeergarage.Objects;

public class Auto {

    //Attributen specificeren
    private int auto_Id;
    private String auto_Kenteken;

    //Aanmaken van een auto object
    public Auto(int auto_Id, String auto_Kenteken) {
        this.auto_Id = auto_Id;
        this.auto_Kenteken = auto_Kenteken;
    }

    //Geeft auto id terug
    public int getAuto_Id() {
        return auto_Id;
    }

    //geeft auto kenteken terug
    public String getAuto_Kenteken() {
        return auto_Kenteken;
    }
}
