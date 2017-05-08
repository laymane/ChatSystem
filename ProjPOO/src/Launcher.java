import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import IHM.ChatFrame;
import IHM.Communica;
import IHM.LogInFrame;
import IHM.UserlistIHM;
import Models.LocalUser;
import TheFuckingNetwork.CommunicaTCPClient;
import TheFuckingNetwork.CommunicaTCPServer;


/**
 * @author skrd
 * @category Launcher
 * Basic launcher used to fiddle with stuff during dev
 */
public class Launcher {
	public static void main(String[] args){
		
		//Communica c = new Communica("B2A","A2B"); //Instance name to set as arg
		//Communica c2 = new Communica("A2B","B2A");
//		UserlistIHM ihm= new UserlistIHM();
//		ihm.launch();
		
		
		//CommunicaTCPServer serv = new CommunicaTCPServer(4450);
		//serv.start();
//		try {CommunicaTCPClient cli = new CommunicaTCPClient(4446, InetAddress.getByName("localhost"));
//		} catch (UnknownHostException e) {	System.out.println("Unknown Host");
//			e.printStackTrace();
//		}
	
	
	
	
	/*	Main entry point	*/
	
		//At first, the chat simply displays a login window prompting the user for his username
		
		LocalUser localUser = new LocalUser();
	
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
	                new LogInFrame(localUser).setVisible(true);
	              
	            }
	        });
	
	
	
	
	
	
	
	}
}
