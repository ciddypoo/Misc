package com.routing;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws SocketException, UnknownHostException, FileNotFoundException {
		int port = args.length == 0 ? 57 : Integer.parseInt(args[0]);
        UDPInterface udp = new UDPInterface(60000);
        udp.receive();			
		//devide by 3600
	}

}
