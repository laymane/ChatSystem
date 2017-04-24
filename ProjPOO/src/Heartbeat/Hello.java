package Heartbeat;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class Hello {

	
		
	public class Hello_Thread extends Thread {
		
		
		public void run(){
			
	    MulticastSocket mcSocket = null;
	    DatagramPacket message;
		int mcPort = 8080;
		InetAddress IpGroup = InetAddress.getByName("194.255.255.255");
        byte[] msg = "Hello".getBytes();
		while(true){

	        DatagramPacket packet = new DatagramPacket(msg, msg.length);
	        packet.setAddress(IpGroup);
	        packet.setPort(mcPort);
	        try {
				mcSocket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        mcSocket.close();
	    
		}
	
	}
	
}
}
