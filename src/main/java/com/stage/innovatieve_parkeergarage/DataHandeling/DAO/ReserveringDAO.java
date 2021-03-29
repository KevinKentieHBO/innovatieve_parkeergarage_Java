package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Reservering;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReserveringDAO {
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException;
    public ArrayList getReserveringenGebruiker(int autoId);
}
