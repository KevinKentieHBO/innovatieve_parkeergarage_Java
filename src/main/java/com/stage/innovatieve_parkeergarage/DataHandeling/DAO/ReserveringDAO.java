package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Reservering;

import java.sql.SQLException;
import java.util.ArrayList;

//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface ReserveringDAO {
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException;
    public ArrayList getReserveringenGebruiker(int autoId);
    public ArrayList getReserveringenGarage(int parkeergarage_Id, String datum);
}
