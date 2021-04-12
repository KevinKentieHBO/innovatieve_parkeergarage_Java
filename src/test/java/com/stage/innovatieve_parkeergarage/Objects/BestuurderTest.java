package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestuurderTest {

    Bestuurder bestuurder = new Bestuurder(1,"Kevin","05-07-1996",new ArrayList<Auto>());

    @Test
    void getBestuurder_Id() {
        int id = bestuurder.getBestuurder_Id();
        assertEquals(1,id);
    }

    @Test
    void setBestuurder_Id() {
        bestuurder.setBestuurder_Id(2);
        int id = bestuurder.getBestuurder_Id();
        assertEquals(2,id);
    }

    @Test
    void getBestuurder_Naam() {
        String naam = bestuurder.getBestuurder_Naam();
        assertEquals("Kevin",naam);
    }

    @Test
    void setBestuurder_Naam() {
        bestuurder.setBestuurder_Naam("Aaron");
        String naam = bestuurder.getBestuurder_Naam();
        assertEquals("Aaron",naam);
    }

    @Test
    void getBestuurder_Geboortedatum() {
        String geboortedatum = bestuurder.getBestuurder_Geboortedatum();
        assertEquals("05-07-1996",geboortedatum);
    }

    @Test
    void setBestuurder_Geboortedatum() {
        bestuurder.setBestuurder_Geboortedatum("01-01-1990");
        String geboortedatum = bestuurder.getBestuurder_Geboortedatum();
        assertEquals("01-01-1990",geboortedatum);
    }

    @Test
    void getMijnAutos() {
        Auto auto1 = new Auto(1,"123-BB-B");
        Auto auto2 = new Auto(2,"234-BB-B");
        bestuurder.setMijnAutos(auto1);
        bestuurder.setMijnAutos(auto2);
        assertEquals(2, bestuurder.getMijnAutos().size());
    }

    @Test
    void setMijnAutos() {
        Auto auto1 = new Auto(1,"123-BB-B");
        Auto auto2 = new Auto(2,"234-BB-B");
        bestuurder.setMijnAutos(auto1);
        bestuurder.setMijnAutos(auto2);
        assertEquals(2, bestuurder.getMijnAutos().size());
    }
}