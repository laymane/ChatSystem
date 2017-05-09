package Heartbeat;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Models.User;
import Models.User.typeConnect;
import Models.Variables;

public class Hello extends Thread {
	
	private int Port;
	private InetAddress IpGroup;
	private InetAddress localIPtoSend;
	//private typeConnect etat;
	User objtosend;

	
	
	public Hello(String nickname, String localIP, String IP, int localPort, int remotePort, User.typeConnect etat) throws UnknownHostException{
		Port = remotePort;
		try {
			IpGroup = InetAddress.getByName(IP);
			localIPtoSend = InetAddress.getByName(localIP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objtosend = new User(nickname, localIPtoSend, localPort, etat);


	}
		public void run(){
		
			
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1000);
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(objtosend);
			os.flush();
//			os.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    byte[] sendBuf = byteStream.toByteArray();	
		//String message = name;
		MulticastSocket mcSocket = null;
		try {
			mcSocket = new MulticastSocket(Port);
			mcSocket.joinGroup(IpGroup);
			DatagramPacket hi = new DatagramPacket(sendBuf, sendBuf.length, IpGroup, Port);
			while(true){
				mcSocket.send(hi);
			//	System.out.println("envoi en cours"+hi.getAddress().toString());
				Thread.sleep(Variables.TIME_BETWEEN_HELLOING);
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





	      
	     
