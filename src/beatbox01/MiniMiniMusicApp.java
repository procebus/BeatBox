package beatbox01;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMiniMusicApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		mini.jouer();
	}
	
	public void jouer(){
		try {
			
			//obtenir un sequenceur 
			Sequencer lecteur = MidiSystem.getSequencer();
			// l'ouvrir pour pouvoir l'utiliser ( pas par defaut)
			lecteur.open();
			
			Sequence seq =new Sequence(Sequence.PPQ, 4);
			
			// demander une piste à la sequence ..
			//Se souvenir que la piste est dans la sequence,
			//et les données MIDI dans la piste 
			Track piste = seq.createTrack();
			
			// on remplit la piste de qques vents MIDI
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1,44, 100); // type de message , canal , note ,vélocité
			MidiEvent noteOn = new MidiEvent(a, 1); // declencher la note sur le premier temps
			piste.add(noteOn);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128,1,44,100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			piste.add(noteOff);
			
//			ShortMessage c = new ShortMessage();
//			c.setMessage(192, 1,20, 100); // type de message , canal , note ,vélocité
//			MidiEvent note2On = new MidiEvent(c, 107); // declencher la note sur le premier temps
//			piste.add(note2On);
//			
//			ShortMessage d = new ShortMessage();
//			d.setMessage(128,1,20,100);
//			MidiEvent note2Off = new MidiEvent(d, 300);
//			piste.add(note2Off);
			
			lecteur.setSequence(seq); // transmettre la seq au séquenceur
			
			lecteur.start(); // demarrer le seq
			Thread.sleep(60*20); // pause de 20 sec pour laisser une chance au flux midi de jouer 
			lecteur.close(); // on ferme le lecteur
			
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}

}
