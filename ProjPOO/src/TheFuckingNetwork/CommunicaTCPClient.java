package TheFuckingNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

public class CommunicaTCPClient { //Connect to the server
	Socket myClientSocket;
	
	PrintWriter outWriter;
	
	Scanner scanner;
	
	public CommunicaTCPClient(int port, InetAddress ip, int localPort){
		scanner = new Scanner(System.in);
		try{
			myClientSocket = new Socket(ip, port); //Connect to the server
			outWriter = new PrintWriter(myClientSocket.getOutputStream(), true); 
			new serverListenerThread(myClientSocket).start(); 
			System.out.println("Client connected ...");
			while(true){
				System.out.print("You: ");
				outWriter.println(scanner.nextLine());
			}
		}catch(Exception e){System.out.print("Client error : " + e);}	
	}
	
	
	
	
	
	
	
	public class serverListenerThread extends Thread{
		Socket myServListeneer;
		BufferedReader inReader;
		InputStreamReader inp;
		public serverListenerThread(Socket s){
			 try {
				 inp = new InputStreamReader(s.getInputStream());
				inReader = new BufferedReader(inp);
			} catch (IOException e) {
				System.out.println("IO exception on the server listening thread");
				e.printStackTrace();
			}
		}
		
		public void run(){
			
			while(true){
				try {
					String s = inReader.readLine();
					System.out.println("Server "+" said: "+s);	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
