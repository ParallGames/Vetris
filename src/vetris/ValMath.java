package vetris;

public class ValMath {
	public static int randInt(int down, int up) {
		return (int) ((up - down) * Math.random()) + down;
	}
}
