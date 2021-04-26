package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.BetaaltariefDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AccountDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.BetaaltariefDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import com.stage.innovatieve_parkeergarage.Objects.Account;
import com.stage.innovatieve_parkeergarage.Objects.Betaaltarief;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class BetaaltariefController {

    //Deze controller wordt gebruikt om data op te halen of af te geven die betrekking heeft op het betaaltarief

    //Initializeer een Domain Access Object
    BetaaltariefDAO betaaltariefdao = new BetaaltariefDAOImplementatie();
    AccountDAO accountDAO = new AccountDAOImplementatie();

    //Get rest api om alle betaaltarieven op te halen in een JSON format
    @GetMapping("/betaaltarief/{encodedparkeergarageid}/{encodeduserId}/{encodedToken}")
    public String betaaltarief(@PathVariable String encodeduserId,
                               @PathVariable String encodedToken,
                               @PathVariable String encodedparkeergarageid) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace( "+", "%2B" ));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace( "+", "%2B" ));
        int id = Integer.parseInt(AESCryption.decrypt(idDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String parkeergarageIdDecoded = URLDecoder.decode(encodedparkeergarageid.replace( "+", "%2B" ));
        int parkeergarageId = Integer.parseInt(AESCryption.decrypt(parkeergarageIdDecoded));

        if(accountDAO.checkAuthentication(id,token)) {
            JsonArray tariefJsonArray = new JsonArray();
            ArrayList<Betaaltarief> tarievenArray = betaaltariefdao.getAllBetaaltariefParkeergarage(parkeergarageId);
            for (Betaaltarief tarief : tarievenArray) {
                JsonObject betaaltariefJson = new JsonObject();
                betaaltariefJson.addProperty("id", tarief.getBetaaltarief_Id());
                betaaltariefJson.addProperty("type", tarief.getBetaaltarief_Type());
                betaaltariefJson.addProperty("waarde", tarief.getBetaaltarief_Waarde());
                tariefJsonArray.add(betaaltariefJson);
            }
            return AESCryption.encrypt(tariefJsonArray.toString());
        }else{
            return null;
        }
    }
}
