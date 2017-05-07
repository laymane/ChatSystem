package TheFuckingNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import Models.User;

/**
 * @author Skeard
 * Represents an instance of chat between 2 or more people.
 */
public class Conversation {

		private String pendingMessages=""; //To modify into serialized messages array
		private ArrayList<User> usersInConversation;
		private ArrayList<clientThread> clientThreads;
		
		
		public Conversation(){
			usersInConversation = new ArrayList<User>();
			clientThreads = new ArrayList<clientThread>();
			System.out.println("Arrays inited.");
		}
		public Conversation(User u){
			this();
			addUserToConversation(u);
		}
		
		public void addUserToConversation(User user){
			usersInConversation.add(user);
			//For each new distant user, start a thread listening to his messages
			clientThread ct = new clientThread(user);
			ct.start();
			clientThreads.add(ct);
			
		}
		
		public void removeUserFromConversation(User e){
			usersInConversation.remove(e);
		}
		
		public void removeUserFromConversation(String s){
			for(User u: usersInConversation){
				if (u.getPseudo().equals(s))
					usersInConversation.remove(u);
			}
		}
		
		//Used by the interface whenever the local user send a message.
		//Send it through the socket of each distant user
		
		/**
		 * Send a local message to each User in the convo
		 * @param s
		 * @TODO Use serialized messages
		 */
		public void sendMessage(String s){
			for(User u: usersInConversation){
				u.getPrintWriter().println(s);
			}
		}
		
		/** Receive a message from the outerwebs. Used by clientThreads
		 * @param s: the message.
		 * @TODO: Serialize message
		 */
		public void receiveMessage(String s){
			this.pendingMessages+=s;
		}
		
		
		/**
		 * Used by the interface to display the chat messages.
		 * @Todo: use message format.
		 * @return the messages received from the outerwebs but not yet pushed to the interface.
		 */
		public String getPendingMessages(){
			String t =  this.pendingMessages;
			this.pendingMessages = "";
			return t;
		}
		
		
		/**
		 * Thread created for each distant user in the convo.
		 * Waits for a message from the distant user, push it to the pending messages and resume listening
		 */
		public class clientThread extends Thread{
			protected volatile boolean running;
			BufferedReader bf;
			String userName;
			User u;
			
			public clientThread(User u){
				bf = u.getBufferedReader();
				this.u=u;
				userName = u.getPseudo();
			}
			
			
			/**
			 * Mostly useless in release mode. Was used in alpha tests.
			 * @return A string of every users in the convo
			 */
			public String getUsernamesInConversation(){
				String s="";
				for(User u: usersInConversation){
					s+=u.getPseudo()+" ";
				}
				return s;
			}
			
		
			
			public void run(){
				running = true;
				while(running){
					try {
						String s =(bf.readLine());
						receiveMessage(s);
						System.out.println(userName+ ": "+s); //Debug
					} catch (IOException e) {
						running = false;
						System.err.println("Error in conversation while reading distant user messages, remote user disconnected");
						try{u.getSocket().close();}catch(Exception ex){}
						usersInConversation.remove(u);			
						
					}
				}
			}
		}
		
		
}
