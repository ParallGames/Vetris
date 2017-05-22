package vetris;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {
	public static int fps = 100;

	private static long time = System.nanoTime();
	private static long interval = 1_000_000_000 / fps;

	private Background background = new Background();
	private Panel panel = new Panel();
	private Foreground foreground = new Foreground();

	private Group root = new Group();
	private Key key = new Key();

	public void repaint() {
		background.update();
		panel.update();
		foreground.update();

		long sleep = time - System.nanoTime() + interval;

		if (sleep > 0) {
			try {
				Thread.sleep(sleep / 1000000, (int) (sleep % 1000000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		time = System.nanoTime();
	}

	@Override
	public synchronized void start(Stage primaryStage) {

		Grid.reset();
		FallBlock.reset();

		Scene scene = new Scene(root, 640, 640);
		scene.setFill(Color.rgb(31, 31, 31));

		root.getChildren().add(key);
		key.requestFocus();
		root.getChildren().add(background);
		root.getChildren().add(panel);
		root.getChildren().add(foreground);

		primaryStage.setTitle("Vetris");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

		new Thread() {
			@Override
			public void run() {
				while (primaryStage.isShowing()) {
					if (!Grid.isPause() && !Grid.isTinyShape()) {
						for (int a = 0; a < Grid.getFallingShapes().size(); a++) {
							if (Grid.getFallingShapes().get(a).tick()) {
								Grid.getFallingShapes().remove(a);
							}
						}
						FallBlock.tick();
						Grid.addSpeed();
					}

					Window.this.repaint();

					if (Grid.isGameOver()) {
						SoundPlayer.playGameOver();

						while (primaryStage.isShowing() && Grid.isGameOver()) {
							Window.this.repaint();
						}
					}
				}
				if (Grid.getScore() > Grid.getRecord()) {
					Save.saveScore(Grid.getScore());
				}
				Save.saveColor(Grid.getColor());
			}
		}.start();
	}
}