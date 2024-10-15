package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		this.gpspoints = new GPSPoint[n];
		this.antall = 0;
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {		
		
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return true;
		}
		return false;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		
	    int tim = GPSDataConverter.toSeconds(time);
		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);
		double ele = Double.parseDouble(elevation);
		
		GPSPoint gpspoint = new GPSPoint(tim, lat, lon, ele);
		return insertGPS(gpspoint);
		
	}

	public void print() {

		System.out.println("====== GPS DATA - START ======");
		for (int i = 0; i < antall; i++) {
			System.out.println((i + 1) + " " + gpspoints[i].toString());
		}
		System.out.println("====== GPS DATA - SLUTT =====");
	}
}