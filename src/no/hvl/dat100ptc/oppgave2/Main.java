package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

    public static void main(String[] args) {
        // Opprettar to GPSPoint-objekt
        GPSPoint gpsPoint1 = new GPSPoint(31946, 60.385390, 5.217217, 61.9);
        GPSPoint gpsPoint2 = new GPSPoint(31947, 60.385491, 5.217417, 61.8);
        
        // Opprettar eit GPSData-objekt med plass til to GPS-punkt
        GPSData gpsData = new GPSData(2);
        
        // Settar inn dei to GPSPoint-objekta
        gpsData.insertGPS(gpsPoint1);
        gpsData.insertGPS(gpsPoint2);
        
        // Skriver ut informasjon om GPSPoint-objekta
        gpsData.print();
    }
}
