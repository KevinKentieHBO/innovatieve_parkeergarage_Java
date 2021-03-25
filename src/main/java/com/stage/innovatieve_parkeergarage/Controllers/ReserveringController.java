package com.stage.innovatieve_parkeergarage.Controllers;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AutoDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ReserveringDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.parkeerplaatsDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ReserveringController {

    AutoDAO autoDAO = new AutoDAOImplementatie();
    ParkeerplaatsDAO parkeerplaatsDAO = new parkeerplaatsDAOImplementatie();
    ReserveringDAO reserveringDAO = new ReserveringDAOImplementatie();

    @GetMapping("/reservering/{datum}/{begintijd}/{eindtijd}/{parkeerplaatsid}/{autoid}")
    public Boolean CatchReservering(@PathVariable String datum,
                                  @PathVariable String eindtijd,
                                  @PathVariable String begintijd,
                                  @PathVariable int parkeerplaatsid,
                                  @PathVariable int autoid) throws SQLException, ClassNotFoundException {
        try {
            Auto auto = autoDAO.getSpecificCar(autoid);
            Parkeerplaats parkeerplaats = parkeerplaatsDAO.getSpecificParkingspot(parkeerplaatsid);
            Reservering reservering = new Reservering(parkeerplaats, begintijd, eindtijd, datum, auto);
            reserveringDAO.CreateReservering(reservering);
            return true;
        }catch(Exception e){
        System.out.println("Reservering is niet aangemaakt : CONTROLLER");
        return false;
    }
    }
}
