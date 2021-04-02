package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReserveringTest {

    Reservering reservering = new Reservering(1,new Parkeerplaats(1,null,1,1),"13:00","15:00","01-01-2021",new Auto(1,"280-BB-1"));//Arrange
    Reservering reserveringsetter = new Reservering(1,new Parkeerplaats(1,null,1,1),"13:00","15:00","01-01-2021",new Auto(1,"280-BB-1"));//Arrange

    @Test
    void setReservering_Parkeerplaats() {
        Parkeerplaats parkeerplaats = new Parkeerplaats(1,new Parkeergarage(1,"Theater","Nieuwegein",14,550,"09:00","22:00"),1,1);//Arrange
        reserveringsetter.setReservering_Parkeerplaats(parkeerplaats);
        assertEquals(1,reserveringsetter.getReservering_Parkeerplaats().getParkeerplaats_Id());//Assert
    }

    @Test
    void setReservering_Begintijd() {
        reserveringsetter.setReservering_Begintijd("09:00");//Act
        assertEquals("09:00",reserveringsetter.getReservering_Begintijd());//Assert
    }

    @Test
    void setReservering_Eindtijd() {
        reserveringsetter.setReservering_Eindtijd("10:00");//Act
        assertEquals("10:00",reserveringsetter.getReservering_Eindtijd());//Assert
    }

    @Test
    void setReservering_Datum() {
        reserveringsetter.setReservering_Datum("01-01-2022");//Act
        assertEquals("01-01-2022",reserveringsetter.getReservering_Datum());//Assert;
    }

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

    @Test
    void tijdVoorbijBegintijd(){
        Boolean check1 = reservering.tijdVoorbijBegintijd("13:00","14:00","01-01-2021","01-01-2021");
        Boolean check2 = reservering.tijdVoorbijBegintijd("13:00","13:00", "01-01-2021","01-01-2021");
        Boolean check3 = reservering.tijdVoorbijBegintijd("13:00","12:00", " 01-01-2021","01-01-2021");

        Boolean check4 = reservering.tijdVoorbijBegintijd("13:00","13:00","02-01-2021","01-01-2021");
        Boolean check5 = reservering.tijdVoorbijBegintijd("13:00","13:00", "02-01-2021","03-01-2021");
        Boolean check6 = reservering.tijdVoorbijBegintijd("13:00","13:00", " 02-01-2021","02-01-2021");

        Boolean check7 = reservering.tijdVoorbijBegintijd("13:00","14:00","02-01-2021","01-01-2021");
        Boolean check8 = reservering.tijdVoorbijBegintijd("13:00","13:00", "02-01-2021","03-01-2021");
        Boolean check9 = reservering.tijdVoorbijBegintijd("13:00","12:00", " 02-01-2021","02-01-2021");

        assertEquals(false,check1);
        assertEquals(true,check2);
        assertEquals(true,check3);

        assertEquals(false,check4);
        assertEquals(true,check5);
        assertEquals(true,check6);

        assertEquals(false,check7);
        assertEquals(true,check8);
        assertEquals(true,check9);
    }
}