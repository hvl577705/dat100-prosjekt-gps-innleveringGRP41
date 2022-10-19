package no.hvl.dat100ptc.oppgave5;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat-minlat));
		return ystep;
		// TODO - SLUTT
		
	}

	/**
	 * @param ybase
	 */
	/**
	 * @param ybase
	 */
	public void showRouteMap(int ybase) {
		// TODO - START
		double lon;
		double lat;

//		for(int i = 0; i < gpspoints.length; i++) {
//			
//		}
		double lon2, lat2;
		int latit, longi, latit2, longi2;
		
		for(int i = 1; i < gpspoints.length; i++) {
			lon = gpspoints[i-1].getLongitude();
			lat = gpspoints[i-1].getLatitude();
			lon = (lon * 1000 - 5000) *2;
			lat = (lat * 1000 - 60000) *2;
			
			lon2 = gpspoints[i].getLongitude();
			lat2 = gpspoints[i].getLatitude();
			lon2 = (lon2 * 1000 - 5000) *2;
			lat2 = (lat2 * 1000 - 60000) *2;
			
			longi = (int) Math.round(lon);
			latit = (int) Math.round(lat);

			longi2 = (int) Math.round(lon2);
			latit2 = (int) Math.round(lat2);
			

			setColor(0,255,0);
			drawLine(longi, ybase-latit, longi2, ybase-latit2);
			setColor(255,0,0);
			drawLine(longi, ybase-latit, longi, ybase-latit);
		}
		// TODO - SLUTT
	}

	public void showStatistics() {
		int TEXTDISTANCE = 20;
		setColor(0,0,0);
		setFont("Courier",12);
		// TODO - START
		String[] navnTab = new String[6];
		navnTab[0] = "Total time ";
		navnTab[1] = "Total distance ";
		navnTab[2] = "Total elevation ";
		navnTab[3] = "Max speed ";
		navnTab[4] = "Average speed ";
		navnTab[5] = "Energy ";
		
		String[] statTab = new String[6];
		statTab[0] = " " + GPSUtils.formatTime(gpscomputer.totalTime()) + " ";
		statTab[1] = " " + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km ";
		statTab[2] = "" + GPSUtils.formatDouble(gpscomputer.totalElevation()) + "0" + " m ";
		statTab[3] = " " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t ";
		statTab[4] = " " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t ";
		statTab[5] = "" + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + "0" + " kcal ";
		
		String kolon = ":";
		
		for(int i = 0; i<navnTab.length; i++) {
			drawString(navnTab[i], MARGIN, TEXTDISTANCE+20*i);
			drawString(kolon, MARGIN + 110, TEXTDISTANCE+20*i);
			drawString(statTab[i], MARGIN + 120, TEXTDISTANCE+20*i);
		}
		// TODO - SLUTT;
	}

}
