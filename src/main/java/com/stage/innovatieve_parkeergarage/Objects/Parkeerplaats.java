package com.stage.innovatieve_parkeergarage.Objects;

import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ParkeerplaatsDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAO.ReserveringDAO;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ParkeerplaatsDAOImplementatie;
import com.stage.innovatieve_parkeergarage.DataHandeling.DAOImplementatie.ReserveringDAOImplementatie;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Parkeerplaats {

    //Attributen specificeren
    private int parkeerplaats_Id;
    private Parkeergarage parkeerplaats_Parkeergarage;
    private int parkeerplaats_Laag;
    private int Parkeerplaats_Locatie;

    //Constructor lege parkeerplaats
    public Parkeerplaats() {
    }

    //Constructor maken object parkeerplaats
    public Parkeerplaats(int parkeerplaats_Id, Parkeergarage parkeerplaats_Parkeergarage, int parkeerplaats_Laag, int parkeerplaats_Locatie) {
        this.parkeerplaats_Id = parkeerplaats_Id;
        this.parkeerplaats_Parkeergarage = parkeerplaats_Parkeergarage;
        this.parkeerplaats_Laag = parkeerplaats_Laag;
        Parkeerplaats_Locatie = parkeerplaats_Locatie;
    }

    //geeft parkeerplaats id terug
    public int getParkeerplaats_Id() {
        return parkeerplaats_Id;
    }

    //geeft parkeergarage object van de parkeerplaats terug
    public Parkeergarage getParkeerplaats_Parkeergarage() {
        return parkeerplaats_Parkeergarage;
    }

    //geeft parkeerplaats laag terug
    public int getParkeerplaats_Laag() {
        return parkeerplaats_Laag;
    }

    //geeft parkeerplaats locatie terug
    public int getParkeerplaats_Locatie() {
        return Parkeerplaats_Locatie;
    }

    //Zoekt een vrije parkeerplaats voor een reservering
    public Parkeerplaats vrijeParkeerplaats(int parkeergarageId, String datum, String eindtijd, String begintijd) throws SQLException, ClassNotFoundException {

        //initializeren Data Access Objecten
        ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
        ReserveringDAO reserveringDAO = new ReserveringDAOImplementatie();

        //lege lijst die de bezette parkeerplaatsen behoud
        ArrayList bezettenParkeerplaatsen = new ArrayList();

        //lege lijst die de vrije parkeerplaatsen behoud
        ArrayList<Parkeerplaats> vrijeParkeerplaatsen = new ArrayList<>();

        //ophalen parkeerplaatsen van parkeergarage
        ArrayList<Parkeerplaats> parkeerplaatsArrayList = parkeerplaatsDAO.getAllParkeerplaatsenParkeergarage(parkeergarageId);

        //ophalen reserveringen van een parkeergarage van dag
        ArrayList<Reservering> res = reserveringDAO.getReserveringenGarage(parkeergarageId,datum);

        //voor elke reservering in alle reserveringen in een parkeergarage van een datum
        for (Reservering gereserveerdeParkeerplaats : res) {

            //Check of de tijden met elkaar overlappen
            Boolean checkBeginTijdVoorafgaandReserveren = checkTime(begintijd, gereserveerdeParkeerplaats.getReservering_Begintijd(),gereserveerdeParkeerplaats.getReservering_Eindtijd());
            Boolean checkEindTijdVoorafgaandReserveren = checkTime(eindtijd, gereserveerdeParkeerplaats.getReservering_Begintijd(),gereserveerdeParkeerplaats.getReservering_Eindtijd());
            Boolean checkBeginTijdReservering = checkTime(gereserveerdeParkeerplaats.getReservering_Begintijd(), begintijd,eindtijd);
            Boolean checkEindTijdReservering = checkTime(gereserveerdeParkeerplaats.getReservering_Eindtijd(), begintijd,eindtijd);

            //Als geen enkele tijd overlapt, doe niks
            if(checkBeginTijdVoorafgaandReserveren.equals(false)
                    && checkEindTijdVoorafgaandReserveren.equals(false)
                    && checkBeginTijdReservering.equals(false)
                    && checkEindTijdReservering.equals(false)){

            }
            //Als een tijd wel overlapt. voeg de reservering toe aan de bezetten parkeerplaatsen lijst
            else{
                bezettenParkeerplaatsen.add(gereserveerdeParkeerplaats.getReservering_Parkeerplaats().parkeerplaats_Id);
            }
        }

        //voor elke parkeerplaats in de parkeergarage
        for (Parkeerplaats mogelijkPlaats : parkeerplaatsArrayList) {
            //als de parkeerplaats aanwezig is in de lijst met bezette parkeerplaatsen, doe niks
            if (bezettenParkeerplaatsen.contains(mogelijkPlaats.parkeerplaats_Id)) {
            }
            //Als de parkeerplaats niet aanwezig is ind e lijst met bezette parkeerplaats, voeg deze toe aan de lijst met vrije parkeerplaatsen
            else {
                vrijeParkeerplaatsen.add(mogelijkPlaats);
            }
        }
        //geef de vrije parkeerplaats met de laagste Id terug
        return vrijeParkeerplaatsen.get(0);
    }

    //Deze functie checkt of de checkTijd tussen de beginTijd en eindTijd is. Geeft een True of False terug
    public Boolean checkTime(String checkTijd, String begintijd, String eindtijd) {
        Boolean uitkomst = false;
        try {
            String string1 = begintijd;
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);


            String string2 = eindtijd;
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            String teBekijkenTijd = checkTijd;
            Date d = new SimpleDateFormat("HH:mm").parse(teBekijkenTijd);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime()) || x.equals(calendar1.getTime()) || x.equals(calendar2.getTime())) {
                System.out.println(true);
                uitkomst = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return uitkomst;
    }
}
