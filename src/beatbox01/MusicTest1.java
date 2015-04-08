package beatbox01;

import javax.sound.midi.*;

public class MusicTest1 {

	public void jouer() {
		try {
			Sequencer sequenceur = MidiSystem.getSequencer();
			System.out.println("nous avons un sequenceur !");
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(" la poisse !!!");
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MusicTest1 mt = new MusicTest1();
		mt.jouer();
		
		
	}

}
