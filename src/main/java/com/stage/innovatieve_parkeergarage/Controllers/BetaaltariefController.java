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

    //Deze controller wordt gebruikt om data op te halen of af te geven die betrekking heeft op het betaaltarief

    //Initializeer een Domain Access Object
    BetaaltariefDAO betaaltariefdao = new BetaaltariefDAOImplementatie();

    //Get rest api om alle betaaltarieven op te halen in een JSON format
    @GetMapping("/betaaltarief")
    public String betaaltarief() throws SQLException, ClassNotFoundException {
        JsonArray tariefJsonArray = new JsonArray();
        ArrayList<Betaaltarief> tarievenArray = betaaltariefdao.getAllBetaaltarief();
        for(Betaaltarief tarief : tarievenArray){
            JsonObject betaaltariefJson = new JsonObject();
            betaaltariefJson.addProperty("id", tarief.getBetaaltarief_Id());
            betaaltariefJson.addProperty("type", tarief.getBetaaltarief_Type());
            betaaltariefJson.addProperty("waarde", tarief.getBetaaltarief_Waarde());
            tariefJsonArray.add(betaaltariefJson);
        }
        return tariefJsonArray.toString();
    }
}
