import Heartbeat.Hello;
import Heartbeat.Probe;

public class Hellolauncher {

	   public static void main(String[] args){
		
		Hello h = new Hello("etvfik"); 
		h.start();
		System.out.println("j'ai lancé ton truc");
		
		Probe h2 = new Probe(); 
		h2.start();
		System.out.println("j'ai lancé ton deuxième truc");
		
	}
	
	
}
