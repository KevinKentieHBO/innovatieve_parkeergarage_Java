package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AccountDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import com.stage.innovatieve_parkeergarage.Objects.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;

@RestController
public class AccountController {

    AccountDAO accountDAO = new AccountDAOImplementatie();

    @GetMapping("/account/{encodedgebruikersnaam}/{encodedwachtwoord}")
    public String login(@PathVariable String encodedgebruikersnaam,
                        @PathVariable String encodedwachtwoord) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecodedGb = URLDecoder.decode(encodedgebruikersnaam.replace( "+", "%2B" ));
        System.out.println(urlDecodedGb);
        String gebruikersnaam = AESCryption.decrypt(urlDecodedGb);
        System.out.println(gebruikersnaam);

        String urlDecodedWw = URLDecoder.decode(encodedwachtwoord.replace( "+", "%2B" ));
        String wachtwoord = AESCryption.decrypt(urlDecodedWw);

        //Leeg JSON object
        JsonObject resultaatJson = new JsonObject();

        Account account = accountDAO.checkLogin(gebruikersnaam,wachtwoord);

        if(account != null) {
            accountDAO.setToken(account);
        }

        //check of de inloggegevens matchen
        if(account != null){
            resultaatJson.addProperty("Resultaat","true");
            resultaatJson.addProperty("Account_Id", account.getAccount_Id());
            resultaatJson.addProperty("Account_Token", account.getAccount_Token());
            resultaatJson.addProperty("Account_Actief_Kenteken_Id", account.getAccount_Bestuurder().getMijnAutos().get(0).getAuto_Id());
            resultaatJson.addProperty("Bestuurder_Naam", account.getAccount_Bestuurder().getBestuurder_Naam());
            return AESCryption.encrypt(resultaatJson.toString());
        }else{
            // Aanroepen van de SQL delete reservering
            resultaatJson.addProperty("Resultaat","false");
            resultaatJson.addProperty("Account_Id", 0);
            resultaatJson.addProperty("Account_Token", "false");
            resultaatJson.addProperty("Account_Actief_Kenteken_Id", "false");
            resultaatJson.addProperty("Bestuurder_Naam", "false");

            return AESCryption.encrypt(resultaatJson.toString());
        }
    }

    @GetMapping("/accountcheck/{gebruikersid}/{token}")
    public String checkAuth(@PathVariable int gebruikersid,
                        @PathVariable String token) throws SQLException, ClassNotFoundException {

        //Leeg JSON object
        JsonObject resultaatJson = new JsonObject();

        if(accountDAO.checkAuthentication(gebruikersid,token)){
            resultaatJson.addProperty("resultaat","true");
            return AESCryption.encrypt(resultaatJson.toString());
        } else {
            // Aanroepen van de SQL delete reservering
            resultaatJson.addProperty("resultaat", "false");
            return AESCryption.encrypt(resultaatJson.toString());
        }
    }

    @GetMapping("/userdata/{encodedgebruikersid}/{encodedtoken}")
    public String getUserData(@PathVariable String encodedgebruikersid,
                              @PathVariable String encodedtoken) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecodedGb = URLDecoder.decode(encodedgebruikersid.replace( "+", "%2B" ));
        int gebruikersid = Integer.parseInt(AESCryption.decrypt(urlDecodedGb));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedtoken.replace( "+", "%2B" ));
        String token = AESCryption.decrypt(tokenDecoded);

        if(accountDAO.checkAuthentication(gebruikersid,token)) {
            Account account = accountDAO.getUserData(gebruikersid);

            //Leeg JSON object
            JsonObject resultaatJson = new JsonObject();
            resultaatJson.addProperty("Account_Email", account.getAccount_Email());
            resultaatJson.addProperty("Account_Saldo", account.getAccount_Saldo());
            resultaatJson.addProperty("Bestuurder_Geboortedatum", account.getAccount_Bestuurder().getBestuurder_Geboortedatum());

            return AESCryption.encrypt(resultaatJson.toString());
        }else{
            return null;
        }
    }
}
