package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ReserveringDAOImplementatieTest {

    ReserveringDAO mock = Mockito.mock(ReserveringDAOImplementatie.class);

    @Test
    void createReservering() throws SQLException, ClassNotFoundException {
        Reservering res = new Reservering(1,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-01-2021",new Auto(1,"123-AB-4"));
        when(mock.CreateReservering(res)).thenReturn(true);
        Boolean resultaat = mock.CreateReservering(res);
        assertEquals(resultaat,true);
    }

    @Test
    void getReserveringenGebruiker() {
        ArrayList<Reservering> reserveringArrayList = new ArrayList<>();
        reserveringArrayList.add(new Reservering(1,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-01-2021",new Auto(1,"123-AB-4")));
        reserveringArrayList.add(new Reservering(2,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-02-2021",new Auto(1,"123-AB-4")));
        reserveringArrayList.add(new Reservering(3,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-03-2021",new Auto(1,"123-AB-4")));
        when(mock.getReserveringenGebruiker(1)).thenReturn(reserveringArrayList);
        ArrayList<Reservering> resultaat = mock.getReserveringenGebruiker(1);
        assertEquals(3,resultaat.size());
    }

    @Test
    void getReserveringenGarage() {
        ArrayList<Reservering> reserveringArrayList = new ArrayList<>();
        reserveringArrayList.add(new Reservering(1,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-01-2021",new Auto(1,"123-AB-4")));
        reserveringArrayList.add(new Reservering(2,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"14:00","15:00","01-01-2021",new Auto(1,"123-AB-4")));
        reserveringArrayList.add(new Reservering(3,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"15:00","16:00","01-01-2021",new Auto(1,"123-AB-4")));
        when(mock.getReserveringenGarage(1,"01-01-2021")).thenReturn(reserveringArrayList);
        assertEquals(3,reserveringArrayList.size());
    }

    @Test
    void verwijderReserveringById() throws SQLException, ClassNotFoundException {
        Reservering res = new Reservering(1,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-01-2021",new Auto(1,"123-AB-4"));
        when(mock.verwijderReserveringById(res.getReservering_Id())).thenReturn(true);
        Boolean resultaat = mock.verwijderReserveringById(res.getReservering_Id());
        assertEquals(resultaat,true);
    }

    @Test
    void getReserveringById() throws SQLException, ClassNotFoundException {
        Reservering res = new Reservering(1,new Parkeerplaats(1, new Parkeergarage(100,"Theater","Nieuwegein", 14,550,"07:00","22:00"),1,1),"13:00","14:00","01-01-2021",new Auto(1,"123-AB-4"));
        when(mock.getReserveringById(1)).thenReturn(res);
        Reservering opgehaaldeReservering = mock.getReserveringById(1);
        assertEquals(opgehaaldeReservering.getReservering_Id(),1);

    }
}