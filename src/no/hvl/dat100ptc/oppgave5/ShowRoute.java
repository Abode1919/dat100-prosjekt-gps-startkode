package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;
import easygraphics.EasyGraphics;

public class ShowRoute extends EasyGraphics {

    private static int MARGIN = 50;       // margen på sidene av vinduet
    private static int MAPXSIZE = 800;    // breidde på kartet
    private static int MAPYSIZE = 800;    // høgd på kartet

    private GPSPoint[] gpspoints;          // array for å lagre GPS-punkta
    private GPSComputer gpscomputer;       // instans av GPSComputer for å hente data

    private double minlon, minlat, maxlon, maxlat; // variablar for minimum og maksimum longitud og latitud

    private double xstep, ystep;           // skaleringsfaktorar for x- og y-aksen

    // Konstruktør for ShowRoute
    public ShowRoute() {
        // Ber brukaren om å oppgi filnavnet til GPS-data
        String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
        gpscomputer = new GPSComputer(filename); // Opprettar GPSComputer med filnavn

        gpspoints = gpscomputer.getGPSPoints(); // Henter GPS-punkta
    }

    // Hovudmetode for å starte programmet
    public static void main(String[] args) {
        launch(args); // starter EasyGraphics
    }

    // Metode for å opprette vinduet og vise ruten
    public void run() {
        // Opprettar vindu med breidde og høgd basert på kartstørrelsane og margen
        makeWindow("Route", MAPXSIZE + 1 * MARGIN, MAPYSIZE + 1 * MARGIN);

        // Finnar minimum og maksimum longitud og latitud
        minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
        minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

        maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
        maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

        // Beregnar skaleringsfaktor for kartet
        xstep = scale(MAPXSIZE, minlon, maxlon);
        ystep = scale(MAPYSIZE, minlat, maxlat);

        // Teiknar kartet for ruta
        showRouteMap(MARGIN + MAPYSIZE);

        // Spelar av ruta ved å animere GPS-punkta
        replayRoute(MARGIN + MAPYSIZE);
        
        // Viser statistikk for ruta
        showStatistics();
    }

    // Metode for å beregne skaleringsfaktor
    public double scale(int maxsize, double minval, double maxval) {
        double step = maxsize / (Math.abs(maxval - minval)); // Beregner skaleringsfaktoren
        return step;
    }

    // Metode for å vise ruta i kartet
    public void showRouteMap(int ybase) {
        setColor(255, 0, 0); // Sett farge for linja (raud)
        
        // Går gjennom GPS-punkta og teiknar linjer mellom dem
        for (int i = 1; i < gpspoints.length; i++) {
            GPSPoint p1 = gpspoints[i - 1]; 
            GPSPoint p2 = gpspoints[i];
            
            // Beregnar x og y-koordinatar for begge punkta
            int x1 = MARGIN + (int) ((p1.getLongitude() - minlon) * xstep);
            int y1 = ybase - (int) ((p1.getLatitude() - minlat) * ystep);
            
            int x2 = MARGIN + (int) ((p2.getLongitude() - minlon) * xstep);
            int y2 = ybase - (int) ((p2.getLatitude() - minlat) * ystep);
            
            drawLine(x1, y1, x2, y2); // Teiknar linje mellom punkta
        }
    }

    // Metode for å vise statistikk om ruta
    public void showStatistics() {
        int TEXTDISTANCE = 20; // Avstand mellom tekstlinjene

        int x = MARGIN; // Startkoordinat for tekst
        int y = MARGIN; 
        
        setColor(0, 0, 0); // Sett farge for teksten (svart)
        setFont("Courier", 12); // Sett font for teksten
        
        // Henter statistikk frå GPSComputer
        double totalDistance = gpscomputer.totalDistance();
        double totalElevation = gpscomputer.totalElevation();
        double maxSpeed = gpscomputer.maxSpeed();
        double averageSpeed = gpscomputer.averageSpeed();
        double totalKcal = gpscomputer.totalKcal(80.0); // Antatt vekt 80 kg
        
        // Teiknar statistikk på skjermen
        drawString(String.format("Total distance: %.2f km", totalDistance / 1000), x, y);
        drawString(String.format("Total elevation: %.2f m", totalElevation), x, y + TEXTDISTANCE);
        drawString(String.format("Max speed: %.2f km/h", maxSpeed), x, y + 2 * TEXTDISTANCE);
        drawString(String.format("Average speed: %.2f km/h", averageSpeed), x, y + 3 * TEXTDISTANCE);
        drawString(String.format("Energy: %.2f kcal", totalKcal), x, y + 4 * TEXTDISTANCE);
    }

    // Metode for å animere ruta
    public void replayRoute(int ybase) {
        setSpeed(2); // Setter hastighet for animasjonen
        int RADIUS = 5; // Radius for sirkelen som representerer posisjonen
        
        GPSPoint start = gpspoints[0]; // Startpunkt
        int x = MARGIN + (int) ((start.getLongitude() - minlon) * xstep);
        int y = ybase - (int) ((start.getLatitude() - minlat) * ystep);
        
        // Teiknar sirkelen på startpunktet
        int circle = fillCircle(x, y, RADIUS);
        setColor(255, 0, 0); // Setter farge for cirkelen (raud)
        
        // Går gjennom alle GPS-punkta for å animere ruta
        for (int i = 0; i < gpspoints.length; i++) {
            GPSPoint next = gpspoints[i];
            
            int nextX = MARGIN + (int) ((next.getLongitude() - minlon) * xstep);
            int nextY = ybase - (int) ((next.getLatitude() - minlat) * ystep);
            
            moveCircle(circle, nextX, nextY); // Flyttar sirkelen til neste punkt
        }
    }
}
