package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Account;

import java.sql.SQLException;

public interface AccountDAO {
    public Account checkLogin(String gebruikersnaam, String wachtwoord) throws ClassNotFoundException, SQLException;
    public Boolean checkAuthentication(int gebruikersId, String token) throws ClassNotFoundException, SQLException;
    public void setToken(Account account) throws ClassNotFoundException, SQLException;
    public Account getUserData(int userId);
}
