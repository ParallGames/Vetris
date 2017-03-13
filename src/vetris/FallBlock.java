package vetris;

import vetris.Grid;
import vetris.Key;
import vetris.Shape;
import vetris.ValMath;

public class FallBlock {
	private static int x = 0;
	private static int y = 0;

	private static Shape shape = new Shape();

	private static Key key = new Key();

	private static long time = System.currentTimeMillis();
	private static long speed = 1000;

	private static boolean collision(boolean collision[][]){
		for(int a = 0;a < 4;a++){
			for(int b = 0;b < 4;b++){
				if(collision[a][b] && a+x > 9){
					return true;
				}
				else if(collision[a][b] && a+x < 0){
					return true;
				}
				else if(collision[a][b] && Grid.getSquare(a+x, b+y)){
					
					return true;
				}
			}
		}
		return false;
	}

	public static void addSpeed(){
		speed-=speed/32;
	}

	public static void rotate(){
		shape.rotate();
		shape.update();
		if(collision(shape.shape)){
			shape.unrotate();
			shape.update();
		}
	}

	public static void reset(){
		shape.setRandomShape();
		shape.update();

		x = ValMath.randInt(0-shape.maxLeft(),10-shape.maxRight());
		y = 0 - shape.maxUp();

		time = System.currentTimeMillis();
	}

	public static void tick(){
		if(key.downDown()){
			if(System.currentTimeMillis() > time + speed/8){
				time = System.currentTimeMillis();
				y++;
			}
		}
		else{
			if(System.currentTimeMillis() > time + speed){
				time = System.currentTimeMillis();
				y++;
			}
		}

		for(int b_x = 0;b_x < 4;b_x++){
			for(int b_y = 0;b_y < 4;b_y++){
				if(shape.shape[b_x][b_y]){
					if(y >= 20-b_y || Grid.getSquare(x+b_x,y+b_y)){
						for(int b_a = 0;b_a < 4;b_a++){
							for(int b_b = 0;b_b < 4;b_b++){
								if(shape.shape[b_a][b_b]){
									Grid.setSquare(x+b_a,y+b_b-1);
								}
							}
						}
						Grid.update();
						reset();
						return;
					}	
				}
			}
		}
	}

	public static void goLeft(){
		for(int b_x = 0;b_x < 4;b_x++){
			for(int b_y = 0;b_y < 4;b_y++){
				if(shape.shape[b_x][b_y] && ((x+b_x) < 1 || Grid.getSquare(x+b_x-1,y+b_y))){
					return;
				}
			}
		}
		x--;
	}

	public static void goRight(){
		for(int b_x = 0;b_x < 4;b_x++){
			for(int b_y = 0;b_y < 4;b_y++){
				if(shape.shape[b_x][b_y] && ((x + b_x) > 8 || Grid.getSquare(x+b_x+1,y+b_y))){
					return;
				}
			}
		}
		x++;
	}

	public static void goDown(){
		y++;
	}

	public static int getShapeX(int p_x){
		return p_x + x;
	}
	public static int getShapeY(int p_y){
		return p_y + y;
	}

	public static boolean isSquare(int p_x,int p_y){
		return shape.shape[p_x][p_y];
	}
}