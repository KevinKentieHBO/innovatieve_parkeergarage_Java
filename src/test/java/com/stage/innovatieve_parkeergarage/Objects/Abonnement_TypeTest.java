package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Abonnement_TypeTest {

    Abonnement_Type abonnement_type = new Abonnement_Type(1,"Jaar","Dit is een test");

    @Test
    void getIdTest(){
        int id = abonnement_type.getId(); //Act
        assertEquals(1,id); //Assert
    }

    @Test
    void setIdTest(){
        abonnement_type.setId(2);
        assertEquals(2,abonnement_type.getId());

    }

    @Test
    void getNaamTest(){
        String naam = abonnement_type.getNaam();
        assertEquals("Jaar", naam);
    }

    @Test
    void setNaamTest(){
        abonnement_type.setNaam("Week");
        String naam = abonnement_type.getNaam();
        assertEquals("Week", naam);
    }

    @Test
    void getBeschrijvingTest(){
        String beschrijving = abonnement_type.getBeschrijving();
        assertEquals("Dit is een test", beschrijving);

    }

    @Test
    void setBeschrijvingTest(){
        abonnement_type.setBeschrijving("Dit is een tweede test");
        assertEquals("Dit is een tweede test", abonnement_type.getBeschrijving());

    }
}