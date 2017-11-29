package vetris;

import javafx.application.Application;

public class Main {
	public static void main(String args[]) {
		ColorManager.loadColor();

		Application.launch(Window.class, args);

		if (Grid.getScore() > Grid.getRecord()) {
			Save.saveScore(Grid.getScore());
		}
		ColorManager.saveColor();
	}
}