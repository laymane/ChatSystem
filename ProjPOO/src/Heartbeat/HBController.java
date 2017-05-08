package Heartbeat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.DefaultListModel;

import Models.User;
import Models.User.typeConnect;
import Models.Variables;

public class HBController extends Thread {
	
	
	CopyOnWriteArrayList<User> userList;
	DefaultListModel<User> m;
	Hello helloer;
	Probe prober;
	
	public HBController(String localUserName, String localIP, String multiIP, int localPort, int remotePort, typeConnect currentetat) throws UnknownHostException {
		helloer = new Hello(localUserName, localIP, multiIP, localPort, remotePort, currentetat);		
		prober = new Probe(multiIP, remotePort);	
		userList =  new CopyOnWriteArrayList<User>();
		prober.setUserList(userList);
		prober.start();
		helloer.start();
		new TicToc().start();
		

	}
	
	public void addModelToProbe (DefaultListModel<User>m){
		this.m =m;
		this.prober.addDefaultListModel(m);
	}
	
	
	//Increment the time since the last ping of every user in the list.
	//This will affect the model as well
	public void incrementTimeSinceLastPingOfUsers(){
		for(User u: userList){
			u.incrementPing();
		}
	}	
	

	public void purgeOldUsers(){
		synchronized(userList){
			for(User u: userList){
				if (u.getTimeSinceLastPing() > Variables.TIMEOUT_VALUE){
					userList.remove(u);
					for(int i=0; i<m.getSize();i++){
						if(u.equals(m.elementAt(i))){
						m.removeElementAt(i);
							break; //This hocus pocus maneuver is necessary to remove user from both the userList and the model.
						}
					}
				}
			}
		
		}
	}
	
	private void displayUserList(){
		synchronized(userList){
			System.out.println("Displaying userlist: \n");
			for(int i = 0; i<userList.size();i++){
				System.out.println((i+1)+ " " + userList.get(i)+"\n");
			}		
		}
		
	}
	
	
	private class TicToc extends Thread{
		protected volatile boolean running;
		public void run(){
			running = true;
			while(running){
				try {Thread.sleep(Variables.TIME_BETWEEN_PURGES);} catch (InterruptedException e) {}
				System.out.println("TicToc does its thing");
				incrementTimeSinceLastPingOfUsers();
				purgeOldUsers();
				displayUserList();
			}
			
		}
	}
}
