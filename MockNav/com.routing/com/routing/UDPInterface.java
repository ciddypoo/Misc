package com.routing;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UDPInterface {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private int port;
	String sentence;
	ServerInterface x = new ServerInterface();
	DataExtractor y = new DataExtractor();
	String XMLMsg;
	DatagramSocket serverSocket;
	byte[] receiveData;
	byte[] sendData;
	InetAddress IPAddress;
	DataOutputStream os;
	
	public UDPInterface(int port) throws FileNotFoundException {
		this.port = port;	
	}
	
	public void send() throws UnknownHostException, SocketException, IOException   {
		byte[] buffer = {10,23,12,31,43,32,24};
        byte [] IP={-64,-88,1,106};
        InetAddress address = InetAddress.getByAddress(IP);
        DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length, address, 57
                );
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
        //System.out.println(InetAddress.getLocalHost().getHostAddress());
	}
	
	public void buildConnection() throws SocketException, UnknownHostException {
		serverSocket = new DatagramSocket(port);
		//IPAddress = InetAddress.getByName("172.20.23.118");
		IPAddress = InetAddress.getByName("169.172.2.0");
	}
	
	public void closeConnection() {
		serverSocket.close();
	}
	
	public void receive(){
		Runnable receiver = new Runnable() {			
		    public void run() {	
		    	
		    	receiveData = new byte[1024];	
		        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		        //String tmp = XMLBuilder(y.extractLinkShape(x.serverQuery("11.645880,48.308196;11.655483,48.316698")));
		        //System.out.println(tmp); 
		        
		        
		    	try {
					buildConnection();
					serverSocket.receive(receivePacket);
					
					sentence = new String( receivePacket.getData(), 0,receivePacket.getLength());
		              
		            //System.out.println("RECEIVED: " + sentence );
		              
	                double[] gps = y.extractGpsPos(sentence);
	                // now send acknowledgement packet back to sender     	
	               
				    String sendString = XMLBuilder(y.extractLinkShape(x.serverQuery(gps[1]/921600+","+gps[0]/921600+";11.553850,48.182780")));
	                //String sendString = XMLBuilder(y.extractLinkShape(x.serverQuery(gps[1]/921600+","+gps[0]/921600+";11.655483,48.316698")));
	                //String sendString = "12345";
				    sendData = sendString.getBytes("UTF-8");	
				    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, port);
				    //System.out.println("sGPS: " + gps[1]/921600+","+gps[0]/921600 ); 
					   
				    serverSocket.send(sendPacket);
				    writeOut(sentence + "\n" + sendString);
				    y.flushList();
				    System.out.println("sent: " + sendString ); 
				   
		    	} catch (IOException e3) {
					e3.printStackTrace();
				}
		    	finally {
		    		serverSocket.close();
		    	}
		    }
		};
		
		final ScheduledFuture<?> receiverHandle =
			       scheduler.scheduleAtFixedRate(receiver, 2, 2 ,TimeUnit.SECONDS);
//			       scheduler.schedule(new Runnable() {
//			       public void run() { receiverHandle.cancel(true); }
//			     }, 60 * 60, TimeUnit.SECONDS);	
	}	
	
	
	public String XMLBuilder (ArrayList<Geometry> response) {
		String gpLat = response.get(0).intersection.getLatitude();
		String gpLon = response.get(0).intersection.getLongitude();
		String shape1Lat = response.get(0).start.getLatitude();
		String shape1Lon = response.get(0).start.getLongitude();
		String shape2Lat = response.get(0).intersection.getLatitude();
		String shape2Lon = response.get(0).intersection.getLongitude();
		String shape3Lat = response.get(0).end.getLatitude();
		String shape3Lon = response.get(0).end.getLongitude();
		String bearing = response.get(0).calculateBearing();
		
		gpLat = String.valueOf(((int)(Double.parseDouble(gpLat)* 921600)));
		gpLon = String.valueOf(((int)(Double.parseDouble(gpLon)* 921600)));
		shape1Lat = String.valueOf(((int)(Double.parseDouble(shape1Lat)* 921600)));
		shape1Lon = String.valueOf(((int)(Double.parseDouble(shape1Lon)* 921600)));
		shape2Lat = String.valueOf(((int)(Double.parseDouble(shape2Lat)* 921600)));
		shape2Lon = String.valueOf(((int)(Double.parseDouble(shape2Lon)* 921600)));
		shape3Lat = String.valueOf(((int)(Double.parseDouble(shape3Lat)* 921600)));
		shape3Lon = String.valueOf(((int)(Double.parseDouble(shape3Lon)* 921600)));
		bearing = String.valueOf(((int)(Double.parseDouble(bearing))));
		
		String xmlformat = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + 
				"<Notify id=\"\" name=\"Hud_Controller\" sender=\"Navi\" time=\"\" type=\"inform\" version=\"\">" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"GuidancePointLat[0]\" value=\""+gpLat+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"GuidancePointLon[0]\" value=\""+gpLon+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"Angle[0]\" value=\""+bearing+"\"/>  " + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeInfoCount\" value=\"3\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLat[0]\" value=\""+shape1Lat+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLat[1]\" value=\""+shape2Lat+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLat[2]\" value=\""+shape3Lat+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLon[0]\" value=\""+shape1Lon+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLon[1]\" value=\""+shape2Lon+"\"/>" + 
				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLon[2]\" value=\""+shape3Lon+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"GuidancePointLat[0]\" value=\""+gpLat+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"GuidancePointLon[0]\" value=\""+gpLon+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"Angle[0]\" value=\""+bearing+"\"/>  " + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeInfoCount\" value=\"3\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLat[0]\" value=\""+shape1Lat+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLat[1]\" value=\""+shape2Lat+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLat[2]\" value=\""+shape3Lat+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLon[0]\" value=\""+shape1Lon+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLon[1]\" value=\""+shape2Lon+"\"/>" + 
//				"<Item category=\"Navi\" reason=\"\" time=\"\" type=\"ShapeLon[2]\" value=\""+shape3Lon+"\"/>" +
				"</Notify>";
		return xmlformat;
	}
	public void writeOut(String s) {
		try {
			s +="\n";
//			os.writeUTF(tmp);
//			os.close();
        	String filen = "C:/testfile.txt";
        	
     
        		FileOutputStream FOS = new FileOutputStream(filen , true);
        		FOS.write(s.getBytes("UTF-8"));
        		FOS.close();
	        	
        	
        	
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}