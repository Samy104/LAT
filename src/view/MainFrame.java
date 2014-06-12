package view;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginListener;

public class MainFrame extends JFrame {
	private final JLabel lblUtilisateur = new JLabel("ID Utilisateur(courriel/matricule)");
	private final JTextField txtUtilisateur = new JTextField();
	private final JLabel lblMotDePasse = new JLabel("Mot de passe");
	private final JPasswordField txtMotDePasse = new JPasswordField();
	
	private final JButton btnLogin = new JButton("Login");
	
	public MainFrame(){
			
			setup();
			this.lblUtilisateur.setBounds(new Rectangle(0,250,250,20)); 
			this.txtUtilisateur.setBounds(new Rectangle(251,250,150,20));
			this.lblMotDePasse.setBounds(new Rectangle(0,290,220,20));
			this.txtMotDePasse.setBounds(new Rectangle(251,290,220,20));
			this.btnLogin.setBounds(new Rectangle(220,460,110,30));
			
			this.setTitle("LAT Login");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBounds(new Rectangle(600, 600));
			this.add(lblUtilisateur);
			this.add(txtUtilisateur);
			this.add(lblMotDePasse);
			this.add(txtMotDePasse);
			this.add(btnLogin);
			this.getContentPane().add(new JLabel());
			this.setVisible(true);
	}
	private void setup(){
		//btnLogin.addActionListener(new LoginListener(this.txtUtilisateur,this.txtMotDePasse));
	}
}
