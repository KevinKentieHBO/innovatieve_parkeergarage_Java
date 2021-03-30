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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@RestController
public class ReserveringController {

    AutoDAO autoDAO = new AutoDAOImplementatie();
    ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
    ReserveringDAO reserveringDAO = new ReserveringDAOImplementatie();

    @GetMapping("/reservering/{datum}/{begintijd}/{eindtijd}/{autoid}/{parkeergarageId}")
    public Boolean CatchReservering(@PathVariable String datum,
                                  @PathVariable String eindtijd,
                                  @PathVariable String begintijd,
                                  @PathVariable int autoid,
                                    @PathVariable int parkeergarageId) throws SQLException, ClassNotFoundException {

            Auto auto = autoDAO.getSpecificCar(autoid);
            Parkeerplaats parkeerplaats = new Parkeerplaats();
            parkeerplaats = parkeerplaats.vrijeParkeerplaats(parkeergarageId,datum,eindtijd,begintijd);
            Reservering reservering = new Reservering(parkeerplaats, begintijd, eindtijd, datum, auto);
            reserveringDAO.CreateReservering(reservering);
            return true;

    }

    @GetMapping("/reserveringen/{autoId}")
    public String getReserveringenGebruiker(@PathVariable int autoId)throws SQLException, ClassNotFoundException{
        JsonArray reserverenJsonArray = new JsonArray();
        ArrayList<Reservering> reserveringArray = reserveringDAO.getReserveringenGebruiker(autoId);
        Collections.sort(reserveringArray, new Comparator<Reservering>() {
            @Override
            public int compare(Reservering o1, Reservering o2) {
                int DatumCompare = o2.getReservering_Datum().compareTo(o1.getReservering_Datum());
                int BegintijdCompare = o1.getReservering_Begintijd().compareTo(o2.getReservering_Begintijd());

                return (DatumCompare == 0) ? BegintijdCompare : DatumCompare;
            }
        });
        Collections.reverse(reserveringArray);

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
