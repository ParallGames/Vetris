package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Panel extends Group {

	private double gEnergy = 0;

	Panel() {
		this.update();
	}

	public void update() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Panel.this.getChildren().clear();

				if (Grid.getEnergy() > gEnergy) {
					gEnergy += 0.01;
				} else if (Grid.getEnergy() < gEnergy) {
					gEnergy -= 0.01;
				}

				Rectangle rectangle;

				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 20; y++) {
						if (Grid.getSquare(x, y)) {
							rectangle = new Rectangle(x * Grid.getSquareSize() + Grid.getSquareSize() * 6,
									y * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());
							rectangle.setFill(Grid.getColor());
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				rectangle = new Rectangle(Grid.getSquareSize() * 17,
						Grid.getSquareSize() * 19 - gEnergy * Grid.getSquareSize() * 1.8, Grid.getSquareSize() * 4,
						gEnergy * Grid.getSquareSize() * 1.8);
				rectangle.setFill(Grid.getColor());
				Panel.this.getChildren().add(rectangle);

				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isSquare(x, y)) {
							rectangle = new Rectangle(
									(int) (FallBlock.getGX() * Grid.getSquareSize() + x * Grid.getSquareSize()
											+ Grid.getSquareSize() * 6),
									(int) (FallBlock.getGY() * Grid.getSquareSize() + y * Grid.getSquareSize()),
									Grid.getSquareSize(), Grid.getSquareSize());
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
									Grid.getSquareSize() * x - 10 * FallBlock.getNextShape().maxLeft()
											- Grid.getSquareSize() / 2 * FallBlock.getNextShape().maxRight()
											+ Grid.getSquareSize() * 2.5,
									Grid.getSquareSize() * y - 10 * FallBlock.getNextShape().maxUp()
											- Grid.getSquareSize() / 2 * FallBlock.getNextShape().maxDown()
											+ Grid.getSquareSize() * 16.5,
									Grid.getSquareSize(), Grid.getSquareSize());

							rectangle.setFill(Grid.getColor());
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				for (int a = 0; a < Grid.getFallingShapes().size(); a++) {
					for (int x = 0; x < 10; x++) {
						for (int y = 0; y < 20; y++) {
							if (Grid.getFallingShapes().get(a).get(x, y)) {
								rectangle = new Rectangle(x * Grid.getSquareSize() + Grid.getSquareSize() * 6,
										(int) (y * Grid.getSquareSize()
												+ Grid.getFallingShapes().get(a).getGY() * Grid.getSquareSize()),
										Grid.getSquareSize(), Grid.getSquareSize());
								rectangle.setFill(Color.rgb(63, 63, 63));
								Panel.this.getChildren().add(rectangle);
							}
						}
					}
				}

				Text text = new Text(Grid.getSquareSize() / 4, Grid.getSquareSize() * 2,
						String.valueOf(Grid.getScore()));
				text.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				text.setFill(Grid.getColor());
				Panel.this.getChildren().add(text);

				text = new Text(Grid.getSquareSize() / 4, Grid.getSquareSize() * 5, String.valueOf(Grid.getRecord()));
				text.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				text.setFill(Grid.getColor());
				Panel.this.getChildren().add(text);
			}
		});

	}
}
