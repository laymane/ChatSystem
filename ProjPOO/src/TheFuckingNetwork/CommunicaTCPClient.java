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
			System.out.println("Client will now attempt to connect to server");
			myClientSocket = new Socket(ip, port); //Connect to the server
			outWriter = new PrintWriter(myClientSocket.getOutputStream(), true); 
			System.out.println("Client will now wait for the server blabber to connect ...");
			new serverListenerThread(localPort).start(); //Wait for a connection from the server
			System.out.println("Client connected ...");
			while(true){
				//Then you insult back
				System.out.print("You: ");
				outWriter.println(scanner.nextLine());
			}
		}catch(Exception e){System.out.print("Client error : " + e);}	
	}
	public class serverListenerThread extends Thread{
		ServerSocket myClientReadSocket;
		Socket serverBlabber;
		BufferedReader inReader;
		PrintWriter outWriter;
		public serverListenerThread(int port){
			try{
				myClientReadSocket = new ServerSocket(port);
				serverBlabber = myClientReadSocket.accept();
				 inReader = new BufferedReader(new InputStreamReader(serverBlabber.getInputStream()));
				 outWriter = new PrintWriter(serverBlabber.getOutputStream(),true);
				 System.out.println("Ready to read shit from server\n");
			} catch (Exception e){
				System.out.println("The client listener is going awol and said: "+e);
			}
			
		}
		
		public void run(){
			
			while(true){
				try {
					String s = inReader.readLine();
					System.out.println("Server "+" said: "+s);	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
