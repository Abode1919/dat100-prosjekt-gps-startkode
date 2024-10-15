package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

    // Variablar for å lagre tid, breiddegrad, lengdegrad og høgde.
    private int time;
    private double latitude;
    private double longitude;
    private double elevation;

    // Konstruktør som set verdiane for variablane når eit nytt GPSPoint-objekt blir oppretta.
    public GPSPoint(int time, double latitude, double longitude, double elevation) {
        
    	this.time = time;               // Initialiserer tid
        this.latitude = latitude;       // Initialiserer breiddegrad
        this.longitude = longitude;     // Initialiserer lengdegrad
        this.elevation = elevation;     // Initialiserer høgde
    }

    // Metode for å hente verdien av tid.
	public int getTime() {
		return time;
	}

    // Metode for å setje verdien av tid.
	public void setTime(int time) {
		this.time = time;		
	}

    // Metode for å hente verdien av breiddegrad.
	public double getLatitude() {
		return latitude;		
	}

    // Metode for å setje verdien av breiddegrad.
	public void setLatitude(double latitude) {
		this.latitude = latitude;		
	}

    // Metode for å hente verdien av lengdegrad.
	public double getLongitude() {
		return longitude;		
	}

    // Metode for å setje verdien av lengdegrad.
	public void setLongitude(double longitude) {
		this.longitude = longitude;		
	}

    // Metode for å hente verdien av høgde.
	public double getElevation() {
		return elevation;		
	}

    // Metode for å setje verdien av høgde.
	public void setElevation(double elevation) {
		this.elevation = elevation;		
	}
	
    // Metode som returnerer ein strengrepresentasjon av objektet i formatet: tid (breiddegrad, lengdegrad) høgde
	public String toString() {
		
		return time + " (" + latitude + "," + longitude + ") " + elevation + "\n";
				
	}
}
