package vetris;

public class FallBlock {
	private static double gX = 0;
	private static double gY = 0;

	private static int x = 0;
	private static int y = 0;

	private static Shape shape = new Shape();
	private static Shape nextShape = new Shape();

	public static synchronized boolean collision() {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (shape.getShape()[a][b]
						&& (a + x > 9 || a + x < 0 || b + y < 0 || b + y > 19 || Grid.getSquare(a + x, b + y))) {
					return true;
				}
			}
		}
		return false;
	}

	public static synchronized boolean fall() {
		if (y > 18 || Grid.getSquare(x, y + 1)) {
			return false;
		}
		y++;
		return true;
	}

	public static synchronized int getGX() {
		return (int) gX;
	}

	public static synchronized int getGY() {
		return (int) gY;
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
				if (shape.getShape()[b_x][b_y] && ((x + b_x) > 8 || Grid.getSquare(x + b_x + 1, y + b_y))) {
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
		if (x > 8) {
			return;
		}
		x++;
		SoundPlayer.playMove();
	}

	public static synchronized void moveUp() {
		if (y < 1) {
			return;
		}
		y--;
		SoundPlayer.playMove();
	}

	public static synchronized void reset() {
		shape = nextShape;
		shape.update();
		nextShape = new Shape();
		nextShape.setRandomShape();
		nextShape.update();

		x = ValMath.randInt(0 - shape.maxLeft(), 10 - shape.maxRight());
		y = 0 - shape.maxUp();

		gX = x * 32;
		gY = y * 32;
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
		} else {
			gY += Grid.getSpeed();
		}

		y = (int) gY / 32 + 1;

		if (FallBlock.getX() * 32 > FallBlock.gX) {
			FallBlock.gX += 2;
		} else if (FallBlock.getX() * 32 < FallBlock.gX) {
			FallBlock.gX -= 2;
		}

		if (Key.isLeftDown() && x * 32 == gX) {
			goLeft();
		}
		if (Key.isRightDown() && x * 32 == gX) {
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
						Grid.update();
						reset();
						return;
					}
				}
			}
		}
	}

	public static synchronized void tickTinyShape() {
		if (x * 32 > gX) {
			gX += 2;
		} else if (x * 32 < gX) {
			gX -= 2;
		}
		if (y * 32 > gY) {
			gY += 2;
		} else if (y * 32 < gY) {
			gY -= 2;
		}
		if (Math.abs(gY - y * 32) < 4) {
			gY = y * 32;
		}

		if (Key.isLeftDown() && x * 32 == gX) {
			moveLeft();
		}
		if (Key.isRightDown() && x * 32 == gX) {
			moveRight();
		}
		if (Key.isDownDown() && y * 32 == gY) {
			moveDown();
		}
		if (Key.isUpDown() && y * 32 == gY) {
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
		} else if (x > 9) {
			x = 9;
		}
	}
}
