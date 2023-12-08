package com.segovelo.customers.app;

import javax.swing.JFrame;
/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/

public class CustomersController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Schedule for the event-dispatching thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
			}
		});
	}

}
