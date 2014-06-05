package view;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class Settings extends JFrame {

	// Local storage
	String[] listGenerationValues;
	
	// UI Elements
	JLabel lblGenerationType = new JLabel("Generation Type: ");
	JComboBox cbGenerationType;
	DefaultComboBoxModel cbGenerationTypeModel;
	
	JButton btnApply;
	
	
	public Settings()
	{
		//Setup the Frame
		this.setTitle("LAT Settings");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(600, 1000));
		
		
		// Setup the layout
		
		this.lblGenerationType.setBounds(new Rectangle(5,10,150,25));
		this.cbGenerationType.setBounds(new Rectangle(155,10,200,25));
		
		this.btnApply = new JButton("Apply");
		
		// Inserting the content
		
		cbGenerationTypeModel = new DefaultComboBoxModel();
        for (String value : this.listGenerationValues) {
        	this.cbGenerationTypeModel.addElement(value);
        }
        this.cbGenerationType = new JComboBox(this.cbGenerationTypeModel);
        this.add(cbGenerationType);
		
		// Attaching the Controllers
		
		// Finalize
		this.setVisible(true);
	}
	
}
