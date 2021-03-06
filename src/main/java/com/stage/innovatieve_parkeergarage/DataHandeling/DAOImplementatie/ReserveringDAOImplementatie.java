package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Deze klassen is een zorgt voor het aanroepen en uitvoeren van statements naar de database
public class ReserveringDAOImplementatie implements ReserveringDAO {

    //Functie die het aanmaken van een reserving regelt door een reservering mee te sturen
    @Override
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException {

        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        System.out.println("INSERT INTO Reservering (Reservering_Parkeerplaats_Id, Reservering_Auto_Id, Reservering_Datum, Reservering_Begintijd, Reservering_Eindtijd) VALUES(" + reservering.getReservering_Parkeerplaats().getParkeerplaats_Id() + "," + reservering.getReservering_Auto().getAuto_Id() + ",'" + reservering.getReservering_Datum() + "','" + reservering.getReservering_Begintijd() + "','" + reservering.getReservering_Eindtijd() + "')");
        statement.executeUpdate("INSERT INTO Reservering (Reservering_Parkeerplaats_Id, Reservering_Auto_Id, Reservering_Datum, Reservering_Begintijd, Reservering_Eindtijd) VALUES(" + reservering.getReservering_Parkeerplaats().getParkeerplaats_Id() + "," + reservering.getReservering_Auto().getAuto_Id() + ",'" + reservering.getReservering_Datum() + "','" + reservering.getReservering_Begintijd() + "','" + reservering.getReservering_Eindtijd() + "')");
        connection.close();
        return true;


    }

