package vetris;

public class FallBlock {
	private static double gX = 0;
	private static double gY = 0;

	private static int x = 0;
	private static int y = 0;

	private static Shape shape = new Shape();
	private static Shape nextShape = new Shape();

	static {
		nextShape.setRandomShape();
		nextShape.update();
	}

	public static synchronized boolean collision() {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (shape.getShape()[a][b]
						&& (a + x > 11 || a + x < 0 || b + y < 0 || b + y > 19 || Grid.getSquare(a + x, b + y))) {
					return true;
				}
			}
		}
		return false;
	}

	public static synchronized double getGX() {
		return gX;
	}

	public static synchronized double getGY() {
		return gY;
	}

	public static synchronized Shape getNextShape() {
		return nextShape;
	}

	public static synchronized Shape getShape() {
		return shape;
	}

	public static synchronized int getX() {
		return x;
	}

	public static synchronized int getY() {
		return y;
	}

	public static synchronized void goLeft() {
		for (int b_x = 0; b_x < 4; b_x++) {
			for (int b_y = 0; b_y < 4; b_y++) {
				if (shape.getShape()[b_x][b_y] && ((x + b_x) < 1 || Grid.getSquare(x + b_x - 1, y + b_y))) {
					return;
				}
			}
		}
		x--;
		SoundPlayer.playMove();
	}

	public static synchronized void goRight() {
		for (int b_x = 0; b_x < 4; b_x++) {
			for (int b_y = 0; b_y < 4; b_y++) {
				if (shape.getShape()[b_x][b_y] && ((x + b_x) > 10 || Grid.getSquare(x + b_x + 1, y + b_y))) {
					return;
				}
			}
		}
		x++;
		SoundPlayer.playMove();
	}

	public static synchronized boolean isNextSquare(int p_x, int p_y) {
		return nextShape.getShape()[p_x][p_y];
	}

	public static synchronized boolean isSquare(int p_x, int p_y) {
		return shape.getShape()[p_x][p_y];
	}

	public static synchronized void moveDown() {
		if (y > 18) {
			return;
		}
		y++;
		SoundPlayer.playMove();
	}

	public static synchronized void moveLeft() {
		if (x < 1) {
			return;
		}
		x--;
		SoundPlayer.playMove();
	}

	public static synchronized void moveRight() {
		if (x > 10) {
			return;
		}
		x++;
		SoundPlayer.playMove();
	}

	public static synchronized void moveUp() {
		if (y < 2) {
			return;
		}
		y--;
		SoundPlayer.playMove();
	}

	public static synchronized void reset() {
		shape = nextShape;
		nextShape = new Shape();
		nextShape.setRandomShape();
		nextShape.update();

		x = 6 - shape.maxLeft() - shape.maxRight();
		y = -shape.maxUp();

		gX = x;
		gY = y;

		Window.updateNextShape();
	}

	public static synchronized void rotate() {
		shape.rotate();
		shape.update();
		if (collision()) {
			shape.unrotate();
			shape.update();
		} else {
			SoundPlayer.playMove();
		}
	}

	public static synchronized void tick() {
		if (Key.isDownDown()) {
			gY += Grid.getSpeed() * 4;
		} else if (Grid.getFallingShapes().size() == 0) {
			gY += Grid.getSpeed();
		}

		y = (int) gY + 1;

		double speed = 0.0625;
		if (Key.isShiftDown()) {
			speed = 0.125;
		}

		if (x > gX) {
			gX += speed;
		} else if (x < gX) {
			gX -= speed;
		}

		if (Math.abs(gX - x) <= 0.0625) {
			gX = x;
		}

		if (Key.isLeftDown() && !Key.isRightDown() && x == gX) {
			goLeft();
		}
		if (Key.isRightDown() && !Key.isLeftDown() && x == gX) {
			goRight();
		}

		for (int b_x = 0; b_x < 4; b_x++) {
			for (int b_y = 0; b_y < 4; b_y++) {
				if (shape.getShape()[b_x][b_y]) {
					if (y >= 20 - b_y || Grid.getSquare(x + b_x, y + b_y)) {
						for (int b_a = 0; b_a < 4; b_a++) {
							for (int b_b = 0; b_b < 4; b_b++) {
								if (shape.getShape()[b_a][b_b]) {
									Grid.setSquare(x + b_a, y + b_b - 1);
								}
							}
						}
						SoundPlayer.playShock();
						reset();
						Grid.update();
						return;
					}
				}
			}
		}
	}

	public static synchronized void tickTinyShape() {
		double speed = 0.0625;
		if (Key.isShiftDown()) {
			speed = 0.125;
		}

		if (x > gX) {
			gX += speed;
		} else if (x < gX) {
			gX -= speed;
		}
		if (y > gY) {
			gY += speed;
		} else if (y < gY) {
			gY -= speed;
		}

		if (Math.abs(gY - y) <= 0.0625) {
			gY = y;
		}
		if (Math.abs(gX - x) <= 0.0625) {
			gX = x;
		}

		if (Key.isLeftDown() && !Key.isRightDown() && x == gX) {
			moveLeft();
		}
		if (Key.isRightDown() && !Key.isLeftDown() && x == gX) {
			moveRight();
		}
		if (Key.isDownDown() && !Key.isUpDown() && y == gY) {
			moveDown();
		}
		if (Key.isUpDown() && !Key.isDownDown() && y == gY) {
			moveUp();
		}
	}

	public static synchronized void tinyShape() {
		shape.setTinyShape();
		if (y < 0) {
			y = 0;
		} else if (y > 19) {
			y = 19;
		}
		if (x < 0) {
			x = 0;
		} else if (x > 11) {
			x = 11;
		}
	}
}
