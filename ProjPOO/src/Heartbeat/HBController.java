package Heartbeat;

import java.util.ArrayList;

import Models.User;

public class HBController {
	ArrayList<User> userList;
	Hello helloer;
	Probe prober;
	
	String localUser;
	
	public HBController(String localUserName) throws InterruptedException{
		localUser = localUserName;
		helloer = new Hello(localUserName);
		prober = new Probe();
		
		
		userList = new ArrayList<User>();
		prober.setUserList(userList);
		
		
		prober.start();
		helloer.start();
		while(true){
			displayUserList();
			Thread.sleep(10000);
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
			System.out.println((i+1)+ " " + userList.get(i).toString()+"\n");
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
