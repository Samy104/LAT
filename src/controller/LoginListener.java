package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.MainInterface;
import dao.User;

public class LoginListener implements ActionListener {
	
	private JTextField nomUtilisateur = null;
	private JPasswordField motDePasse = null;

	public LoginListener(JTextField txtUtilisateur, JPasswordField txtMotDePasse) {
		this.nomUtilisateur = txtUtilisateur;
		this.motDePasse = txtMotDePasse;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(nomUtilisateur.getText().length() != 0 && this.motDePasse != null){
			User.Login(this.nomUtilisateur.getText(), retrievePassword(this.motDePasse.getPassword()));
			layouts.ParseXML.ParseXMLToFrame("template/maininterface.xml");
		}
	}
	
	public String retrievePassword(char[] pw){
		String temp = "";
		
		for(int i = 0; i < pw.length; i++){
			temp += pw[i];
		}
		return temp;
	}

}