    //Functie die een lijst met reserveringen ophaalt voor een specifieke gebruiker
    @Override
    public ArrayList getReserveringenGebruiker(int autoId) {
        try {
            ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();

            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Reservering where Reservering_Auto_Id = " + autoId);
            ArrayList reserveringen = new ArrayList<Reservering>();
            while (rs.next()) {
                Parkeerplaats parkeerplaats = parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1));
                Reservering reservering = new Reservering(rs.getInt(1), parkeerplaats, rs.getString(3), rs.getString(4), rs.getString(5), null);
                reserveringen.add(reservering);
            }
            rs.close();
            connection.close();
            return reserveringen;
        } catch (
                Exception e) {
            System.out.println("Reservering is niet opgehaald : SQL");
            return null;
        }
    }

    //Functie die alle reserveringen van een specifieke parkeergarage ophaalt op een specifieke datum.
    @Override
    public ArrayList getReserveringenGarage(int parkeergarage_Id, String datum) {
        try {
            ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();

            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            System.out.println("select * from Reservering, Parkeerplaats, parkeergarage where Reservering_Datum = '" + datum + "' and Parkeerplaats_Parkeergarage_Id = " + parkeergarage_Id + " and Parkeerplaats_Id = Reservering_parkeerplaats_Id and parkeergarage_id = parkeerplaats_parkeergarage_id");
            ResultSet rs = statement.executeQuery("select * from Reservering, Parkeerplaats, parkeergarage where Reservering_Datum = '" + datum + "' and Parkeerplaats_Parkeergarage_Id = " + parkeergarage_Id + " and Parkeerplaats_Id = Reservering_parkeerplaats_Id and parkeergarage_id = parkeerplaats_parkeergarage_id");
            ArrayList reserveringen = new ArrayList<Reservering>();
            while (rs.next()) {
                Parkeerplaats parkeerplaats = parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1));
                Reservering reservering = new Reservering(rs.getInt(1), parkeerplaats, rs.getString(3), rs.getString(4), rs.getString(5), null);
                reserveringen.add(reservering);
            }
            rs.close();
            connection.close();
            return reserveringen;
        } catch (
                Exception e) {
            System.out.println("Reservering is niet opgehaald : SQL");
            return null;
        }
    }

    @Override
    public Reservering getGemaakteReservering(String datum, String eindtijd, String begintijd, int autoid, int parkeergarageId) {
        try {
            ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
            Reservering reservering = null;
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Reservering where Reservering_Datum = '" + datum + "' and Reservering_Begintijd = '" + begintijd + "' and Reservering_Eindtijd = '" + eindtijd + "' and Reservering_Auto_Id = " + autoid);
            while (rs.next()) {
                Parkeerplaats parkeerplaats = parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1));
                reservering = new Reservering(rs.getInt(1), parkeerplaats, rs.getString(3), rs.getString(4), rs.getString(5), null);
            }
            rs.close();
            connection.close();
            return reservering;
        } catch (
                Exception e) {
            System.out.println("Reservering is niet opgehaald : SQL");
            return null;
        }
    }

    //verwijderd een reservering door een id mee te geven
    @Override
    public Boolean verwijderReserveringById(int id) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from Reservering where Reservering_Id = " + id);
        connection.close();
        return false;
    }

    //Haalt een reservering op door het id mee te geven
    @Override
    public Reservering getReserveringById(int id) throws ClassNotFoundException, SQLException {
        try {
            ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
            Reservering reservering = null;

            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Reservering where Reservering_Id = " + id);
            while (rs.next()) {
                Parkeerplaats parkeerplaats = parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1));
                reservering = new Reservering(rs.getInt(1), parkeerplaats, rs.getString(3), rs.getString(4), rs.getString(5), null);
            }
            rs.close();
            connection.close();
            return reservering;
        } catch (
                Exception e) {
            System.out.println("Reservering is niet opgehaald : SQL");
            return null;
        }
    }

    @Override
    public Boolean updateReserveringById(int id, String datum, String begintijd, String eindtijd, Parkeerplaats parkeerplaats) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            System.out.println("UPDATE Reservering SET Reservering_Begintijd = '"+begintijd+"' , Reservering_Eindtijd = '"+eindtijd+"', Reservering_Datum = '"+datum+"' , Reservering_Parkeerplaats_Id = "+parkeerplaats.getParkeerplaats_Id()+" Where Reservering_Id = "+id);
            statement.executeUpdate("UPDATE Reservering SET Reservering_Begintijd = '"+begintijd+"' , Reservering_Eindtijd = '"+eindtijd+"', Reservering_Datum = '"+datum+"' , Reservering_Parkeerplaats_Id = "+parkeerplaats.getParkeerplaats_Id()+" Where Reservering_Id = "+id);
            connection.close();
            return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public Reservering getReserveringPython(int autoId, String datum, int parkeergarageId) throws ClassNotFoundException, SQLException {
        ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Reservering,Parkeerplaats,Parkeergarage where Reservering_Parkeerplaats_Id = Parkeerplaats_Id and Parkeergarage_Id = Parkeerplaats_Parkeergarage_Id and Reservering_Auto_Id = " + autoId + " and Reservering_Datum = '" + datum + "' and Parkeergarage_Id = " + parkeergarageId);
        Reservering res = new Reservering(rs.getInt(1), parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1)), rs.getString(3), rs.getString(4), rs.getString(5), null);
        rs.close();
        connection.close();
        return res;
    }

    @Override
    public void updateInrijtijd(int resId, String tijd) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Reservering SET Resevering_Inrijtijd = '"+tijd+"' WHERE Reservering_Id = " + resId);
        connection.close();
    }

    @Override
    public void updateUitrijtijd(int resId, String tijd) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Reservering SET Reservering_Uitrijtijd = '"+tijd+"' WHERE Reservering_Id = " + resId);
        connection.close();
    }

    @Override
    public Reservering getTijden(int resId) throws ClassNotFoundException, SQLException {
        ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        System.out.println("SELECT * FROM Reservering where Reservering_Id = " + resId);
        ResultSet rs = statement.executeQuery("SELECT * FROM Reservering where Reservering_Id = " + resId);
        Reservering res = new Reservering(rs.getInt(1), parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1)), rs.getString(3), rs.getString(4), rs.getString(5), null);
        res.setInrijtijd(rs.getString(7));
        res.setUitrijtijd(rs.getString(8));
        rs.close();
        connection.close();
        return res;
    }

    @Override
    public Reservering getInrijtijd(int resId) throws ClassNotFoundException, SQLException {
        ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Reservering where Reservering_Id = " + resId);
        Reservering res = new Reservering(rs.getInt(1), parkeerplaatsDAO.getReserveringParkingspotGet(rs.getInt(1)), rs.getString(3), rs.getString(4), rs.getString(5), null);
        res.setInrijtijd(rs.getString(7));
        rs.close();
        connection.close();
        return res;
    }
}
