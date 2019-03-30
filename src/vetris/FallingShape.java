package vetris;

public class FallingShape {
	private boolean shape[][] = new boolean[12][20];

	private double gY = 0;

	private boolean isFromPlayer;

	FallingShape(boolean shape[][]) {
		clear();
		this.shape = shape;
		isFromPlayer = true;
	}

	FallingShape(boolean shape[][], int x, double gY) {
		int y = (int) gY;
		this.gY += gY - y;
		clear();
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (shape[a][b]) {
					this.shape[a + x][b + y] = shape[a][b];
				}
			}
		}
		isFromPlayer = false;
	}

	public boolean isFromPlayer() {
		return isFromPlayer;
	}

	private void clear() {
		for (int a = 0; a < 12; a++) {
			for (int b = 0; b < 20; b++) {
				shape[a][b] = false;
			}
		}
	}

	public boolean get(int x, int y) {
		return shape[x][y];
	}

	public double getGY() {
		return gY;
	}

	public boolean tick() {
		gY += Grid.getSpeed() * 4;
		int shapeY = (int) gY + 1;
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 20; y++) {
				if (shape[x][y]) {
					if (shapeY >= 20 - y || Grid.getSquare(x, shapeY + y)) {
						for (int a = 0; a < 12; a++) {
							for (int b = 0; b < 20; b++) {
								if (shape[a][b]) {
									Grid.setSquare(a, shapeY + b - 1);
								}
							}
						}
						Grid.update();
						return true;
					}
				}
			}
		}
		return false;
	}
}
