package com.stage.innovatieve_parkeergarage.Objects;

public class Auto {
    private int auto_Id;
    private String auto_Kenteken;

    public Auto(int auto_Id, String auto_Kenteken) {
        this.auto_Id = auto_Id;
        this.auto_Kenteken = auto_Kenteken;
    }

    public int getAuto_Id() {
        return auto_Id;
    }

    public String getAuto_Kenteken() {
        return auto_Kenteken;
    }
}
