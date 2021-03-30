package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ParkeerplaatsDAOImplementatieTest {

    ParkeerplaatsDAO mock = Mockito.mock(ParkeerplaatsDAOImplementatie.class);

    @Test
    void getSpecificParkingspot() throws SQLException, ClassNotFoundException {
        when(mock.getSpecificParkingspot(1,100)).thenReturn(new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1));
        Parkeerplaats parkeerplaats = mock.getSpecificParkingspot(1,100);
        assertEquals(1,parkeerplaats.getParkeerplaats_Id());
    }

    @Test
    void getReserveringParkingspot() throws SQLException, ClassNotFoundException {
        when(mock.getReserveringParkingspotGet(12)).thenReturn(new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1));
        Parkeerplaats parkeerplaats = mock.getReserveringParkingspotGet(12);
        assertEquals(1,parkeerplaats.getParkeerplaats_Id());
    }

    @Test
    void getAllParkeerplaatsenParkeergarage() throws SQLException, ClassNotFoundException {
        ArrayList<Parkeerplaats> parkeerplaatsArrayList = new ArrayList<>();
        parkeerplaatsArrayList.add(new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1));
        parkeerplaatsArrayList.add(new Parkeerplaats(2, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,2));
        parkeerplaatsArrayList.add(new Parkeerplaats(3, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,3));
        parkeerplaatsArrayList.add(new Parkeerplaats(4, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,4));

        when(mock.getAllParkeerplaatsenParkeergarage(100)).thenReturn(parkeerplaatsArrayList);

        ArrayList<Parkeerplaats> alleParkeerplaatsen = mock.getAllParkeerplaatsenParkeergarage(100);
        assertEquals(4,alleParkeerplaatsen.size());
    }
}