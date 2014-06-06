package main;

import javax.swing.JFrame;

import view.MainFrame;

public class Main
{
	
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //new MainFrame();
            	JFrame frame = layouts.ParseXML.ParseXMLToFrame("template/maininterface.xml");
            	frame.setVisible(true);
            }
		});

	}

}