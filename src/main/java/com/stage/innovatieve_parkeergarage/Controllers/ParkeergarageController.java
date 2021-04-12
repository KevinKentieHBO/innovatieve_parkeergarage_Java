package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeergarageDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AccountDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ParkeergarageDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ParkeergarageController {

    //Deze controller wordt gebruikt om data op te halen of af te geven die betrekking heeft op de Parkeergarage

    //Initializeer een Domain Access Object
    ParkeergarageDAO parkeergarageDAO = new ParkeergarageDAOImplementatie();
    AccountDAO accountDAO = new AccountDAOImplementatie();

    //Functie die met een Get Rest Api alle parkeergarages in JSON format beschikbaar stelt.
    @GetMapping("/parkeergarages/{encodeduserId}/{encodedToken}")
    public String GetAllParkingFacilities(@PathVariable String encodeduserId,
                                          @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace( "+", "%2B" ));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace( "+", "%2B" ));
        int id = Integer.parseInt(AESCryption.decrypt(idDecoded));

        if(accountDAO.checkAuthentication(id,token)) {
            JsonArray garageJsonArray = new JsonArray();
            ArrayList<Parkeergarage> parkeergarageArray = parkeergarageDAO.getAllParkingFacilities();
            for (Parkeergarage garage : parkeergarageArray) {
                JsonObject parkeergarageJson = new JsonObject();
                parkeergarageJson.addProperty("parkeergarage_Id", garage.getParkeergarage_Id());
                parkeergarageJson.addProperty("parkeergarage_Naam", garage.getParkeergarage_Naam());
                parkeergarageJson.addProperty("parkeergarage_Locatie", garage.getParkeergarage_Locatie());
                parkeergarageJson.addProperty("parkeergarage_Parkeerlagen", garage.getParkeergarage_Parkeerlagen());
                parkeergarageJson.addProperty("parkeergarage_Aantal_Plaatsen", garage.getParkeergarage_Aantal_Plaatsen());
                parkeergarageJson.addProperty("parkeergarage_Opening", garage.getParkeergarage_Opening());
                parkeergarageJson.addProperty("parkeergarage_Sluiting", garage.getParkeergarage_Sluiting());
                garageJsonArray.add(parkeergarageJson);
            }
            return AESCryption.encrypt(garageJsonArray.toString());
        }else{
            return null;
        }
    }
}
