package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.MainInterface;
import dao.User;

public class LoginListener implements ActionListener, KeyListener {
	
	private JTextField nomUtilisateur = null;
	private JPasswordField motDePasse = null;
	private JFrame frame = null;

	public LoginListener(JTextField txtUtilisateur, JPasswordField txtMotDePasse, JFrame frame) {
		this.nomUtilisateur = txtUtilisateur;
		this.motDePasse = txtMotDePasse;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(nomUtilisateur.getText().length() != 0 && this.motDePasse != null){
			logMeIn();
		}
	}
	
	public String retrievePassword(char[] pw){
		String temp = "";
		
		for(int i = 0; i < pw.length; i++){
			temp += pw[i];
		}
		return temp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			 	logMeIn();
	        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void logMeIn()
	{
		if(User.Login(this.nomUtilisateur.getText(), retrievePassword(this.motDePasse.getPassword())))
		{
			frame.dispose();
			layouts.ParseXML.ParseXMLToFrame("template/maininterface.xml");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veuillez remplir les champs correctement");
		}
	}

}
