package no.hvl.dat100ptc.oppgave5;

import no.hvl.dat100ptc.TODO;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;		// margin on the sides 
	
	private static final int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		int x = MARGIN; // Start tegning fra marg
		int barWidth = 3; // Bredde på hver søyle
	
		for (GPSPoint point : gpspoints) {
			int elevation = (int) point.getElevation(); // Hent høyden (elevasjonen)
			
			if (elevation < 0) {
				elevation = 0; // Behandle negative høyder som 0
			}
	
			// Sørg for at søylehøyden ikke overstiger MAXBARHEIGHT
			int barHeight = Math.min(elevation, MAXBARHEIGHT);
	
			// Tegn den vertikale søylen fra (x, ybase) til (x, ybase - barHeight)
			drawLine(x, ybase, x, ybase - barHeight);
	
			// Flytt x-koordinaten for neste søyle
			x += barWidth;
		}
	}

}
