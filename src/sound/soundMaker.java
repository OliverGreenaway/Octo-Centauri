package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;

public class soundMaker {
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		Clip clip = AudioSystem.getClip();
		System.out.println(AudioSystem.getMixerInfo());
		//AudioSystem.

		AudioInputStream stream = AudioSystem.getAudioInputStream(new File(new JFileChooser().getCurrentDirectory()+"/workspace/other/UI/Assets/sounds/laugh.wav"));
		//System.out.println( " " + (clip.));
		//stream.
		clip.open(stream);

		clip.start();

		FloatControl gainControl =
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(5.0f);

		System.out.println(gainControl.getValue());





		System.out.println();
//		while(true){

			//clip.loop(Clip.LOOP_CONTINUOUSLY);
			System.out.println(clip.isActive() + " " + clip.getLevel() );

	//	}
	}
}
