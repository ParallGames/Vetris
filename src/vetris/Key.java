package vetris;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Key extends Group {

	private static boolean leftDown = false;
	private static boolean rightDown = false;
	private static boolean upDown = false;
	private static boolean downDown = false;

	private static boolean enterDown = false;

	private static boolean cDown = false;
	private static boolean pDown = false;

	public static boolean downDown() {
		return downDown;
	}

	Key() {
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.LEFT && !Grid.isPause() && !leftDown) {
				if (Grid.isTinyShape()) {
					FallBlock.moveLeft();
				} else {
					FallBlock.goLeft();
				}
				leftDown = true;
			}
			if (key.getCode() == KeyCode.RIGHT && !Grid.isPause() && !rightDown) {
				if (Grid.isTinyShape()) {
					FallBlock.moveRight();
				} else {
					FallBlock.goRight();
				}
				rightDown = true;
			}
			if (key.getCode() == KeyCode.UP && !Grid.isPause() && !upDown) {
				if (Grid.isTinyShape()) {
					FallBlock.moveUp();
				} else {
					FallBlock.rotate();
				}
				upDown = true;
			}
			if (key.getCode() == KeyCode.DOWN && !Grid.isPause() && !downDown) {
				if (Grid.isTinyShape()) {
					FallBlock.moveDown();
				}
				downDown = true;
			}
			if (key.getCode() == KeyCode.ENTER && !Grid.isPause() && !enterDown) {
				if (Grid.isGameOver()) {
					Grid.reset();
					FallBlock.reset();
				} else if (Grid.isTinyShape() && !FallBlock.collision()) {
					Grid.setTinyShape(false);
					Grid.setSquare(FallBlock.getX(), FallBlock.getY());
					Grid.update();
					SoundPlayer.playShock();
					FallBlock.reset();
				} else if (Grid.hasEnoughEnergy()) {
					Grid.setTinyShape(true);
					FallBlock.tinyShape();
				}
				enterDown = true;
			}
			if (key.getCode() == KeyCode.C && !cDown) {
				Grid.setColor(
						Color.color(Grid.getColor().getBlue(), Grid.getColor().getRed(), Grid.getColor().getGreen()));
				cDown = true;
			}
			if (key.getCode() == KeyCode.P && !pDown) {
				Grid.setPause(!Grid.isPause());
				pDown = true;
			}
		});

		this.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			if (key.getCode() == KeyCode.LEFT) {
				leftDown = false;
			}
			if (key.getCode() == KeyCode.RIGHT) {
				rightDown = false;
			}
			if (key.getCode() == KeyCode.UP) {
				upDown = false;
			}
			if (key.getCode() == KeyCode.DOWN) {
				downDown = false;
			}

			if (key.getCode() == KeyCode.ENTER) {
				enterDown = false;
			}

			if (key.getCode() == KeyCode.C) {
				cDown = false;
			}
			if (key.getCode() == KeyCode.P) {
				pDown = false;
			}
		});
	}
}