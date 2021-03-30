package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReserveringTest {

    Reservering reservering = new Reservering(1,new Parkeerplaats(1,null,1,1),"13:00","15:00","01-01-2021",new Auto(1,"280-BB-1"));//Arrange

    @Test
    void getReservering_Id() {
        int id = reservering.getReservering_Id();//Act
        assertEquals(1,id);//Assert
    }

    @Test
    void getReservering_Parkeerplaats() {
        int parkeerplaatsId = reservering.getReservering_Parkeerplaats().getParkeerplaats_Id();//Act
        assertEquals(1,parkeerplaatsId);//Assert
    }

    @Test
    void getReservering_Begintijd() {
        String begintijd = reservering.getReservering_Begintijd();//Act
        assertEquals("13:00",begintijd);//Assert
    }

    @Test
    void getReservering_Eindtijd() {
        String eindtijd = reservering.getReservering_Eindtijd();//Act
        assertEquals("15:00",eindtijd);//Assert
    }

    @Test
    void getReservering_Datum() {
        String datum = reservering.getReservering_Datum();//Act
        assertEquals("01-01-2021",datum);//Assert
    }

    @Test
    void getReservering_Auto() {
        int autoId = reservering.getReservering_Auto().getAuto_Id();//Act
        assertEquals(1,autoId);//Assert
    }

    @Test
    void stringToDate() throws ParseException {
        Date datum = reservering.stringToDate();//Act
        assertEquals("Fri Jan 01 00:00:00 CET 2021",datum.toString());//Assert
    }
}