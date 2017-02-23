package TheFuckingNetwork;

import java.net.ServerSocket;
import java.net.Socket;


public class CommunicaTCPServer {
	public CommunicaTCPServer(int port){
		try{
			ServerSocket myServSocket = new ServerSocket(port);
			while(true){
				new Threader(myServSocket.accept()).start();
			}
		}catch(Exception e){
			System.out.println("Something broke"+e.toString());
		}
	}
	
	 class Threader extends Thread{
		private Socket clientSocket;
		public Threader(Socket s){
			
		}
	}
}
