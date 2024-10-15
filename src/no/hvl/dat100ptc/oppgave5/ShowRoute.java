package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;
import easygraphics.EasyGraphics;

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

		makeWindow("Route", MAPXSIZE + 1 * MARGIN, MAPYSIZE + 1 * MARGIN);

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

		setColor(255,0,0);
		for (int i = 1; i < gpspoints.length; i++) {
			GPSPoint p1 = gpspoints[i - 1]; 
			GPSPoint p2 = gpspoints[i];
			
			int x1 = MARGIN + (int) ((p1.getLongitude() - minlon) * xstep);
			int y1 = ybase - (int) ((p1.getLatitude() - minlat) * ystep);
			
			int x2 = MARGIN + (int) ((p2.getLongitude() - minlon) * xstep);
			int y2 = ybase - (int) ((p2.getLatitude() - minlat) * ystep);
			
			drawLine(x1, y1, x2, y2);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		int x = MARGIN;
		int y = MARGIN;
		
		setColor(0,0,0);
		setFont("Courier",12);
		
		double totalDistance = gpscomputer.totalDistance();
		double totalElevation = gpscomputer.totalElevation();
		double maxSpeed = gpscomputer.maxSpeed();
		double averageSpeed = gpscomputer.averageSpeed();
		double totalKcal = gpscomputer.totalKcal(80.0);
		
		drawString(String.format("Total distance: %.2f km", totalDistance / 1000), x, y);
		drawString(String.format("Total elevation: %.2f m", totalElevation), x, y + TEXTDISTANCE);
		drawString(String.format("Mac speed: %.2f km/h", maxSpeed), x, y + 2 * TEXTDISTANCE);
		drawString(String.format("Average speed: %.2f km/h", averageSpeed), x, y + 3 * TEXTDISTANCE);
		drawString(String.format("Energy: %.2f kcal", totalKcal), x, y + 4 * TEXTDISTANCE);
	}

	public void replayRoute(int ybase) {

		setSpeed(2);
		int RADIUS = 5;
		
		GPSPoint start = gpspoints[0];
		int x = MARGIN + (int) ((start.getLongitude() - minlon) * xstep);
		int y = ybase - (int) ((start.getLatitude() - minlat) * ystep);
		
		int circle = fillCircle(x, y, RADIUS);
		setColor(255,0,0);
		
		for (int i = 0; i < gpspoints.length; i++) {
			GPSPoint next = gpspoints[i];
			
			int nextX = MARGIN + (int) ((next.getLongitude() - minlon) * xstep);
			int nextY = ybase - (int) ((next.getLatitude() - minlat) * ystep);
			
			moveCircle(circle, nextX, nextY);
 		}
 	}

}