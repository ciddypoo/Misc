package com.routing;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerInterface {
	String address = "/Users/AEF/Desktop/Osrm/curl \"http://127.0.0.1:5000/route/v1/driving/";
	String param = "?generate_hints=false&steps=true&geometries=geojson&overview=full\"";	
	String coordinates;
	
	public String serverQuery (double lat1, double lon1, double lat2, double lon2) {
		coordinates = Double.toString(lat1) + "," + Double.toString(lon1) + ";" + Double.toString(lat2) + "," + Double.toString(lon2);	
		StringBuilder sb = new StringBuilder();
		try {			
            Process process = Runtime.getRuntime().exec(address + coordinates + param );           
            BufferedReader stdInput = new BufferedReader(new 
            	     InputStreamReader(process.getInputStream()));           
            BufferedReader stdError = new BufferedReader(new 
            	     InputStreamReader(process.getErrorStream()));
            String s = null;            
          
            while ((s = stdInput.readLine()) != null) {
            	sb.append(s);          	
            }		             	
             
	        } catch (Exception ex) {
	            // process cause
	            ex.printStackTrace();
	        }		
			return sb.toString();		
	}
	public String serverQuery (String coords) {
		coordinates = coords;	
		StringBuilder sb = new StringBuilder();
		try {			
            Process process = Runtime.getRuntime().exec(address + coordinates + param );           
            BufferedReader stdInput = new BufferedReader(new 
            	     InputStreamReader(process.getInputStream()));           
            BufferedReader stdError = new BufferedReader(new 
            	     InputStreamReader(process.getErrorStream()));
            String s = null;            
          
            while ((s = stdInput.readLine()) != null) {
            	sb.append(s);          	
            }		             	
             
	        } catch (Exception ex) {
	            // process cause
	            ex.printStackTrace();
	        }		
			return sb.toString();		
	}	
}
