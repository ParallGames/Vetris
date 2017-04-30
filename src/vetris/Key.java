package vetris;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Key extends Group {

	private static boolean downPressed = false;

	public static boolean downDown() {
		return downPressed;
	}

	Key() {
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.LEFT && !Grid.isPause()) {
				if (Grid.isTinyShape()) {
					FallBlock.moveLeft();
				} else {
					FallBlock.goLeft();
				}
			}
			if (key.getCode() == KeyCode.RIGHT && !Grid.isPause()) {
				if (Grid.isTinyShape()) {
					FallBlock.moveRight();
				} else {
					FallBlock.goRight();
				}
			}
			if (key.getCode() == KeyCode.UP && !Grid.isPause()) {
				if (Grid.isTinyShape()) {
					FallBlock.moveUp();
				} else {
					FallBlock.rotate();
				}
			}
			if (key.getCode() == KeyCode.DOWN && !Grid.isPause()) {
				if (Grid.isTinyShape()) {
					FallBlock.moveDown();
				} else {
					downPressed = true;
				}
			}
			if (key.getCode() == KeyCode.ENTER && !Grid.isPause()) {
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
			}
			if (key.getCode() == KeyCode.P) {
				Grid.setPause(!Grid.isPause());
			}
		});

		this.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			if (key.getCode() == KeyCode.DOWN) {
				downPressed = false;
			}
		});
	}
}