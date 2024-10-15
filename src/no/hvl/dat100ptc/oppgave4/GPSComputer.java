package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {
		double totalDistance = 0;
	
		// Gå gjennom alle punkter og legg sammen avstandene mellom etterfølgende punkter
		for (int i = 0; i < gpspoints.length - 1; i++) {
			totalDistance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return totalDistance;
	}

	public double totalElevation() {
		double totalElevation = 0;
	
		// Gå gjennom alle punktene
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double elevationDiff = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			if (elevationDiff > 0) {
				totalElevation += elevationDiff;
			}
		}
		return totalElevation;
	}

	public int totalTime() {
		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
	}
		

	public double[] speeds() {
		double[] speeds = new double[gpspoints.length - 1];
	
		// Beregn hastigheten mellom hvert etterfølgende punkt
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double distance = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			int time = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			speeds[i] = distance / time;
		}
		return speeds;
	}
	
	public double maxSpeed() {
		double[] speeds = speeds();
		double maxSpeed = 0;
	
		// Finn maksimal hastighet
		for (double speed : speeds) {
			if (speed > maxSpeed) {
				maxSpeed = speed;
			}
		}
		return maxSpeed;
	}

	public double averageSpeed() {
		double totalDistance = totalDistance(); // i meter
		int totalTime = totalTime(); // i sekunder
		return (totalDistance / totalTime) * 3.6; // Konverter fra m/s til km/t
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		double speedmph = speed * MS; // Konverter til miles per hour (mph)
		double met = 0;
	
		// Bestem MET basert på hastighet
		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else if (speedmph < 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}
	
		double hours = secs / 3600.0;
		return met * weight * hours;
	}

	public double totalKcal(double weight) {
		double totalkcal = 0;
		double[] speeds = speeds();
		int[] times = new int[speeds.length];
	
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int time = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			totalkcal += kcal(weight, time, speeds[i]);
		}
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		System.out.println("==============================================");
		System.out.println("Total Time     :   " + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance :      " + GPSUtils.formatDouble(totalDistance() / 1000) + " km");
		System.out.println("Total elevation:     " + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max speed      :      " + GPSUtils.formatDouble(maxSpeed() * 3.6) + " km/t");
		System.out.println("Average speed  :      " + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy         :     " + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
		System.out.println("==============================================");
	}

}
