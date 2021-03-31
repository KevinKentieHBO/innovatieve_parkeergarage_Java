package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Auto;

import java.sql.SQLException;


//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface AutoDAO {
    public Auto getSpecificCar(int auto_Id) throws ClassNotFoundException, SQLException;
}
