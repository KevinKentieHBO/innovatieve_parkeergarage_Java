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
import java.util.ArrayList;

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

    @Test
    void getAutoByPlate() throws SQLException, ClassNotFoundException {
        AutoDAO mock = Mockito.mock(AutoDAOImplementatie.class);

        when(mock.getCarByPlate("123-AB-4")).thenReturn(new Auto(2,"123-AB-4"));
        Auto value = mock.getCarByPlate("123-AB-4");
        assertEquals(2,value.getAuto_Id());
    }

    @Test
    void getAllCars() throws SQLException, ClassNotFoundException {
        AutoDAO mock = Mockito.mock(AutoDAOImplementatie.class);
        ArrayList<Auto> autoArrayList = new ArrayList<>();
        autoArrayList.add(new Auto(1,"123-AB-3"));
        autoArrayList.add(new Auto(2,"123-AB-4"));

        when(mock.getAllCars(1)).thenReturn(autoArrayList);
        ArrayList<Auto> value = mock.getAllCars(1);
        assertEquals(value.size(),2);
    }
}