package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.BetaaltariefDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Betaaltarief;


public class BetaaltariefDAOImplementatie implements BetaaltariefDAO {

    @Override
    public ArrayList getAllBetaaltarief() throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Betaaltarief");
        ArrayList tarieven = new ArrayList<Betaaltarief>();
        while(rs.next()) {
            Betaaltarief tarief = new Betaaltarief(rs.getInt(1), rs.getString(2), rs.getDouble(3),null);
            tarieven.add(tarief);
        }
        rs.close();
        connection.close();
        return tarieven;
    }
}
