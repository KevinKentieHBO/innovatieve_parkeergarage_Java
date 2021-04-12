package com.stage.innovatieve_parkeergarage.Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.AutoDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.AutoDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ReserveringDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ParkeerplaatsDAOImplementatie;
import com.stage.innovatieve_parkeergarage.Logica.AESCryption;
import com.stage.innovatieve_parkeergarage.Objects.Auto;
import com.stage.innovatieve_parkeergarage.Objects.Parkeerplaats;
import com.stage.innovatieve_parkeergarage.Objects.Reservering;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class ReserveringController {

    //Deze controller wordt gebruikt om data op te halen of af te geven die betrekking heeft op de Reservering

    //Initializeer een Domain Access Object
    AutoDAO autoDAO = new AutoDAOImplementatie();
    ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
    ReserveringDAO reserveringDAO = new ReserveringDAOImplementatie();

    //Functie die via een Post Rest Api een Reservering kan verwerken naar de database
    @GetMapping("/reservering/{encodeddatum}/{encodedbegintijd}/{encodedeindtijd}/{encodedautoid}/{encodedparkeergarageId}")
    public Boolean CatchReservering(@PathVariable String encodeddatum,
                                  @PathVariable String encodedeindtijd,
                                  @PathVariable String encodedbegintijd,
                                  @PathVariable String encodedautoid,
                                    @PathVariable String encodedparkeergarageId) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecodedPid = URLDecoder.decode(encodedparkeergarageId.replace( "+", "%2B" ));
        int parkeergarageId = Integer.parseInt(AESCryption.decrypt(urlDecodedPid));

        String urlDecodedDat = URLDecoder.decode(encodeddatum.replace( "+", "%2B" ));
        String datum = AESCryption.decrypt(urlDecodedDat);

        String urlDecodedBtijd = URLDecoder.decode(encodedbegintijd.replace( "+", "%2B" ));
        String begintijd = AESCryption.decrypt(urlDecodedBtijd);

        String urlDecodedEtijd = URLDecoder.decode(encodedeindtijd.replace( "+", "%2B" ));
        String eindtijd = AESCryption.decrypt(urlDecodedEtijd);

        String urlDecodedAid = URLDecoder.decode(encodedautoid.replace( "+", "%2B" ));
        int autoid = Integer.parseInt(AESCryption.decrypt(urlDecodedAid));

        Auto auto = autoDAO.getSpecificCar(autoid);
        Parkeerplaats parkeerplaats = new Parkeerplaats();
        parkeerplaats = parkeerplaats.vrijeParkeerplaats(parkeergarageId,datum,eindtijd,begintijd);
        Reservering reservering = new Reservering(parkeerplaats, begintijd, eindtijd, datum, auto);
        reserveringDAO.CreateReservering(reservering);
        return true;
    }

    //Functie die via een put Rest Api een reservering kan wijzigen door de te wijzige data en het idee door te geven
    @GetMapping("/reservering/update/{encodeddatum}/{encodedbegintijd}/{encodedeindtijd}/{encodedreserveringid}/{encodedparkeergarageid}")
    public String updateReservering(@PathVariable String encodeddatum,
                                    @PathVariable String encodedeindtijd,
                                    @PathVariable String encodedbegintijd,
                                    @PathVariable String encodedreserveringid,
                                    @PathVariable String encodedparkeergarageid) throws SQLException, ClassNotFoundException {

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecodedPid = URLDecoder.decode(encodedparkeergarageid.replace( "+", "%2B" ));
        int parkeergarageid = Integer.parseInt(AESCryption.decrypt(urlDecodedPid));

        String urlDecodedDat = URLDecoder.decode(encodeddatum.replace( "+", "%2B" ));
        String datum = AESCryption.decrypt(urlDecodedDat);

        String urlDecodedBtijd = URLDecoder.decode(encodedbegintijd.replace( "+", "%2B" ));
        String begintijd = AESCryption.decrypt(urlDecodedBtijd);

        String urlDecodedEtijd = URLDecoder.decode(encodedeindtijd.replace( "+", "%2B" ));
        String eindtijd = AESCryption.decrypt(urlDecodedEtijd);

        String urlDecodedresId = URLDecoder.decode(encodedreserveringid.replace( "+", "%2B" ));
        int reserveringid = Integer.parseInt(AESCryption.decrypt(urlDecodedresId));

        JsonObject resultaatJson = new JsonObject();
        Parkeerplaats parkeerplaats = new Parkeerplaats();
        Reservering reservering = reserveringDAO.getReserveringById(reserveringid);
        reservering.setReservering_Datum(datum);
        System.out.println(reservering.getReservering_Datum());
        reservering.setReservering_Eindtijd(eindtijd);
        reservering.setReservering_Begintijd(begintijd);
        reservering.setReservering_Parkeerplaats(parkeerplaats.vrijeParkeerplaats(parkeergarageid,reservering.getReservering_Datum(),reservering.getReservering_Eindtijd(),reservering.getReservering_Begintijd()));
        if(reserveringDAO.updateReserveringById(reservering.getReservering_Id(),reservering.getReservering_Datum(),reservering.getReservering_Begintijd(),reservering.getReservering_Eindtijd(),reservering.getReservering_Parkeerplaats())){
            System.out.println("true");
            resultaatJson.addProperty("resultaat","true");
            return AESCryption.encrypt(resultaatJson.toString());
        }else {
            // Aanroepen van de SQL delete reservering
            System.out.println("false");
            resultaatJson.addProperty("resultaat", "false");
            return AESCryption.encrypt(resultaatJson.toString());
        }
    }

    //Functie die via een get Rest Api een reservering kan vinden op datum, begintijd, eindtijd, autoid en parkeergarageid
    @GetMapping("/reservering/get/{encodeddatum}/{encodedbegintijd}/{encodedeindtijd}/{encodedautoid}/{encodedparkeergarageId}")
    public String getGemaakteReservering(@PathVariable String encodeddatum,
                                         @PathVariable String encodedeindtijd,
                                         @PathVariable String encodedbegintijd,
                                         @PathVariable String encodedautoid,
                                         @PathVariable String encodedparkeergarageId) throws SQLException, ClassNotFoundException{

        //Decodeer de url van URLencode naar Base64, vervolgens decrypt met AES128
        String urlDecodedAid = URLDecoder.decode(encodedautoid.replace( "+", "%2B" ));
        int autoid = Integer.parseInt(AESCryption.decrypt(urlDecodedAid));

        String urlDecodedDat = URLDecoder.decode(encodeddatum.replace( "+", "%2B" ));
        String datum = AESCryption.decrypt(urlDecodedDat);

        String urlDecodedBtijd = URLDecoder.decode(encodedbegintijd.replace( "+", "%2B" ));
        String begintijd = AESCryption.decrypt(urlDecodedBtijd);

        String urlDecodedEtijd = URLDecoder.decode(encodedeindtijd.replace( "+", "%2B" ));
        String eindtijd = AESCryption.decrypt(urlDecodedEtijd);

        String urlDecodedPid = URLDecoder.decode(encodedparkeergarageId.replace( "+", "%2B" ));
        int parkeergarageId = Integer.parseInt(AESCryption.decrypt(urlDecodedPid));

        JsonObject reserveringJson = new JsonObject();
        Reservering reservering = reserveringDAO.getGemaakteReservering(datum,eindtijd,begintijd,autoid,parkeergarageId);
        reserveringJson.addProperty("reservering_Id", reservering.getReservering_Id());
        reserveringJson.addProperty("reservering_Parkeerplaats_Id", reservering.getReservering_Parkeerplaats().getParkeerplaats_Id());
        reserveringJson.addProperty("reservering_Begintijd", reservering.getReservering_Begintijd());
        reserveringJson.addProperty("reservering_Eindtijd", reservering.getReservering_Eindtijd());
        reserveringJson.addProperty("reservering_Datum", reservering.getReservering_Datum());
        reserveringJson.addProperty("reservering_Parkeerplaats_laag", reservering.getReservering_Parkeerplaats().getParkeerplaats_Laag());
        reserveringJson.addProperty("reservering_Parkeerplaats_plek", reservering.getReservering_Parkeerplaats().getParkeerplaats_Locatie());
        reserveringJson.addProperty("reservering_Parkeergarage", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Naam());
        reserveringJson.addProperty("reservering_ParkeergarageLocatie", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Locatie());
        reserveringJson.addProperty("reservering_Parkeergarage_Opening", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Opening());
        reserveringJson.addProperty("reservering_Parkeergarage_Sluiting", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Sluiting());
        reserveringJson.addProperty("reservering_parkeergarage_Id", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Id());

        return AESCryption.encrypt(reserveringJson.toString());
    }

    //Functie die een unieke reservering verwijderd.
    @GetMapping("/reservering/verwijder/{encodedreserveringid}")
    public String verwijderReservering(@PathVariable String encodedreserveringid) throws SQLException, ClassNotFoundException {
        JsonObject resultaatJson = new JsonObject();

        String urlDecodedresId = URLDecoder.decode(encodedreserveringid.replace( "+", "%2B" ));
        int reserveringid = Integer.parseInt(AESCryption.decrypt(urlDecodedresId));

        //bepalen van de huidige tijd
        DateTimeFormatter dtf_Tijd = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dtf_Datum = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        //Ophalen reservering uit de database
        Reservering reservering = reserveringDAO.getReserveringById(reserveringid);
        System.out.println(reservering.getReservering_Id());

        //Als de begintijd van de reservering niet geweest is
        if(reservering.tijdVoorbijBegintijd(dtf_Tijd.format(now),reservering.getReservering_Begintijd(),reservering.getReservering_Datum(), dtf_Datum.format(now))){
            System.out.println("false");
            resultaatJson.addProperty("resultaat","false");
            return AESCryption.encrypt(resultaatJson.toString());
        }else{
            // Aanroepen van de SQL delete reservering
            System.out.println("true");
            reserveringDAO.verwijderReserveringById(reservering.getReservering_Id());
            resultaatJson.addProperty("resultaat","true");
            return AESCryption.encrypt(resultaatJson.toString());
        }
    }

    //Functie die de reserveringen van een gebruiker weergeeft in JSON format via een Rest Api
    @GetMapping("/reserveringen/{encodedautoId}")
    public String getReserveringenGebruiker(@PathVariable String encodedautoId)throws SQLException, ClassNotFoundException{
        JsonArray reserverenJsonArray = new JsonArray();

        String urlDecodedAid = URLDecoder.decode(encodedautoId.replace( "+", "%2B" ));
        int autoId = Integer.parseInt(AESCryption.decrypt(urlDecodedAid));

        ArrayList<Reservering> reserveringArray = reserveringDAO.getReserveringenGebruiker(autoId);

        //Sorteer de lijst aflopend op datum en aflopend op begintijd
        Collections.sort(reserveringArray, new Comparator<Reservering>() {
            @Override
            public int compare(Reservering o1, Reservering o2) {
                Calendar calendarReservering1 = Calendar.getInstance();
                Calendar calendarReservering2 = Calendar.getInstance();
                try {
                    Date datumReservering1 = new SimpleDateFormat("dd-MM-yyyy").parse(o1.getReservering_Datum());
                    Date datumReservering2 = new SimpleDateFormat("dd-MM-yyyy").parse(o2.getReservering_Datum());
                    calendarReservering1.setTime(datumReservering1);
                    calendarReservering2.setTime(datumReservering2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int DagCompare = calendarReservering1.compareTo(calendarReservering2);
                int BegintijdCompare = o1.getReservering_Begintijd().compareTo(o2.getReservering_Begintijd());

                return (DagCompare == 0) ? BegintijdCompare : DagCompare;
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
            reserveringJson.addProperty("reservering_Parkeergarage_Opening", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Opening());
            reserveringJson.addProperty("reservering_Parkeergarage_Sluiting", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Sluiting());
            reserveringJson.addProperty("reservering_parkeergarage_Id", reservering.getReservering_Parkeerplaats().getParkeerplaats_Parkeergarage().getParkeergarage_Id());
            reserverenJsonArray.add(reserveringJson);
        }
        return AESCryption.encrypt(reserverenJsonArray.toString());
    }
}
