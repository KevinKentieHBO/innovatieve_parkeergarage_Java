package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AbonnementDAO;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement_Type;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AbonnementDAOImplementatieTest {

    @Test
    void getAbonnementParkeergarage() throws SQLException, ClassNotFoundException {
        AbonnementDAO mock = Mockito.mock(AbonnementDAOImplementatie.class);

        ArrayList<Abonnement> list = new ArrayList();
        list.add(new Abonnement(1, new Abonnement_Type(1,"Jaar","Dit is een test"),7,1000.99));
        list.add(new Abonnement(2, new Abonnement_Type(1,"Jaar","Dit is een test"),7,1000.99));

        when(mock.getAbonnementenParkeergarage(1)).thenReturn(list);
        ArrayList<Abonnement> getList = mock.getAbonnementenParkeergarage(1);
        assertEquals(2,getList.size());
    }

    @Test
    void getAbonnementenAutoId() throws SQLException, ClassNotFoundException {
        AbonnementDAO mock = Mockito.mock(AbonnementDAOImplementatie.class);

        ArrayList<Abonnement> list = new ArrayList();
        list.add(new Abonnement(1, new Abonnement_Type(1,"Jaar","Dit is een test"),7,1000.99));
        list.add(new Abonnement(2, new Abonnement_Type(1,"Jaar","Dit is een test"),7,1000.99));

        when(mock.getAbonnementenAutoId(1)).thenReturn(list);
        ArrayList<Abonnement> getList = mock.getAbonnementenAutoId(1);
        assertEquals(2,getList.size());
    }

}