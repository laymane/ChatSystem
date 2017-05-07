package Heartbeat;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.plaf.synth.SynthSeparatorUI;

import Models.User;


public class Probe extends Thread {
	
	private int Port;
	private InetAddress IpGroup;
	private DefaultListModel<User> m = null;

	private ArrayList<User> userList;
	User o;
	
	
	public Probe(String multiIP, int remotePort){
		Port = remotePort;
		try {
			IpGroup = InetAddress.getByName(multiIP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Probe(String multiIP, int remotePort, DefaultListModel<User> m){
		this(multiIP, remotePort);
		this.m=m;
		
	}
	public void setUserList(ArrayList<User> userList){
		this.userList=userList;
		
	}
	
	public void addDefaultListModel(DefaultListModel<User> m){
		this.m = m;
	}
	public void run(){
		
		MulticastSocket mcSocket = null;
			try {
				mcSocket = new MulticastSocket(Port);
				mcSocket.joinGroup(IpGroup);
				while(true){
					try{Thread.sleep(5000);}catch(Exception e){}
					System.out.println("attente de datagramme");
					byte[] buf = new byte[1000];
					DatagramPacket recv = new DatagramPacket(buf, buf.length);
					mcSocket.receive(recv);
				    ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
				    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
					//System.out.println("JE DISPLAY LE");
				    User o = (User)is.readObject();
				    /*System.out.println("voici le port"+o.getPort());
				    System.out.println("voici le ip"+o.getIP());
				    System.out.println("voici le pseudo"+o.getPseudo());
				    */			    

				    verifyAndUpdate(o);
				    int i = 0;
					Random randomGenerator = new Random();
					 
						
						  try {
							Thread.sleep(2000);
							  m.addElement(new User("User "+randomGenerator.nextInt(20)));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				
				    
				       
				    }				
					
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mcSocket.close();
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
			
		
	
	public void verifyAndUpdate(User o){
		//Eviter l'ajout de doublons
		//Pour les users deja existants: timeSinceLastPing = 0;
		//System.out.println("test");
		if(userList.size()==0){
			userList.add(o);
			m.addElement(o);
		}
		else{

		for(int i = 0; i<userList.size();i++){
			//System.out.println("coucou j'ai mis à 0");
	        if (userList.get(i).getIP().equals(o.getIP())) {
	        	userList.get(i).setTimeSinceLastPing(0);
	        	
	        	System.out.println("doublon -> remis à0");
	        }
	        else{
	        	System.out.println("ok no doublons");
	        }
	    }
		}
	}
}
	
	
		

