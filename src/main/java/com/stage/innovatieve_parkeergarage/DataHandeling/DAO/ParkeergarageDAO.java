package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface ParkeergarageDAO {
    public ArrayList getAllParkingFacilities() throws ClassNotFoundException, SQLException;
}
