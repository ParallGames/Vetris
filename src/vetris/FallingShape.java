package vetris;

public class FallingShape {
	private boolean m_shape[][] = new boolean[12][20];

	private int m_y = 0;
	private double m_gY = 0;

	FallingShape(boolean shape[][]) {
		clear();
		m_shape = shape;
	}

	FallingShape(boolean shape[][], int x, int y) {
		clear();
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (shape[a][b]) {
					m_shape[a + x][b + y] = shape[a][b];
				}
			}
		}
	}

	private void clear() {
		for (int a = 0; a < 12; a++) {
			for (int b = 0; b < 20; b++) {
				m_shape[a][b] = false;
			}
		}
	}

	public boolean get(int x, int y) {
		return m_shape[x][y];
	}

	public double getGY() {
		return m_gY;
	}

	public boolean tick() {
		m_gY += Grid.getSpeed() * 4;
		m_y = (int) m_gY + 1;

		for (int b_x = 0; b_x < 12; b_x++) {
			for (int b_y = 0; b_y < 20; b_y++) {
				if (m_shape[b_x][b_y]) {
					if (m_y >= 20 - b_y || Grid.getSquare(b_x, m_y + b_y)) {
						for (int b_a = 0; b_a < 12; b_a++) {
							for (int b_b = 0; b_b < 20; b_b++) {
								if (m_shape[b_a][b_b]) {
									Grid.setSquare(b_a, m_y + b_b - 1);
								}
							}
						}
						SoundPlayer.playShock();
						Grid.update();
						return true;
					}
				}
			}
		}
		return false;
	}
}
