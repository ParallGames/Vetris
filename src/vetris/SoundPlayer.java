package vetris;

import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	
	private static Media move;
	private static Media shock;
	private static Media gameOver;
	private static Media line;
	
	public static void loadSounds(){
		try {
			move = new Media(SoundPlayer.class.getResource("/resources/sounds/Move.wav").toURI().toString());
			shock = new Media(SoundPlayer.class.getResource("/resources/sounds/Shock.wav").toURI().toString());
			gameOver = new Media(SoundPlayer.class.getResource("/resources/sounds/GameOver.wav").toURI().toString());
			line = new Media(SoundPlayer.class.getResource("/resources/sounds/Line.wav").toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void playMove(){
		MediaPlayer mp = new MediaPlayer(move);
		mp.setOnEndOfMedia(new Runnable(){
			public void run(){
				mp.dispose();
			}
		});
		mp.play();
	}
	
	public static void playShock(){
		MediaPlayer mp = new MediaPlayer(shock);
		mp.setOnEndOfMedia(new Runnable(){
			public void run(){
				mp.dispose();
			}
		});
		mp.play();
	}
	
	public static void playGameOver(){
		MediaPlayer mp = new MediaPlayer(gameOver);
		mp.setOnEndOfMedia(new Runnable(){
			public void run(){
				mp.dispose();
			}
		});
		mp.play();
	}
	
	public static void playLine(){
		MediaPlayer mp = new MediaPlayer(line);
		mp.setOnEndOfMedia(new Runnable(){
			public void run(){
				mp.dispose();
			}
		});
		mp.play();
	}
}