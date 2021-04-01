package com.stage.innovatieve_parkeergarage.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Reservering {

    //Attributen specificeren
    private int reservering_Id;
    private Parkeerplaats reservering_Parkeerplaats;
    private String reservering_Begintijd;
    private String reservering_Eindtijd;
    private String reservering_Datum;
    private Auto reservering_Auto;

    //Constructor om een Reservering Object aan te maken die een Id meeneemt
    public Reservering(int reservering_Id, Parkeerplaats reservering_Parkeerplaats, String reservering_Begintijd, String reservering_Eindtijd, String reservering_Datum, Auto reservering_Auto) {
        this.reservering_Id = reservering_Id;
        this.reservering_Parkeerplaats = reservering_Parkeerplaats;
        this.reservering_Begintijd = reservering_Begintijd;
        this.reservering_Eindtijd = reservering_Eindtijd;
        this.reservering_Datum = reservering_Datum;
        this.reservering_Auto = reservering_Auto;
    }

    //Constructor om een Reservering Object aan te maken die geen Id meeneemt
    public Reservering(Parkeerplaats reservering_Parkeerplaats, String reservering_Begintijd, String reservering_Eindtijd, String reservering_Datum, Auto reservering_Auto) {
        this.reservering_Parkeerplaats = reservering_Parkeerplaats;
        this.reservering_Begintijd = reservering_Begintijd;
        this.reservering_Eindtijd = reservering_Eindtijd;
        this.reservering_Datum = reservering_Datum;
        this.reservering_Auto = reservering_Auto;
    }

    //geeft reservering id terug
    public int getReservering_Id() {
        return reservering_Id;
    }

    //geeft object parkeerplaats van de reservering terug
    public Parkeerplaats getReservering_Parkeerplaats() {
        return reservering_Parkeerplaats;
    }

    //geeft reservering begintijd terug
    public String getReservering_Begintijd() {
        return reservering_Begintijd;
    }

    //geeft reservering eindtijd terug
    public String getReservering_Eindtijd() {
        return reservering_Eindtijd;
    }

    //geeft reservering datum terug
    public String getReservering_Datum() {
        return reservering_Datum;
    }

    //geeft object auto van de reservering terug
    public Auto getReservering_Auto() {
        return reservering_Auto;
    }

    //omzetten van de datum naar een Date type
    public Date stringToDate() throws ParseException {
        Date datum = new SimpleDateFormat("dd-MM-yyyy").parse(reservering_Datum);
        return datum;
    }

    //Deze functie checkt de checkTijd lager ligt dan de begintijd voor de verwijdering van de reservering
    public Boolean tijdVoorbijBegintijd(String checkTijd, String begintijd, String reservering_Datum, String huidige_Datum) {
        Boolean uitkomst = false;
        try {
            Date datumReservering1 = new SimpleDateFormat("dd-MM-yyyy").parse(reservering_Datum);

            String string1 = begintijd;
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);

            String reserveringDatum = reservering_Datum;
            Date resD = new SimpleDateFormat("dd-MM-yyyy").parse(reserveringDatum);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(resD);
            calendar2.add(Calendar.DATE, 1);

            String teBekijkenTijd = checkTijd;
            Date d = new SimpleDateFormat("HH:mm").parse(teBekijkenTijd);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            String huidigeDatum = huidige_Datum;
            Date huiD = new SimpleDateFormat("dd-MM-yyyy").parse(huidigeDatum);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.setTime(huiD);
            calendar4.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if ((calendar2.getTime().before(calendar4.getTime()) || calendar2.getTime().equals(calendar4.getTime())) &&(x.after(calendar1.getTime()) || x.equals(calendar1.getTime()))) {
                uitkomst = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(uitkomst);
        return uitkomst;
    }

    //vergelijken van twee datums om een vrije parkeerplaats te zoeken
    Comparator<Reservering> comparator = Comparator.comparing(Reservering::getReservering_Datum).thenComparing(Reservering::getReservering_Begintijd);
}