package com.autoData.org;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;  
public class TabbedPaneExample {  
JFrame f;  
TabbedPaneExample(){  
    f=new JFrame();  
    JButton save=new JButton("Save");
    f.add(save);
    
    JFileChooser fc=new JFileChooser();
    
    
    save.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			
		}
		
	});
    
    
   /* JTextArea ta=new JTextArea(200,200);  
    JPanel p1=new JPanel();  
    p1.add(ta);  
    JPanel p2=new JPanel();  
    JPanel p3=new JPanel();  
    JTabbedPane tp=new JTabbedPane(2);
    
    JPanel pnlTab = new JPanel();
    pnlTab.setLayout(new GridBagLayout());
    pnlTab.setOpaque(false);
    JLabel llb=new JLabel("<html><p>main&nbsp;</p></html>");
    //llb.setMargin(new Insets(-5,0,-2,0));
    JButton btnClose = new JButton("<html><p  style='color:red'>x</p></html>");
    btnClose.setMargin(new Insets(0,0,0,0));
    btnClose.setOpaque(false);
    btnClose.setContentAreaFilled(false);
    btnClose.setBorder(null);
 
    JPopupMenu popupmenu = new JPopupMenu("Edit"); 
    JMenuItem remove = new JMenuItem("Remove");
    popupmenu.add(remove);
   //tp.insertTab("",null, pnlTab, null, 0);
    
    GridBagConstraints gbc=new GridBagConstraints();
    gbc.gridx=0;
    gbc.gridy=0;
    pnlTab.add(llb,gbc);
    gbc.gridx=1;
    gbc.gridy=0;
    pnlTab.add(btnClose,gbc);
    tp.setBounds(50,50,200,200);  
    tp.add("Main",p1);  
    tp.add("visit",p2);  
    tp.add("help",p3);    
    f.add(tp);  
    tp.setTabComponentAt(0,pnlTab);*/
    f.setSize(400,400);  
    f.setLayout(new GridLayout(1,1));  
    f.setVisible(true);  
    f.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){
			System.exit(0);
		}        
	});  
   /* btnClose.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			//System.out.println(e.);
			popupmenu.show(btnClose , btnClose.getX(), btnClose.getY());
			
		}
		
	});*/
}  
public static void main(String[] args) {  
    new TabbedPaneExample();  
}}  