package no.hvl.dat100ptc.oppgave1;

public class GPSPoint {

    // Objektvariable
    private int time;         // tiden i sekunder
    private double latitude;  // breddegrad
    private double longitude; // lengdegrad
    private double elevation; // høyde i meter

    // Konstruktør
    public GPSPoint(int time, double latitude, double longitude, double elevation) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    // Get- og Set-metoder
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    // toString-metoden som returnerer strengrepresentasjon
    @Override
    public String toString() {
        return time + " (" + latitude + "," + longitude + ") " + elevation + "\n";
    }
}
