package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Panel extends Group {

	private static double gEnergy = 0;
	private static double energySpeed = 0;

	private static final Text score = new Text();
	private static final Text record = new Text();

	private static final Group gridGroup = new Group();
	private static final Group updateGroup = new Group();

	Panel() {
		update();
		this.getChildren().add(score);
		this.getChildren().add(record);
		this.getChildren().add(gridGroup);
		this.getChildren().add(updateGroup);
	}

	public static synchronized void update() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				updateGroup.getChildren().clear();

				if (Grid.getEnergy() > gEnergy + 0.01) {
					energySpeed += 0.0005;
				} else if (Grid.getEnergy() < gEnergy - 0.01) {
					energySpeed -= 0.0005;
				} else if (Math.abs(energySpeed) < 0.01) {
					energySpeed = 0;
					gEnergy = Grid.getEnergy();
				}
				energySpeed -= energySpeed / 128;
				gEnergy += energySpeed;
				if (gEnergy < 0) {
					gEnergy = 0;
					energySpeed = -energySpeed;
				} else if (gEnergy > 10) {
					gEnergy = 10;
					energySpeed = -energySpeed;
				}

				Rectangle rectangle;

				double gEnergy1 = (gEnergy < 5) ? gEnergy * Grid.getSquareSize() * 3.6 : 5 * Grid.getSquareSize() * 3.6;
				double gEnergy2 = (gEnergy > 5) ? (gEnergy - 5) * Grid.getSquareSize() * 3.6 : 0;

				rectangle = new Rectangle(Grid.getSquareSize() * 19 + Grid.getTranslate() * 2,
						Grid.getSquareSize() * 19 - gEnergy1, Grid.getSquareSize() * 2, gEnergy1);
				rectangle.setFill((Grid.getEnergy() < 5) ? ColorManager.getColor() : ColorManager.getDarkColor());
				updateGroup.getChildren().add(rectangle);

				rectangle = new Rectangle(Grid.getSquareSize() * 21 + Grid.getTranslate() * 2,
						Grid.getSquareSize() * 19 - gEnergy2, Grid.getSquareSize() * 2, gEnergy2);
				rectangle.setFill((Grid.getEnergy() < 10) ? ColorManager.getColor() : ColorManager.getDarkColor());
				updateGroup.getChildren().add(rectangle);

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
							updateGroup.getChildren().add(rectangle);
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
							updateGroup.getChildren().add(rectangle);
						}
					}
				}

				for (int a = 0; a < Grid.getFallingShapes().size(); a++) {
					for (int x = 0; x < 12; x++) {
						for (int y = 0; y < 20; y++) {
							if (Grid.getFallingShapes().get(a).get(x, y)) {
								rectangle = new Rectangle(
										x * Grid.getSquareSize() + Grid.getSquareSize() * 6 + Grid.getTranslate(),
										(int) (y * Grid.getSquareSize()
												+ Grid.getFallingShapes().get(a).getGY() * Grid.getSquareSize()),
										Grid.getSquareSize(), Grid.getSquareSize());
								rectangle.setFill(Color.rgb(63, 63, 63));
								updateGroup.getChildren().add(rectangle);
							}
						}
					}
				}

				score.setTranslateX(Grid.getSquareSize());
				score.setTranslateY(Grid.getSquareSize() * 2);
				score.setText(String.valueOf(Grid.getScore()));
				score.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				score.setFill(ColorManager.getColor());

				record.setTranslateX(Grid.getSquareSize());
				record.setTranslateY(Grid.getSquareSize() * 5);
				record.setText(String.valueOf(Grid.getRecord()));
				record.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				record.setFill(ColorManager.getColor());
			}
		});
	}

	public static synchronized void updateGrid() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gridGroup.getChildren().clear();
				Rectangle rectangle;
				for (int x = 0; x < 12; x++) {
					for (int y = 0; y < 20; y++) {
						if (Grid.getSquare(x, y)) {
							rectangle = new Rectangle(
									x * Grid.getSquareSize() + Grid.getSquareSize() * 6 + Grid.getTranslate(),
									y * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());
							rectangle.setFill(ColorManager.getColor());
							gridGroup.getChildren().add(rectangle);
						}
					}
				}
			}
		});
	}

	public static synchronized void updateSize() {
		updateGrid();
	}

	public static synchronized void updateColor() {
		updateGrid();
	}
}
