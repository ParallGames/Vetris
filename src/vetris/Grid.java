package vetris;

import java.util.Vector;

public class Grid {
	private static final boolean grid[][] = new boolean[12][20];
	private static boolean gameOver = false;
	private static boolean pause = false;
	private static boolean tinyShape = false;

	private static double speed = 0.02;

	private static int squareSize = 32;
	private static int translate = 0;

	private static int score = 0;
	private static int record = Save.loadScore();
	private static int energy = 0;

	private static final Vector<FallingShape> fallingShapes = new Vector<FallingShape>();

	public static void addSpeed() {
		speed += speed / 16384;
		if (speed > 0.24) {
			speed = 0.24;
		}
	}

	public static int getEnergy() {
		return energy;
	}

	public static synchronized Vector<FallingShape> getFallingShapes() {
		return fallingShapes;
	}

	public static int getRecord() {
		return record;
	}

	public static int getScore() {
		return score;
	}

	public static double getSpeed() {
		return speed;
	}

	public static boolean getSquare(int x, int y) {
		if (y > 19) {
			return true;
		}
		return grid[x][y];
	}

	public static int getSquareSize() {
		return squareSize;
	}

	public static int getTranslate() {
		return translate;
	}

	public static boolean hasEnoughEnergy() {
		if (energy >= 5 && !tinyShape) {
			energy -= 5;
			return true;
		}
		return false;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static boolean isPause() {
		return pause;
	}

	public static boolean isTinyShape() {
		return tinyShape;
	}

	public static void reset() {
		speed = 0.02;
		score = 0;
		record = Save.loadScore();
		energy = 0;

		gameOver = false;
		pause = false;

		for (byte a = 0; a < 12; a++) {
			for (byte b = 0; b < 20; b++) {
				grid[a][b] = false;
			}
		}
		Window.updateGrid();
	}

	public static void setPause(boolean p) {
		pause = p;
	}

	public static synchronized void setSquare(int x, int y) {
		if (y > 0) {
			grid[x][y] = true;
		} else if (!gameOver) {
			if (score > record) {
				Save.saveScore(score);
			}
			gameOver = true;
		}
	}

	public static void setTinyShape(boolean t) {
		tinyShape = t;
	}

	public static void setSquareSize(int size) {
		squareSize = size;
	}

	public static void setTranslate(int t) {
		translate = t;
	}

	public static synchronized void update() {
		boolean isLine;
		for (int line = 0; line < 20; line++) {
			isLine = true;
			for (int x = 0; x < 12; x++) {
				if (!grid[x][line]) {
					isLine = false;
				}
			}
			if (isLine) {
				for (int x = 0; x < 12; x++) {
					grid[x][line] = false;
				}
				score += 12;
				energy++;
				if (energy > 10) {
					energy = 10;
				}
			}
		}

		boolean possiblyFall[][] = new boolean[12][20];
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 20; y++) {
				possiblyFall[x][y] = grid[x][y];
			}
		}

		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 20; y++) {
				if (possiblyFall[x][y]) {
					boolean fall[][] = new boolean[12][20];
					for (int a = 0; a < 12; a++) {
						for (int b = 0; b < 20; b++) {
							fall[a][b] = false;
						}
					}
					fall[x][y] = true;
					boolean change = true;
					while (change) {
						change = false;
						for (int a = 0; a < 12; a++) {
							for (int b = 0; b < 20; b++) {
								if (possiblyFall[a][b] && !fall[a][b]) {
									if (a < 11 && fall[a + 1][b]) {
										fall[a][b] = true;
										change = true;
									} else if (a > 0 && fall[a - 1][b]) {
										fall[a][b] = true;
										change = true;
									} else if (b < 19 && fall[a][b + 1]) {
										fall[a][b] = true;
										change = true;
									} else if (b > 0 && fall[a][b - 1]) {
										fall[a][b] = true;
										change = true;
									}
								}
							}
						}
					}
					for (int a = 0; a < 12; a++) {
						for (int b = 0; b < 20; b++) {
							if (fall[a][b]) {
								possiblyFall[a][b] = false;
							}
						}
					}
					boolean canFall = true;
					for (int a = 0; a < 12; a++) {
						if (fall[a][19]) {
							canFall = false;
						}
					}
					if (canFall) {
						for (int a = 0; a < 12; a++) {
							for (int b = 0; b < 20; b++) {
								if (fall[a][b]) {
									grid[a][b] = false;
								}
							}
						}
						fallingShapes.add(new FallingShape(fall));
					}
				}
			}
		}
		Window.updateGrid();
	}
}
