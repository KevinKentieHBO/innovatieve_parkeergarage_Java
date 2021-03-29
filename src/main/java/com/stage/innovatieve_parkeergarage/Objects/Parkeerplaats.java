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
    private int parkeerplaats_Id;
    private Parkeergarage parkeerplaats_Parkeergarage;
    private int parkeerplaats_Laag;
    private int Parkeerplaats_Locatie;

    public Parkeerplaats() {
    }

    public Parkeerplaats(int parkeerplaats_Id, Parkeergarage parkeerplaats_Parkeergarage, int parkeerplaats_Laag, int parkeerplaats_Locatie) {
        this.parkeerplaats_Id = parkeerplaats_Id;
        this.parkeerplaats_Parkeergarage = parkeerplaats_Parkeergarage;
        this.parkeerplaats_Laag = parkeerplaats_Laag;
        Parkeerplaats_Locatie = parkeerplaats_Locatie;
    }

    public int getParkeerplaats_Id() {
        return parkeerplaats_Id;
    }

    public Parkeergarage getParkeerplaats_Parkeergarage() {
        return parkeerplaats_Parkeergarage;
    }

    public int getParkeerplaats_Laag() {
        return parkeerplaats_Laag;
    }

    public int getParkeerplaats_Locatie() {
        return Parkeerplaats_Locatie;
    }

    public Parkeerplaats vrijeParkeerplaats(int parkeergarageId, String datum, String eindtijd, String begintijd) throws SQLException, ClassNotFoundException {
        ParkeerplaatsDAO parkeerplaatsDAO = new ParkeerplaatsDAOImplementatie();
        ReserveringDAO reserveringDAO = new ReserveringDAOImplementatie();
        ArrayList i = new ArrayList();
        ArrayList<Parkeerplaats> vrijeParkeerplaatsen = new ArrayList<>();

        //ophalen parkeerplaatsen van parkeergarage
        ArrayList<Parkeerplaats> parkeerplaatsArrayList = parkeerplaatsDAO.getAllParkeerplaatsenParkeergarage(parkeergarageId);

        //ophalen reserveringen van een parkeergarage van dag
        ArrayList<Reservering> res = reserveringDAO.getReserveringenGarage(parkeergarageId,datum);

        System.out.println(res.size());

        for (Reservering gereserveerdeParkeerplaats : res) {
            Boolean checkBeginTijdVoorafgaandReserveren = checkTime(begintijd, gereserveerdeParkeerplaats.getReservering_Begintijd(),gereserveerdeParkeerplaats.getReservering_Eindtijd());
            Boolean checkEindTijdVoorafgaandReserveren = checkTime(eindtijd, gereserveerdeParkeerplaats.getReservering_Begintijd(),gereserveerdeParkeerplaats.getReservering_Eindtijd());
            Boolean checkBeginTijdReservering = checkTime(gereserveerdeParkeerplaats.getReservering_Begintijd(), begintijd,eindtijd);
            Boolean checkEindTijdReservering = checkTime(gereserveerdeParkeerplaats.getReservering_Eindtijd(), begintijd,eindtijd);

            if(checkBeginTijdVoorafgaandReserveren.equals(false)
                    && checkEindTijdVoorafgaandReserveren.equals(false)
                    && checkBeginTijdReservering.equals(false)
                    && checkEindTijdReservering.equals(false)){

            }else{
                i.add(gereserveerdeParkeerplaats.getReservering_Parkeerplaats().parkeerplaats_Id);
            }
        }

        for (Parkeerplaats mogelijkPlaats : parkeerplaatsArrayList) {
            if (i.contains(mogelijkPlaats.parkeerplaats_Id)) {
            } else {
                vrijeParkeerplaatsen.add(mogelijkPlaats);
            }
        }
        return vrijeParkeerplaatsen.get(0);
    }

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
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                System.out.println(true);
                uitkomst = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return uitkomst;
    }
}
