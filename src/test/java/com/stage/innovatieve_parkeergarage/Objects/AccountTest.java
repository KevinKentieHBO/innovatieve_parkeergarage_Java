package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account = new Account(1,new Bestuurder(1,"Kevin","05-07-1996",new ArrayList<Auto>()),"KevinKentie","kevin-kentie@hotmail.com", "Stage123!",0.00);

    @Test
    void getAccount_Id() {
        int id = account.getAccount_Id();
        assertEquals(1,id);
    }

    @Test
    void getAccount_Bestuurder() {
        Bestuurder bestuurder = account.getAccount_Bestuurder();
        assertEquals(1,bestuurder.getBestuurder_Id());
    }

    @Test
    void getAccount_Gebruikersnaam() {
        String gebruikersnaam = account.getAccount_Gebruikersnaam();
        assertEquals("KevinKentie",gebruikersnaam);
    }

    @Test
    void getAccount_Email() {
        String email = account.getAccount_Email();
        assertEquals("kevin-kentie@hotmail.com",email);
    }

    @Test
    void getAccount_Wachtwoord() {
        String wachtwoord = account.getAccount_Wachtwoord();
        assertEquals("Stage123!",wachtwoord);
    }

    @Test
    void getAccount_Saldo() {
        Double saldo = account.getAccount_Saldo();
        assertEquals(0.00,saldo);
    }

    @Test
    void getAccount_Token() {
        account.setAccount_Token("123");
        String token = account.getAccount_Token();
        assertEquals("123",token);
    }

    @Test
    void setAccount_Token() {
        account.setAccount_Token("123");
        String token = account.getAccount_Token();
        assertEquals("123",token);
    }
}