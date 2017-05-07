package IHM;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import Models.User;

public class UserlistIHM{

	 public DefaultListModel<User> model;
	 public DefaultListModel<User> getListModel(){
		  return this.model;
	  }
	public class ListModelExample extends JPanel {

		  JList list;
		 
		  int counter = 15;
		 
		
		 
		  public ListModelExample() {
		    setLayout(new BorderLayout());
		    model = new DefaultListModel();
		    list = new JList(model);
		    JScrollPane pane = new JScrollPane(list);
		    JButton addButton = new JButton("Add Element");
		    JButton removeButton = new JButton("Remove Element");
//		    for (int i = 0; i < 15; i++)
//		      model.addElement(new User("Bob "+i));

		    addButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        model.addElement(new User("Element " + counter));
		        counter++;
		      }
		    });
		    removeButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        if (model.getSize() > 0)
		          model.removeElementAt(0);
		      }
		    });

		    add(pane);
		   // add(addButton, BorderLayout.WEST);
		    //add(removeButton, BorderLayout.EAST);
		    new bogusTester(model).start();
		    System.out.println("Bogus tester running");
		  }

		
		}
	  public  void launch() {
		    JFrame frame = new JFrame("Bogus user list");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setContentPane(new ListModelExample());
		    frame.setSize(260, 200);
		    frame.setVisible(true);
		  }
	  
	  class bogusTester extends Thread{
		  DefaultListModel<User> m;
		  bogusTester(DefaultListModel<User> m){
			  this.m=m;
		  }
		  public void run(){
			  int i = 0;
			  Random randomGenerator = new Random();
			  while(true){
				 // m.addElement(new User("User "+randomGenerator.nextInt(20)));
				  try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		  }
	  }

}
