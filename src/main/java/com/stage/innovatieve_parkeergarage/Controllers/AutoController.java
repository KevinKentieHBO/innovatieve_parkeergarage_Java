package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AccountDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AutoDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class AutoController {
    //Initializeer een Domain Access Object
    AutoDAO autoDAO = new AutoDAOImplementatie();
    AccountDAO accountDAO = new AccountDAOImplementatie();

    //Get rest api om een auto op te halen in een JSON format
    @GetMapping("/auto/get/{encodedKenteken}/{encodeduserId}/{encodedToken}")
    public String getAuto(@PathVariable String encodedKenteken,
                          @PathVariable String encodeduserId,
                          @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int id = Integer.parseInt(AESCryption.decrypt(idDecoded));
        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String kentekenDecoded = URLDecoder.decode(encodedKenteken.replace("+", "%2B"));
        String kenteken = AESCryption.decrypt(kentekenDecoded);

        if (accountDAO.checkAuthentication(id, token)) {
            Auto auto = autoDAO.getCarByPlate(kenteken);
            System.out.println(auto.getAuto_Id());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("AutoId", auto.getAuto_Id());
            return AESCryption.encrypt(jsonObject.toString());
        } else {
            return null;
        }
    }

    //Get rest api om alle auto's op te halen in een JSON format
    @GetMapping("/allauto/get/{encodeduserId}/{encodedToken}")
    public String getAllAutos(@PathVariable String encodeduserId,
                              @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        JsonArray jsonArray = new JsonArray();
        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int id = Integer.parseInt(AESCryption.decrypt(idDecoded));


        if (accountDAO.checkAuthentication(id, token)) {
            ArrayList<Auto> autolijst = autoDAO.getAllCars(id);
            for (Auto auto : autolijst) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("Auto_Id", auto.getAuto_Id());
                jsonObject.addProperty("Auto_Kenteken", auto.getAuto_Kenteken());
                jsonArray.add(jsonObject);
            }
            return AESCryption.encrypt(jsonArray.toString());
        } else {
            return null;
        }
    }

    //Get rest api om alle auto's op te halen in een JSON format
    @GetMapping("/addauto/{encodedKenteken}/{encodeduserId}/{encodedToken}")
    public String addAuto(@PathVariable String encodedKenteken,
                          @PathVariable String encodeduserId,
                          @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        JsonArray jsonArray = new JsonArray();
        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int id = Integer.parseInt(AESCryption.decrypt(idDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String kentekenDecoded = URLDecoder.decode(encodedKenteken.replace("+", "%2B"));
        String kenteken = AESCryption.decrypt(kentekenDecoded);


        if (accountDAO.checkAuthentication(id, token)) {
            if (autoDAO.addAuto(id, kenteken)) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("resultaat", "true");
                return AESCryption.encrypt(jsonObject.toString());
            } else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("resultaat", "false");
                return AESCryption.encrypt(jsonObject.toString());
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("resultaat", "false");
        return AESCryption.encrypt(jsonObject.toString());
    }
}
