package beatbox02;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.sound.midi.*;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("unused")
public class BeatBox {
	
	
	JPanel panneauPrincipal;
	ArrayList<JCheckBox> listscases;
	Sequencer sequenceur;
	Sequence sequence;
	Track piste;
	JFrame leCadre;
	String [] nomsInstruments = {"Grosse Caisse","Charleston fermé","Charleston ouvert","Caisse claire","Cymbale Crash","Clap","Tom aigu","bongos","Maracas","Sifflet","Conga basse","Cloche","Calqué","Tom médium basse","Agogo","Conga ouvert"};
	int [] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63}; 
	

	public static void main(String[] args) {
	
//		BeatBox maboite=  new BeatBox();
//		maboite.construireIHM();
		
		new BeatBox().construireIHM();
		
		
		
		

	}
	
	public void construireIHM() {
		leCadre = new JFrame("Cyber BeatBox");
		leCadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout agencement = new BorderLayout();
		JPanel arrierePlan = new JPanel(agencement);
		arrierePlan.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // marge
		
		listscases = new ArrayList<JCheckBox>();
		Box boiteBoutons = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Jouer");
		start.addActionListener(new EcouteStart());
		boiteBoutons.add(start);
		
		JButton stop = new JButton("Arrêter");
		stop.addActionListener(new EcouteStop());
		boiteBoutons.add(stop);
		
		JButton plusVite = new JButton("Accélérer");
		plusVite.addActionListener(new EcoutePlusVite());
		boiteBoutons.add(plusVite);
		
		JButton moinsVite = new JButton("Ralentir");
		moinsVite.addActionListener(new EcouteMoinsVite());
		boiteBoutons.add(moinsVite);
		
		
		// ajout 
		JButton save = new JButton("Sauvegarder");
		save.addActionListener(new Sauvegarder());
		boiteBoutons.add(save);
		
		
		
		Box boiteNoms = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i <16; i++) {
			boiteNoms.add(new Label(nomsInstruments[i]));
		}
		
		arrierePlan.add(BorderLayout.EAST, boiteBoutons);
		arrierePlan.add(BorderLayout.WEST, boiteNoms);
		
		leCadre.getContentPane().add(arrierePlan);
		
		GridLayout grille = new GridLayout(16,16);
		grille.setVgap(1);
		grille.setHgap(2);
		panneauPrincipal = new JPanel(grille);
		arrierePlan.add(BorderLayout.CENTER, panneauPrincipal);
		
		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			listscases.add(c);
			panneauPrincipal.add(c);
		}
		
		installerMidi();
		

		
		leCadre.setBounds(100,100,300,300);
		//on demande d'attribuer une taille minimale à la fenêtre
		leCadre.pack();
		leCadre.setVisible(true);
	}
	
	public void installerMidi() {
		try {
			sequenceur= MidiSystem.getSequencer();
			sequenceur.open();
			sequence = new Sequence(Sequence.PPQ, 4);
			piste = sequence.createTrack();
			sequenceur.setTempoInBPM(120);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void contruirePisteEtdemarrer() {
		int[] listePistes = null;
		sequence.deleteTrack(piste);
		piste = sequence.createTrack();
		for (int i = 0; i < 16; i++) {
			listePistes = new int[16];
			int touche = instruments[i];
			for (int j = 0; j < 16; j++) {
				JCheckBox jc = (JCheckBox) listscases.get(j + (16*i));
				if (jc.isSelected()) {
					//listePistes[j]=touche;
					listePistes[j]=70;
				}else {
					listePistes[j]=0;
				}
				
			}
			creerPistes(listePistes);
			piste.add(makeEvent(176,1,127,0,16));
		}
		
		piste.add(makeEvent(192,9,1,0,15));
		try {
			sequenceur.setSequence(sequence);
			sequenceur.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequenceur.start();
			sequenceur.setTempoInBPM(120);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class EcouteStart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			contruirePisteEtdemarrer();
		}
//		public void actionPerformed(ActionListener a){
//			//contruirePisteEtdemarrer();
//			MorceauAJouer bip = new MorceauAJouer();
//			bip.jouer();
//
//		}
//
//		public void actionPerformed(ActionEvent arg0) {
//			
//			
//		}


	}
	public class Sauvegarder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MorceauAJouer bip = new MorceauAJouer();
		    bip.jouer();
		    
		    JOptionPane.showMessageDialog(null, "fuck");
		}
	}
	
	
	public class EcouteStop implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			sequenceur.stop();
		}
		
	}
	
	public class EcoutePlusVite implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			float facteurTempo = sequenceur.getTempoFactor();
			sequenceur.setTempoFactor((float) (facteurTempo *1.03));
		}
		
	}
	
	public class EcouteMoinsVite implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			float facteurTempo = sequenceur.getTempoFactor();
			sequenceur.setTempoFactor((float) (facteurTempo *0.97));
		}
		
	}
	
	public void creerPistes(int[] liste) {
		for (int i = 0; i < 16; i++) {
			int touche =liste[i];
			if (touche !=0) {
				piste.add(makeEvent(144,9,touche,100,i));
				piste.add(makeEvent(128,9,touche,100,i+1));
			}
		}
	}
	
	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick) {
		MidiEvent evenement = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			evenement =new MidiEvent(a, tick);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return evenement;
	}


}
