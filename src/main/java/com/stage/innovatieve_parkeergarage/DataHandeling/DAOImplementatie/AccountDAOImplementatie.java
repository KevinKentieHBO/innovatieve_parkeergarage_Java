package com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.SQLite_Con;
import com.stage.innovatieve_parkeergarage.Logica.Authentication;
import com.stage.innovatieve_parkeergarage.Objects.Account;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import com.stage.innovatieve_parkeergarage.Objects.Bestuurder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class AccountDAOImplementatie implements AccountDAO {
    @Override
    public Account checkLogin(String gebruikersnaam, String wachtwoord) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Account,Bestuurder,Bestuurder_Auto,Auto where Account_Gebruikersnaam = '"+gebruikersnaam+"' and Account_Wachtwoord = '"+wachtwoord+"' and Account_Bestuurder_Id = Bestuurder_Id and Bestuurder_Auto_Bestuurder_Id = Bestuurder_Id and Bestuurder_Auto_Auto_Id = Auto_Id and Bestuurder_Auto_Actief = 1");
            ArrayList<Auto> autoArrayList = new ArrayList<>();
            autoArrayList.add(new Auto(rs.getInt(15), rs.getString(16)));
            Account account = new Account(rs.getInt(1), new Bestuurder(rs.getInt(8), rs.getString(9), rs.getString(10), autoArrayList), rs.getString(2), rs.getString(3), "", rs.getDouble(5));
            account.setAccount_Token(Authentication.getToken());
            rs.close();
            connection.close();
            return account;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public Boolean checkAuthentication(int gebruikersId, String token) throws ClassNotFoundException, SQLException {
        Boolean checkLoginResultaat = false;

        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select Count(*) from Account where Account_Id = "+gebruikersId+" and Account_Token = '"+token+"'");
        if (rs.getInt(1) != 0)
        {
            checkLoginResultaat = true;
        }
        rs.close();
        connection.close();
        return checkLoginResultaat;
    }

    @Override
    public void setToken(Account account) throws ClassNotFoundException, SQLException {
        Connection connection = new SQLite_Con().makeConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Account set Account_Token = '"+ account.getAccount_Token()+"' WHERE Account_Id = " + account.getAccount_Id());
        connection.close();
    }

    @Override
    public Account getUserData(int userId) {
        try {
            Connection connection = new SQLite_Con().makeConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Account,Bestuurder where Account_Id = "+userId+" and Account_Bestuurder_Id = Bestuurder_Id");
            Account account = new Account(rs.getInt(1), new Bestuurder(rs.getInt(8), rs.getString(9), rs.getString(10), null), rs.getString(2), rs.getString(3), "", rs.getDouble(5));
            account.setAccount_Token(Authentication.getToken());
            rs.close();
            connection.close();
            return account;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
