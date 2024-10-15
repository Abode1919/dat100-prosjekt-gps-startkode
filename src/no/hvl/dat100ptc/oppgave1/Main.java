package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		GPSPoint gpsPoint = new GPSPoint(1, 2.0, 3.0, 5.0);
        
        System.out.println("Tidspunkt: " + gpsPoint.getTime());
        
        gpsPoint.setTime(2);
        
        System.out.println(gpsPoint.toString());
    }

}