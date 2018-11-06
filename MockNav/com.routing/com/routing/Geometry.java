package com.routing;
import com.peertopark.java.geocalc.*;

public class Geometry {
	public Waypoint initStart;
	public Waypoint start;
	public Waypoint intersection;
	public Waypoint end;
	public Waypoint initEnd;

	
	Geometry(Waypoint x, Waypoint y, Waypoint z) {
		initStart = new Waypoint();
		initEnd = new Waypoint();
		start = x;
		intersection = y;
		end = z;
		initStart.setLatitude(start.getLatitude());
		initStart.setLongitude(start.getLongitude());
		initEnd.setLatitude(end.getLatitude());
		initEnd.setLongitude(end.getLongitude());
		
		if(distance(Double.parseDouble(start.getLatitude()),Double.parseDouble(intersection.getLatitude()),Double.parseDouble(start.getLongitude()),Double.parseDouble(intersection.getLongitude())) >= 10) {
			Point p = interToStart(Double.parseDouble(start.getLatitude()),Double.parseDouble(intersection.getLatitude()),Double.parseDouble(start.getLongitude()),Double.parseDouble(intersection.getLongitude()));			
			start.setLatitude(Double.toString(p.getLatitude()));
			start.setLongitude(Double.toString(p.getLongitude()));
		};
		if(distance(Double.parseDouble(intersection.getLatitude()),Double.parseDouble(end.getLatitude()),Double.parseDouble(intersection.getLongitude()),Double.parseDouble(end.getLongitude())) >= 10) {
			Point p = interToEnd(Double.parseDouble(intersection.getLatitude()),Double.parseDouble(end.getLatitude()),Double.parseDouble(intersection.getLongitude()),Double.parseDouble(end.getLongitude()));			
			end.setLatitude(Double.toString(p.getLatitude()));
			end.setLongitude(Double.toString(p.getLongitude()));
		};
		
//		if(distance(Double.parseDouble(intersection.getLatitude()),Double.parseDouble(end.getLatitude()),Double.parseDouble(intersection.getLongitude()),Double.parseDouble(end.getLongitude())) <= 10) {
//			proxyPoint(Double.parseDouble(start.getLatitude()),Double.parseDouble(intersection.getLatitude()),Double.parseDouble(start.getLongitude()),Double.parseDouble(intersection.getLongitude()));
//		};
		
//			System.out.println(initStart.getLatitude() + "," + initStart.getLongitude());      	
//	    	System.out.println(start.getLatitude() + "," + start.getLongitude());
//	    	System.out.println(intersection.getLatitude() + "," + intersection.getLongitude());
//	    	System.out.println(end.getLatitude() + "," + end.getLongitude());
//	    	System.out.println(initEnd.getLatitude() + "," + initEnd.getLongitude());
//	    	System.out.println("--------------------------------------------------------------");	    
	}
	
	public static double distance(double lat1, double lat2, double lon1, double lon2 ) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = 1;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
	
	public Point interToStart(double lat1, double lat2, double lon1,double lon2) {
		Coordinate lat = new DegreeCoordinate(lat1);
		Coordinate lng = new DegreeCoordinate(lon1);
		Point p1 = new Point(lat, lng);

		lat = new DegreeCoordinate(lat2);
		lng = new DegreeCoordinate(lon2);
		Point p2 = new Point(lat, lng);
		
		double bearing = EarthCalc.getBearing(p2, p1); 
		
		Point newPoint = EarthCalc.pointRadialDistance(p2, bearing, 10);
		return newPoint;
	}
	
	public Point interToEnd(double lat1, double lat2, double lon1,double lon2) {
		Coordinate lat = new DegreeCoordinate(lat1);
		Coordinate lng = new DegreeCoordinate(lon1);
		Point p1 = new Point(lat, lng);

		lat = new DegreeCoordinate(lat2);
		lng = new DegreeCoordinate(lon2);
		Point p2 = new Point(lat, lng);
		
		double bearing = EarthCalc.getBearing(p1, p2); 
		
		Point newPoint = EarthCalc.pointRadialDistance(p1, bearing, 10);
		return newPoint;
	}
	
	public String calculateBearing () {
		Coordinate lat = new DegreeCoordinate(Double.parseDouble(this.intersection.latitude));
		Coordinate lng = new DegreeCoordinate(Double.parseDouble(this.intersection.longitude));
		Point p1 = new Point(lat, lng);

		lat = new DegreeCoordinate(Double.parseDouble(this.end.latitude));
		lng = new DegreeCoordinate(Double.parseDouble(this.end.longitude));
		Point p2 = new Point(lat, lng);
		
		double bearing = EarthCalc.getBearing(p1, p2); 
		return Double.toString(bearing);
	}

	public Waypoint getStart() {
		return start;
	}

	public void setStart(Waypoint start) {
		this.start = start;
	}

	public Waypoint getIntersection() {
		return intersection;
	}

	public void setIntersection(Waypoint intersection) {
		this.intersection = intersection;
	}

	public Waypoint getEnd() {
		return end;
	}

	public void setEnd(Waypoint end) {
		this.end = end;
	}

	
	
}
