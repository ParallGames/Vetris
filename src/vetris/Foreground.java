package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Foreground extends Group {
	private static double gameOverOpacity = 0;
	private static double pauseOpacity = 0;

	private static final Rectangle background = new Rectangle();
	private static final Rectangle pause[] = new Rectangle[3];
	private static final Text gameOver = new Text("Game Over");
	private static final Text score = new Text();
	private static final Text record = new Text();

	Foreground() {
		background.setTranslateX(0);
		background.setTranslateY(0);
		background.setFill(Color.rgb(0, 0, 0));
		this.getChildren().add(background);

		pause[0] = new Rectangle();
		pause[0].setFill(Color.rgb(63, 63, 63));
		this.getChildren().add(pause[0]);

		pause[1] = new Rectangle();
		this.getChildren().add(pause[1]);

		pause[2] = new Rectangle();
		this.getChildren().add(pause[2]);

		this.getChildren().add(score);
		this.getChildren().add(record);
		this.getChildren().add(gameOver);

		updateSize();
		updateColor();
		update();
	}

	public static synchronized void updateColor() {
		pause[1].setFill(ColorManager.getColor());
		pause[2].setFill(ColorManager.getColor());

		gameOver.setFill(ColorManager.getColor());
		score.setFill(ColorManager.getColor());
		record.setFill(ColorManager.getColor());
	}

	public static synchronized void updateSize() {
		background.setWidth(Window.getWidth());
		background.setHeight(Window.getHeight());

		pause[0].setTranslateX(Grid.getSquareSize() * 9 + Grid.getTranslate());
		pause[0].setTranslateY(Grid.getSquareSize() * 7);
		pause[0].setWidth(Grid.getSquareSize() * 6);
		pause[0].setHeight(Grid.getSquareSize() * 6);

		pause[1].setTranslateX(Grid.getSquareSize() * 10 + Grid.getTranslate());
		pause[1].setTranslateY(Grid.getSquareSize() * 8);
		pause[1].setWidth(Grid.getSquareSize());
		pause[1].setHeight(Grid.getSquareSize() * 4);

		pause[2].setTranslateX(Grid.getSquareSize() * 13 + Grid.getTranslate());
		pause[2].setTranslateY(Grid.getSquareSize() * 8);
		pause[2].setWidth(Grid.getSquareSize());
		pause[2].setHeight(Grid.getSquareSize() * 4);

		gameOver.setTranslateX(Grid.getSquareSize() * 4 + Grid.getTranslate());
		gameOver.setTranslateY(Grid.getSquareSize() * 6);
		gameOver.setFont(Font.font("Noto Mono", Grid.getSquareSize() * 3));

		score.setTranslateX(Grid.getSquareSize() * 7 + Grid.getTranslate());
		score.setTranslateY(Grid.getSquareSize() * 10);
		score.setFont(Font.font("Noto Mono", Grid.getSquareSize() * 2));

		record.setTranslateX(Grid.getSquareSize() * 6 + Grid.getTranslate());
		record.setTranslateY(Grid.getSquareSize() * 13);
		record.setFont(Font.font("Noto Mono", Grid.getSquareSize() * 2));
	}

	public static synchronized void updateGameOver() {
		score.setText("Score:" + String.valueOf(Grid.getScore()));
		record.setText("Record:" + String.valueOf(Grid.getRecord()));
	}

	public static synchronized void update() {
		if (Grid.isGameOver()) {
			gameOverOpacity += 0.01;
			if (gameOverOpacity > 0.9) {
				gameOverOpacity = 0.9;
			}
		} else {
			gameOverOpacity -= 0.01;
			if (gameOverOpacity < 0.0) {
				gameOverOpacity = 0.0;
			}
		}
		if (Grid.isPause()) {
			pauseOpacity += 0.01;
			if (pauseOpacity > 0.9) {
				pauseOpacity = 0.9;
			}
		} else {
			pauseOpacity -= 0.01;
			if (pauseOpacity < 0.0) {
				pauseOpacity = 0.0;
			}
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				background.setOpacity((pauseOpacity > gameOverOpacity) ? pauseOpacity : gameOverOpacity);

				gameOver.setOpacity(gameOverOpacity);
				score.setOpacity(gameOverOpacity);
				record.setOpacity(gameOverOpacity);

				pause[0].setOpacity(pauseOpacity);
				pause[1].setOpacity(pauseOpacity);
				pause[2].setOpacity(pauseOpacity);
			}
		});
	}
}
