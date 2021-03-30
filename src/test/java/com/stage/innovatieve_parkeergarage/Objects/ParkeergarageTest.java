package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkeergarageTest {

    Parkeergarage parkeergarage = new Parkeergarage(1,"Theater", "Nieuwegein", 14, 550, "09:00","22:00");//Arrange

    @Test
    void getParkeergarage_Id() {
        int id = parkeergarage.getParkeergarage_Id();//Act
        assertEquals(1,id);//Assert
    }

    @Test
    void getParkeergarage_Naam() {
        String naam = parkeergarage.getParkeergarage_Naam();//Act
        assertEquals("Theater",naam);//Assert
    }

    @Test
    void getParkeergarage_Locatie() {
        String locatie = parkeergarage.getParkeergarage_Locatie();//Act
        assertEquals("Nieuwegein",locatie);//Assert
    }

    @Test
    void getParkeergarage_Parkeerlagen() {
        int lagen = parkeergarage.getParkeergarage_Parkeerlagen();//Act
        assertEquals(14,lagen);//Assert
    }

    @Test
    void getParkeergarage_Aantal_Plaatsen() {
        int plaatsen = parkeergarage.getParkeergarage_Aantal_Plaatsen();//Act
        assertEquals(550,plaatsen);//Assert
    }

    @Test
    void getParkeergarage_Opening() {
        String opening = parkeergarage.getParkeergarage_Opening();//Act
        assertEquals("09:00",opening);//Assert
    }

    @Test
    void getParkeergarage_Sluiting() {
        String sluiting = parkeergarage.getParkeergarage_Sluiting();//Act
        assertEquals("22:00",sluiting);//Assert
    }
}