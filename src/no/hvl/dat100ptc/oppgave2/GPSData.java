package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

    private GPSPoint[] gpspoints;
    protected int antall = 0;

    public GPSData(int n) {
        // Opprettar ein referansetabell av GPS-punkt
        this.gpspoints = new GPSPoint[n];
        this.antall = 0; // Settar antall til 0
    }

    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;
    }
    
    protected boolean insertGPS(GPSPoint gpspoint) {
        if (antall < gpspoints.length) {
            gpspoints[antall] = gpspoint; // Settar inn GPS-punktet
            antall++; // Inkrementerer antall
            return true;
        }
        return false; // Ingen plass i tabellen
    }

    public boolean insert(String time, String latitude, String longitude, String elevation) {
        GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
        return insertGPS(gpspoint); // Settar inn GPS-punktet
    }

    public void print() {
        System.out.println("====== GPS Data - START ======");
        for (int i = 0; i < antall; i++) {
            if (gpspoints[i] != null) {
                System.out.println(gpspoints[i].toString()); // Skriver ut GPSPoint-objektet
            }
        }
        System.out.println("====== GPS Data - SLUTT ======");
    }
}
