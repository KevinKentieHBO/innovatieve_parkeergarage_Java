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

public class ReserveringDAOImplementatie implements ReserveringDAO {

    @Override
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Reservering (Reservering_Parkeerplaats_Id, Reservering_Auto_Id, Reservering_Datum, Reservering_Begintijd, Reservering_Eindtijd) VALUES(" + reservering.getReservering_Parkeerplaats().getParkeerplaats_Id() + "," + reservering.getReservering_Auto().getAuto_Id() + ",'" + reservering.getReservering_Datum() + "','" + reservering.getReservering_Begintijd() + "','" + reservering.getReservering_Eindtijd() + "')");
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Reservering is niet aangemaakt : SQL");
            return false;
        }

    }

    @Override
    public ArrayList getReserveringenGebruiker(int autoId) {
        try {
            ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();

            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Reservering where Reservering_Auto_Id = " + autoId);
            ArrayList reserveringen = new ArrayList<Reservering>();
            while(rs.next()) {
                Parkeerplaats parkeerplaats = parkeerplaatsDAO.getSpecificParkingspot(rs.getInt(6));
                Reservering reservering = new Reservering(rs.getInt(1),parkeerplaats,rs.getString(3),rs.getString(4),rs.getString(5),null);
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
}
