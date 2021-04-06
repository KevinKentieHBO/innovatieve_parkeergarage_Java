package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AbonnementDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AbonnementDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Objects.Abonnement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class AbonnementController {

    AbonnementDAO abonnementDAO = new AbonnementDAOImplementatie();

    //Functie die met een Get Rest Api alle parkeergarages in JSON format beschikbaar stelt.
    @GetMapping("/abonnement/{id}")
    public String GetAllAbonnementenParkeergarage(@PathVariable int id) throws SQLException, ClassNotFoundException {
        JsonArray abonnementenJsonArray = new JsonArray();
        ArrayList<Abonnement> abonnementArrayList = abonnementDAO.getAbonnementenParkeergarage(id);
        for(Abonnement abonnement : abonnementArrayList){
            JsonObject abonnementJson = new JsonObject();
            abonnementJson.addProperty("abonnement_Id",abonnement.getId());
            abonnementJson.addProperty("abonnement_Tarief",abonnement.getJaartarief());
            abonnementJson.addProperty("abonnement_Type",abonnement.getAbonnement_type().getNaam());
            abonnementJson.addProperty("abonnement_Beschrijving",abonnement.getAbonnement_type().getBeschrijving());
            abonnementenJsonArray.add(abonnementJson);
        }
        return abonnementenJsonArray.toString();
    }
}
