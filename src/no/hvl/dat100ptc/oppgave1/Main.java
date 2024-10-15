package no.hvl.dat100ptc.oppgave1;

public class Main {

    public static void main(String[] args) {
        
        // Opprettar eit GPSPoint-objekt med tid = 1, breiddegrad = 2.0, lengdegrad = 3.0, og høgde = 5.0
        GPSPoint gpsPoint = new GPSPoint(1, 2.0, 3.0, 5.0);
        
        // Skriver ut tidspunktet (tid-verdien) til gpsPoint ved hjelp av getTime()-metoden
        System.out.println("Tidspunkt: " + gpsPoint.getTime());
        
        // Endrar tid-verdien til gpsPoint til 2 ved hjelp av setTime()-metoden
        gpsPoint.setTime(2);
        
        // Skriver ut strengrepresentasjonen av gpsPoint ved hjelp av toString()-metoden
        System.out.println(gpsPoint.toString());
    }

}
