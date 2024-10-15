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

    private static int MARGIN = 50;        // margen på sidene av vinduet
    private static int BARHEIGHT = 100;     // høgd på linjene som viser fart

    private GPSComputer gpscomputer;        // instans av GPSComputer for å hente fartdata

    // Konstruktør for ShowSpeed
    public ShowSpeed() {
        // Ber brukaren om å oppgi filnavnet til GPS-data
        String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
        gpscomputer = new GPSComputer(filename); // Opprettar GPSComputer med filnavn
    }

    // Hovudmetode for å starte programmet
    public static void main(String[] args) {
        launch(args); // starter EasyGraphics
    }

    // Metode for å opprette vinduet og vise fartprofilen
    public void run() {
        // Opprettar vindu med breidde og høgd basert på margen og antall fartdata
        makeWindow("Speed profile", 
                2 * MARGIN + 
                2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
        
        // Viser fartprofilen
        showSpeedProfile(MARGIN + BARHEIGHT);
    }
    
    // Metode for å vise fartprofilen
    public void showSpeedProfile(int ybase) {
        int x = MARGIN; // Startkoordinat for x
        double[] speeds = gpscomputer.speeds(); // Henter fartdata
        double averageSpeed = gpscomputer.averageSpeed(); // Henter gjennomsnittsfart
        
        // Konverterer gjennomsnittsfart frå m/s til km/h
        double averageSpeedKmH = averageSpeed * 3.6;
        
        // Går gjennom fartdataene og teiknar linjer for kvar fart
        for (int i = 0; i < speeds.length; i++) {
            // Konverterer fart frå m/s til km/h
            double speedKmH = speeds[i] * 3.6;
        
            // Beregnar y-koordinat for farten
            int speedY = ybase - (int) speedKmH;
            drawLine(x, ybase, x, speedY); // Teiknar linje for fart
            x++; // Aukar x-koordinaten for neste linje
        }
        
        // Teiknar ei horisontal linje for gjennomsnittsfarten
        int avgSpeedY = ybase - (int) averageSpeedKmH;
        drawLine(MARGIN, avgSpeedY, x, avgSpeedY); // Teiknar linje for gjennomsnittsfart
    }
}
