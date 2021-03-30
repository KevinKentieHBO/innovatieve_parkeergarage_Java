package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkeerplaatsTest {

    Parkeerplaats parkeerplaats = new Parkeerplaats(1,new Parkeergarage(1,"Theater","Nieuwegein",14,550,"09:00","22:00"),1,1);//Arrange

    @Test
    void getParkeerplaats_Id() {
        int id = parkeerplaats.getParkeerplaats_Id();//Act
        assertEquals(1,id);//Assert
    }

    @Test
    void getParkeerplaats_Parkeergarage() {
        int parkeerGarageId = parkeerplaats.getParkeerplaats_Parkeergarage().getParkeergarage_Id();//Act
        assertEquals(1,parkeerGarageId);//Assert
    }

    @Test
    void getParkeerplaats_Laag() {
        int laag = parkeerplaats.getParkeerplaats_Laag();//Act
        assertEquals(1,laag);//Assert
    }

    @Test
    void getParkeerplaats_Locatie() {
        int locatie = parkeerplaats.getParkeerplaats_Locatie();//Act
        assertEquals(1,locatie);//Assert
    }

    @Test
    void checkTime() {
        assertEquals(parkeerplaats.checkTime("13:00","12:00","14:00"),true);
        assertEquals(parkeerplaats.checkTime("11:00","12:00","14:00"),false);
        assertEquals(parkeerplaats.checkTime("15:00","12:00","14:00"),false);
    }
}