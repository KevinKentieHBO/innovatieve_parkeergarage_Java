package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AutoDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ReserveringDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ParkeerplaatsDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ReserveringController {

    AutoDAO autoDAO = new AutoDAOImplementatie();
    ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
    ReserveringDAO reserveringDAO = new ReserveringDAOImplementatie();

    @GetMapping("/reservering/{datum}/{begintijd}/{eindtijd}/{autoid}")
    public Boolean CatchReservering(@PathVariable String datum,
                                  @PathVariable String eindtijd,
                                  @PathVariable String begintijd,
                                  @PathVariable int autoid) throws SQLException, ClassNotFoundException {
        try {
            Auto auto = autoDAO.getSpecificCar(autoid);
            Parkeerplaats parkeerplaats = parkeerplaatsDAO.getSpecificParkingspot(1);
            Reservering reservering = new Reservering(parkeerplaats, begintijd, eindtijd, datum, auto);
            reserveringDAO.CreateReservering(reservering);
            return true;
        }catch(Exception e){
        System.out.println("Reservering is niet aangemaakt : CONTROLLER");
        return false;
    }
    }

    @GetMapping("/reserveringen/{autoId}")
    public String getReserveringenGebruiker(@PathVariable int autoId)throws SQLException, ClassNotFoundException{
        JsonArray reserverenJsonArray = new JsonArray();
        ArrayList<Reservering> reserveringArray = reserveringDAO.getReserveringenGebruiker(autoId);

        for(Reservering reservering : reserveringArray){
            JsonObject reserveringJson = new JsonObject();
            reserveringJson.addProperty("reservering_Id", reservering.getReservering_Id());
            reserveringJson.addProperty("reservering_Parkeerplaats_Id", reservering.getReservering_Parkeerplaats().getParkeerplaats_Id());
            reserveringJson.addProperty("reservering_Begintijd", reservering.getReservering_Begintijd());
            reserveringJson.addProperty("reservering_Eindtijd", reservering.getReservering_Eindtijd());
            reserveringJson.addProperty("reservering_Datum", reservering.getReservering_Datum());
            reserveringJson.addProperty("reservering_Parkeerplaats_laag", reservering.getReservering_Parkeerplaats().getParkeerplaats_Laag());
            reserveringJson.addProperty("reservering_Parkeerplaats_plek", reservering.getReservering_Parkeerplaats().getParkeerplaats_Locatie());
            reserveringJson.addProperty("reservering_Parkeergarage", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Naam());
            reserveringJson.addProperty("reservering_ParkeergarageLocatie", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Locatie());
            reserverenJsonArray.add(reserveringJson);
        }
        return reserverenJsonArray.toString();
    }
}
