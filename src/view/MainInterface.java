package view;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainInterface extends JFrame{

	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuAbout, menuHelp;
	JMenuItem menuTest;
	
	JLabel txtCurrentLottery = new JLabel("Current Lottery: ");
	
	public MainInterface()
	{
		//Setup the Frame
		this.setTitle("Welcome to LAT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(1920, 1080));
		
		// Setup the menus
		menuBar = new JMenuBar();
		
		menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menuFile);
		/*
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		
		menuAbout = new JMenu("About");
		menuBar.add(menuAbout);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);*/
		
		this.add(menuBar);
		
		// Setup the layout
		
		// Inserting the content
		
		// Attaching the Controllers
		
		// Finalize
		this.setVisible(true);
	}
}
