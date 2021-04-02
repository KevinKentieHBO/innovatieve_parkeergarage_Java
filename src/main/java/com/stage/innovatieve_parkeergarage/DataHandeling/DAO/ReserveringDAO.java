package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;

import java.sql.SQLException;
import java.util.ArrayList;

//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface ReserveringDAO {
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException;
    public ArrayList getReserveringenGebruiker(int autoId);
    public ArrayList getReserveringenGarage(int parkeergarage_Id, String datum);
    public Reservering getGemaakteReservering(String datum, String eindtijd, String begintijd, int autoid, int parkeergarageId);
    public Boolean verwijderReserveringById(int id) throws ClassNotFoundException, SQLException;
    public Reservering getReserveringById(int id) throws ClassNotFoundException, SQLException;
    public Boolean updateReserveringById(int id, String datum, String begintijd, String eindtijd, Parkeerplaats parkeerplaats) throws ClassNotFoundException, SQLException;
}
