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

import Models.User;

public class CommunicaTCPClient { //Connect to the server
	Socket myClientSocket;
	
	PrintWriter outWriter;
	User distantUser;
	Conversation conversation;
//	Scanner scanner;
	
	public Conversation getTcpClientConversation(){
		return this.conversation;
	}
	
	public CommunicaTCPClient(int port, InetAddress ip, int localPort, String pseudo,CommunicaTCPServer tcpServ){
//		scanner = new Scanner(System.in);
		try{
			myClientSocket = new Socket(ip, port); //Connect to the server
			distantUser = new User(pseudo, myClientSocket);
			
			outWriter = distantUser.getPrintWriter(); 
			conversation = new Conversation(distantUser);
			tcpServ.addConversation(conversation);
			new serverListenerThread(myClientSocket).start(); 
			System.out.println("Client connected ...");
			
			outWriter.println(pseudo);
//			while(manual){
//				System.out.print("You: ");
//				outWriter.println(scanner.nextLine());
//			}
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
