package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.BetaaltariefDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.BetaaltariefDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Objects.Betaaltarief;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class BetaaltariefController {

    BetaaltariefDAO betaaltariefdao = new BetaaltariefDAOImplementatie();

    @GetMapping("/betaaltarief")
    public String betaaltarief() throws SQLException, ClassNotFoundException {
        JsonArray tariefJsonArray = new JsonArray();
        ArrayList<Betaaltarief> tarievenArray = betaaltariefdao.getAllBetaaltarief();
        for(Betaaltarief tarief : tarievenArray){
            JsonObject betaaltariefJson = new JsonObject();
            betaaltariefJson.addProperty("id", tarief.getId());
            betaaltariefJson.addProperty("type", tarief.getType());
            betaaltariefJson.addProperty("waarde", tarief.getWaarde());
            tariefJsonArray.add(betaaltariefJson);
        }
        return tariefJsonArray.toString();
    }
}
