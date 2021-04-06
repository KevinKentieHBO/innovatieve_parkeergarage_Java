package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import com.stage.innovatieve_parkeergarage.Objects.Abonnement;

import java.sql.SQLException;
import java.util.ArrayList;

//Een Dao klassen die persistentie van de database mogelijk maakt zonder de details van de database te weergeven
public interface AbonnementDAO {
    public ArrayList<Abonnement> getAbonnementenParkeergarage(int id) throws SQLException, ClassNotFoundException;
}
