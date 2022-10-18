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

	public void showRouteMap(int ybase) {

		// TODO - START
		int lon;
		int lat;
		setColor(255,0,0);
		for(int i = 0; i < gpspoints.length; i++) {
			lon = (int) gpspoints[i].getLongitude();
			lat = (int) gpspoints[i].getLatitude();
			drawLine(MARGIN+lon, ybase-lat, MARGIN+lon, ybase-lat);
		}
		// TODO - SLUTT
	}

	public void showStatistics() {
		int TEXTDISTANCE = 20;
		setColor(0,0,0);
		setFont("Courier",12);
		// TODO - START
		NumberFormat formatter = new DecimalFormat("#0.00");
		drawString("Total Time       : " + GPSUtils.formatTime(gpscomputer.totalTime()), MARGIN, TEXTDISTANCE);
		drawString("Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km", MARGIN, TEXTDISTANCE*2);
		drawString("Total elevation:     " + formatter.format(gpscomputer.totalElevation()) + " m", MARGIN, TEXTDISTANCE*3);
		drawString("Max speed       : " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t", MARGIN, TEXTDISTANCE*4);
		drawString("Average speed: " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", MARGIN, TEXTDISTANCE*5);
		drawString("Energy              :       " + formatter.format(gpscomputer.totalKcal(80)) + " kcal", MARGIN, TEXTDISTANCE*6);
		// TODO - SLUTT;
	}

}
