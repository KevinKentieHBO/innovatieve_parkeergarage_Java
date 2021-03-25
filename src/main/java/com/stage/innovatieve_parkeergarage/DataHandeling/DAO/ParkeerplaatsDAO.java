package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;

import java.sql.SQLException;

public interface ParkeerplaatsDAO {
    public Parkeerplaats getSpecificParkingspot(int parkeerplaats_Id) throws ClassNotFoundException, SQLException;
}
