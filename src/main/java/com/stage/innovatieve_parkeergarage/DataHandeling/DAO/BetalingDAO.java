package com.stage.innovatieve_parkeergarage.DataHandeling.DAO;

import java.sql.SQLException;

public interface BetalingDAO {
    public void createBetaling(Double kosten, String datum, String tijd, int accountId) throws ClassNotFoundException, SQLException;
}
