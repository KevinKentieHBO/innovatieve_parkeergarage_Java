package com.stage.innovatieve_parkeergarage.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public class Reservering {

    //Attributen specificeren
    private int reservering_Id;
    private Parkeerplaats reservering_Parkeerplaats;
    private String reservering_Begintijd;
    private String reservering_Eindtijd;
    private String reservering_Datum;
    private Auto reservering_Auto;
    private String inrijtijd;
    private String uitrijtijd;

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

    //veranderen van de data in het attribuut parkeerplaats
    public void setReservering_Parkeerplaats(Parkeerplaats reservering_Parkeerplaats) {
        this.reservering_Parkeerplaats = reservering_Parkeerplaats;
    }

    //veranderen van de data in het attribuut Begintijd
    public void setReservering_Begintijd(String reservering_Begintijd) {
        this.reservering_Begintijd = reservering_Begintijd;
    }

    //veranderen van de data in het attribuut Eindtijd
    public void setReservering_Eindtijd(String reservering_Eindtijd) {
        this.reservering_Eindtijd = reservering_Eindtijd;
    }

    //veranderen van de data in het attribuut Datum
    public void setReservering_Datum(String reservering_Datum) {
        this.reservering_Datum = reservering_Datum;
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
        return uitkomst;
    }

    //Deze functie checkt of de huidige tijd tussen de begintijd van de reservering of een uur eerder ligt.
    public String checkBegintijdInrijden(String checkTijd, String eindtijd) {
        String uitkomst = "0";
        try {
            String string1 = eindtijd;
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.MINUTE,-15);
            calendar1.add(Calendar.DATE, 1);

            String string2 = checkTijd;
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.MINUTE,-30);
            calendar2.add(Calendar.DATE, 1);

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            Date d = new SimpleDateFormat("HH:mm").parse(formatter.format(date));
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            System.out.println(calendar2.getTime());
            System.out.println(calendar3.getTime());
            System.out.println(calendar1.getTime());

            Date x = calendar3.getTime();
            if (x.after(calendar2.getTime()) && x.before(calendar1.getTime())) {
                uitkomst = "1";
            }
            else if(x.after(calendar1.getTime())){
                uitkomst = "2";
            }
            else if (x.before(calendar2.getTime())){
                uitkomst = "3";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return uitkomst;
    }

    public String getInrijtijd() {
        return inrijtijd;
    }

    public void setInrijtijd(String inrijtijd) {
        this.inrijtijd = inrijtijd;
    }

    public String getUitrijtijd() {
        return uitrijtijd;
    }

    public void setUitrijtijd(String uitrijtijd) {
        this.uitrijtijd = uitrijtijd;
    }

    //vergelijken van twee datums om een vrije parkeerplaats te zoeken
    Comparator<Reservering> comparator = Comparator.comparing(Reservering::getReservering_Datum).thenComparing(Reservering::getReservering_Begintijd);
}