package TheFuckingNetwork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CommunicaTCPClient { //Connect to the server
	Socket myClientSocket;
	PrintWriter outWriter;
	BufferedReader inReader;
	Scanner scanner;
	
	public CommunicaTCPClient(int port, InetAddress ip){
		scanner = new Scanner(System.in);
		try{
			myClientSocket = new Socket(ip, port);
			outWriter = new PrintWriter(myClientSocket.getOutputStream(), true);
			inReader = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
			System.out.println("Client connected ...");
			while(true){
				//Then you insult back
				System.out.print("You: ");
				outWriter.println(scanner.nextLine());
			}
		}catch(Exception e){System.out.print("Client error : " + e);}	
	}
	
}
