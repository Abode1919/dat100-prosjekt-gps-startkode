package no.hvl.dat100ptc.oppgave2;

import java.io.*;

import javax.swing.*;

public class GPSDataFileReader {

    // Separator brukt i CSV-filer (kommaseparerte verdier)
	private static String SEP_STR = ",";

    // Format for GPS-data i fila (vi les berre dei fire første felta)
	private static  String GPSDATA_FORMAT = "time,lat,lon,elevation,accuracy,bearing,speed,satellites,"
			+ "provider,hdop,vdop,pdop,geoidheight,ageofdgpsdata,dgpsid,activity,battery,annotation";

    // Plassering av GPS-loggfiler i prosjektet (i logs-mappa)
	private static String GPSLOGS_DIR = System.getProperty("user.dir") + "/logs/";

    // Metode for å lese ei GPS-fil og returnere eit GPSData-objekt
	public static GPSData readGPSFile(String filename) {

		BufferedReader br = null;  // Leser frå fila
		GPSData gpsdata = null;    // GPSData som skal returnerast

		String time, latitude, longitude, elevation;  // Strenger for å halde på GPS-data

		try {

			// Opprettar ein BufferedReader for å lese frå fila med namnet filename.csv
			br = new BufferedReader(new FileReader(GPSLOGS_DIR + filename + ".csv"));

			// Les første linje som spesifiserer kor mange dataoppføringar det er i fila
			String line = br.readLine();
			int n = Integer.parseInt(line);  // Konverterer linja til eit heiltal

			// Opprettar GPSData-objekt med plass til n oppføringar
			gpsdata = new GPSData(n);

			// Hopp over beskrivingslinja (andre linja) ved å lese den utan å bruke den
			line = br.readLine();

			int i = 0;

			// Les neste linje med faktiske GPS-data
			line = br.readLine();

			// Løkke som går gjennom alle linjene med GPS-data til vi har lest n oppføringar
			while (line != null && i < n) {

				// Splittar kvar linje basert på komma-separatoren
				String[] gpsdatapoint = line.split(SEP_STR);

				// Hentar ut tid, breiddegrad, lengdegrad og høgde
				time = gpsdatapoint[0];
				latitude = gpsdatapoint[1];
				longitude = gpsdatapoint[2];
				elevation = gpsdatapoint[3];
				
				// Legg inn GPS-punktet i gpsdata-objektet
				gpsdata.insert(time, latitude, longitude, elevation);

				// Prøver å lese neste linje
				line = br.readLine();
				i++;  // Teller kor mange oppføringar som er lest
			}

		} catch (FileNotFoundException e) {
		    // Viser feilmelding dersom fila ikkje finst
		    JOptionPane.showMessageDialog(null, "GPS filen " + filename + " finnes ikke");
			e.printStackTrace();
		} catch (IOException e) {
		    // Viser feilmelding dersom fila ikkje kan lesast
			JOptionPane.showMessageDialog(null, "GPS filen " + filename + " kunne ikke leses");
			e.printStackTrace();
		} finally {
		    // Forsøk å lukke filen dersom den er open
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Returnerer GPSData-objektet med alle innlesne GPS-punkt
		return gpsdata;
	}
}
