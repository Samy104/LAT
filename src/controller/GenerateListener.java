package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import platform.Chain;

public class GenerateListener implements ActionListener {

	JComboBox cb = null;
	JLabel lblResult = null;
	JTextField numWanted = null;
	JTextField maxRng = null;
	
	
	public GenerateListener(JComboBox cb, JLabel res, JTextField numWanted, JTextField maxRng)
	{
		this.cb = cb;
		this.lblResult = res;
		this.numWanted = numWanted;
		this.maxRng = maxRng;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(numWanted == null || maxRng == null || numWanted.getText().isEmpty() || maxRng.getText().isEmpty())
		{
			lblResult.setText("Make sure the fields are filled with valid values");
			this.lblResult.setForeground(Color.RED);
		}
		else if(!numWanted.getText().matches("[0-9]+") || !maxRng.getText().matches("[0-9]+"))
		{
			lblResult.setText("Make sur the fields contains only digits");
			this.lblResult.setForeground(Color.RED);
		}
		else if(Integer.parseInt(numWanted.getText()) > Integer.parseInt(maxRng.getText()))
		{
			lblResult.setText("Make sure the requested numbers is smaller than the range");
			this.lblResult.setForeground(Color.RED);
		}
		else
		{
			switch(this.cb.getSelectedItem().toString().toLowerCase())
			{
				case "random":
					Chain randChain = new Chain(Integer.parseInt(numWanted.getText()), Integer.parseInt(maxRng.getText()));
					randChain.GenerateChain("random");
					this.lblResult.setText(randChain.getListString());
					this.lblResult.setForeground(Color.BLACK);
					break;
				default:
					break;
			}
		}
		
	}

}
