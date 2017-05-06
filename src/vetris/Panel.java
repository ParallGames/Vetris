package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Panel extends Group {

	private int gEnergy = 0;

	Panel() {
		this.update();
	}

	public void update() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Panel.this.getChildren().clear();

				if (Grid.getEnergy() * 56 > gEnergy) {
					gEnergy++;
				} else if (Grid.getEnergy() * 56 < gEnergy) {
					gEnergy--;
				}

				if (FallBlock.getY() * 32 > FallBlock.gY) {
					FallBlock.gY += 320000 / Window.fps / Grid.getSpeed();
				} else if (FallBlock.getY() * 32 < FallBlock.gY) {
					FallBlock.gY -= 320000 / Window.fps / Grid.getSpeed();
				}
				if (Math.abs(FallBlock.gY - FallBlock.getY() * 32) < 320000 / Window.fps / Grid.getSpeed()) {
					FallBlock.gY = FallBlock.getY() * 32;
				}

				if (FallBlock.getX() * 32 > FallBlock.gX) {
					FallBlock.gX += 4;
				} else if (FallBlock.getX() * 32 < FallBlock.gX) {
					FallBlock.gX -= 4;
				}

				Rectangle rectangle;

				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 20; y++) {
						if (Grid.getSquare(x, y)) {
							rectangle = new Rectangle(x * 32 + 160, y * 32, 32, 32);
							rectangle.setFill(Grid.getColor());
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				rectangle = new Rectangle(520, 600 - gEnergy, 80, gEnergy);
				rectangle.setFill(Grid.getColor());
				Panel.this.getChildren().add(rectangle);

				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isSquare(x, y)) {
							rectangle = new Rectangle(FallBlock.gX + x * 32 + 160, FallBlock.gY + y * 32, 32, 32);
							if (Grid.getSquare(FallBlock.getX() + x, FallBlock.getY() + y)) {
								rectangle.setFill(Color.rgb((int) (Grid.getColor().getRed() * 255) - 63,
										(int) (Grid.getColor().getGreen() * 255) - 63,
										(int) (Grid.getColor().getBlue() * 255) - 63));
							} else {
								rectangle.setFill(Color.rgb(63, 63, 63));
							}
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isNextSquare(x, y)) {
							rectangle = new Rectangle(
									20 * x - 10 * FallBlock.getNextShape().maxLeft()
											- 10 * FallBlock.getNextShape().maxRight() + 70,
									20 * y - 10 * FallBlock.getNextShape().maxUp()
											- 10 * FallBlock.getNextShape().maxDown() + 550,
									20, 20);

							rectangle.setFill(Grid.getColor());
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				Text text = new Text(8, 64, String.valueOf(Grid.getScore()));
				text.setFont(new Font("Noto Mono", 32));
				text.setFill(Grid.getColor());
				Panel.this.getChildren().add(text);

				text = new Text(8, 160, String.valueOf(Grid.getRecord()));
				text.setFont(new Font("Noto Mono", 32));
				text.setFill(Grid.getColor());
				Panel.this.getChildren().add(text);
			}
		});

	}
}
