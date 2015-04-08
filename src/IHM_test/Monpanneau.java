package IHM_test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class Monpanneau extends JPanel{
	public void paintComponent(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(20, 50,100,100);
	}
}
