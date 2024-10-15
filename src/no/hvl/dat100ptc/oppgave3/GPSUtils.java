package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		double latitude1 = Math.toRadians(gpspoint1.getLatitude());
		double longitude1 = Math.toRadians(gpspoint1.getLongitude());
		double latitude2 = Math.toRadians(gpspoint2.getLatitude());
		double longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double deltaphi = latitude2 - latitude1;
		double deltadelta = longitude2 - longitude1;
		
	    double a = compute_a(latitude1, latitude2, deltaphi, deltadelta);
		double c = compute_c(a);
	    double d = R * c;
		
		return d;
		 
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	    double a = Math.sin(deltaphi / 2) * Math.sin(deltaphi / 2) +
	               Math.cos(phi1) * Math.cos(phi2) *
	               Math.sin(deltadelta / 2) * Math.sin(deltadelta / 2);
	    return a;
	}

	private static double compute_c(double a) {
	    return 2 * Math.asin(Math.sqrt(a));
	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		double distanceInMeter = distance(gpspoint1, gpspoint2);
		
		int time1 = gpspoint1.getTime();
		int time2 = gpspoint2.getTime();
		
		double timeInSeconds = time2 - time1;
		
		double speed = distanceInMeter/timeInSeconds;
		return speed;
	}

	public static String formatTime(int secs) {

		int hours = secs / 3600;
		int minutes = (secs % 3600) / 60;
		int seconds = secs % 60;
		
		String timestr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
		return String.format("%10s", timestr);
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str = String.format(Locale.US, "%.2f", d);
		
		return String.format("%" + TEXTWIDTH + "s", str);
			
	}
}