package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

    // Indeks for start på tidsdelen av streng (forbi dato)
	private static int TIME_STARTINDEX = 11;

    // Metode som konverterer ein tidsstreng (hh:mm:ss) til total tid i sekund
	public static int toSeconds(String timestr) {
		
		int secs;   // Total tid i sekund
		int hr, min;   // Time og minutt
		double sec;   // Sekund

		// Hentar ut tid-delen av strengen (etter kl. 11)
		String timePart = timestr.substring(TIME_STARTINDEX);
		
		// Splittar tid-delen i time, minutt og sekund
		String[] timeComponents = timePart.split(":");
		
        hr = Integer.parseInt(timeComponents[0]);   // Konverterer time til integer
        min = Integer.parseInt(timeComponents[1]);  // Konverterer minutt til integer
        
        // Fjernar 'Z' og konverterer sekund til double
        String secPart = timeComponents[2].replace("Z", "");
        sec = Double.parseDouble(secPart);
         
        // Reknar ut total tid i sekund: time * 3600 + minutt * 60 + sekund
        secs = hr * 3600 + min * 60 + (int) sec;
        return secs;  // Returnerer den totale tida i sekund
	}

    // Metode som konverterer strenger for tid, breiddegrad, lengdegrad og høgde til eit GPSPoint
	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		int timeInSeconds = toSeconds(timeStr);  // Konverterer tid til sekund
		
		double latitude = Double.parseDouble(latitudeStr);    // Konverterer breiddegrad til double
		double longitude = Double.parseDouble(longitudeStr);  // Konverterer lengdegrad til double
		double elevation = Double.parseDouble(elevationStr);  // Konverterer høgde til double
			
		// Opprettar og returnerer eit GPSPoint basert på dei konverterte verdiane
		GPSPoint gpsPoint = new GPSPoint(timeInSeconds, latitude, longitude, elevation);
		
		return gpsPoint;
				
	}
	
}
