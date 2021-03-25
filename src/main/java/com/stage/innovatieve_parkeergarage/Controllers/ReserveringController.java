package com.stage.innovatieve_parkeergarage.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ReserveringController {

    @GetMapping("/reservering/{datum}/{begintijd}/{eindtijd}/{bestuurderid}/{autoid}")
    public String makeReservering(@PathVariable String datum,
                                  @PathVariable String eindtijd,
                                  @PathVariable String begintijd,
                                  @PathVariable int bestuurderid,
                                  @PathVariable int autoid) throws SQLException, ClassNotFoundException {
        return "";
    }
}
