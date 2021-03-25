package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReserveringDAOImplementatie implements ReserveringDAO {

    @Override
    public Boolean CreateReservering(Reservering reservering) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("INSERT INTO Reservering (Reservering_Parkeerplaats_Id, Reservering_Auto_Id, Reservering_Datum, Reservering_Begintijd, Reservering_Eindtijd) VALUES(" + reservering.getReservering_Parkeerplaats().getParkeerplaats_Id() + "," + reservering.getReservering_Auto().getAuto_Id() + ",'" + reservering.getReservering_Datum() + "','" + reservering.getReservering_Begintijd() + "','" + reservering.getReservering_Eindtijd() + "')");
            return true;
        }catch(Exception e){
            System.out.println("Het maken van een reservering is niet gelukt");
            return false;
        }
    }
}
