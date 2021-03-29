package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkeerplaatsDAOImplementatie implements ParkeerplaatsDAO {

    @Override
    public Parkeerplaats getSpecificParkingspot(int parkeerplaats_Id) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Parkeerplaats,Parkeergarage WHERE Parkeerplaats_Id = "+parkeerplaats_Id+" AND Parkeergarage_Id = Parkeerplaats_Parkeergarage_Id");
        Parkeerplaats parkeerplaats = new Parkeerplaats(rs.getInt(1), new Parkeergarage(rs.getInt(5), rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getString(11)),rs.getInt(3),rs.getInt(4));
        rs.close();
        connection.close();
        return parkeerplaats;
    }
}
