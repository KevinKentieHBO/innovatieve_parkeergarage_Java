package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BetalingTest {

    Betaling betaling = new Betaling(1,1.12,"01-01-2021","13:00",new Account(1,new Bestuurder(1,"Kevin","05-07-1996",new ArrayList<Auto>()),"KevinKentie","kevin-kentie@hotmail.com", "Stage123!",0.00));

    @Test
    void getBetaling_Id() {
        int id = betaling.getBetaling_Id();
        assertEquals(1,id);
    }

    @Test
    void getBetaling_Bedrag() {
        Double bedrag = betaling.getBetaling_Bedrag();
        assertEquals(1.12, bedrag);
    }

    @Test
    void getBetaling_Datum() {
        String datum = betaling.getBetaling_Datum();
        assertEquals("01-01-2021",datum);
    }

    @Test
    void getBetaling_Tijd() {
        String tijd = betaling.getBetaling_Tijd();
        assertEquals("13:00",tijd);
    }

    @Test
    void getBetaling_Account() {
        Account account = betaling.getBetaling_Account();
        assertEquals(1,account.getAccount_Id());
    }
}