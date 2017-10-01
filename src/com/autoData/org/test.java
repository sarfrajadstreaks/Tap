package com.autoData.org;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class test {
	

	   private JFrame mainFrame;
	   private JMenuBar menuBar;
	   private JPanel panelNorth;
	   private JPanel panelSouth;
	   private JPanel panelEast;
	   private JPanel panelWest;
	   private JPanel panelCenter;
	   private JPanel panelCenterParent;
	   public test(){
	      prepareGUI();
	   }
	   public static void main(String[] args){
		   test swingControlDemo = new test();  
	          
	   }
	   private void prepareGUI(){
		   Button buttons[];
		  
	      mainFrame = new JFrame("Java SWING Examples");
	      panelNorth= new JPanel();
	      panelSouth= new JPanel();
	      panelEast= new JPanel();
	      panelWest= new JPanel();
	      panelCenter= new JPanel();
	      panelCenterParent= new JPanel();
	      
	      panelNorth.add(new JButton("North"));
	      JRadioButton btn=new JRadioButton("Male");
	      JRadioButton btn1=new JRadioButton("Female");
	      btn1.setSelected(true);
	      ButtonGroup grp=new ButtonGroup();
	      grp.add(btn);grp.add(btn1);
	      
	      panelNorth.add(btn);
	      panelNorth.add(btn1);
	      System.out.println(btn1.isSelected());
	      panelCenter.setLayout(new GridBagLayout());
	      GridBagConstraints gbc=new GridBagConstraints();
	      for(int i=1;i<50;i++){
	    	  gbc.gridx=0;
		      gbc.gridy=-1+i;
		      panelCenter.add(new JButton("center"+i),gbc);
	      }
	      for(int i=1;i<50;i++){
	    	  gbc.gridx=0+i;
		      gbc.gridy=0;
		      panelCenter.add(new JButton("center"+i),gbc);
	      }
	      panelCenterParent.add(panelCenter);
	      JScrollPane png = new JScrollPane(panelCenterParent);
	          		  
	      panelSouth.add(new JButton("South"));
	      
	      mainFrame.add(panelNorth,BorderLayout.NORTH);
	      mainFrame.add(png,BorderLayout.CENTER);
	      mainFrame.add(panelSouth,BorderLayout.SOUTH);
	      
	      menuBar=new JMenuBar();
	      menuBar.add(new JMenu("File"));
		  menuBar.add(new JMenu("Edit"));
			
		//mainFrame.add(btn, gbc);
	      mainFrame.setSize(800,700);
	     //mainFrame.setLayout(new BorderLayout ());
	     mainFrame.setJMenuBar(menuBar);
	     
	     /*buttons = new Button [5];  
	     
	     for (int i = 0;i<3;i++) {  
	        buttons[i] = new Button ("Button " + (i + 1));  
	        mainFrame.add (buttons[i]);  
	      }  */
	     
	       mainFrame.setVisible(true);  
	   }
	  
	}
