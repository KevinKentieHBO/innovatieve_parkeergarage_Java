package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbonnementTest {

    Abonnement abonnement = new Abonnement(1, new Abonnement_Type(1,"Jaar","Dit is een test"),7,1000.99);


    @Test
    void getIdTest(){
        int id = abonnement.getId(); //Act
        assertEquals(1,id); //Assert
    }

    @Test
    void setIdTest(){
        abonnement.setId(2);
        assertEquals(2,abonnement.getId());

    }

    @Test
    void getAbonnement_TypeTest(){
        Abonnement_Type abonnement_type = abonnement.getAbonnement_type();
        assertEquals(1, abonnement_type.getId());
    }

    @Test
    void setAbonnement_TypeTest(){
        Abonnement_Type abonnement_typeSet = new Abonnement_Type(2,"Jaar","Dit is een test");
        abonnement.setAbonnement_type(abonnement_typeSet);
        Abonnement_Type abonnement_type = abonnement.getAbonnement_type();
        assertEquals(2, abonnement_type.getId());
    }

    @Test
    void getTijdseenheidTest(){
        int tijdseenheid = abonnement.getTijdseenheid();
        assertEquals(7, tijdseenheid);

    }

    @Test
    void setTijdseenheidTest(){
        abonnement.setTijdseenheid(6);
        assertEquals(6, abonnement.getTijdseenheid());

    }

    @Test
    void getJaartariefTest(){
        Double jaartarief = abonnement.getJaartarief();
        assertEquals(1000.99, jaartarief);
    }

    @Test
    void setJaartariefTest(){
        abonnement.setJaartarief(999.99);
        assertEquals(999.99, abonnement.getJaartarief());
    }

    @Test
    void setBegindatum(){
        abonnement.setBegindatum("01-01-2021");
        String begindatum = abonnement.getBegindatum();
        assertEquals("01-01-2021",begindatum);
    }

    @Test
    void getBegindatum(){
        abonnement.setBegindatum("01-01-2021");
        String begindatum = abonnement.getBegindatum();
        assertEquals("01-01-2021",begindatum);
    }

    @Test
    void setEinddatum(){
        abonnement.setEinddatum("01-02-2021");
        String einddatum = abonnement.getEinddatum();
        assertEquals("01-02-2021",einddatum);
    }

    @Test
    void getEinddatum(){
        abonnement.setEinddatum("01-02-2021");
        String einddatum = abonnement.getEinddatum();
        assertEquals("01-02-2021",einddatum);
    }

    @Test
    void isAbonnementActief(){
        abonnement.setBegindatum("01-01-2021");
        abonnement.setEinddatum("01-02-2021");
        String res = abonnement.isAbonnementActief();
        assertEquals("Verlopen",res);
    }
}