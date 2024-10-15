package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	public static void main(String[] args) {

		// Opprettar to GPSPoint-objekt med tid, breiddegrad, lengdegrad og høgde
		GPSPoint point1 = new GPSPoint(3, 25.525, 4, 34.535);
		GPSPoint point2 = new GPSPoint(6, 30.524, 6, 14.5352);
		
		// Opprettar eit GPSData-objekt med plass til 2 GPS-punkt
		GPSData gpsData = new GPSData(2);
		
		// Legg til dei to GPS-punkta i GPSData-objektet
		gpsData.insertGPS(point1);
		gpsData.insertGPS(point2);
		
		// Skriv ut GPS-data (med GPS-punkta) til konsollen
		gpsData.print();
	}
}
