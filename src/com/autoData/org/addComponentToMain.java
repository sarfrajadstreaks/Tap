package com.autoData.org;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class addComponentToMain extends autoDataUI implements MouseListener{
	private String addedComp,addedLabelText,addedDbText,addedXPosition,addedYPosition;
	public addComponentToMain() {
		mainFrame.addMouseListener(this); 
		
		GridBagConstraints gbc=new GridBagConstraints();
		addedComp=(String) addCompDCombo.getSelectedItem();
		addedLabelText=LabelText.getText();
		addedDbText=dbLabelText.getText();
		
		addedXPosition=cor_X.getText();
		addedYPosition=cor_Y.getText();
		gbc.gridx=Integer.parseInt(addedXPosition);
		gbc.gridy=Integer.parseInt(addedYPosition);
		gbc.anchor=GridBagConstraints.WEST;
		JPanel temp=new JPanel();
		//addMouseListener(temp);
		temp.setLayout(new FlowLayout(FlowLayout.LEFT));
			if(	addedComp.equals("TextField")){
				temp.add(new JLabel(addedLabelText));
				temp.add(new JTextField(20));
			}
			else if(addedComp.equals("TextArea"))
			{
				temp.add(new JLabel(addedLabelText));
				temp.add(new JTextArea());
			}
			else if(addedComp.equals("CheckBox")){
				temp.add(new JLabel(addedLabelText));
				temp.add(new JCheckBox());
				
			}
			else if(addedComp.equals("RadioButton"))
			{
				temp.add(new JLabel(addedLabelText));
				temp.add(new JRadioButton());
			}
			gbc.gridx=Integer.parseInt(addedXPosition);
			gbc.gridy=Integer.parseInt(addedYPosition);
			gbc.anchor=GridBagConstraints.WEST;
			//panelCenter.add(temp,gbc);
			addCompD.dispose();
			mainFrame.validate();	
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Clicked on");
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
