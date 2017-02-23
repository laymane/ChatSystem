package TheFuckingNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import DebugTools.Err;


public class CommunicaTCPServer {
	ServerSocket myServSocket;
	Socket client;
	PrintWriter outWriter;
	BufferedReader inReader;
	
	public CommunicaTCPServer(int port){
		try{
			 myServSocket = new ServerSocket(port);
			 System.out.println("Server waiting for dumbasses");
			 client = myServSocket.accept();
			 
			 outWriter = new PrintWriter(client.getOutputStream(),true);
			 inReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			 System.out.println("Server connected, client connected to server");
			 while(true){
				 //First you insult the client
				 outWriter.println("You fucking dumbass!\n");
				 //Then you listen to him
				 System.out.println("Client said: "+inReader.readLine());
			 }
		} catch(IOException e){
			Err.out("IO Exception");
			e.printStackTrace();
		}
	}
}
