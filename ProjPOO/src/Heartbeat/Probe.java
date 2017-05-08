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
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.DefaultListModel;
import javax.swing.plaf.synth.SynthSeparatorUI;

import Models.User;
import Models.Variables;

public class Probe extends Thread {
	
	private int Port;
	private InetAddress IpGroup;
	private DefaultListModel<User> m = null;

	private CopyOnWriteArrayList<User> userList;
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
	public void setUserList(CopyOnWriteArrayList<User> userList2){
		this.userList=userList2;
		
	}
	
	public void addDefaultListModel(DefaultListModel<User> m){
		this.m = m;
	}
	
	
	public void run(){
		MulticastSocket mcSocket = null;
			try {
				mcSocket = new MulticastSocket(Port);
				mcSocket.joinGroup(IpGroup);
				try{Thread.sleep(Variables.TIME_BETWEEN_PROBING);}catch(Exception e){}
				while(true){
					
					System.out.println("attente de datagramme");
					byte[] buf = new byte[1000];
					DatagramPacket recv = new DatagramPacket(buf, buf.length);
					mcSocket.receive(recv);
				    ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
				    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
				    User o = (User)is.readObject();
				    verifyAndUpdateUserList(o);
				    
				    /* this part is for testing purposes only */
					Random randomGenerator = new Random();
					 
						
						  try {
							Thread.sleep(2000);
							User us = new User("User "+randomGenerator.nextInt(20));
							synchronized(userList){userList.add(us);}
							 m.addElement(us);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				
				    
				     /* End of the testing part */  
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
			

	
	public void verifyAndUpdateUserList(User o){
		//Eviter l'ajout de doublons
		//Pour les users deja existants: timeSinceLastPing = 0;
		//System.out.println("test");
		synchronized(userList){
			if(userList.size()==0){
				userList.add(o);
				m.addElement(o);
			}
			else{
	
				for(int i = 0; i<userList.size();i++){
			        if (userList.get(i).getIP().equals(o.getIP())) {
			        	userList.get(i).setTimeSinceLastPing(0);			        	
			        	System.out.println("doublon -> remis a 0");
			        	break;
			        }
			        else{
			        	System.out.println("ok no doublons");
			        	userList.add(o);
			        	m.addElement(o);
			        }
				}
			}		
		}
	}

}
	
	
		

