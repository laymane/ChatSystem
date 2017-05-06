package Heartbeat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Probe extends Thread{
	
	private int Port = 8080;
	private InetAddress IpGroup;

	
	
	public Probe(){
		try {
			IpGroup = InetAddress.getByName("239.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run(){
		
		MulticastSocket mcSocket = null;
			try {
				mcSocket = new MulticastSocket(Port);
				mcSocket.joinGroup(IpGroup);
				while(true){
					System.out.println("attente de datagramme");
					byte[] buf = new byte[1000];
					DatagramPacket recv = new DatagramPacket(buf, buf.length);
					mcSocket.receive(recv);
					System.out.println(buf);
					
					
					
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mcSocket.close();
				}
			
		
	}
		
}
