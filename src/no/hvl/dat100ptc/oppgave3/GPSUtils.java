package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	// Finner maksverdi i eit array
	public static double findMax(double[] da) {
		double max = da[0]; // Startar med første verdi som maks
		
		// Går gjennom alle verdiar og oppdaterer maks om nødvendig
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	// Finner minimumsverdi i eit array
	public static double findMin(double[] da) {
		double min = da[0]; // Startar med første verdi som minimum
		
		// Går gjennom alle verdiar og oppdaterer minimum om nødvendig
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	// Hentar alle breiddegradar frå GPS-punkt
	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		
		// Fyller array med breiddegradar frå GPS-punkt
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
	}

	// Hentar alle lengdegradar frå GPS-punkt
	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		
		// Fyller array med lengdegradar frå GPS-punkt
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
	}

	// Beregner avstand mellom to GPS-punkt basert på Haversine-formelen
	private static final int R = 6371000; // Jordens radius i meter

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		// Konverterer breiddegrad og lengdegrad frå grader til radianar
		double latitude1 = Math.toRadians(gpspoint1.getLatitude());
		double longitude1 = Math.toRadians(gpspoint1.getLongitude());
		double latitude2 = Math.toRadians(gpspoint2.getLatitude());
		double longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		// Differanse mellom breiddegradar og lengdegradar
		double deltaphi = latitude2 - latitude1;
		double deltadelta = longitude2 - longitude1;
		
		// Brukar hjelpefunksjonane for å berekne avstand
	    double a = compute_a(latitude1, latitude2, deltaphi, deltadelta);
		double c = compute_c(a);
	    double d = R * c; // Avstand i meter
		
		return d;
	}
	
	// Hjelpefunksjon som berekner variabelen 'a' i Haversine-formelen
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	    return Math.sin(deltaphi / 2) * Math.sin(deltaphi / 2) +
	           Math.cos(phi1) * Math.cos(phi2) *
	           Math.sin(deltadelta / 2) * Math.sin(deltadelta / 2);
	}

	// Hjelpefunksjon som berekner 'c' basert på 'a' i Haversine-formelen
	private static double compute_c(double a) {
	    return 2 * Math.asin(Math.sqrt(a));
	}

	// Beregner fart mellom to GPS-punkt i meter per sekund
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double distanceInMeter = distance(gpspoint1, gpspoint2); // Avstand i meter
		double timeInSeconds = gpspoint2.getTime() - gpspoint1.getTime(); // Tidsdifferanse i sekund
		
		// Fart i meter per sekund
		return distanceInMeter / timeInSeconds;
	}

	// Formaterer tid i sekunder til HH:MM:SS-format
	public static String formatTime(int secs) {
		int hours = secs / 3600;
		int minutes = (secs % 3600) / 60;
		int seconds = secs % 60;
		
		// Returnerer tid i formatet HH:MM:SS, og justerer for breidde
		return String.format("%10s", String.format("%02d:%02d:%02d", hours, minutes, seconds));
	}

	// Formaterer ein double-verdi til to desimalar med riktig breidde
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		// Returnerer ein double-verdi med to desimalar og justerer for breidde
		return String.format("%" + TEXTWIDTH + "s", String.format(Locale.US, "%.2f", d));
	}
}
