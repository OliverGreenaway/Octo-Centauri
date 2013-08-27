import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Sound {
	public static void main(String[] args) throws Exception {
		try {
		    AudioInputStream stream;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(new File("resources/sounds/testMusic.wav"));
		    clip = AudioSystem.getClip();

		    clip.open(stream);
		    clip.loop(Clip.LOOP_CONTINUOUSLY);

		    System.out.println(clip.getFramePosition()+" "+clip.getFrameLength());

		    SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                // A GUI element to prevent the Clip's daemon Thread
	                // from terminating at the end of the main()
	                JOptionPane.showMessageDialog(null, "Close to exit!");
	            }
	        });

		    System.out.println(clip.isRunning());

		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
