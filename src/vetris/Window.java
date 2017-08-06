package vetris;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {
	private static final int fps = 100;

	private static long time = System.nanoTime();
	private static final long interval = 1_000_000_000 / fps;

	private final Background background = new Background();
	private final Panel panel = new Panel();
	private final Foreground foreground = new Foreground();

	private final Group root = new Group();
	private final Key key = new Key();

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
		primaryStage.getIcons().add(new Image(Window.class.getResourceAsStream("/resources/images/Icon.png")));
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		
		ChangeListener<Number> resizeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> a, Number b, Number c) {

			}
		};

		primaryStage.widthProperty().addListener(resizeListener);
		primaryStage.heightProperty().addListener(resizeListener);
		
		primaryStage.show();
		
		new Thread() {
			@Override
			public void run() {
				while (primaryStage.isShowing()) {
					if (!Grid.isPause()) {
						for (int a = 0; a < Grid.getFallingShapes().size(); a++) {
							if (Grid.getFallingShapes().get(a).tick()) {
								Grid.getFallingShapes().remove(a);
							}
						}
						if (Grid.isTinyShape()) {
							FallBlock.tickTinyShape();
						} else {
							FallBlock.tick();
							Grid.addSpeed();
						}
					}

					Window.this.repaint();

					if (Grid.isGameOver()) {
						SoundPlayer.playGameOver();

						while (primaryStage.isShowing() && Grid.isGameOver()) {
							Window.this.repaint();
						}
					} else if (!primaryStage.isFocused()) {
						Grid.setPause(true);
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