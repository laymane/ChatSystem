package TheFuckingNetwork;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicaTCPClient { //Connect to the server
	
	public CommunicaTCPClient(int port, InetAddress ip){
		try{
			Socket myClientSocket = new Socket(ip, port);
		}catch(Exception e){System.out.print("Boop");}	
	}
	public Socket getSocket(){
		return myClientSocket();
	}
}
