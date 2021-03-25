package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Reservering;

import java.sql.SQLException;

public interface ReserveringDAO {
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException;
}
