package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
    
		// Hent hastighetsdata
		double[] speeds = gpscomputer.speeds();
		
		// Sett farge for søylene
		setColor(0, 0, 255); // Blå farge for hastighetssøyler
		
		// Tegn en søyle for hver hastighet
		int x = MARGIN;
		for (int i = 0; i < speeds.length; i++) {
			// Skaler hastigheten til høyden på søylen (multiplikasjonsfaktor kan justeres)
			int barHeight = (int) speeds[i];  // Alternativt: (int)(speeds[i] * 5) for mer synlige søyler
			
			// Y-posisjonen for søylen starter på ybase og går oppover avhengig av hastigheten
			int y = ybase - barHeight;
			
			// Tegn søylen
			drawLine(x, ybase, x, y);
			
			// Øk x-koordinaten for neste søyle (bredde på søylene kan justeres)
			x += 2; // Avstand mellom søylene
		}
	}
}
