package vetris;

public class Grid {
	private static boolean grid[][] = new boolean[10][20];
	private static boolean gameOver = false;
	private static int speed = 1000;

	private static int score = 0;

	public static void reset(){
		score = 0;
		speed = 1000;
		gameOver = false;
		for(byte a = 0;a < 10;a++){
			for(byte b = 0;b < 20;b++){
				grid[a][b] = false;
			}
		}
	}
	
	public static void addSpeed(){
		speed-=speed/32;
	}
	
	public static int getSpeed(){
		return speed;
	}

	public static boolean isGameOver(){
		return gameOver;
	}
	
	public static boolean getSquare(int x,int y){
		return grid[x][y];
	}

	public static int getScore(){
		return score;
	}

	public static void update(){
		boolean isLine;
		for(int line = 0;line < 20;line++){
			isLine = true;
			for(int a = 0;a < 10;a++){
				if(!grid[a][line]){
					isLine = false;
				}
			}
			if(isLine){
				for(byte a = 0;a < 10;a++){
					grid[a][line] = false;
				}
				score += 10;
				addSpeed();
				for(int a = line - 1;a > 0;a--){
					for(int b = 0;b < 10;b++){
						if(!grid[b][a+1]){
							grid[b][a+1] = grid[b][a];
							grid[b][a] = false;
						}
					}
				}
			}
		}
	}

	public static void setSquare(int x,int y){
		if(y > 0){
			grid[x][y] = true;
		}
		else{
			gameOver = true;
		}
	}
}
