package no.hvl.dat100ptc.oppgave2;

import java.io.*;

import javax.swing.*;

public class GPSDataFileReader {

    // separator i CSV-filer
    private static String SEP_STR = ",";

    private static  String GPSDATA_FORMAT = "time,lat,lon,elevation,accuracy,bearing,speed,satellites,"
            + "provider,hdop,vdop,pdop,geoidheight,ageofdgpsdata,dgpsid,activity,battery,annotation";

    // plassering av GPS-datafiler i dette Eclipse-prosjektet
    private static String GPSLOGS_DIR = "../logs/";

    public static GPSData readGPSFile(String filename) {
        BufferedReader br = null;
        GPSData gpsdata = null;

        String time, latitude, longitude, elevation;

        try {
            br = new BufferedReader(new FileReader(GPSLOGS_DIR + filename + ".csv"));

            String line = br.readLine();

            // første linje spesifiserer talet på innslag i gps-datafila
            int n = Integer.parseInt(line);

            // allokerer tabellar for det rette talet av innslag
            gpsdata = new GPSData(n);

            // hoppar over beskrivelseslinja ved å berre lese den
            line = br.readLine();

            int i = 0;

            line = br.readLine();

            while (line != null && i < n) {
                // splittar logginnslag
                String[] gpsdatapoint = line.split(SEP_STR);

                time = gpsdatapoint[0];
                latitude = gpsdatapoint[1];
                longitude = gpsdatapoint[2];
                elevation = gpsdatapoint[3];
                
                gpsdata.insert(time, latitude, longitude, elevation);

                // prøver å lese neste linje
                line = br.readLine();
                i++;
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"GPS-fila " + filename + " finst ikkje");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"GPS-fila " + filename + " kunne ikkje lesast");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return gpsdata;
    }
}
