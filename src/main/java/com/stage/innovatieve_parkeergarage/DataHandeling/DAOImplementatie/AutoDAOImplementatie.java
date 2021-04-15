package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Auto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    @Override
    public Auto getCarByPlate(String auto_Kenteken) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Auto WHERE Auto_Kenteken = '"+auto_Kenteken+"'");
        Auto auto = new Auto(rs.getInt(1),rs.getString(2));
        rs.close();
        connection.close();
        return auto;
    }

    @Override
    public ArrayList<Auto> getAllCars(int id) throws ClassNotFoundException, SQLException {
        ArrayList<Auto> autoArrayList = new ArrayList<>();
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Auto, Account, Bestuurder, Bestuurder_Auto where Account.Account_Bestuurder_Id = Bestuurder.Bestuurder_Id and Bestuurder_Auto.Bestuurder_Auto_Bestuurder_Id = Bestuurder.Bestuurder_Id and Bestuurder_Auto.Bestuurder_Auto_Auto_Id = Auto.Auto_Id and Account.Account_Id = " + id);
        while (rs.next()){
            autoArrayList.add(new Auto(rs.getInt(1),rs.getString(2)));
        }
        rs.close();
        connection.close();
        return autoArrayList;
    }

    @Override
    public Boolean addAuto(int bestuurderid, String kenteken) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            statement.executeUpdate("INSERT INTO Auto (Auto_Kenteken) values ('" + kenteken + "')");
            ResultSet rs = statement2.executeQuery("SELECT * FROM AUTO WHERE Auto_Kenteken = '" + kenteken + "'");
            statement3.executeUpdate("INSERT INTO Bestuurder_Auto(Bestuurder_Auto_Bestuurder_Id, Bestuurder_Auto_Auto_Id, Bestuurder_Auto_Actief) VALUES (" + bestuurderid + "," + rs.getInt(1) + ",0)");
            rs.close();
            connection.close();
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
