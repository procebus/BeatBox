package IHM_test;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import beatbox01.MiniMiniMusicApp;

public class simpleIHM1B implements ActionListener {

	JButton bouton;
	JRadioButton radiobouton;
	Graphics grap1;
	
	MiniMiniMusicApp bip;
	
	
	public static void main(String[] args) {
		
		simpleIHM1B ihm = new simpleIHM1B();
		ihm.go();

	}
	
	public void go(){
		JFrame cadre = new JFrame();
		bouton = new JButton("Cliquez moi");
		//radiobouton = new JRadioButton("Choisissez moi");
		
		bouton.addActionListener(this);
		
		cadre.getContentPane().add(bouton);
		//cadre.getContentPane().add(radiobouton);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.setSize(300, 300);
		cadre.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		bouton.setText("j'ai cliqué");
		bip = new MiniMiniMusicApp();
		bip.jouer();
		JOptionPane.showMessageDialog(null, "alerte !");
	}

}
