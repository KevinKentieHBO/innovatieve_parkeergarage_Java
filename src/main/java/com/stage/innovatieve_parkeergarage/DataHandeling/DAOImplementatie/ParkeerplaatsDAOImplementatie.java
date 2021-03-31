package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;

import java.sql.*;
import java.util.ArrayList;

//Deze klassen is een zorgt voor het aanroepen en uitvoeren van statements naar de database
public class ParkeerplaatsDAOImplementatie implements ParkeerplaatsDAO {

    //Deze functie haalt een specifieke parkeerplaats op uit de database door een parkeerplaats id en een parkeergarage id mee te geven
    @Override
    public Parkeerplaats getSpecificParkingspot(int parkeerplaats_Id, int parkeergarage_Id) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Parkeerplaats,Parkeergarage WHERE Parkeerplaats_Id = "+parkeerplaats_Id+" AND Parkeergarage_Id = "+parkeergarage_Id+ " And parkeergarage_id = parkeerplaats_parkeergarage_id");
        Parkeerplaats parkeerplaats = new Parkeerplaats(rs.getInt(1), new Parkeergarage(rs.getInt(5), rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getString(11)),rs.getInt(3),rs.getInt(4));
        rs.close();
        connection.close();
        return parkeerplaats;
    }

    //Deze functie haalt een specifieke gereserveerde parkeerplaats op door een reservering Id mee te geven
    @Override
    public Parkeerplaats getReserveringParkingspotGet(int reservering_id) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Parkeerplaats,Parkeergarage,Reservering,Auto WHERE reservering_id="+reservering_id+" and Auto_id = Reservering_Auto_Id and Reservering_Parkeerplaats_Id = Parkeerplaats_Id and Parkeergarage_Id = Parkeerplaats_Parkeergarage_Id");
        Parkeerplaats parkeerplaats = new Parkeerplaats(rs.getInt(1), new Parkeergarage(rs.getInt(5), rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getString(11)),rs.getInt(3),rs.getInt(4));
        rs.close();
        connection.close();
        return parkeerplaats;
    }

    //Deze funtie haalt alle parkeerplaatsen van een parkeergarage op
    @Override
    public ArrayList<Parkeerplaats> getAllParkeerplaatsenParkeergarage(int parkeerplaats_Id) throws ClassNotFoundException, SQLException {
        ArrayList<Parkeerplaats> parkeerplaatsArrayList = new ArrayList<>();
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from parkeerplaats, parkeergarage where parkeergarage_Id = "+parkeerplaats_Id+" and parkeergarage_id = parkeerplaats_parkeergarage_Id");
        while(rs.next()){
            Parkeerplaats parkeerplaats = new Parkeerplaats(rs.getInt(1), new Parkeergarage(rs.getInt(5), rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getString(11)),rs.getInt(3),rs.getInt(4));
            parkeerplaatsArrayList.add(parkeerplaats);
        }
        rs.close();
        connection.close();
        return parkeerplaatsArrayList;
    }
}
