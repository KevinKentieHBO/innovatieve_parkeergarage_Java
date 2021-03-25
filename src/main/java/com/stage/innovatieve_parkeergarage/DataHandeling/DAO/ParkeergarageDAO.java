package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ParkeergarageDAO {
    public ArrayList getAllParkingFacilities() throws ClassNotFoundException, SQLException;
}
