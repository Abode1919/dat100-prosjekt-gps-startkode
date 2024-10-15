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

		double totalDistance = 0.0;
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			totalDistance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return totalDistance;
	}

	public double totalElevation() {

		double totalElevation = 0.0;
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double currentElevation = gpspoints[i].getElevation();
			double nextElevation = gpspoints[i + 1].getElevation();
			
			if (nextElevation > currentElevation) {
				totalElevation += nextElevation - currentElevation;
			}
		}
		return totalElevation;
	}

	public int totalTime() {

		if (gpspoints.length < 2) {
			return 0;
		}
		
		int startTime = gpspoints[0].getTime();
		int endTime = gpspoints[gpspoints.length - 1].getTime();
		
		return endTime - startTime;
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double distance = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);		
			int timeDiff = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
		
			
			if (timeDiff == 0) {
				speeds[i] = 0;
			} else {
				speeds[i] = distance / timeDiff;
			}
		}
		return speeds;
		
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		double[] speeds = speeds();
		
		for (double speed : speeds) {
			if (speed > maxspeed) {
				maxspeed = speed;
			}
		}
		return maxspeed;
		
	}

	public double averageSpeed() {
		
		double totalDistance = totalDistance();
		
		int totalTime = totalTime();
		
		double average = totalDistance / totalTime;
		
		return average;
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		double met = 0;		
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph <= 12 ) {
			met = 6.0;
		} else if (speedmph <= 14) {
			met = 8.0;
		} else if (speedmph <= 16) {
			met = 10.0;
		} else if (speedmph <= 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}
		
		double timeInHours = secs / 3600.0;
		
		double kcal = met * weight * timeInHours;
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double[] speeds = speeds();
		
		for (int i = 0; i < speeds.length; i++) {
			int secs = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			
			totalkcal += kcal(weight, secs, speeds[i]);
		}
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		int weight = 80;
		int totalTime = totalTime();
		double totalDistance = totalDistance() / 1000.0;
		double totalElevation = totalElevation();
		double maxSpeed = maxSpeed() * 3.6;
		double averageSpeed = averageSpeed() * 3.6;
		double totalKcal = totalKcal(weight);
		
		System.out.println("==============================================");
	    System.out.printf("Total Time     :   %s\n", GPSUtils.formatTime(totalTime));
	    System.out.printf("Total distance :   %8.2f km\n", totalDistance);
	    System.out.printf("Total elevation:   %8.2f m\n", totalElevation);
	    System.out.printf("Max speed      :   %8.2f km/t\n", maxSpeed);
	    System.out.printf("Average speed  :   %8.2f km/t\n", averageSpeed);
	    System.out.printf("Energy         :   %8.2f kcal\n", totalKcal);
	    System.out.println("==============================================");
	}

}