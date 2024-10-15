package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min;
		double sec;
		
		String timePart = timestr.substring(TIME_STARTINDEX);
		
		String[] timeComponents = timePart.split(":");
		
        hr = Integer.parseInt(timeComponents[0]);
        min = Integer.parseInt(timeComponents[1]);
        
        String secPart = timeComponents[2].replace("Z", "");
        sec = Double.parseDouble(secPart);
         
        secs = hr * 3600 + min * 60 + (int) sec;
        return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		int timeInSeconds = toSeconds(timeStr);
		
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
			
		GPSPoint gpsPoint = new GPSPoint(timeInSeconds, latitude, longitude, elevation);
		
		return gpsPoint;
				
	}
	
}