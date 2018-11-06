package com.routing;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UDPTranceiver {
	private int port;
	private DatagramSocket serverSocket;
	private byte[] sendData;
	private InetAddress IPAddress;
	private File file;
	private ArrayList<String> packetStrings;
	
	public UDPTranceiver(int port) throws FileNotFoundException {
		this.port = port;	
		file = new File("c:\\UDPsignalsLong.txt");
		packetStrings = new ArrayList<String>();
	}
	
	public void send() throws UnknownHostException, SocketException, IOException   {
		prepareList();
        buildConnection();
        for (String s : packetStrings) {
        	try {
    			sendData = s.getBytes("UTF-8");
    			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, port);
    			serverSocket.send(sendPacket);
    			System.out.println(s);
    			TimeUnit.MILLISECONDS.sleep(200);
    			 
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }	
		closeConnection();
        System.out.println("Connection Closed");
	}
	
	public void buildConnection() throws SocketException, UnknownHostException {
		serverSocket = new DatagramSocket(port);
		IPAddress = InetAddress.getByName("169.172.2.0");	
		System.out.println("Connection established. Beginning to send the payload.");
	}
	
	public void closeConnection() {
		serverSocket.close();
	}
	
	public void prepareList() throws FileNotFoundException, IOException {
		String s = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    while ((s = br.readLine()) != null) {
		       packetStrings.add(s);
		       System.out.println("Added");
		    }
		}
	}
}