package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AbonnementDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement_Type;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;

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

    @Override
    public ArrayList<Abonnement> getAbonnementenAutoId(int autoid) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from Abonnement, Abonnement_Type, Abonnement_Auto, Parkeergarage where Abonnement_Auto_Auto_Id = "+autoid+" and Abonnement_Abbonement_Type_Id = Abonnement_Type_Id  and Abonnement_Auto_Abonnement_Id = Abonnement_Id and Parkeergarage_Id = Abonnement_Parkeergarage_Id");
        ArrayList abonnementen = new ArrayList<Abonnement>();
        while(rs.next()) {
            Abonnement abonnement = new Abonnement(rs.getInt(1), new Abonnement_Type(rs.getInt(6),rs.getString(7),rs.getString(8)),rs.getInt(2),rs.getDouble(3));
            abonnement.setBegindatum(rs.getString(12));
            abonnement.setEinddatum(rs.getString(13));
            abonnement.setParkeergarage(new Parkeergarage(rs.getInt(14),rs.getString(15),rs.getString(16),rs.getInt(17),rs.getInt(18),rs.getString(19),rs.getString(20)));
            abonnementen.add(abonnement);
        }
        rs.close();
        connection.close();
        return abonnementen;
    }
}
