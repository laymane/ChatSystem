package TheFuckingNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;

import DebugTools.Err;
import IHM.ChatFrame;
import Models.User;


/**
 * The listening thread for incoming chat connexions
 * @author Skeard
 *
 */
public class CommunicaTCPServer extends Thread {
	
	ServerSocket myServSocket;
	ArrayList<Conversation> conversations;
	ArrayList<Socket> clientList;
	int nbConnected = 0;
	private int port;
	
	protected volatile boolean running;
	public ChatFrame chatFrame;
	 
	
	public CommunicaTCPServer(int port, ChatFrame chatFrame) {
		this.chatFrame=chatFrame;
		this.port=port;
		conversations = new ArrayList<Conversation>();
		try{
			 myServSocket = new ServerSocket(port);	 
		} catch(IOException e){
			System.err.println("Exception while opening server socket on port "+port+" in COMMUNICA");
			e.printStackTrace();
		}
	}
	
	public void run(){
		running = true;
		Socket client = null;
		while(running){			 
			 System.out.println("Waiting for incoming connexions on port "+port+".");			 
			try {
				client = myServSocket.accept();
			
			} catch (IOException e) {
				System.err.println("Exception while accepting serversocket connexion in COMMUNICA");
				running = false;
				e.printStackTrace();
			}
			 nbConnected++;
			 
			 User u = new User("Bob"+nbConnected, client);
			 Conversation cv = new Conversation(u);	 
			 conversations.add(cv);
			  chatFrame.startConversation(cv);
			 System.out.println("Client connected.");
			 }
	}
	
	
	//Used to push system messages.
//	public class serverWriter extends Thread{
//	
//		
//		Scanner s;
//		public serverWriter(){
//			s = new Scanner(System.in);
//		}
//		
//		public void run(){
//			while(true){
//				System.out.println("You: ");
//				String str = s.nextLine();
//				for(PrintWriter pw : outWriterList){
//					pw.println(str);
//				}
//				System.out.println("Message sent.");
//			}
//		}
//	
//	}
	
	
}



