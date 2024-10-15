package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		// Farge for ruten
		setColor(0, 0, 255); // Blå farge
	
		// Skalér og tegn ruten
		for (int i = 0; i < gpspoints.length - 1; i++) {
	
			int x1 = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
			int y1 = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
			
			int x2 = MARGIN + (int) ((gpspoints[i + 1].getLongitude() - minlon) * xstep);
			int y2 = ybase - (int) ((gpspoints[i + 1].getLatitude() - minlat) * ystep);
			
			drawLine(x1, y1, x2, y2);
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
	
		setColor(0, 0, 0); // Sort farge
		setFont("Courier", 12);
	
		drawString("Total distance: " + gpscomputer.totalDistance() + " km", MARGIN, TEXTDISTANCE);
		drawString("Total elevation: " + gpscomputer.totalElevation() + " m", MARGIN, TEXTDISTANCE + 20);
		drawString("Total time: " + GPSUtils.formatTime(gpscomputer.totalTime()), MARGIN, TEXTDISTANCE + 40);
		drawString("Average speed: " + gpscomputer.averageSpeed() + " km/t", MARGIN, TEXTDISTANCE + 60);
		drawString("Max speed: " + gpscomputer.maxSpeed() + " km/t", MARGIN, TEXTDISTANCE + 80);
		drawString("Calories burned: " + gpscomputer.totalKcal(80) + " kcal", MARGIN, TEXTDISTANCE + 100);
	}

	public void replayRoute(int ybase) {

		int r = 5; // Radius for sirkelen
		setSpeed(10); // Sett hastigheten for bevegelsen
	
		// Tegn startpunkt
		int x1 = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
		int y1 = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);
		
		int circle = fillCircle(x1, y1, r);
	
		// Flytt sirkelen langs ruten
		for (int i = 1; i < gpspoints.length; i++) {
	
			int x2 = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
			int y2 = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);
			
			moveCircle(circle, x2, y2);
		}
	}

}
