package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

    // Array for å lagre GPS-punkt og variabel for å halde styr på talet av GPS-punkt
	private GPSPoint[] gpspoints;
	protected int antall = 0;

    // Konstruktør som opprettar eit array med n plassar for GPS-punkt
	public GPSData(int n) {
		this.gpspoints = new GPSPoint[n];
		this.antall = 0;  // Startar med ingen GPS-punkt lagra
	}

    // Metode som returnerer arrayet med GPS-punkt
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
    // Metode som legg til eit GPS-punkt i arrayet dersom det er plass
	protected boolean insertGPS(GPSPoint gpspoint) {		
		
		if (antall < gpspoints.length) {  // Sjekkar om det er plass i arrayet
			gpspoints[antall] = gpspoint;  // Legg til GPS-punktet
			antall++;                      // Oppdaterer talet på punkt
			return true;                   // Returnerer true om punktet vart lagt til
		}
		return false;  // Returnerer false om det ikkje var plass
	}

    // Metode som konverterer data frå streng til korresponderande GPS-punkt og legg til i arrayet
	public boolean insert(String time, String latitude, String longitude, String elevation) {
		
	    int tim = GPSDataConverter.toSeconds(time);  // Konverterer tid til sekund
		double lat = Double.parseDouble(latitude);    // Konverterer breiddegrad til double
		double lon = Double.parseDouble(longitude);   // Konverterer lengdegrad til double
		double ele = Double.parseDouble(elevation);   // Konverterer høgde til double
		
		GPSPoint gpspoint = new GPSPoint(tim, lat, lon, ele);  // Opprettar nytt GPSPoint
		return insertGPS(gpspoint);  // Legg til GPS-punktet i arrayet
	}

    // Metode som skriv ut alle GPS-punkta som er lagra
	public void print() {

		System.out.println("====== GPS DATA - START ======");
		for (int i = 0; i < antall; i++) {
			System.out.println((i + 1) + " " + gpspoints[i].toString());  // Skriv ut kvart punkt med indeks
		}
		System.out.println("====== GPS DATA - SLUTT =====");
	}
}
