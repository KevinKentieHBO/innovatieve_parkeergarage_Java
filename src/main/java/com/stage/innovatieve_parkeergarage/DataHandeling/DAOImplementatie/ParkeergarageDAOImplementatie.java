package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeergarageDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ParkeergarageDAOImplementatie implements ParkeergarageDAO {
    @Override
    public ArrayList getAllParkingFacilities() throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Parkeergarage");
        ArrayList parkeergarages = new ArrayList<Parkeergarage>();
        while(rs.next()) {
            Parkeergarage parkeergarage = new Parkeergarage(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7));
            parkeergarages.add(parkeergarage);
        }
        rs.close();
        connection.close();
        return parkeergarages;
    }
}
