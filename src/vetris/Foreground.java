package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Foreground extends Group {
	private double opacity = 0.9;

	private final Rectangle background = new Rectangle();
	private final Rectangle pause[] = new Rectangle[3];
	private final Text gameOver = new Text("Game Over");
	private final Text score = new Text();
	private final Text record = new Text();

	private final Group pauseGroup = new Group();
	private final Group gameOverGroup = new Group();

	Foreground() {
		background.setTranslateX(0);
		background.setTranslateY(0);
		this.getChildren().add(background);

		pause[0] = new Rectangle();
		pauseGroup.getChildren().add(pause[0]);

		pause[1] = new Rectangle();
		pauseGroup.getChildren().add(pause[1]);

		pause[2] = new Rectangle();
		pauseGroup.getChildren().add(pause[2]);

		gameOverGroup.getChildren().add(score);
		gameOverGroup.getChildren().add(record);
		gameOverGroup.getChildren().add(gameOver);

		this.getChildren().add(pauseGroup);
		this.getChildren().add(gameOverGroup);

		this.updateSize();
		this.update();
	}

	public void updateSize() {
		background.setWidth(Window.getScene().getWidth());
		background.setHeight(Window.getScene().getHeight());

		pause[0].setTranslateX(Grid.getSquareSize() * 8 + Grid.getTranslate());
		pause[0].setTranslateY(Grid.getSquareSize() * 7);
		pause[0].setWidth(Grid.getSquareSize() * 6);
		pause[0].setHeight(Grid.getSquareSize() * 6);

		pause[1].setTranslateX(Grid.getSquareSize() * 9 + Grid.getTranslate());
		pause[1].setTranslateY(Grid.getSquareSize() * 8);
		pause[1].setWidth(Grid.getSquareSize());
		pause[1].setHeight(Grid.getSquareSize() * 4);

		pause[2].setTranslateX(Grid.getSquareSize() * 12 + Grid.getTranslate());
		pause[2].setTranslateY(Grid.getSquareSize() * 8);
		pause[2].setWidth(Grid.getSquareSize());
		pause[2].setHeight(Grid.getSquareSize() * 4);

		gameOver.setTranslateX(Grid.getSquareSize() * 3 + Grid.getTranslate());
		gameOver.setTranslateY(Grid.getSquareSize() * 6);
		gameOver.setFont(Font.font("Noto Mono", Grid.getSquareSize() * 3));

		score.setTranslateX(Grid.getSquareSize() * 6 + Grid.getTranslate());
		score.setTranslateY(Grid.getSquareSize() * 10);
		score.setFont(Font.font("Noto Mono", Grid.getSquareSize() * 2));

		record.setTranslateX(Grid.getSquareSize() * 5 + Grid.getTranslate());
		record.setTranslateY(Grid.getSquareSize() * 13);
		record.setFont(Font.font("Noto Mono", Grid.getSquareSize() * 2));

	}

	public void updateGameOver() {
		score.setText("score:" + String.valueOf(Grid.getScore()));
		record.setText("score:" + String.valueOf(Grid.getRecord()));
	}

	public void update() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (Grid.isGameOver() || Grid.isPause()) {
					opacity += 0.01;
					if (opacity > 0.9) {
						opacity = 0.9;
					}
				} else {
					opacity -= 0.01;
					if (opacity < 0.0) {
						opacity = 0.0;
					}
				}

				background.setFill(Color.rgb(0, 0, 0, opacity));

				Color color = Color.color(ColorManager.getColor().getRed(), ColorManager.getColor().getGreen(),
						ColorManager.getColor().getBlue(), opacity);

				Foreground.this.getChildren().remove(gameOverGroup);
				Foreground.this.getChildren().remove(pauseGroup);

				if (Grid.isGameOver()) {

					Foreground.this.getChildren().add(gameOverGroup);

					gameOver.setFill(color);

					score.setFill(color);

					record.setFill(color);
				} else if (Grid.isPause()) {

					Foreground.this.getChildren().add(pauseGroup);

					pause[0].setFill(Color.rgb(63, 63, 63, opacity));

					pause[1].setFill(color);

					pause[2].setFill(color);
				}
			}
		});
	}
}
