package sound;

import java.util.ArrayList;

public class MixingDesk {
	private ArrayList<AudioPlayer> players = new ArrayList<AudioPlayer>();
	private Boolean muted = false;


	/**
	 * this is the central source and controls for all the sound in the game
	 *
	 */
	public MixingDesk(){

	}


	/**
	 * Plays specified sound name
	 * @param sound
	 * @param playOnce
	 */
	public void addAudioPlayer(String sound, Boolean playOnce){
		if(muted){
			// do nothing
			//a.togglePaused();
		}else{
			AudioPlayer a = new AudioPlayer(sound, playOnce);
			players.add(a);
			a.start();
		}
	}


	/**
	 * toggles whether sound is on or not.
	 */
	public boolean toggleMute(){
		for(AudioPlayer a : players){
			a.togglePaused();
		}
		muted = !muted;
		return muted;
	}

	/**
	 * turns off all the music but leaves sound effects on.
	 *
	 */
	public void toggleMusic(){
		for(AudioPlayer a : players){
			if(!a.getRunOnce()){
				a.togglePaused();
			}
		}
	}


	/**
	 *
	 *
	 */
	public void stopAudio(){
		for(AudioPlayer a : players){
			a.stopPlayer();
		}
	}


	public static void main(String[] args) throws InterruptedException {

		MixingDesk desk = new MixingDesk();
		desk.addAudioPlayer("MenuMusic.wav", false);
		Thread.sleep(1000);

		desk.toggleMute();
		desk.addAudioPlayer("DyingDude.wav", true);
		desk.addAudioPlayer("DyingDude.wav", true);
		desk.addAudioPlayer("DyingDude.wav", true);

		Thread.sleep(1000);


		desk.toggleMute();

	}

}
