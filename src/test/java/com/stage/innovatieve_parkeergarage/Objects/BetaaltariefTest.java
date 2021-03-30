package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetaaltariefTest {

    Betaaltarief betaaltarief = new Betaaltarief(1,"dag", 20.11,new Parkeergarage(1,"Theather","Nieuwegein",14,550,"09:00","22:00")); //Arrange

    @Test
    void getBetaaltarief_Id() {
        int id = betaaltarief.getBetaaltarief_Id();//Act
        assertEquals(1,id);//Assert
    }

    @Test
    void getBetaaltarief_Type() {
        String type = betaaltarief.getBetaaltarief_Type(); //Act
        assertEquals("dag",type); //Assert
    }

    @Test
    void getBetaaltarief_Waarde() {
        Double waarde = betaaltarief.getBetaaltarief_Waarde(); //Act
        assertEquals(20.11,waarde); //Assert
    }

    @Test
    void getBetaaltatief_Parkeergarage() {
        int parkeergarageId = betaaltarief.getBetaaltatief_Parkeergarage().getParkeergarage_Id(); //Act
        assertEquals(1,parkeergarageId); //Assert
    }
}