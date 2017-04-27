package vetris;

import java.net.URISyntaxException;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class Player{
	
}

public class SoundPlayer {
	
	private static AudioClip move;
	private static AudioClip shock;
	private static AudioClip gameOver;
	private static AudioClip line;
	
	public static void loadSounds(){
		move = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Move.wav").toExternalForm());
		shock = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Shock.wav").toExternalForm());
		gameOver = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/GameOver.wav").toExternalForm());
		line = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Line.wav").toExternalForm());
	}
	
	public static void playMove(){
		move.play();
	}
	
	public static void playShock(){
		shock.play();
	}
	
	public static void playGameOver(){
		gameOver.play();
	}
	
	public static void playLine(){
		line.play();
	}
}