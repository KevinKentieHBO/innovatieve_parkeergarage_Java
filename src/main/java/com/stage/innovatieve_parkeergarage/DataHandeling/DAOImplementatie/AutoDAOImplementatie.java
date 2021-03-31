package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Auto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Deze klassen is een zorgt voor het aanroepen en uitvoeren van statements naar de database
public class AutoDAOImplementatie implements AutoDAO {

    //Deze functie haalt een specifieke auto op uit de database met het Auto Id
    @Override
    public Auto getSpecificCar(int auto_Id) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Auto WHERE Auto_Id = "+auto_Id);
        Auto auto = new Auto(rs.getInt(1),rs.getString(2));
        rs.close();
        connection.close();
        return auto;
    }
}
