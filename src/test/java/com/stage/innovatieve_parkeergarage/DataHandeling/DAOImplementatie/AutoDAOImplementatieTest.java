package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AutoDAOImplementatieTest {

    @Test
    void getSpecificCar() throws SQLException, ClassNotFoundException {
        AutoDAO mock = Mockito.mock(AutoDAOImplementatie.class);

        when(mock.getSpecificCar(2)).thenReturn(new Auto(2,"123-AB-4"));
        Auto value = mock.getSpecificCar(2);
        assertEquals(2,value.getAuto_Id());
    }
}