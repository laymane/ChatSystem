package TheFuckingNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import DebugTools.Err;


public class CommunicaTCPServer {
	ServerSocket myServSocket;
	
	ArrayList<Socket> clientList;
	ArrayList<PrintWriter> outWriterList;
	ArrayList<BufferedReader> inReaderList;
	ArrayList<Socket> clientReceiverList;
	 
	
	public CommunicaTCPServer(int port) {
		
		 clientList = new ArrayList<Socket>();
		 clientReceiverList = new ArrayList<Socket>();
		 outWriterList = new ArrayList<PrintWriter>();
		 inReaderList= new ArrayList<BufferedReader>();
		 

		try{
			 myServSocket = new ServerSocket(port);
			 
			 while(true){			 
			 System.out.println("Server waiting for dumbasses, port"+port);
						 
			 Socket client = myServSocket.accept();
			 clientList.add(client);		 
			 
			 outWriterList.add(new PrintWriter(clientList.get(clientList.size()-1).getOutputStream(),true));
			 BufferedReader bf;
			 Socket sk = clientList.get(clientList.size()-1);
			 InputStreamReader in = new InputStreamReader(sk.getInputStream());
			 bf = new BufferedReader(in);
			 inReaderList.add(bf);
			 
			 clientReceiverList.add(new Socket("localhost", 4451));
			 
			 System.out.println("Server connected, client connected to server");
			
			 new clientThread(outWriterList.get(outWriterList.size()-1), inReaderList.get(inReaderList.size()-1), "patate"+inReaderList.size()).start();		 
			 
			 System.out.println("Dumbass connected, resuming listening duty");
			 }
			 
		} catch(IOException e){
			
			try {
				myServSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Err.out("IO Exception");
			e.printStackTrace();
		}
	}
	public class listeningThread extends Thread{
	
		
	}
	
	public class clientThread extends Thread {
		
		BufferedReader bf;
		PrintWriter in;
		String clientName;
		
		public clientThread(PrintWriter in, BufferedReader bf, String str){
			super (str);
			this.bf = bf;
			this.in = in;	
			this.clientName = str;
			
		}
		
		
	


		public void run(){
			
			while(true){
				try {
					String s = bf.readLine();
					System.out.println(clientName+" said: "+s);
					for(Iterator<Socket> ic = clientList.iterator(); ic.hasNext();){
						in.println(clientName+" said: "+s);
					}
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
}



