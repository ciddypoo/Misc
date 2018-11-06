package com.routing;

public class Waypoint {
	
	public String name;
	public String distance;
	public String longitude;
	public String latitude;
	public String waypointsLocation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getWaypointsLocation() {
		return waypointsLocation;
	}
	public void setWaypointsLocation(String waypointsLocation) {
		this.waypointsLocation = waypointsLocation.replaceAll("\\[", "");
		this.waypointsLocation = this.waypointsLocation.replaceAll("\\]", "");
		convertLocation(this.waypointsLocation);
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	private void convertLocation(String location) {
		String[] x = location.split(",");
		this.longitude = x[0];
		this.latitude = x[1];		
	}
}


