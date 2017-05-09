package test;
import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;

import Heartbeat.Hello;
import Heartbeat.Probe;
import Models.User;
import Models.Variables;
import Models.User.typeConnect;


public class TestHeartbeat {

		//Hw ehrfu = new Hw();
		//User us = new User("Jean-Bapt");
	Hello helloer = null;
	Probe prober = null;

	public TestHeartbeat() {
		// TODO Auto-generated constructor stub
		
		

	}
	
	public void startHelloerandProbe(){
		try {
			helloer = new Hello("Jean-Bapt", "193.245.135.124", Variables.MULTI_IP, Variables.LOCAL_PORT, Variables.REMOTE_PORT, typeConnect.CONNECTED);
			//helloer.start();
			prober = new Probe(Variables.MULTI_IP, Variables.REMOTE_PORT);
				
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helloer.start();
	}
	
	
	
	@Test
	public void test() {
		startHelloerandProbe();
		User u = prober.getNextPacket();
		System.out.println("coucou");
		assertEquals("Jean-Bapt", u.getPseudo());
		//fail("Not yet implemented");
	}

}
