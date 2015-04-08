package beatbox02;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MorceauAJouer {
	
	
	
	public void jouer(){
	try {
		
		
		Sequencer lecteur = MidiSystem.getSequencer();
		lecteur.open();
		
		Sequence seq =new Sequence(Sequence.PPQ, 4);
		
		Track piste = seq.createTrack();
		
		
		ShortMessage a = new ShortMessage();
		a.setMessage(144, 1,44, 100);
		MidiEvent noteOn = new MidiEvent(a, 1); 
		piste.add(noteOn);
		
		ShortMessage b = new ShortMessage();
		b.setMessage(128,1,44,100);
		MidiEvent noteOff = new MidiEvent(b, 16);
		piste.add(noteOff);
		
		lecteur.setSequence(seq); 
		
		lecteur.start(); 
		Thread.sleep(60*20); 
		lecteur.close(); 
		
		
	} catch (Exception ex) {
		
		ex.printStackTrace();
	}
	}

}
