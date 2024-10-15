package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

    private static int TIME_STARTINDEX = 11; 

    public static int toSeconds(String timestr) {
        // Ekstraherer time, minutt og sekund frå tidsstrengen
        String hourStr = timestr.substring(TIME_STARTINDEX, TIME_STARTINDEX + 2);
        String minuteStr = timestr.substring(TIME_STARTINDEX + 3, TIME_STARTINDEX + 5);
        String secondStr = timestr.substring(TIME_STARTINDEX + 6, TIME_STARTINDEX + 8);
        
        // Konverterer til heiltal
        int hours = Integer.parseInt(hourStr);
        int minutes = Integer.parseInt(minuteStr);
        int seconds = Integer.parseInt(secondStr);
        
        // Konverterer til totale sekund
        return hours * 3600 + minutes * 60 + seconds;
    }

    public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
        // Konverterer tid
        int timeInSeconds = toSeconds(timeStr);
        
        // Konverterer breddegrad, lengdegrad og høgd til double
        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);
        double elevation = Double.parseDouble(elevationStr);
        
        // Opprettar og returnerer eit nytt GPSPoint-objekt
        return new GPSPoint(timeInSeconds, latitude, longitude, elevation);
    }
}
