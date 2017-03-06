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
public class Launcher {
	public static void main(String[] args){
		
		//Communica c = new Communica("B2A","A2B"); //Instance name to set as arg
		//Communica c2 = new Communica("A2B","B2A");
		
		CommunicaTCPServer serv = new CommunicaTCPServer(4451);
//		try {CommunicaTCPClient cli = new CommunicaTCPClient(4446, InetAddress.getByName("localhost"));
//		} catch (UnknownHostException e) {	System.out.println("Unknown Host");
//			e.printStackTrace();
//		}
	}
}
