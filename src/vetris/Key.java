package vetris;

import javafx.application.Platform;
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
					Grid.getFallingShapes()
							.add(new FallingShape(FallBlock.getShape().getShape(), FallBlock.getX(), FallBlock.getY()));
					FallBlock.reset();
				} else if (Grid.hasEnoughEnergy()) {
					Grid.setTinyShape(true);
					FallBlock.tinyShape();
				}
				enterDown = true;
			}
			if (key.getCode() == KeyCode.SPACE && !spaceDown && !Grid.isPause() && !Grid.isTinyShape()
					&& !Grid.isGameOver()) {
				Grid.getFallingShapes()
						.add(new FallingShape(FallBlock.getShape().getShape(), FallBlock.getX(), FallBlock.getY()));
				FallBlock.reset();
				spaceDown = true;
			}
			if (key.getCode() == KeyCode.SHIFT) {
				shiftDown = true;
			}
			if (key.getCode() == KeyCode.C && !cDown) {
				ColorManager.nextColor();
				cDown = true;
			}
			if (key.getCode() == KeyCode.P && !pDown && !Grid.isGameOver()) {
				Grid.setPause(!Grid.isPause());
				pDown = true;
			}
			if (key.getCode() == KeyCode.F11) {
				if (Window.getPrimaryStage().isFullScreen()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Window.getPrimaryStage().setFullScreen(false);
						}
					});
				} else {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Window.getPrimaryStage().setFullScreen(true);
						}
					});
				}
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
		});
	}
}