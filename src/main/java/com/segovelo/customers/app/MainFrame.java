package com.segovelo.customers.app;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton  btnLoadFile;
	JButton  btnSentFile;
	JButton  btnGetCustomer;
	JTextField txtFileName;
	JTextField txtPostResponse;
	JTextField txtCustomerRef;
	JTextArea txtGetCustomer;
    JFileChooser fileChooser;
	 String filePath = null;
	 String jsonString = null;
	public MainFrame(){		
       super("Customers App");
	   this.setLayout(new FlowLayout(FlowLayout.CENTER));
	   fileChooser = new  JFileChooser(System.getProperty("user.dir") + "\\src\\main\\resources");
	   Container contentPane = this.getContentPane();
	   
	   JPanel saveBtnPane = new JPanel();
	   saveBtnPane.setLayout(new BoxLayout(saveBtnPane, BoxLayout.LINE_AXIS));
	   saveBtnPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 80));
	   saveBtnPane.add(Box.createHorizontalGlue());
	   btnLoadFile = new JButton("Load File"); 	    
	   btnSentFile = new JButton("Send File"); 
	   saveBtnPane.add(btnLoadFile);	   
	   saveBtnPane.add(Box.createRigidArea(new Dimension(250, 0)));
	   saveBtnPane.add(btnSentFile);
	   
	   JLabel labelFileName = new JLabel("File Name");
	   labelFileName.setPreferredSize(new Dimension(250,40));
	   JLabel labelPostResponse = new JLabel("Post Response");
	   labelPostResponse.setPreferredSize(new Dimension(400,40));
	   JLabel labelCustmrRef = new JLabel("Customer Ref");
	   labelCustmrRef.setPreferredSize(new Dimension(250,40));
	   JLabel labelGetResponse = new JLabel("Retrieve Response");
	   labelGetResponse.setPreferredSize(new Dimension(400,40));

	   txtFileName = new JTextField("");
	   txtFileName.setPreferredSize(new Dimension(250,40));
	   txtPostResponse = new JTextField("");
	   txtPostResponse.setPreferredSize(new Dimension(400,40));
	   	    
	   txtCustomerRef = new JTextField("");
	   txtCustomerRef.setPreferredSize(new Dimension(250,40));	   
	   txtGetCustomer = new  JTextArea("");
	   txtGetCustomer.setPreferredSize(new Dimension(400,150));
	   txtGetCustomer.setLineWrap(Boolean.TRUE);
	   txtGetCustomer.setWrapStyleWord(Boolean.TRUE);
	   
	   
	   btnGetCustomer = new JButton("Get Customer");
	   	   
	   
	   contentPane.add(labelFileName);
	   contentPane.add(labelPostResponse);
	   contentPane.add(txtFileName);
	   contentPane.add(txtFileName);
	   contentPane.add(txtPostResponse);
	   contentPane.add(saveBtnPane);
	   
	   JPanel getCustmrPane = new JPanel();
	   getCustmrPane.setLayout(new BoxLayout(getCustmrPane, BoxLayout.PAGE_AXIS));
	   getCustmrPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
	   getCustmrPane.add(Box.createVerticalGlue());
	   getCustmrPane.add(txtCustomerRef);
	   getCustmrPane.add(Box.createRigidArea(new Dimension(0, 20)));
	   getCustmrPane.add(btnGetCustomer);
	   contentPane.add(labelCustmrRef);
	   contentPane.add(labelGetResponse);
	   contentPane.add(getCustmrPane);
	   contentPane.add(txtGetCustomer);
	   
	   myEventHandler eventHandler = new myEventHandler();
	   btnLoadFile.addActionListener(eventHandler);
	   btnSentFile.addActionListener(eventHandler);
	   btnGetCustomer.addActionListener(eventHandler);
	   
	   this.setSize(700, 500);
	   this.setVisible(true);
	   this.setResizable(Boolean.FALSE);
	
	}
	
	public class myEventHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
		   if(e.getSource()== btnLoadFile) {
	         int returnVal = fileChooser.showOpenDialog(null);
             if(returnVal == JFileChooser.APPROVE_OPTION) {
            	 txtFileName.setText(fileChooser.getSelectedFile().getName());
            	 filePath = fileChooser.getSelectedFile().getAbsolutePath();
             }	     
		   }
		     else if(e.getSource()==btnSentFile && StringUtils.isNotBlank(filePath)) {
		    	  jsonString = JsonUtility.fileToJson(filePath);
		    	  System.out.println("jsonString : " + jsonString);
		    	  //List<Customer> customers = JsonUtility.jsonToCustomers(jsonString);
		    	  SaveCustomersResponse response = HttpClientUtility.sendPostRequest("http://localhost:8080/customers/save",jsonString);
		    	  txtPostResponse.setText(response.getStatus() + " - " + response.getMessage());
		     }
		   else if(e.getSource() == btnGetCustomer && StringUtils.isNotBlank(txtCustomerRef.getText())) {
			   String customerRef = txtCustomerRef.getText().strip();
			   System.out.println("\n\n\t customer Ref: " + customerRef);
			   RetrieveCustomerResponse response = HttpClientUtility.retrieveCustomer("http://localhost:8080/customers/retrieve", customerRef);
			   txtGetCustomer.setText(response.getStatus() + " - " + response.getMessage());
		   }
		}
	} 
}
