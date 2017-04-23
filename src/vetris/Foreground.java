package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Foreground extends Group {
	private double opacity = 0.8;

	Foreground() {
		this.update();
	}

	public void update() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (Grid.isGameOver() || Grid.isPause()) {
					opacity += 0.01;
					if (opacity > 0.8) {
						opacity = 0.8;
					}
				} else {
					opacity -= 0.01;
					if (opacity < 0.0) {
						opacity = 0.0;
					}
				}

				Foreground.this.getChildren().clear();
				Rectangle rectangle = new Rectangle(0, 0, 640, 640);
				rectangle.setFill(Color.rgb(0, 0, 0, opacity));
				Foreground.this.getChildren().add(rectangle);

				if (Grid.isGameOver()) {
					Text text = new Text(50, 200, "Game Over");
					text.setFont(new Font("Noto Mono", 100));
					text.setFill(Color.rgb(255, 63, 63, opacity));
					Foreground.this.getChildren().add(text);

					text = new Text(160, 400, "Score:" + String.valueOf(Grid.getScore()));
					text.setFont(new Font("Noto Mono", 50));
					text.setFill(Color.rgb(255, 63, 63, opacity));
					Foreground.this.getChildren().add(text);

					text = new Text(160, 500, "Record:" + String.valueOf(Grid.getRecord()));
					text.setFont(new Font("Noto Mono", 50));
					text.setFill(Color.rgb(255, 63, 63, opacity));
					Foreground.this.getChildren().add(text);
				} else if (Grid.isPause()) {
					rectangle = new Rectangle(224, 224, 192, 192);
					rectangle.setFill(Color.rgb(63, 63, 63, opacity));
					Foreground.this.getChildren().add(rectangle);

					rectangle = new Rectangle(256, 256, 32, 128);
					rectangle.setFill(Color.rgb(255, 63, 63, opacity));
					Foreground.this.getChildren().add(rectangle);

					rectangle = new Rectangle(352, 256, 32, 128);
					rectangle.setFill(Color.rgb(255, 63, 63, opacity));
					Foreground.this.getChildren().add(rectangle);
				}
			}
		});
	}
}
