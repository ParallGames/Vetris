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
					gEnergy += 0.015625;
				} else if (Grid.getEnergy() < gEnergy) {
					gEnergy -= 0.015625;
				}

				Rectangle rectangle;

				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 20; y++) {
						if (Grid.getSquare(x, y)) {
							rectangle = new Rectangle(
									x * Grid.getSquareSize() + Grid.getSquareSize() * 6 + Grid.getTranslate(),
									y * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());
							rectangle.setFill(ColorManager.getColor());
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				double gEnergy1 = (gEnergy < 5) ? gEnergy * Grid.getSquareSize() * 3.6 : 5 * Grid.getSquareSize() * 3.6;
				double gEnergy2 = (gEnergy > 5) ? (gEnergy - 5) * Grid.getSquareSize() * 3.6 : 0;

				rectangle = new Rectangle(Grid.getSquareSize() * 17 + Grid.getTranslate() * 2,
						Grid.getSquareSize() * 19 - gEnergy1, Grid.getSquareSize() * 2, gEnergy1);
				rectangle.setFill((gEnergy < 5) ? ColorManager.getColor() : ColorManager.getDarkColor());
				Panel.this.getChildren().add(rectangle);

				rectangle = new Rectangle(Grid.getSquareSize() * 19 + Grid.getTranslate() * 2,
						Grid.getSquareSize() * 19 - gEnergy2, Grid.getSquareSize() * 2, gEnergy2);
				rectangle.setFill((gEnergy < 10) ? ColorManager.getColor() : ColorManager.getDarkColor());
				Panel.this.getChildren().add(rectangle);

				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isSquare(x, y)) {
							rectangle = new Rectangle(
									(int) (FallBlock.getGX() * Grid.getSquareSize() + x * Grid.getSquareSize()
											+ Grid.getSquareSize() * 6 + Grid.getTranslate()),
									(int) (FallBlock.getGY() * Grid.getSquareSize() + y * Grid.getSquareSize()),
									Grid.getSquareSize(), Grid.getSquareSize());
							if (Grid.getSquare(FallBlock.getX() + x, FallBlock.getY() + y)) {
								rectangle.setFill(ColorManager.getDarkColor());
							} else {
								rectangle.setFill(Color.rgb(63, 63, 63));
							}
							Panel.this.getChildren().add(rectangle);
						}
					}
				}
				int translateX = Grid.getSquareSize()
						+ (FallBlock.getNextShape().maxLeft() + 3 - FallBlock.getNextShape().maxRight())
								* Grid.getSquareSize() / 2
						- FallBlock.getNextShape().maxLeft() * Grid.getSquareSize();
				int translateY = Grid.getSquareSize() * 15
						+ (FallBlock.getNextShape().maxUp() + 3 - FallBlock.getNextShape().maxDown())
								* Grid.getSquareSize() / 2
						- FallBlock.getNextShape().maxUp() * Grid.getSquareSize();

				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isNextSquare(x, y)) {
							rectangle = new Rectangle(translateX + x * Grid.getSquareSize(),
									translateY + y * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());

							rectangle.setFill(ColorManager.getColor());
							Panel.this.getChildren().add(rectangle);
						}
					}
				}

				for (int a = 0; a < Grid.getFallingShapes().size(); a++) {
					for (int x = 0; x < 10; x++) {
						for (int y = 0; y < 20; y++) {
							if (Grid.getFallingShapes().get(a).get(x, y)) {
								rectangle = new Rectangle(
										x * Grid.getSquareSize() + Grid.getSquareSize() * 6 + Grid.getTranslate(),
										(int) (y * Grid.getSquareSize()
												+ Grid.getFallingShapes().get(a).getGY() * Grid.getSquareSize()),
										Grid.getSquareSize(), Grid.getSquareSize());
								rectangle.setFill(Color.rgb(63, 63, 63));
								Panel.this.getChildren().add(rectangle);
							}
						}
					}
				}

				Text text = new Text(Grid.getSquareSize(), Grid.getSquareSize() * 2, String.valueOf(Grid.getScore()));
				text.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				text.setFill(ColorManager.getColor());
				Panel.this.getChildren().add(text);

				text = new Text(Grid.getSquareSize(), Grid.getSquareSize() * 5, String.valueOf(Grid.getRecord()));
				text.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				text.setFill(ColorManager.getColor());
				Panel.this.getChildren().add(text);
			}
		});

	}
}
