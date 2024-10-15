package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {
		
	
		GPSPoint point1 = new GPSPoint(3, 25.525, 4, 34.535);
		GPSPoint point2 = new GPSPoint(6, 30.524, 6, 14.5352);
		
		GPSData gpsData = new GPSData(2);
		
		gpsData.insertGPS(point1);
		gpsData.insertGPS(point2);
		
		gpsData.print();
	}
}