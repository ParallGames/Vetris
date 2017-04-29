package vetris;

public class Grid {
	private static boolean grid[][] = new boolean[10][20];
	private static boolean gameOver = false;
	private static boolean pause = false;
	private static boolean tinyShape = false;

	private static int speed = 1000;
	private static int score = 0;
	private static int record = Save.loadScore();
	private static int energy = 0;

	public static void addSpeed() {
		speed -= speed / 32;
	}

	public static int getEnergy() {
		return energy;
	}

	public static int getRecord() {
		return record;
	}

	public static int getScore() {
		return score;
	}

	public static int getSpeed() {
		return speed;
	}

	public static boolean getSquare(int x, int y) {
		return grid[x][y];
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

	public static boolean isTinyShape(){
		return tinyShape;
	}
	
	public static void setPause(boolean p) {
		pause = p;
	}
	
	public static void setTinyShape(boolean t){
		tinyShape = t;
	}

	public static void reset() {
		speed = 1000;
		score = 0;
		record = Save.loadScore();
		energy = 0;

		gameOver = false;
		pause = false;

		for (byte a = 0; a < 10; a++) {
			for (byte b = 0; b < 20; b++) {
				grid[a][b] = false;
			}
		}
	}

	public static void setSquare(int x, int y) {
		if (y > 0) {
			grid[x][y] = true;
		} else {
			if (score > record) {
				Save.saveScore(score);
			}
			gameOver = true;
		}
	}

	public static void update() {
		boolean isLine;
		for (int line = 0; line < 20; line++) {
			isLine = true;
			for (int a = 0; a < 10; a++) {
				if (!grid[a][line]) {
					isLine = false;
				}
			}
			if (isLine) {
				for (byte a = 0; a < 10; a++) {
					grid[a][line] = false;
				}

				SoundPlayer.playLine();
				score += 10;
				energy++;
				if (energy > 10) {
					energy = 10;
				}

				addSpeed();
				for (int a = line - 1; a > 0; a--) {
					for (int b = 0; b < 10; b++) {
						if (!grid[b][a + 1]) {
							grid[b][a + 1] = grid[b][a];
							grid[b][a] = false;
						}
					}
				}
			}
		}
	}
}
