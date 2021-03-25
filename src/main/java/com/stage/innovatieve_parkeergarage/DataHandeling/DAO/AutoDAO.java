package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Auto;

import java.sql.SQLException;

public interface AutoDAO {
    public Auto getSpecificCar(int auto_Id) throws ClassNotFoundException, SQLException;
}
