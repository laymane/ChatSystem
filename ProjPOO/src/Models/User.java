package Models;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

public class User implements Serializable {

	
	private  String pseudo;	
	private String statut="";
	private  InetAddress IP;
	private  int port;
	public enum typeConnect {
		  CONNECTED,
		  DECONNECTED;
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
		this.pseudo = pseudo;
		IP = iP;
		this.port = port;
		this.etat=etat;
	}
	
	public String getPseudo() {
		return this.pseudo;
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
	
	

	
}
