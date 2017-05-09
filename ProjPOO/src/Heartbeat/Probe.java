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
	
	//Used for junit only
	public User getNextPacket(){
		MulticastSocket mcSocket = null;
		User o = null;
		try {
			mcSocket = new MulticastSocket(Port);
			mcSocket.joinGroup(IpGroup);
			try{Thread.sleep(Variables.TIME_BETWEEN_PROBING);}catch(Exception e){}
			
				byte[] buf = new byte[1000];
				DatagramPacket recv = new DatagramPacket(buf, buf.length);
				mcSocket.receive(recv);
			    ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
			    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
			    o = (User)is.readObject();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mcSocket.close();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	
	public void run(){
		MulticastSocket mcSocket = null;
			try {
				mcSocket = new MulticastSocket(Port);
				mcSocket.joinGroup(IpGroup);
				try{Thread.sleep(Variables.TIME_BETWEEN_PROBING);}catch(Exception e){}
				while(true){
					byte[] buf = new byte[1000];
					DatagramPacket recv = new DatagramPacket(buf, buf.length);
					mcSocket.receive(recv);
				    ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
				    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
				    User o = (User)is.readObject();
				    
				    verifyAndUpdateUserList(o);
				    
				    
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
		boolean added= false;
		//Eviter l'ajout de doublons
		//Pour les users deja existants: timeSinceLastPing = 0;
		//System.out.println("test");
		synchronized(userList){
			if(userList.size()==0){
				userList.add(o);
				m.addElement(o);
				System.err.println("Added to user list by empty list in Probe: "+o.toString());
			}
			else{
	
				for(int i = 0; i<userList.size();i++){
			        if (userList.get(i).getPseudo().equals(o.getPseudo())) {
			        	userList.get(i).setTimeSinceLastPing(0);	
			        	System.err.println("Updated user in Probe "+o.toString());
			        	added = true;
			        	break;
						

			        }		       			        				        
				}
				if (!added){
					userList.add(o);
		        	m.addElement(o);
		        	System.err.println("Added to user list by default in Probe: "+o.toString());
				}
			}		
		}
	}

}
	
	
		

