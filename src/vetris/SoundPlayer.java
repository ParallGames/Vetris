package vetris;

import javafx.scene.media.AudioClip;

public class SoundPlayer {

	private static final AudioClip move = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Move.wav").toExternalForm());
	private static final AudioClip shock = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Shock.wav").toExternalForm());
	private static final AudioClip gameOver = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/GameOver.wav").toExternalForm());
	private static final AudioClip line = new AudioClip(SoundPlayer.class.getResource("/resources/sounds/Line.wav").toExternalForm());

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