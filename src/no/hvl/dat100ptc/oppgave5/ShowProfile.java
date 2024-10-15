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

    private static final int MARGIN = 50;       // margen på sidene av vinduet
    private static final int MAXBARHEIGHT = 500; // maksimal høgde, antatt at ingen høgde overstiger 500 meter
    
    private GPSPoint[] gpspoints; // array for å lagre GPS-punkta

    // Konstruktør for ShowProfile
    public ShowProfile() {
        // Ber brukaren om å oppgi filnavnet til GPS-data (utan .csv)
        String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): ");
        
        // Opprettar ein GPSComputer for å lese GPS-data frå fil
        GPSComputer gpscomputer = new GPSComputer(filename);
        
        // Henter GPS-punkta frå GPSComputer
        gpspoints = gpscomputer.getGPSPoints();
    }

    // Hovudmetode for å starte programmet
    public static void main(String[] args) {
        launch(args); // starter EasyGraphics
    }

    // Metode for å opprette vinduet og vise høgdeprofilen
    public void run() {
        int N = gpspoints.length; // talet på datapunkt
        
        // Opprettar vindu med breidde og høgde basert på talet på GPS-punkta
        makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

        // Teiknar høgdeprofilen med basislinje i ybase + MAXBARHEIGHT
        showHeightProfile(MARGIN + MAXBARHEIGHT); 
    }

    // Metode for å vise høgdeprofilen i vinduet
    public void showHeightProfile(int ybase) {
        int x = MARGIN; // første høgde skal teiknast ved MARGIN

        // Går gjennom kvart GPS-punkt
        for (GPSPoint point : gpspoints) {
            int height = (int) Math.max(point.getElevation(), 0); // hentar høgda og sørger for at den ikkje er negativ
            
            int y = ybase - height; // beregner y-koordinaten for å teikne linja
            
            // Teiknar linje frå ybase til den aktuelle høgda
            drawLine(x, ybase, x, y);
            
            x++; // aukar x-koordinaten for neste punkt
        }
    }
}
