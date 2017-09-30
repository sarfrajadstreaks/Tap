package com.autoData.org;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class initialize {
	private JFrame mainFrame;
	private JLabel statusLabel;

	public initialize(){
		
		double version =Double.parseDouble(Runtime.class.getPackage().getSpecificationVersion());
		if(version<1.7){
			prepareGUI();
		}
		else{

			autoDataUI swingControlDemo = new autoDataUI();  
			
		}
	}
	private void prepareGUI(){
		mainFrame = new JFrame("AutoData Message");
		mainFrame.setSize(400,120);
		mainFrame.setLayout(new GridLayout(3, 1));
		statusLabel = new JLabel("",JLabel.CENTER);        
		statusLabel.setSize(350,100);
		statusLabel.setText("1.7 or above Java Required.");
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);  
	}

}
