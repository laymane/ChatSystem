package Heartbeat;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Hello extends Thread {
	
	private String name = "Unknown";
	private int Port = 8080;
	private InetAddress IpGroup;
	
	public Hello(String nickname){
		name = nickname;
		try {
			IpGroup = InetAddress.getByName("239.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		public void run(){
			
		String message = name;
		MulticastSocket mcSocket = null;
		//blabla
		//dosike
		try {
			mcSocket = new MulticastSocket(Port);
			mcSocket.joinGroup(IpGroup);
			DatagramPacket hi = new DatagramPacket(message.getBytes(), message.length(), IpGroup, Port);
			while(true){
				mcSocket.send(hi);
				System.out.println("envoi en cours"+hi.getAddress().toString());
				Thread.sleep(10000);
			}
			//

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mcSocket.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mcSocket.close();
		}

	}	
			
}





	      
	     
