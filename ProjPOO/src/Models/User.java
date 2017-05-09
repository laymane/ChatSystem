package Models;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class User implements Serializable {

	private int timeSinceLastPing;
	
	private  String pseudo;	
	private String statut="";
	private  InetAddress IP;
	private  int port;
	public boolean pseudoInit;
	private Socket socket;
	private PrintWriter outWriter;
	private BufferedReader Inreader;
	
	
	public enum typeConnect {
		ONLINE,  
		CONNECTED,
		DISCONNECTED;
	}
	private typeConnect etat; 

	/***
	 * 
	 * @param pseudo Pseudo que les autres utilisateurs verront
	 * @param iP L'adresse IP du user
	 * @param port Le port d'ecoute du user
	 * @param etat CONNECTION ou DECONNECTION
	 */
	public User(String pseudo, InetAddress iP, int port, typeConnect etat) {
		this.pseudoInit = false;
		this.pseudo = pseudo;
		IP = iP;
		this.port = port;
		this.etat=etat;
	}
	
	public User(String pseudo){
		this.pseudo=pseudo;
		try {
			IP = InetAddress.getByName("226.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = 0;
		this.etat = null;
		this.timeSinceLastPing = 0;
	}

	public User(String pseudo2, Socket client) {
		this.pseudo=pseudo2;
		this.socket = client;
		try{
			this.outWriter = new PrintWriter(socket.getOutputStream(),true);
			this.Inreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		}catch(IOException e){
			System.err.println("Error at user socket init process");
			e.printStackTrace();
		}
	}
	public String getPseudo() {
		return this.pseudo;
	}
	public void setPseudo(String ps){
		this.pseudo = ps;
	}
	
	public InetAddress getIP() {
		return IP;
	}

	public int getPort() {
		return port;
	}

	public typeConnect getEtat() {
		return etat;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getTimeSinceLastPing(){
		return this.timeSinceLastPing;
	}
	
	public void setTimeSinceLastPing(int timeSinceLastPing) {
		this.timeSinceLastPing = timeSinceLastPing;
	}
	
	public void setSocket(Socket s){this.socket = s;}
	public Socket getSocket(){return this.socket;}
	
	public void setPrintWriter(PrintWriter pw){this.outWriter = pw;}
	public PrintWriter getPrintWriter(){return this.outWriter;}
	
	public void setOutWriter(BufferedReader bf){this.Inreader = bf;}
	public BufferedReader getBufferedReader(){return this.Inreader;}
	
	
	public void incrementPing(){
		this.timeSinceLastPing++;
	}
	
	@Override
	public String toString(){
//		System.out.println(this.pseudo);
		String r = "";
		r+=" Name : " +this.pseudo;
		r+=" @IP : " +this.IP;
		r+=" Port : " +this.port;
		r+=" Etat : " +this.etat;
		r+=" Last Ping : "+this.timeSinceLastPing;
		return r;
	}
	
	
	public boolean equals(User u){
		if(u.getPort() == this.port){
			if(u.getIP().equals(this.IP)){
				if(u.getPseudo().equals(this.pseudo)){
					return true;
				} 
			} 
		}
		return false;
	}

	
}
