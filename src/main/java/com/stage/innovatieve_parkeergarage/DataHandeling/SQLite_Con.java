package com.stage.innovatieve_parkeergarage.DataHandeling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Klassen die de connectie met de database regelt
public class SQLite_Con {
    public Connection makeConnection() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/static/Innovatieve_Parkeergarage.db");
    }catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return connection;
    }
}
