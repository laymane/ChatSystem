package IHM;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Communica extends JFrame{
	private BufferedWriter writer;
	private BufferedReader reader;
	private JButton bReceive, bSend;
	private JLabel lmessrec, lmesssend;
	private JTextArea textRec,textToSend;
	private JPanel left, right;
	private String instanceName,buddyName;
	
	 private Calendar cal;
     private SimpleDateFormat  sdf;
	public Communica(String instanceName, String buddyName){
		this.instanceName = instanceName;
		this.buddyName = buddyName;
		cal = Calendar.getInstance();
		sdf = new SimpleDateFormat("HH:mm:ss");
		doTheThing();
	}
	
	public void doTheThing(){
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(instanceName), "utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(buddyName),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bReceive = new JButton("Receive");
		bReceive.setName("receive");
		bReceive.addActionListener(new myCommunicaListener());
		bSend = new JButton("Send");
		bSend.setName("send");
		bSend.addActionListener(new myCommunicaListener());
		
		lmessrec = new JLabel("Received Message: ");
		lmesssend = new JLabel("Message to send: ");
		textRec = new JTextArea(3,16);
		textRec.setToolTipText("Ceci est la zone de texte a recevoir");
		textToSend = new JTextArea(3,16);
		textToSend.setText("CEci est la zone de texte a envoyer");
		left = new JPanel();
		right = new JPanel();
		
		left.add("North",lmesssend);
		left.add("Center",bSend);
		left.add("South",lmessrec);
		
		right.add("North",textToSend);
		right.add("Center",bReceive);
		right.add("South",textRec);
		left.setVisible(true);
		right.setVisible(true);
		this.add("North",left);
		this.add("South",right);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if(instanceName.equals("A2B")){
	     setBounds(0,0,screenSize.width/2, screenSize.height/2);
		}
		else{
			setBounds(screenSize.width/2,0,screenSize.width/2,screenSize.height/2);
		}
		this.setVisible(true);
	}
	
	class myCommunicaListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(((JButton)arg0.getSource()).getName().equals("send")){
				String s = textToSend.getText();
				 try{
					 writer.write(s);
					 writer.flush();
				 } catch (Exception e){
					 System.out.println("Something broke");
				 }
			}else{
				try{
					textRec.setText(textRec.getText()+"\n"+sdf.format(cal.getTime())+": " +reader.readLine()+"\n");
					
				}
				catch(Exception e){
					System.out.println("Something broke while reading");
				}
			}
			
		}		
	}
	 public static void infoBox(String infoMessage, String titleBar)
	    {
	        JOptionPane.showMessageDialog(null, infoMessage, "The box says: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }

}
