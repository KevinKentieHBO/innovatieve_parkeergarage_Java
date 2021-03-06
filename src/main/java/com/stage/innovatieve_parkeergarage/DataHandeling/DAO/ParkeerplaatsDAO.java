package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;

import java.sql.SQLException;
import java.util.ArrayList;

//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface ParkeerplaatsDAO {
    public Parkeerplaats getSpecificParkingspot(int parkeerplaats_Id, int parkeergarage_Id) throws ClassNotFoundException, SQLException;

    public Parkeerplaats getReserveringParkingspotGet(int Auto_Id) throws ClassNotFoundException, SQLException;

    public ArrayList<Parkeerplaats> getAllParkeerplaatsenParkeergarage(int parkeerplaats_Id) throws ClassNotFoundException, SQLException;
}
