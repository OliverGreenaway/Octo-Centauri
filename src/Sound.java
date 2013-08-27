import java.io.File;
import java.util.Random;

import javax.sound.sampled.*;
import javax.sound.sampled.FloatControl.Type;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Sound {
	public static void main(String[] args) throws Exception {
		try {
		    AudioInputStream stream;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(new File("resources/sounds/testMusic.wav"));

		    for(Mixer.Info info : AudioSystem.getMixerInfo())
		    	System.out.println(info);

		    //Mixer mixer = AudioSystem.getMixer(AudioSystem.getMixerInfo()[0]);

		    AudioFormat format = stream.getFormat();
		    System.out.println(new AudioFormat(44100, 8, 1, false, false));

		    SourceDataLine line = AudioSystem.getSourceDataLine(format);

		    line.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					System.out.println(event);
				}
			});

		    line.open();
		    line.start();

		    byte[] data = new byte[500000];
		    while(true) {
		    	//int bytes = stream.read(data);

		    	int bytes = data.length;
		    	new Random().nextBytes(data);

		    	line.write(data, 0, bytes);
		    	System.out.println("transferred "+bytes+" bytes");
		    	break;
		    }
		    line.drain();
		    line.close();


		    /*clip = (Clip)AudioSystem.getMixer(AudioSystem.getMixerInfo()[3]).getLine(new DataLine.Info(Clip.class, stream.getFormat()));

		    clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					System.out.println(event);
				}
			});

		    clip.open(stream);
		    clip.start();

		    while(true) {
		    	System.out.println(clip.isRunning()+" "+clip.getFramePosition()+" "+clip.getFrameLength());
		    	Thread.sleep(500);
		    }*/

		    /*SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                // A GUI element to prevent the Clip's daemon Thread
	                // from terminating at the end of the main()
	                JOptionPane.showMessageDialog(null, "Close to exit!");
	            }
	        });*/



		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
