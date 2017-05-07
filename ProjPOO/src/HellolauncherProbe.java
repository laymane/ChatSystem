import java.net.UnknownHostException;

import Heartbeat.*;
import IHM.UserlistIHM;
import Models.User.typeConnect;

public class HellolauncherProbe {


	public static void main(String[] args) throws InterruptedException, UnknownHostException{
		
		   HBController hbc = new HBController("Jean-Edouard", "localhost", "226.255.255.255", 6456, 7876, typeConnect.CONNECTED );
		   UserlistIHM ihm= new UserlistIHM();
		   ihm.launch();
		   hbc.addModelToProbe(ihm.getListModel());
		   
	}
	
	
}
