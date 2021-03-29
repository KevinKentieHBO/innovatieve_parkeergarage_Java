package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeergarageDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ParkeergarageDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Objects.Parkeergarage;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ParkeergarageController {

    ParkeergarageDAO parkeergarageDAO = new ParkeergarageDAOImplementatie();

    @GetMapping("/parkeergarages")
    public String GetAllParkingFacilities() throws SQLException, ClassNotFoundException {
        JsonArray garageJsonArray = new JsonArray();
        ArrayList<Parkeergarage> parkeergarageArray = parkeergarageDAO.getAllParkingFacilities();
        for(Parkeergarage garage : parkeergarageArray){
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
        return garageJsonArray.toString();
    }
}
