package no.hvl.dat100ptc.oppgave1;

public class Main {

    public static void main(String[] args) {
        // Opprett GPSPoint objekt
        GPSPoint point = new GPSPoint(1, 2.0, 3.0, 5.0);

        // Skriv ut tidspunkt ved hjelp av getTime()
        System.out.println("Tid: " + point.getTime());

        // Endre tidspunkt til 2 ved hjelp av setTime()
        point.setTime(2);

        // Skriv ut objektinformasjon ved hjelp av toString()
        System.out.println(point.toString());
    }
}
