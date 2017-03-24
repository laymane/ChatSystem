import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import IHM.Communica;
import TheFuckingNetwork.CommunicaTCPClient;
import TheFuckingNetwork.CommunicaTCPServer;


/**
 * @author skrd
 * @category Launcher
 * Basic launcher used to fiddle with stuff during dev
 */
public class clientLauncher {
	public static void main(String[] args){
		int localport = Integer.parseInt(args[0]);
		//Communica c = new Communica("B2A","A2B"); //Instance name to set as arg
		//Communica c2 = new Communica("A2B","B2A");
		
//		CommunicaTCPServer serv = new CommunicaTCPServer(4446);
		try {CommunicaTCPClient cli = new CommunicaTCPClient(4450, InetAddress.getByName("localhost"), localport);
		} catch (UnknownHostException e) {	System.out.println("Unknown Host");
			e.printStackTrace();
		}
	}
}
