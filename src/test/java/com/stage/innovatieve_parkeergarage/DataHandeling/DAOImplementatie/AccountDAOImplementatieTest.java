package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.Objects.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountDAOImplementatieTest {

    @Test
    void checkLogin() throws SQLException, ClassNotFoundException {
        AccountDAO mock = Mockito.mock(AccountDAOImplementatie.class);

        when(mock.checkLogin("Kevin","123")).thenReturn(new Account(1,null,"Kevin","kevin@hotmail.com","123",12.12));
        Account value = mock.checkLogin("Kevin","123");
        assertEquals(1,value.getAccount_Id());
    }

    @Test
    void checkAuth() throws SQLException, ClassNotFoundException {
        AccountDAO mock = Mockito.mock(AccountDAOImplementatie.class);

        when(mock.checkAuthentication(1,"123")).thenReturn(true);
        boolean value = mock.checkAuthentication(1,"123");
        assertEquals(value,true);
    }

    @Test
    void getUserData() throws SQLException, ClassNotFoundException {
        AccountDAO mock = Mockito.mock(AccountDAOImplementatie.class);

        when(mock.getUserData(1)).thenReturn(new Account(1,null,"Kevin","kevin@hotmail.com","123",12.12));
        Account value = mock.getUserData(1);
        assertEquals("Kevin", value.getAccount_Gebruikersnaam());
    }

}