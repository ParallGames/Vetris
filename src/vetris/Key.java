package vetris;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Key extends Group {

	private static boolean leftDown = false;
	private static boolean rightDown = false;
	private static boolean upDown = false;
	private static boolean downDown = false;

	private static boolean enterDown = false;
	private static boolean spaceDown = false;

	private static boolean shiftDown = false;

	private static boolean cDown = false;
	private static boolean pDown = false;

	private static boolean f11Down = false;

	public static boolean isDownDown() {
		return downDown;
	}

	public static boolean isLeftDown() {
		return leftDown;
	}

	public static boolean isRightDown() {
		return rightDown;
	}

	public static boolean isUpDown() {
		return upDown;
	}

	public static boolean isShiftDown() {
		return shiftDown;
	}

	Key() {
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.LEFT && !Grid.isPause() && !leftDown) {
				leftDown = true;
				if (Grid.isTinyShape()) {
					FallBlock.moveLeft();
				} else {
					FallBlock.goLeft();
				}
			}
			if (key.getCode() == KeyCode.RIGHT && !Grid.isPause() && !rightDown) {
				rightDown = true;
				if (Grid.isTinyShape()) {
					FallBlock.moveRight();
				} else {
					FallBlock.goRight();
				}
			}
			if (key.getCode() == KeyCode.UP && !Grid.isPause() && !upDown) {
				upDown = true;
				if (Grid.isTinyShape()) {
					FallBlock.moveUp();
				} else {
					FallBlock.rotate();
				}
			}
			if (key.getCode() == KeyCode.DOWN && !Grid.isPause() && !downDown) {
				downDown = true;
				if (Grid.isTinyShape()) {
					FallBlock.moveDown();
				}
			}
			if (key.getCode() == KeyCode.ENTER && !Grid.isPause() && !enterDown) {
				enterDown = true;
				if (Grid.isGameOver()) {
					Grid.reset();
					Grid.getFallingShapes().clear();
					FallBlock.reset();
				} else if (Grid.isTinyShape() && !FallBlock.collision()) {
					Grid.setTinyShape(false);
					Grid.getFallingShapes().add(
							new FallingShape(FallBlock.getShape().getShape(), FallBlock.getX(), FallBlock.getGY()));
					FallBlock.reset();
				} else if (Grid.hasEnoughEnergy()) {
					Grid.setTinyShape(true);
					FallBlock.tinyShape();
				}
			}
			if (key.getCode() == KeyCode.SPACE && !spaceDown && !Grid.isPause() && !Grid.isTinyShape()
					&& !Grid.isGameOver()) {
				spaceDown = true;
				Grid.getFallingShapes()
						.add(new FallingShape(FallBlock.getShape().getShape(), FallBlock.getX(), FallBlock.getGY()));
				FallBlock.reset();
			}
			if (key.getCode() == KeyCode.SHIFT) {
				shiftDown = true;
			}
			if (key.getCode() == KeyCode.C && !cDown) {
				cDown = true;
				ColorManager.nextColor();
			}
			if (key.getCode() == KeyCode.P && !pDown && !Grid.isGameOver()) {
				pDown = true;
				Grid.setPause(!Grid.isPause());
			}
			if (key.getCode() == KeyCode.F11 && !f11Down) {
				f11Down = true;
				Window.getPrimaryStage().setFullScreen(!Window.getPrimaryStage().isFullScreen());
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
			if (key.getCode() == KeyCode.SPACE) {
				spaceDown = false;
			}
			if (key.getCode() == KeyCode.SHIFT) {
				shiftDown = false;
			}
			if (key.getCode() == KeyCode.C) {
				cDown = false;
			}
			if (key.getCode() == KeyCode.P) {
				pDown = false;
			}
			if (key.getCode() == KeyCode.F11) {
				f11Down = false;
			}
		});
	}
}