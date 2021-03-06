package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AbonnementDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AccountDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AbonnementDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AccountDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement_Type;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AbonnementController {

    AbonnementDAO abonnementDAO = new AbonnementDAOImplementatie();
    AccountDAO accountDAO = new AccountDAOImplementatie();

    //Functie die met een Get Rest Api alle abonnementen van een parkeergarage in JSON format beschikbaar stelt.
    @GetMapping("/abonnement/{encryptedId}/{encodeduserId}/{encodedToken}")
    public String GetAllAbonnementenParkeergarage(@PathVariable String encryptedId,
                                                  @PathVariable String encodeduserId,
                                                  @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecoded = URLDecoder.decode(encryptedId.replace("+", "%2B"));
        int id = Integer.parseInt(AESCryption.decrypt(urlDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int userId = Integer.parseInt(AESCryption.decrypt(idDecoded));

        if (accountDAO.checkAuthentication(userId, token)) {
            //Lege JSON Array
            JsonArray abonnementenJsonArray = new JsonArray();

            //int id = Integer.parseInt(AESCryption.decrypt(encryptedId));
            ArrayList<Abonnement> abonnementArrayList = abonnementDAO.getAbonnementenParkeergarage(id);

            //Sorteren van de lijst aflopend op datum
            Collections.sort(abonnementArrayList, new Comparator<Abonnement>() {
                @Override
                public int compare(Abonnement o1, Abonnement o2) {

                    int prijsComapre = Double.compare(o1.getJaartarief(), o2.getJaartarief());

                    return (prijsComapre == 0) ? prijsComapre : prijsComapre;
                }
            });

            for (Abonnement abonnement : abonnementArrayList) {
                JsonObject abonnementJson = new JsonObject();
                abonnementJson.addProperty("abonnement_Id", abonnement.getId());
                abonnementJson.addProperty("abonnement_Tarief", abonnement.getJaartarief());
                abonnementJson.addProperty("abonnement_Type", abonnement.getAbonnement_type().getNaam());
                abonnementJson.addProperty("abonnement_Beschrijving", abonnement.getAbonnement_type().getBeschrijving());
                abonnementenJsonArray.add(abonnementJson);
            }
            return AESCryption.encrypt(abonnementenJsonArray.toString());
        } else {
            return null;
        }
    }

    //Functie die met een Get Rest Api alle abonnementen van een auto in JSON format beschikbaar stelt.
    @GetMapping("/abonnement/get/{encodedautoid}/{encodeduserId}/{encodedToken}")
    public String GetAllAbonnementenAuto(@PathVariable String encodedautoid,
                                         @PathVariable String encodeduserId,
                                         @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecoded = URLDecoder.decode(encodedautoid.replace("+", "%2B"));
        int id = Integer.parseInt(AESCryption.decrypt(urlDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int userId = Integer.parseInt(AESCryption.decrypt(idDecoded));

        if (accountDAO.checkAuthentication(userId, token)) {
            JsonArray abonnementenJsonArray = new JsonArray();
            ArrayList<Abonnement> abonnementArrayList = abonnementDAO.getAbonnementenAutoId(id);

            //Sorteren van de lijst aflopend op datum
            Collections.sort(abonnementArrayList, new Comparator<Abonnement>() {
                @Override
                public int compare(Abonnement o1, Abonnement o2) {
                    Calendar calendarReservering1 = Calendar.getInstance();
                    Calendar calendarReservering2 = Calendar.getInstance();
                    try {
                        Date datumReservering1 = new SimpleDateFormat("dd-MM-yyyy").parse(o1.getEinddatum());
                        Date datumReservering2 = new SimpleDateFormat("dd-MM-yyyy").parse(o2.getEinddatum());
                        calendarReservering1.setTime(datumReservering1);
                        calendarReservering2.setTime(datumReservering2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int DagCompare = calendarReservering2.compareTo(calendarReservering1);

                    return (DagCompare == 0) ? DagCompare : DagCompare;
                }
            });

            for (Abonnement abonnement : abonnementArrayList) {
                JsonObject abonnementJson = new JsonObject();
                abonnementJson.addProperty("abonnement_Id", abonnement.getId());
                abonnementJson.addProperty("abonnement_Tarief", abonnement.getJaartarief());
                abonnementJson.addProperty("abonnement_Type", abonnement.getAbonnement_type().getNaam());
                abonnementJson.addProperty("abonnement_Beschrijving", abonnement.getAbonnement_type().getBeschrijving());
                abonnementJson.addProperty("abonnement_Begindatum", abonnement.getBegindatum());
                abonnementJson.addProperty("abonnement_Einddatum", abonnement.getEinddatum());
                abonnementJson.addProperty("abonnement_Status", abonnement.isAbonnementActief());
                abonnementJson.addProperty("parkeergarage_Naam", abonnement.getParkeergarage().getParkeergarage_Naam());
                abonnementenJsonArray.add(abonnementJson);
            }
            return AESCryption.encrypt(abonnementenJsonArray.toString());
        }
        else{
            return null;
        }
    }

    //Functie die met een Get Rest Api alle abonnementen van een parkeergarage in JSON format beschikbaar stelt.
    @GetMapping("/abonnement/{encodedautoid}/{encodedgarageid}/{encodeduserId}/{encodedToken}")
    public String GetAllAbonnementenParkeergarage(@PathVariable String encodedautoid,
                                                  @PathVariable String encodedgarageid,
                                                  @PathVariable String encodeduserId,
                                                  @PathVariable String encodedToken) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecoded = URLDecoder.decode(encodedautoid.replace("+", "%2B"));
        int autoId = Integer.parseInt(AESCryption.decrypt(urlDecoded));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecodedGId = URLDecoder.decode(encodedgarageid.replace("+", "%2B"));
        int garageId = Integer.parseInt(AESCryption.decrypt(urlDecodedGId));

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String tokenDecoded = URLDecoder.decode(encodedToken.replace("+", "%2B"));
        String token = AESCryption.decrypt(tokenDecoded);

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String idDecoded = URLDecoder.decode(encodeduserId.replace("+", "%2B"));
        int userId = Integer.parseInt(AESCryption.decrypt(idDecoded));

        if (accountDAO.checkAuthentication(userId, token)) {
            JsonArray abonnementenJsonArray = new JsonArray();
            ArrayList<Abonnement> abonnementArrayList = abonnementDAO.getAbonnementenAutoIdParkeergarage(autoId, garageId);
            for (Abonnement abonnement : abonnementArrayList) {
                if (abonnement.isAbonnementActief() == "Actief") {
                    JsonObject abonnementJson = new JsonObject();
                    abonnementJson.addProperty("abonnement_Id", abonnement.getId());
                    abonnementJson.addProperty("abonnement_Tarief", abonnement.getJaartarief());
                    abonnementJson.addProperty("abonnement_Type", abonnement.getAbonnement_type().getNaam());
                    abonnementJson.addProperty("abonnement_Beschrijving", abonnement.getAbonnement_type().getBeschrijving());
                    abonnementJson.addProperty("abonnement_Begindatum", abonnement.getBegindatum());
                    abonnementJson.addProperty("abonnement_Einddatum", abonnement.getEinddatum());
                    abonnementJson.addProperty("abonnement_Status", abonnement.isAbonnementActief());
                    abonnementJson.addProperty("parkeergarage_Naam", abonnement.getParkeergarage().getParkeergarage_Naam());
                    abonnementenJsonArray.add(abonnementJson);
                }
            }
            return AESCryption.encrypt(abonnementenJsonArray.toString());
        }
        else{
            return null;
        }
    }
}
