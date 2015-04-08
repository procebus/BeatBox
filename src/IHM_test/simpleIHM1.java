package IHM_test;

import javax.swing.JButton;
import javax.swing.JFrame;

public class simpleIHM1 {

	public static void main(String[] args) {
		JFrame cadre = new JFrame();
		JButton bouton = new JButton("cliquez moi");
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.getContentPane().add(bouton);
		cadre.setSize(300, 300);
		cadre.setVisible(true);
		

	}

}
