package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AbonnementDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement_Type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AbonnementDAOImplementatie implements AbonnementDAO {
    @Override
    public ArrayList<Abonnement> getAbonnementenParkeergarage(int id) throws SQLException, ClassNotFoundException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Abonnement, Abonnement_Type where Abonnement_Parkeergarage_Id = " +id + " and Abonnement_Abbonement_Type_Id = Abonnement_Type_Id");
        ArrayList abonnementen = new ArrayList<Abonnement>();
        while(rs.next()) {
            Abonnement abonnement = new Abonnement(rs.getInt(1), new Abonnement_Type(rs.getInt(6),rs.getString(7),rs.getString(8)),rs.getInt(2),rs.getDouble(3));
            abonnementen.add(abonnement);
        }
        rs.close();
        connection.close();
        return abonnementen;
    }
}
