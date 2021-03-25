package com.stage.innovatieve_parkeergarage.Objects;

public class Betaaltarief {
    private int id;
    private String Type;
    private Double Waarde;

    public Betaaltarief(int id, String type, Double waarde) {
        this.id = id;
        Type = type;
        Waarde = waarde;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public Double getWaarde() {
        return Waarde;
    }
}
