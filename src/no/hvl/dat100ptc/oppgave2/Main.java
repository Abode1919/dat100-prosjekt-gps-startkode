package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

    public static void main(String[] args) {
        // Oppretter to GPSPoint-objekter
        GPSPoint gpsPoint1 = new GPSPoint(31946, 60.385390, 5.217217, 61.9);
        GPSPoint gpsPoint2 = new GPSPoint(31947, 60.385491, 5.217417, 61.8);
        
        // Oppretter et GPSData-objekt med plass til to GPS-punkter
        GPSData gpsData = new GPSData(2);
        
        // Setter inn de to GPSPoint-objektene
        gpsData.insertGPS(gpsPoint1);
        gpsData.insertGPS(gpsPoint2);
        
        // Skriver ut informasjon om GPSPoint-objektene
        gpsData.print();
    }
}
