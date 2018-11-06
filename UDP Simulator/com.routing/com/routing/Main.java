package com.routing;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
        UDPTranceiver udp = new UDPTranceiver(60000);
        udp.send();
	}
}
