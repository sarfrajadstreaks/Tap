package com.autoData.org;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
public class autoDataUI {
	public JFrame mainFrame;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem fileMenu_new;
	private JMenuItem fileMenu_open;
	private JMenuItem fileMenu_save;
	private JMenuItem fileMenu_saveAs;
	private JMenuItem fileMenu_exit;

	private JMenu editMenu;
	private JMenu Setting;
	private JPanel panelNorth;
	public JPanel panelCenter;
	private JPanel panelCenterParent;
	private JPanel panelSouth;
	public JDialog addCompD;
	public JDialog settingCompD;
	private JTabbedPane tabbedPane;
	private JTextField tabName;
	private JTextField dbTable;
	private String filename;
	private String addedComp,addedLabelText,addedDbText,addedXPosition,addedYPosition;
	private ArrayList<String>settingSettings=new ArrayList();
	private ArrayList<String>tabsAdded=new ArrayList();
	private ArrayList<String>tabsBackend=new ArrayList();
	private ArrayList<String>componentAdded=new ArrayList();
	JScrollPane png;
	JFileChooser fc=new JFileChooser();
	readWriteExcel commitexcel=new readWriteExcel();
	public autoDataUI(){

		prepareGUI();
	}
	private void prepareGUI(){
		
		//Create a window frame only
		mainFrame = new JFrame("AutoData");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		//buttons
		JButton addComp=new JButton("<html><b>+<b></html>");
		JButton setting=new JButton("Setting");
		JButton commit=new JButton("Commit");
		//Dialog

		addCompD= new JDialog(mainFrame , "Add Component", true);
		settingCompD= new JDialog(mainFrame , "Settings", true);
		addCompDialogBox();
		settingDialogBox();
		panelNorth= new JPanel();
		//panelCenter= new JPanel();
		tabbedPane=new JTabbedPane(1);
		//tabbedPane.setLayout(new GridBagLayout());
		panelCenterParent= new JPanel();
		panelSouth= new JPanel();
		menuBar=new JMenuBar();

		fileMenu=new JMenu("File");
		//editMenu=new JMenu("Edit");

		//create sub file menus
		fileMenu_new=new JMenuItem("New");
		fileMenu_open=new JMenuItem("Open");
		fileMenu_save=new JMenuItem("Save");
		fileMenu_saveAs=new JMenuItem("Save As..");
		fileMenu_exit=new JMenuItem("Exit");
		//add file sub menus to File main menu
		fileMenu.add(fileMenu_new);
		fileMenu.add(fileMenu_open);
		fileMenu.add(fileMenu_save);
		fileMenu.add(fileMenu_saveAs);
		fileMenu.add(fileMenu_exit);

		//create edit menu main
		//add menus to edit menus

		//add main menus to main
		menuBar.add(fileMenu);
		//menuBar.add(editMenu);
		mainFrame.setJMenuBar(menuBar);
		panelNorth.add(addComp);
		panelNorth.add(commit);
		panelNorth.add(setting);
		//add menu bar to window frame		


		/*panelCenter.setLayout(new GridBagLayout());
		//tabbedPane.setBounds(0,0, 500, 500);
		GridBagConstraints gbc=new GridBagConstraints();*/
		//tabbedPane.add("Tab",panelCenter);
		panelCenterParent.add(tabbedPane);
		png = new JScrollPane(panelCenterParent);

		panelSouth.add(new JButton("South"));

		mainFrame.add(panelNorth,BorderLayout.NORTH);
		mainFrame.add(png,BorderLayout.CENTER);
		mainFrame.add(panelSouth,BorderLayout.SOUTH);

		
		
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		fileMenu_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		fileMenu_new.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				tabbedPane.removeAll();
				settingSettings.clear();
				tabsAdded.clear();
				tabsBackend.clear();
				componentAdded.clear();
			}
		});
		fileMenu_save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//addCompD.setVisible(true);// TODO Auto-generated method stub
				saveFile();
			}

		});
		fileMenu_saveAs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//addCompD.setVisible(true);// TODO Auto-generated method stub
				
					fc.setDialogTitle("Save File As..");
					int us=fc.showSaveDialog(mainFrame);
					if(us==fc.APPROVE_OPTION){
						File fs=fc.getSelectedFile();
						try {
							filename=fs.toString();
							saveFile.write(settingSettings,tabsAdded,tabsBackend,componentAdded,fs.toString());
						} catch (IOException e1){
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
		});
		fileMenu_open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				
				fc.setDialogTitle("Open File..");
				int us=fc.showOpenDialog(mainFrame);
				if(us==fc.APPROVE_OPTION)
				{
					
					tabbedPane.removeAll();
					File fs=fc.getSelectedFile();
					filename=fs.toString();
					try {
						String content=readFile.readit(filename);
						//System.out.println(content);
						String tmp[]=content.split("\n");
						if(tmp[0].equals("839711410211497106")){
							
							for(int i=1;i<tmp.length;i++)
							{
								tmp[i]=tmp[i].replaceAll("\\[", "");
								tmp[i]=tmp[i].replaceAll("\\]", "");
								String section[]=tmp[i].split(",");
								if(i==1){
									settingSettings.clear();
									settingFileType=section[0];
									settingPath=section[1];
									settingFilename=section[2];
									settingSettings.clear();
									settingSettings.add(settingFileType);
									settingSettings.add(settingPath);
									settingSettings.add(settingFilename);
									fileType.setSelectedItem(settingFileType);
									path.setText(settingPath);
									fileName.setText(settingFilename);
								}
								else if(i==2){
									tabsAdded.clear();
									tabbedFirstComp.clear();
									for(int j=0;j<section.length;j++){
										//tabName=new JTextField();
										tabName.setText(section[j]);
										addTab();
									}
									
								}
								else if(i==3){
									componentAdded.clear();
									int step=0;
									
									for(int j=0;j<section.length/5;j++){
										{
											String compDetailSplit[]=section[j+step].split(":");
											addCompDCombo.setSelectedItem(compDetailSplit[1]);
											String tabDetailSplit[]=compDetailSplit[0].split("-");
											tabbedPane.setSelectedIndex(Integer.parseInt(tabDetailSplit[0].replace(" ", "")));
										}
										{
											step++;
											String compDetailSplit[]=section[j+step].split(":");
											LabelText.setText(compDetailSplit[1]);
										}
										{
											step++;
											String compDetailSplit[]=section[j+step].split(":");
											dbLabelText.setText(compDetailSplit[1]);
										}
										{
											step++;
											String compDetailSplit[]=section[j+step].split(":");
											cor_X.setText(compDetailSplit[1]);
										}
										{
											step++;
											String compDetailSplit[]=section[j+step].split(":");
											cor_Y.setText(compDetailSplit[1]);
											addComponentToMainPanel(new newComponentToAdd());
										}
											//step=step+5;
										
									}
									
								}
								else if(i==4){
									tabsBackend.clear();
									for(int j=0;j<section.length;j++){
										tabsBackend.add(section[j]);
									}
								}
								
								
							}
						}
						else{
							JOptionPane.showMessageDialog(mainFrame, "Invalid/Corrupted file");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		});
		//Actions on
		commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				saveFile();
				String content = null;
				try {
					content = readFile.readit(filename);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String tmp[]=content.split("\n");
				tmp[1]=tmp[1].replaceAll("\\[", "");
				tmp[1]=tmp[1].replaceAll("\\]", "");
				String filedetail[]=tmp[1].split(",");
				String ext=filedetail[0].trim();
				String pathF=filedetail[1].trim();
				String nmFile=filedetail[2].trim();
				String absolutefile=pathF+nmFile+"."+ext;
				System.out.println("File:"+absolutefile.trim());
				
				tmp[4]=tmp[4].replaceAll("\\[", "");
				tmp[4]=tmp[4].replaceAll("\\]", "");
				String sheets[]=tmp[4].trim().split(",");
				System.out.println("Sheets:"+sheets[0].trim()+","+sheets[1].trim());
				
				tmp[3]=tmp[3].replaceAll("\\[", "");
				tmp[3]=tmp[3].replaceAll("\\]", "");
				String colname[]=tmp[3].split(",");
				int steps=0;
				ArrayList<String>elements=new ArrayList();
				ArrayList<String>elementDbRef=new ArrayList();
				//ArrayList<String>elementTabRef=new ArrayList();
				for(int i=0;i<colname.length;i=i+5){
					
					String ttemp[]=colname[i].trim().split(":");
					if(ttemp[1].contains("Label") && i%5==0)
					{
						
					}
					else if((i+2)<colname.length){
						String tabreff[]=ttemp[0].trim().split("-");
						if (Integer.parseInt(tabreff[0].trim())==tabbedPane.getSelectedIndex()){
							//tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
							//elements.add(tabreff[0].trim()+"~"+ttemp[1].trim());
							String ttempp[]=colname[i+2].split(":");
							String tabreff1[]=ttempp[0].split("-");
							elementDbRef.add(ttempp[1].trim());
						}
					}
					
				}
				ArrayList<String>appComp=new ArrayList();
				ArrayList<String>appCompValue=new ArrayList();
				
				Component c[]=tabbedFirstComp.get(tabbedPane.getSelectedIndex()).getComponents();
				for(int i=0;i<c.length;i++){
					JPanel xc=(JPanel) c[i];
					Component ccc[]=xc.getComponents();
					for(int j=0;j<ccc.length;j++){
						if(ccc[j] instanceof JLabel){
							
						}
						else{
							 if(ccc[j] instanceof JTextField){
								 //appComp.add(tabbedPane.getSelectedIndex()+"~TextField");
								 JTextField val=(JTextField) ccc[j];
								 appCompValue.add(val.getText());
								
							 }
							 else  if(ccc[j] instanceof JCheckBox){
								 //appComp.add(tabbedPane.getSelectedIndex()+"~CheckBox");
								 JCheckBox val=(JCheckBox) ccc[j];
								if(val.isSelected()){
									appCompValue.add("Check");
								}
								else{
									appCompValue.add("UnCheck");
								}
								 
							 }
							 else if(ccc[j] instanceof JRadioButton){
								 //appComp.add(tabbedPane.getSelectedIndex()+"~RadioButton");
								 JRadioButton val=(JRadioButton) ccc[j];
								 if(val.isSelected()){
									   appCompValue.add("ON");
									}
								 else
									 appCompValue.add("OFF");
							 }
						}
						
					}
				}
				//System.out.println(appComp);
				System.out.println(appCompValue);
				//System.out.println(elements);
				System.out.println(elementDbRef);
				try {
					commitexcel.readWriteXLSX(absolutefile.trim(), sheets[tabbedPane.getSelectedIndex()].trim(), elementDbRef, appCompValue);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//new readWriteExcel().readWriteXLSX(absolutefile.trim(), sheetToUpdate, header, value);
			}
		});
		addComp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				addCompD.setVisible(true);
			}

		});
		setting.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {

				settingCompD.setVisible(true);// TODO Auto-generated method stub

			}

		});
		

		mainFrame.setSize((int)width,(int)height-25);


		mainFrame.setVisible(true);  
	}
	JComboBox addCompDCombo;
	JTextField LabelText;
	JTextField dbLabelText;
	JTextField cor_X;
	JTextField cor_Y;
	JButton addCompDialogBtnOK;
	JButton addCompDialogBtnCancel;
	private void addCompDialogBox(){

		addCompD.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx=0;
		gbc.gridy=0;
		//gbc.gridwidth=2;
		addCompD.add(new JLabel("All fields are mandatory"),gbc);

		String addCompDialogComponents[]={"TextField","CheckBox","RadioButton","Label","New Tab"};
		//addCompDCombo.addItem(addCompDialogComponents);
		addCompDCombo=new JComboBox(addCompDialogComponents);
		addCompDCombo.setPreferredSize(new Dimension(200, 20));
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(new JLabel("Component"),gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(addCompDCombo,gbc);

		gbc.gridx=0;
		gbc.gridy=3;
		gbc.anchor=GridBagConstraints.WEST;
		JLabel LabelTextL=new JLabel("Label Text");
		addCompD.add(LabelTextL,gbc);

		LabelText= new JTextField(20);
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(LabelText,gbc);

		gbc.gridx=0;
		gbc.gridy=4;
		gbc.anchor=GridBagConstraints.WEST;
		JLabel dbLabelTextL=new JLabel("DataBase Field Text");
		addCompD.add(dbLabelTextL,gbc);

		dbLabelText= new JTextField(20);
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(dbLabelText,gbc);

		gbc.gridx=0;
		gbc.gridy=5;
		gbc.anchor=GridBagConstraints.WEST;
		JLabel cor_XL=new JLabel("X-Position");
		addCompD.add(cor_XL,gbc);

		cor_X=new JTextField(02);
		gbc.gridx=1;
		gbc.gridy=5;
		gbc.anchor=GridBagConstraints.WEST;	
		addCompD.add(cor_X,gbc);

		gbc.gridx=0;
		gbc.gridy=6;
		gbc.anchor=GridBagConstraints.WEST;
		JLabel cor_YL=new JLabel("Y-Position");
		addCompD.add(cor_YL,gbc);

		cor_Y=new JTextField(02);	
		gbc.gridx=1;
		gbc.gridy=6;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(cor_Y,gbc);

		gbc.gridx=0;
		gbc.gridy=7;
		gbc.anchor=GridBagConstraints.WEST;
		JLabel tabNameL=new JLabel("Tab Name");
		addCompD.add(tabNameL,gbc);
		tabNameL.setVisible(false);

		tabName=new JTextField(20);	
		gbc.gridx=1;
		gbc.gridy=7;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(tabName,gbc);
		tabName.setVisible(false);

		gbc.gridx=0;
		gbc.gridy=8;
		gbc.anchor=GridBagConstraints.WEST;
		JLabel dbTableL=new JLabel("Data Table:");
		addCompD.add(dbTableL,gbc);
		dbTableL.setVisible(false);

		dbTable=new JTextField(20);	
		gbc.gridx=1;
		gbc.gridy=8;
		gbc.anchor=GridBagConstraints.WEST;
		addCompD.add(dbTable,gbc);
		dbTable.setVisible(false);
		if(String.valueOf(addCompDCombo.getSelectedItem()).contains("Tab")){

			tabNameL.setVisible(true);
			tabName.setVisible(true);

			dbTableL.setVisible(true);
			dbTable.setVisible(true);


			LabelTextL.setVisible(false);
			LabelText.setVisible(false);
			dbLabelTextL.setVisible(false);
			dbLabelText.setVisible(false);
			cor_XL.setVisible(false);
			cor_X.setVisible(false);
			cor_YL.setVisible(false);
			cor_Y.setVisible(false);
		}

		addCompDCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tabName.setText("");
				dbTable.setText("");
				LabelText.setText("");
				dbLabelText.setText("");
				cor_X.setText("");
				cor_Y.setText("");
				if(String.valueOf(addCompDCombo.getSelectedItem()).contains("Tab")){

					tabNameL.setVisible(true);
					tabName.setVisible(true);

					dbTableL.setVisible(true);
					dbTable.setVisible(true);


					LabelTextL.setVisible(false);
					LabelText.setVisible(false);
					dbLabelTextL.setVisible(false);
					dbLabelText.setVisible(false);
					cor_XL.setVisible(false);
					cor_X.setVisible(false);
					cor_YL.setVisible(false);
					cor_Y.setVisible(false);
				}
				else if(String.valueOf(addCompDCombo.getSelectedItem()).contains("Label")){
					tabNameL.setVisible(false);
					tabName.setVisible(false);
					dbTableL.setVisible(false);
					dbTable.setVisible(false);

					LabelTextL.setVisible(true);
					LabelText.setVisible(true);
					dbLabelTextL.setVisible(false);
					dbLabelText.setVisible(false);
					cor_XL.setVisible(true);
					cor_X.setVisible(true);
					cor_YL.setVisible(true);
					cor_Y.setVisible(true);
				}
				else{
					tabNameL.setVisible(false);
					tabName.setVisible(false);
					dbTableL.setVisible(false);
					dbTable.setVisible(false);

					LabelTextL.setVisible(true);
					LabelText.setVisible(true);
					dbLabelTextL.setVisible(true);
					dbLabelText.setVisible(true);
					cor_XL.setVisible(true);
					cor_X.setVisible(true);
					cor_YL.setVisible(true);
					cor_Y.setVisible(true);
				}
			}
		});



		addCompDialogBtnOK=new JButton("OK");
		addCompDialogBtnCancel=new JButton("Cancel");

		gbc.gridx=0;
		gbc.gridy=9;
		gbc.anchor=GridBagConstraints.CENTER;
		addCompD.add(addCompDialogBtnOK,gbc);
		gbc.gridx=1;
		gbc.gridy=9;

		gbc.anchor=GridBagConstraints.CENTER;
		addCompD.add(addCompDialogBtnCancel,gbc);
		addCompD.setSize(400, 300);
		//addCompD.setResizable(false);

		addCompD.setLocationRelativeTo(null);


		addCompDialogBtnOK.addActionListener(new ActionListener()
		{


			public void actionPerformed(ActionEvent e) {
				//System.out.println(tabName.getText());
				String tb=tabName.getText();
				if(!tb.equals("")){
					addTab();
				}
				else{
					if(tabbedPane.getTabCount()!=0){
						addComponentToMainPanel(new newComponentToAdd());
					}
					else{
						JOptionPane.showMessageDialog(addCompD, "No Tab are added!");
					}

				}


				mainFrame.validate();

			}

		});
		addCompDialogBtnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {

				addCompD.dispose();

			}

		});

	}
	Object Source = null;
	String radiobuttonGroup = null;
	ArrayList<ButtonGroup> btngrp=new ArrayList();
	private void addComponentToMainPanel(newComponentToAdd newPanel)
	{

		GridBagConstraints gbc=new GridBagConstraints();
		addedComp=(String) addCompDCombo.getSelectedItem();
		addedLabelText=LabelText.getText();
		addedDbText=dbLabelText.getText();
		
		addedXPosition=cor_X.getText();
		addedYPosition=cor_Y.getText();
		gbc.gridx=Integer.parseInt(addedXPosition);
		gbc.gridy=Integer.parseInt(addedYPosition);
		gbc.anchor=GridBagConstraints.WEST;

		JPopupMenu popupmenu = new JPopupMenu("Edit");   
		JMenuItem remove = new JMenuItem("Remove");  
		//JMenuItem edit = new JMenuItem("Edit");  

		popupmenu.add(remove);// popupmenu.add(edit); 

		newPanel.addMouseListener(new MouseAdapter() {  
			public void mouseClicked(MouseEvent e){
				popupmenu.show(newPanel , e.getX(), e.getY());
				Source= e.getSource();
			}                 
		});

		remove.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) { 
				
				Container comp=((JPanel)Source).getParent();
				Component[] cc=comp.getComponents();
				int index = 0;
				for(int i=0;i<cc.length;i++){
					if (cc[i]==((JPanel)Source)){
						index=i;
						break;
						//System.out.println("Got it-"+i);
					}
				}
			int i=0;
			while(i!=componentAdded.size()){	
				for(i=0;i<componentAdded.size();i++){
					String temp[]=componentAdded.get(i).split(":");
					if(temp[0].equals(tabbedPane.getSelectedIndex()+"-"+index)){
						componentAdded.remove(i);
						break;
					}
				}
				
				tabbedFirstComp.get(tabbedPane.getSelectedIndex()).remove((JPanel)Source);
				mainFrame.revalidate();
				}  
			}
		});

		/*edit.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
				addCompD.setVisible(true);
			}  
		});*/
		
		newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		if(	addedComp.equals("TextField")){
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(new JTextField(20));
			radiobuttonGroup=null;
			btngrp.clear();//.remove(0);
		}
		
		else if(addedComp.equals("CheckBox")){
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(new JCheckBox());
			radiobuttonGroup=null;
			btngrp.clear();
		}
		
		else if(addedComp.equals("RadioButton"))
		{
			JRadioButton rbtn=new JRadioButton("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>");
			btngrp.add(new ButtonGroup());
			
			if(radiobuttonGroup!=null && radiobuttonGroup.equals(addedDbText)){
				btngrp.get(0).add(rbtn);
			}
			else{
				//btngrp.get(0)=new ButtonGroup();
				btngrp.get(0).add(rbtn);
			}
			//newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(rbtn);
			radiobuttonGroup=addedDbText;
		}
		else if(addedComp.equals("Label"))
		{
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			addedDbText="nullValue";
			radiobuttonGroup=null;
			btngrp.clear();
			//newPanel.add(new JTextArea());
		}
		gbc.gridx=Integer.parseInt(addedXPosition);
		gbc.gridy=Integer.parseInt(addedYPosition);
		gbc.anchor=GridBagConstraints.WEST;

		tabbedFirstComp.get(tabbedPane.getSelectedIndex()).add(newPanel,gbc);
		Component cc[]=tabbedFirstComp.get(tabbedPane.getSelectedIndex()).getComponents();
		int i;
		for(i=0;i<cc.length;i++)
		{
			if(cc[i]==newPanel){
				break;
			}
		}
				
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedComp);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedLabelText);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedDbText);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedXPosition);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedYPosition);
		addCompD.dispose();
		mainFrame.validate();	
	}

	private String settingFileType,settingPath,settingFilename;
	
	JComboBox fileType;
	JTextField path=new JTextField();
	JTextField fileName=new JTextField();
	private void settingDialogBox()
	{
		
		path.setToolTipText("C:/SampleFolder/");
		
		fileName.setToolTipText("Enter File Name Without Extension");
		JButton saveBtn=new JButton("Save");
		JButton cancelBtn=new JButton("Cancel");

		settingCompD.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		String addCompDialogComponents[]={"xlsx","xls"};   
		fileType=new JComboBox(addCompDialogComponents);

		fileType.setPreferredSize(new Dimension(145, 20));
		gbc.gridx=0;
		gbc.gridy=0;
		//gbc.weighty = 0.01;
		gbc.anchor=GridBagConstraints.WEST;
		settingCompD.add(new JLabel("DataBase Type   "),gbc);

		gbc.gridx=1;
		gbc.gridy=0;

		gbc.anchor=GridBagConstraints.WEST;
		settingCompD.add(fileType,gbc);


		gbc.gridx=0;
		gbc.gridy=1;
		gbc.weighty = 0;
		gbc.anchor=GridBagConstraints.WEST;
		settingCompD.add(new JLabel("Path "),gbc);

		gbc.gridx=1;
		gbc.gridy=1;
		gbc.anchor=GridBagConstraints.WEST;
		path.setPreferredSize(new Dimension(145, 20));

		settingCompD.add(path,gbc);

		gbc.gridx=0;
		gbc.gridy=2;
		gbc.anchor=GridBagConstraints.WEST;
		settingCompD.add(new JLabel("File Name"),gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		gbc.anchor=GridBagConstraints.WEST;
		fileName.setPreferredSize(new Dimension(145, 20));
		settingCompD.add(fileName,gbc);

		gbc.gridx=0;
		gbc.gridy=3;
		gbc.anchor=GridBagConstraints.WEST;
		settingCompD.add(new JLabel(" "),gbc);

		gbc.gridx=0;
		gbc.gridy=4;
		gbc.anchor=GridBagConstraints.CENTER;
		settingCompD.add(saveBtn,gbc);

		gbc.gridx=1;
		gbc.gridy=4;
		gbc.anchor=GridBagConstraints.CENTER;
		settingCompD.add(cancelBtn,gbc);

		saveBtn.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) { 
				
				settingFileType=(String)fileType.getSelectedItem();
				settingPath=path.getText();
				settingFilename=fileName.getText();
				settingSettings.clear();
				settingSettings.add(settingFileType);
				settingSettings.add(settingPath);
				settingSettings.add(settingFilename);
				settingCompD.dispose();
				mainFrame.validate();
			}  
		});
		
		cancelBtn.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
				settingCompD.dispose();
				mainFrame.validate();
			}  
		});

		settingCompD.setSize(300, 170);
		settingCompD.setLocationRelativeTo(null);


	}
	
	
	ArrayList<JPanel> tabbedFirstComp=new ArrayList<JPanel>();
	int tabInd;
	
	private void addTab(){
		panelCenter= new JPanel();
		JPopupMenu popupmenu = new JPopupMenu("Edit"); 
		JMenuItem remove = new JMenuItem("Remove");
		popupmenu.add(remove);
		GridBagConstraints gbc=new GridBagConstraints();

		tabbedFirstComp.add(panelCenter);

		panelCenter.setLayout(new GridBagLayout());
		tabbedPane.setBounds(50,50,200,200);
		tabbedPane.add(tabName.getText(),panelCenter);
		
		tabbedPane.validate();
		tabsAdded.add(tabName.getText());
		tabsBackend.add(dbTable.getText());
		//System.out.println("On adding "+tabsAdded);
		JPanel pnlTab = new JPanel();
		pnlTab.setLayout(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel llb=new JLabel("<html><p>"+tabName.getText()+"&nbsp;</p></html>");
		//llb.setMargin(new Insets(-5,0,-2,0));
		//ArrayList<JButton> tabDeleteBtn=new ArrayList<JButton>();
		JButton btnClose = new JButton("<html><p style='color:red'>x<sub>"+(tabbedPane.getTabCount())+"</sub></p></html>");
		//tabDeleteBtn.add(btnClose);

		btnClose.setToolTipText("Remove");
		btnClose.setMargin(new Insets(0,0,0,0));
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorder(null);
		gbc.gridx=0;
		gbc.gridy=0;
		pnlTab.add(llb,gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		pnlTab.add(btnClose,gbc);

		tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1,pnlTab);

		btnClose.addMouseListener(new MouseAdapter() {  
			public void mouseClicked(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON3){
					popupmenu.show(btnClose , e.getX(), e.getY());
					tabInd=Integer.parseInt(btnClose.getText().replaceAll(".*?(\\d+).*","$1"));
				}
			}                 
		});
		remove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				int i=0;
				while(i!=componentAdded.size()){	
					for(i=0;i<componentAdded.size();i++){
						String temp[]=componentAdded.get(i).split("-");
						if(Integer.parseInt(temp[0])==tabInd-1){
							componentAdded.remove(i);
							break;
						}
					}
				}  
				
				tabbedPane.remove(tabInd-1);
				tabsAdded.remove(tabInd-1);
				tabsBackend.remove(tabInd-1);
				//System.out.println("After remove "+tabsAdded);
			}

		});
		addCompD.dispose();
		mainFrame.validate();


	}

	private void openFile(newComponentToAdd newPanel){
		//setup setting
		
		//setup main panel
		
		GridBagConstraints gbc=new GridBagConstraints();
		addedComp=(String) addCompDCombo.getSelectedItem();
		addedLabelText=LabelText.getText();
		addedDbText=dbLabelText.getText();
		
		addedXPosition=cor_X.getText();
		addedYPosition=cor_Y.getText();
		gbc.gridx=Integer.parseInt(addedXPosition);
		gbc.gridy=Integer.parseInt(addedYPosition);
		gbc.anchor=GridBagConstraints.WEST;

		JPopupMenu popupmenu = new JPopupMenu("Edit");   
		JMenuItem remove = new JMenuItem("Remove");  
		//JMenuItem edit = new JMenuItem("Edit");  

		popupmenu.add(remove);// popupmenu.add(edit); 

		newPanel.addMouseListener(new MouseAdapter() {  
			public void mouseClicked(MouseEvent e){
				popupmenu.show(newPanel , e.getX(), e.getY());
				Source= e.getSource();
			}                 
		});

		remove.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) { 
				
				Container comp=((JPanel)Source).getParent();
				Component[] cc=comp.getComponents();
				int index = 0;
				for(int i=0;i<cc.length;i++){
					if (cc[i]==((JPanel)Source)){
						index=i;
						break;
						//System.out.println("Got it-"+i);
					}
				}
			int i=0;
			while(i!=componentAdded.size()){	
				for(i=0;i<componentAdded.size();i++){
					String temp[]=componentAdded.get(i).split(":");
					if(temp[0].equals(tabbedPane.getSelectedIndex()+"-"+index)){
						componentAdded.remove(i);
						break;
					}
				}
				
				tabbedFirstComp.get(tabbedPane.getSelectedIndex()).remove((JPanel)Source);
				mainFrame.revalidate();
				}  
			}
		});

		newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		if(	addedComp.equals("TextField")){
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(new JTextField(20));
		}
		else if(addedComp.equals("TextArea"))
		{
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(new JTextArea());
		}
		else if(addedComp.equals("CheckBox")){
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(new JCheckBox());

		}
		else if(addedComp.equals("RadioButton"))
		{
			newPanel.add(new JLabel("<html><p>"+addedLabelText+"<sub>"+addedXPosition+","+addedYPosition+"</sub></p></html>"));
			newPanel.add(new JRadioButton());
		}
		gbc.gridx=Integer.parseInt(addedXPosition);
		gbc.gridy=Integer.parseInt(addedYPosition);
		gbc.anchor=GridBagConstraints.WEST;

		tabbedFirstComp.get(tabbedPane.getSelectedIndex()).add(newPanel,gbc);
		Component cc[]=tabbedFirstComp.get(tabbedPane.getSelectedIndex()).getComponents();
		int i;
		for(i=0;i<cc.length;i++)
		{
			if(cc[i]==newPanel){
				break;
			}
		}
				
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedComp);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedLabelText);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedDbText);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedXPosition);
		componentAdded.add(tabbedPane.getSelectedIndex()+"-"+i+":"+addedYPosition);
	}
	private void saveFile(){
		if(filename==null){
			fc.setDialogTitle("Save File..");
			int us=fc.showSaveDialog(mainFrame);
			if(us==fc.APPROVE_OPTION){
				File fs=fc.getSelectedFile();
				try {
					filename=fs.toString();
					saveFile.write(settingSettings,tabsAdded,tabsBackend,componentAdded,fs.toString());
				} catch (IOException e1){
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else{
			try {
				saveFile.write(settingSettings,tabsAdded,tabsBackend,componentAdded,filename);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
