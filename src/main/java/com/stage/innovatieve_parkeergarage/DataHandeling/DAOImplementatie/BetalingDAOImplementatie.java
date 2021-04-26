package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.BetalingDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BetalingDAOImplementatie implements BetalingDAO {

    @Override
    public void createBetaling(Double kosten, String datum, String tijd, int accountId) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        System.out.println("INSERT INTO Betaling (Betaling_Bedrag, Betaling_Datum, Betaling_Tijd, Betaling_Account_Id) values ("+kosten+",'"+datum+"','"+tijd+"',"+accountId+")");
        statement.executeUpdate("INSERT INTO Betaling (Betaling_Bedrag, Betaling_Datum, Betaling_Tijd, Betaling_Account_Id) values ("+kosten+",'"+datum+"','"+tijd+"',"+accountId+")");
        connection.close();
    }
}
