package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.BetalingDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AccountDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.BetalingDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;

@RestController
public class BetalingController {
    BetalingDAO betalingDAO = new BetalingDAOImplementatie();
    AccountDAO accountDAO = new AccountDAOImplementatie();

    @GetMapping("/betaling/create/{encodedkosten}/{encodeddatum}/{encodedtijd}/{encodedaccountid}/{encodeduserId}/{encodedToken}")
    public void getAuto(@PathVariable String encodedkosten,
                          @PathVariable String encodeduserId,
                          @PathVariable String encodedToken,
                          @PathVariable String encodeddatum,
                          @PathVariable String encodedtijd,
                        @PathVariable String encodedaccountid) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String datumDecoded = URLDecoder.decode(encodeddatum.replace("+", "%2B"));
        String datum = AESCryption.decrypt(datumDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tijdDecoded = URLDecoder.decode(encodedtijd.replace("+", "%2B"));
        String tijd = AESCryption.decrypt(tijdDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int id = Integer.parseInt(AESCryption.decrypt(idDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String accountIdDecoded = URLDecoder.decode(encodedaccountid.replace("+", "%2B"));
        int accountId = Integer.parseInt(AESCryption.decrypt(accountIdDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String kostenDecoded = URLDecoder.decode(encodedkosten.replace("+", "%2B"));
        Double kosten = Double.parseDouble(AESCryption.decrypt(kostenDecoded));

        if (accountDAO.checkAuthentication(id, token)) {
            betalingDAO.createBetaling(kosten,datum,tijd,accountId);
        }
    }
}
