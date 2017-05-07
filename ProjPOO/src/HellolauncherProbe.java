import java.net.UnknownHostException;

import Heartbeat.*;
import Models.User.typeConnect;

public class HellolauncherProbe {


	public static void main(String[] args) throws InterruptedException, UnknownHostException{
		
		   HBController hbc = new HBController("Jean-Edouard", "192.145.25.54", "226.255.255.255", 6456, 7876, typeConnect.CONNECTED );
		   
	}
	
	
}
