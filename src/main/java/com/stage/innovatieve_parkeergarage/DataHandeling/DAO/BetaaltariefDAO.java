package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface BetaaltariefDAO{
    public ArrayList getAllBetaaltarief() throws ClassNotFoundException, SQLException;
    public ArrayList getAllBetaaltariefParkeergarage(int parkeergarage_Id) throws ClassNotFoundException, SQLException;
}
