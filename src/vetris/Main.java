package vetris;

import javafx.application.Application;

public class Main {
	public static void main(String args[]) {
		SoundPlayer.loadSounds();
		Application.launch(Window.class, args);
	}
}