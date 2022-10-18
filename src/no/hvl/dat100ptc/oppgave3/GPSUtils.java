package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max = da[0];
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	public static double findMin(double[] da) {
		double min;
		// TODO - START
		min = da[0];
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
		// TODO - SLUT
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		// TODO - START
		double[] latTab = new double[gpspoints.length];
		for(int i = 0; i<gpspoints.length; i++) {
			latTab[i] = gpspoints[i].getLatitude();
		}
		return latTab;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		// TODO - START
		double[] lonTab = new double[gpspoints.length];
		for(int i = 0; i<gpspoints.length; i++) {
			lonTab[i] = gpspoints[i].getLongitude();
		}
		return lonTab;
		// TODO - SLUTT
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double d;
		double lat1 = gpspoint1.getLatitude(), 
				long1 = gpspoint1.getLongitude(), 
				lat2 = gpspoint2.getLatitude(),
				long2 = gpspoint2.getLongitude();
		// TODO - START
		double a = pow(sin(toRadians(lat2-lat1)/2),2) + 
				(cos(toRadians(lat1)) * cos(toRadians(lat2)) * 
						pow(sin((toRadians(long2)-toRadians(long1))/2),2));
		double c = 2*(atan2(sqrt(a), sqrt(1-a)));
		d = R * c;
		return d;
		// TODO - SLUTT
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		int secs;
		double speed;
		// TODO - START
		double dist = distance(gpspoint1, gpspoint2);
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = dist/secs;
		speed = speed * 3.6;
		return speed;
		// TODO - SLUTT
	}

	public static String formatTime(int secs) {
		String timestr = "  ";
		String TIMESEP = ":";
		// TODO - START
		int hh = secs / 3600;
		timestr += String.format("%02d",hh) + TIMESEP;
		int mm = (secs - hh * 3600) / 60;
		timestr += String.format("%02d",mm) + TIMESEP;
		int ss = (secs - hh * 3600) - (mm * 60);
		timestr += String.format("%02d",ss);
		return timestr;
		// TODO - SLUTT
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		String str;
		// TODO - START
		str = "" + Math.round(d * 100.0) / 100.0;
		String spc = " ";
		if(str.length()<TEXTWIDTH) {
			while(str.length()<TEXTWIDTH) {
				str = spc + str;
			}
		}
		return str;
		// TODO - SLUTT
	}
}
