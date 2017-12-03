package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Panel extends Group {

	private static double gEnergy = 0;
	private static double energySpeed = 0;

	private static final Text score = new Text();
	private static final Text record = new Text();

	private static final Rectangle powerBar[] = new Rectangle[2];

	private static final Group nextShapeGroup = new Group();
	private static final Group gridGroup = new Group();
	private static final Group updateGroup = new Group();

	Panel() {
		powerBar[0] = new Rectangle();
		this.getChildren().add(powerBar[0]);
		powerBar[1] = new Rectangle();
		this.getChildren().add(powerBar[1]);

		this.getChildren().add(score);
		this.getChildren().add(record);
		this.getChildren().add(gridGroup);
		this.getChildren().add(updateGroup);
		this.getChildren().add(nextShapeGroup);

		update();
		updateSize();
		updateColor();
	}

	public static synchronized void update() {
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

		double gEnergy0 = (gEnergy < 5) ? gEnergy * Grid.getSquareSize() * 3.6 : 5 * Grid.getSquareSize() * 3.6;
		double gEnergy1 = (gEnergy > 5) ? (gEnergy - 5) * Grid.getSquareSize() * 3.6 : 0;

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				powerBar[0].setTranslateY(Grid.getSquareSize() * 19 - gEnergy0);
				powerBar[0].setHeight(gEnergy0);
				powerBar[0].setFill((Grid.getEnergy() >= 5) ? ColorManager.getDarkColor() : ColorManager.getColor());

				powerBar[1].setTranslateY(Grid.getSquareSize() * 19 - gEnergy1);
				powerBar[1].setHeight(gEnergy1);
				powerBar[1].setFill((Grid.getEnergy() == 10) ? ColorManager.getDarkColor() : ColorManager.getColor());

				updateGroup.getChildren().clear();

				Rectangle rectangle;

				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isSquare(x, y)) {
							rectangle = new Rectangle(
									(int) ((FallBlock.getGX() + x + 6) * Grid.getSquareSize() + Grid.getTranslate()),
									(int) ((FallBlock.getGY() + y) * Grid.getSquareSize()), Grid.getSquareSize(),
									Grid.getSquareSize());
							if (Grid.getSquare(FallBlock.getX() + x, FallBlock.getY() + y)) {
								rectangle.setFill(ColorManager.getDarkColor());
							} else {
								rectangle.setFill(ColorManager.GRAY);
							}
							updateGroup.getChildren().add(rectangle);
						}
					}
				}

				for (int a = 0; a < Grid.getFallingShapes().size(); a++) {
					for (int x = 0; x < 12; x++) {
						for (int y = 0; y < 20; y++) {
							if (Grid.getFallingShapes().get(a).get(x, y)) {
								rectangle = new Rectangle((x + 6) * Grid.getSquareSize() + Grid.getTranslate(),
										(int) ((y + Grid.getFallingShapes().get(a).getGY()) * Grid.getSquareSize()),
										Grid.getSquareSize(), Grid.getSquareSize());
								if (Grid.getFallingShapes().get(a).isFromPlayer()) {
									rectangle.setFill(ColorManager.getColor());
								} else {
									rectangle.setFill(ColorManager.GRAY);
								}

								updateGroup.getChildren().add(rectangle);
							}
						}
					}
				}
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
							rectangle = new Rectangle((x + 6) * Grid.getSquareSize() + Grid.getTranslate(),
									y * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());
							rectangle.setFill(ColorManager.getColor());
							gridGroup.getChildren().add(rectangle);
						}
					}
				}
				score.setText(String.valueOf(Grid.getScore()));
				record.setText(String.valueOf(Grid.getRecord()));
			}
		});
	}

	public static synchronized void updateSize() {
		powerBar[0].setWidth(Grid.getSquareSize() * 2);
		powerBar[0].setTranslateX(Grid.getSquareSize() * 19 + Grid.getTranslate() * 2);

		powerBar[1].setWidth(Grid.getSquareSize() * 2);
		powerBar[1].setTranslateX(Grid.getSquareSize() * 21 + Grid.getTranslate() * 2);

		score.setTranslateX(Grid.getSquareSize());
		score.setTranslateY(Grid.getSquareSize() * 2);
		score.setFont(new Font("Noto Mono", Grid.getSquareSize()));

		record.setTranslateX(Grid.getSquareSize());
		record.setTranslateY(Grid.getSquareSize() * 5);
		record.setFont(new Font("Noto Mono", Grid.getSquareSize()));

		update();
		updateGrid();
		updateNextShape();
	}

	public static synchronized void updateColor() {
		score.setFill(ColorManager.getColor());
		record.setFill(ColorManager.getColor());
		
		update();
		updateGrid();
		updateNextShape();
	}
	
	public static synchronized void updateNextShape() {
		int translateX = (5 - FallBlock.getNextShape().maxLeft() - FallBlock.getNextShape().maxRight())
				* Grid.getSquareSize() / 2;
		int translateY = (33 - FallBlock.getNextShape().maxUp() - FallBlock.getNextShape().maxDown())
				* Grid.getSquareSize() / 2;
		Platform.runLater(new Runnable() {
			public void run() {
				nextShapeGroup.getChildren().clear();
				for (int x = 0; x < 4; x++) {
					for (int y = 0; y < 4; y++) {
						if (FallBlock.isNextSquare(x, y)) {
							Rectangle rectangle = new Rectangle(translateX + x * Grid.getSquareSize(),
									translateY + y * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());

							rectangle.setFill(ColorManager.getColor());
							nextShapeGroup.getChildren().add(rectangle);
						}
					}
				}
			}
		});
	}
}
