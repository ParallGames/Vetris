package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Foreground extends Group {
	private double opacity = 0.9;

	Foreground() {
		this.update();
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

				Foreground.this.getChildren().clear();
				Rectangle rectangle = new Rectangle(0, 0, Window.getScene().getWidth(), Window.getScene().getHeight());
				rectangle.setFill(Color.rgb(0, 0, 0, opacity));
				Foreground.this.getChildren().add(rectangle);

				Color color = Color.color(Grid.getColor().getRed(), Grid.getColor().getGreen(),
						Grid.getColor().getBlue(), opacity);

				if (Grid.isGameOver()) {
					Text text = new Text(Grid.getSquareSize() * 3 + Grid.getTranslate(), Grid.getSquareSize() * 6,
							"Game Over");
					text.setFont(new Font("Noto Mono", Grid.getSquareSize() * 3));
					text.setFill(color);
					Foreground.this.getChildren().add(text);

					text = new Text(Grid.getSquareSize() * 6 + Grid.getTranslate(), Grid.getSquareSize() * 10,
							"Score:" + String.valueOf(Grid.getScore()));
					text.setFont(new Font("Noto Mono", Grid.getSquareSize() * 2));
					text.setFill(color);
					Foreground.this.getChildren().add(text);

					text = new Text(Grid.getSquareSize() * 5 + Grid.getTranslate(), Grid.getSquareSize() * 13,
							"Record:" + String.valueOf(Grid.getRecord()));
					text.setFont(new Font("Noto Mono", Grid.getSquareSize() * 2));
					text.setFill(color);
					Foreground.this.getChildren().add(text);
				} else if (Grid.isPause()) {
					rectangle = new Rectangle(Grid.getSquareSize() * 8 + Grid.getTranslate(), Grid.getSquareSize() * 7,
							Grid.getSquareSize() * 6, Grid.getSquareSize() * 6);
					rectangle.setFill(Color.rgb(63, 63, 63, opacity));
					Foreground.this.getChildren().add(rectangle);

					rectangle = new Rectangle(Grid.getSquareSize() * 9 + Grid.getTranslate(), Grid.getSquareSize() * 8,
							Grid.getSquareSize(), Grid.getSquareSize() * 4);
					rectangle.setFill(color);
					Foreground.this.getChildren().add(rectangle);

					rectangle = new Rectangle(Grid.getSquareSize() * 12 + Grid.getTranslate(), Grid.getSquareSize() * 8,
							Grid.getSquareSize(), Grid.getSquareSize() * 4);
					rectangle.setFill(color);
					Foreground.this.getChildren().add(rectangle);
				}
			}
		});
	}
}
