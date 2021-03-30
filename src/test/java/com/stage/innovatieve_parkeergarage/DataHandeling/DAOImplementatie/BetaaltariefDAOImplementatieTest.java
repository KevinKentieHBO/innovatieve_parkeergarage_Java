package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.BetaaltariefDAO;
import com.stage.innovatieve_parkeergarage.Objects.Betaaltarief;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class BetaaltariefDAOImplementatieTest {

    @Test
    void getAllBetaaltarief() throws SQLException, ClassNotFoundException {
        BetaaltariefDAO mock = Mockito.mock(BetaaltariefDAOImplementatie.class);

        ArrayList<Betaaltarief> list = new ArrayList();
        list.add(new Betaaltarief(1,"uur",1.10,null));
        list.add(new Betaaltarief(2,"dag",6.00,null));

        when(mock.getAllBetaaltarief()).thenReturn(list);
        ArrayList<Betaaltarief> getList = mock.getAllBetaaltarief();
        assertEquals(2,getList.size());
    }
}