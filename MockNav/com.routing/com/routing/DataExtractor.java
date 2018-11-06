package com.routing;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

public class DataExtractor {
	ArrayList<Waypoint> waypoints = new ArrayList<>();
	ArrayList<Geometry> linkPoints = new ArrayList<>();
	Waypoint geoPoint1 = new Waypoint();
	Waypoint geoPoint2 = new Waypoint();
	Waypoint geoPoint3 = new Waypoint();
	
	public ArrayList<Geometry> extractLinkShape(String serverOutput) {
		String geoPatternString = "(?<=(\"coordinates\":.)).*?(]})";
        Pattern geoPattern = Pattern.compile(geoPatternString);
        Matcher geoMatcher = geoPattern.matcher(serverOutput);
		
		String[] sp = new String[1];
		StringBuffer sbb = new StringBuffer();
		while(geoMatcher.find()) {
			sbb.append(geoMatcher.group());
		}
		
		sp = sbb.toString().split("]}");
	    
	    for (int i = 1; i < sp.length; i++) {	        	
		   	 String[] x = sp[i].split("\\]\\,\\[");
			 if (sp.length > i+1) {
			   	 geoPoint1.setWaypointsLocation(x[x.length-2]);
			   	 geoPoint2.setWaypointsLocation(x[x.length-1]);
			   	 x = sp[i+1].split("\\]\\,\\[");
				 geoPoint3.setWaypointsLocation(x[1]);
				 linkPoints.add(new Geometry(geoPoint1,geoPoint2,geoPoint3));  
				 geoPoint1 = new Waypoint();
				 geoPoint2 = new Waypoint();
				 geoPoint3 = new Waypoint();
			 }   	    	    		   	 
	    }
	    //System.out.println(linkPoints.get(0).getStart().latitude);
	    //System.out.println(linkPoints.get(0).getStart().longitude);
	    System.out.println(linkPoints.get(0).getIntersection().latitude);
	    System.out.println(linkPoints.get(0).getIntersection().longitude);
	   // System.out.println(linkPoints.get(0).getEnd().latitude);
	   // System.out.println(linkPoints.get(0).getEnd().longitude);	   
	    return linkPoints;
	}

	public double [] extractGpsPos (String s) {
		  double[] pos = new double[2];
		  String latPatternString = "(?<=(\"VehiclePosLat\" value=\"))(\\d)+";
	      Pattern latPattern = Pattern.compile(latPatternString);
	      Matcher latMatcher = latPattern.matcher(s);
	      
	      while(latMatcher.find()) {
	    	  //System.out.println(latMatcher.group());
	    	  pos[0] = Double.parseDouble(latMatcher.group());
	      }
	      
	      String longPatternString = "(?<=(\"VehiclePosLon\" value=\"))(\\d)+";
	      Pattern longPattern = Pattern.compile(longPatternString);
	      Matcher longMatcher = longPattern.matcher(s);
	      
	      while(longMatcher.find()) {
	    	  //System.out.println(longMatcher.group());
	    	  pos[1] = Double.parseDouble(longMatcher.group());
	      }
	      //System.out.println(pos[0]+" !!!!! "+pos[1]);
	      return pos;
	}
	public void flushList() {
		linkPoints.clear();
	}

	public void toDo() {
		//String[] s1 = sb.toString().split("}]");
		//int count = 0;
        
        //for (int i =0; s1.length > i; i++)"VehiclePosLat" value="
        //System.out.println(sb.toString());
        
//      String latPatternString = "(?<=\"location\":\\[)(\\d)([^,]+)";
//      Pattern latPattern = Pattern.compile(latPatternString);
//      Matcher latMatcher = latPattern.matcher(sb.toString());
//      String longPatternString = "(?<=(\"location\":.))(\\d)([^\\]]+)";
//      Pattern longPattern = Pattern.compile(longPatternString);
//      Matcher longMatcher = longPattern.matcher(sb.toString());
        
        
        
//        String locationPatternString = "(?<=(\"location\":.))+";
//        Pattern locationPattern = Pattern.compile(locationPatternString);
//        Matcher locationMatcher = locationPattern.matcher(sb.toString());
//        
//        
//                  
//        String namePatternString = "(?<=\"name\":\")([a-zA-Z])([^\"]+)";
//        Pattern namePattern = Pattern.compile(namePatternString);
//        Matcher nameMatcher = namePattern.matcher(sb.toString());
//        
//        String distancePatternString = "(?<=\"distance\":)(\\d)([^,])+";
//        Pattern distancePattern = Pattern.compile(distancePatternString);
//        Matcher distanceMatcher = distancePattern.matcher(sb.toString());

//        while(latMatcher.find()) {
//            //System.out.println(latMatcher.group());
//        	Waypoint wp = new Waypoint();
//            wp.setLatitude(latMatcher.group());                              
//            wps.add(wp);               
//        }

//        while(locationMatcher.find()) {
//            //System.out.println(latMatcher.group());
//        	//if (count > 3) {
//        	Waypoint wp = new Waypoint();
//        	String[] parts = locationMatcher.group().split(",");
//        	wp.setLatitude(parts[0]);
//        	wp.setLongitude(parts[1]); 
//        	//System.out.println(wp.getLatitude() + "," + wp.getLongitude());            	
//            wps.add(wp);    
//        	//}
//        	count ++;
//        }
        
//        count = 0;
//        while(longMatcher.find()) {
//            System.out.println("long: " +longMatcher.group()); 
//            //System.out.println(count);
//            wps.get(count).setLongitude(longMatcher.group());
//            count++;
//        }
        
//        count = 0;
//        while(nameMatcher.find()) {
//            //System.out.println(nameMatcher.group()); 
//            wps.get(count).setName(nameMatcher.group());
//            count++;
//        }
        
        
		
        
//        count = 0;
//        while(distanceMatcher.find()) {
//            //System.out.println(distanceMatcher.group()); 
//            if(count != 0) {
//        	wps.get(count).setDistance(distanceMatcher.group());
//            }
//            count++;
//        }
//        
//        

//	    for (int i=0;i < linkPoints.size() ; i++) {            	
//	    	System.out.println("Start lat/long " + i + ": " + linkPoints.get(i).getStart().getLatitude() + "," + linkPoints.get(i).getStart().getLongitude());
//	    	System.out.println("Inter lat/long " + i + ": " + linkPoints.get(i).getIntersection().getLatitude() + "," + linkPoints.get(i).getIntersection().getLongitude());
//	    	System.out.println("End lat/long " + i + ": " + linkPoints.get(i).getEnd().getLatitude() + "," + linkPoints.get(i).getEnd().getLongitude());
//	    	System.out.println("");
//	    }

	
	}


	public ArrayList<Geometry> getLinkPoints() {
		return linkPoints;
	}
	
	public void setLinkPoints(ArrayList<Geometry> linkPoints) {
		this.linkPoints = linkPoints;
	}
	
	
}
