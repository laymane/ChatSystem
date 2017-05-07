package Heartbeat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Models.User;
import Models.User.typeConnect;

public class HBController {
	ArrayList<User> userList;
	Hello helloer;
	Probe prober;
	


	
	
	
	public HBController(String localUserName, String localIP, String multiIP, int localPort, int remotePort, typeConnect currentetat) throws UnknownHostException {
		helloer = new Hello(localUserName, localIP, multiIP, localPort, remotePort, currentetat);
		
		
		prober = new Probe(multiIP, remotePort);
		
		
		userList = new ArrayList<User>();
		prober.setUserList(userList);
		
		
		prober.start();
		helloer.start();
		while(true){
			displayUserList();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			incrementUserList();
		}
	}
	
	
	private void runHello(){
		this.helloer.start();
		System.out.println("Hello is running\n");
	}
	
	private void runProbe(){
		this.prober.start();	
		System.out.println("Probe is running\n");
	}
	
	private void displayUserList(){
		System.out.println("Displaying userlist: \n");
		for(int i = 0; i<userList.size();i++){
			System.out.println((i+1)+ " " + userList.get(i)+"\n");
		}

		
	}
	private void incrementUserList(){
		for(int i = 0; i<userList.size();i++){
			userList.get(i).incrementPing();
			if(userList.get(i).getTimeSinceLastPing() >= 10){
				userList.remove(i);
				i--;
			}
		}
		
	}
}
