package Heartbeat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import Models.User;


public class Probe extends Thread{
	
	private int Port = 8080;
	private InetAddress IpGroup;

	private ArrayList<User> userList;
	
	
	public Probe(){
		try {
			IpGroup = InetAddress.getByName("239.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setUserList(ArrayList<User> userList){
		this.userList=userList;
		
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
					//Todo:
					//Todo: Virer les doublons
					String user = new String(buf);
					
					userList.add(new User(user));
					//displayUserList();
					
					
					
					
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mcSocket.close();
				}
			
		
	}
	public void trimDoubles(){
		//Eviter l'ajout de doublons
		//Pour les users deja existants: timeSinceLastPing = 0;
	}
	
	
		
}
