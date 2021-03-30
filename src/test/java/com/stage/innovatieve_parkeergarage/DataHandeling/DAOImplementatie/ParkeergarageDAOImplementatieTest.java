package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;


import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeergarageDAO;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ParkeergarageDAOImplementatieTest {

    @Test
    void getAllParkingFacilities() throws SQLException, ClassNotFoundException {
        ParkeergarageDAO mock = Mockito.mock(ParkeergarageDAOImplementatie.class);

        ArrayList<Parkeergarage> lijst = new ArrayList<>();
        lijst.add(new Parkeergarage(1,"Theater","Nieuwegein", 14,550,"07:00","22:00"));
        lijst.add(new Parkeergarage(2,"City Plaza","Nieuwegein", 2,700,"09:00","00:00"));

        when(mock.getAllParkingFacilities()).thenReturn(lijst);
        assertEquals(2,lijst.size());
    }
}