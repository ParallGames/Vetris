package vetris;

import javafx.scene.media.AudioClip;

public class SoundPlayer {

	private static AudioClip move;
	private static AudioClip shock;
	private static AudioClip gameOver;
	private static AudioClip line;

	public static void loadSounds() {
		move = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Move.wav").toExternalForm());
		shock = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Shock.wav").toExternalForm());
		gameOver = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/GameOver.wav").toExternalForm());
		line = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Line.wav").toExternalForm());
	}

	public static void playGameOver() {
		gameOver.play();
	}

	public static void playLine() {
		line.play();
	}

	public static void playMove() {
		move.play();
	}

	public static void playShock() {
		shock.play();
	}
}