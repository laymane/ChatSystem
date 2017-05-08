package Models;

import java.net.InetAddress;

public class LocalUser {
	private String username;
	private int probePort;
	private String localIP;
	/*Todo: Fill all stuff here*/
	
	public void setUserName(String s){this.username=s;}
	public String getUserName(){return this.username;}
	
	public void setLocalIP(String s){this.localIP =s;}
	public String getLocalIP(){return this.localIP;}
}
