package vetris;

public class FallBlock {
	public static int gX = 0;
	public static int gY = 0;
	
	private static int x = 0;
	private static int y = 0;

	private static Shape shape = new Shape();

	private static Key key = new Key();

	private static long time = System.currentTimeMillis();

	public static boolean collision(){
		for(int a = 0;a < 4;a++){
			for(int b = 0;b < 4;b++){
				if(shape.getShape()[a][b] && (a+x > 9 || a+x < 0 || b+y < 0 || b+y > 19 || Grid.getSquare(a+x,b+y))){
					return true;
				}
			}
		}
		return false;
	}

	public static void rotate(){
		shape.rotate();
		shape.update();
		if(collision()){
			shape.unrotate();
			shape.update();
		}
	}

	public static void reset(){
		shape.setRandomShape();
		shape.update();

		x = ValMath.randInt(0-shape.maxLeft(),10-shape.maxRight());
		y = 0 - shape.maxUp();
		
		gX = x*32;
		gY = y*32;

		time = System.currentTimeMillis();
	}
	
	public static void tinyShape(){
		shape.setTinyShape();
	}

	public static void tick(){
		if(key.downDown()){
			if(System.currentTimeMillis() > time + Grid.getSpeed()/8){
				time = System.currentTimeMillis();
				y++;
			}
		}
		else{
			if(System.currentTimeMillis() > time + Grid.getSpeed()){
				time = System.currentTimeMillis();
				y++;
			}
		}

		for(int b_x = 0;b_x < 4;b_x++){
			for(int b_y = 0;b_y < 4;b_y++){
				if(shape.getShape()[b_x][b_y]){
					if(y >= 20-b_y || Grid.getSquare(x+b_x,y+b_y)){
						for(int b_a = 0;b_a < 4;b_a++){
							for(int b_b = 0;b_b < 4;b_b++){
								if(shape.getShape()[b_a][b_b]){
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
				if(shape.getShape()[b_x][b_y] && ((x+b_x) < 1 || Grid.getSquare(x+b_x-1,y+b_y))){
					return;
				}
			}
		}
		x--;
	}

	public static void goRight(){
		for(int b_x = 0;b_x < 4;b_x++){
			for(int b_y = 0;b_y < 4;b_y++){
				if(shape.getShape()[b_x][b_y] && ((x + b_x) > 8 || Grid.getSquare(x+b_x+1,y+b_y))){
					return;
				}
			}
		}
		x++;
	}

	public static void goDown(){
		y++;
	}
	
	public static void moveLeft(){
		if(x < 1){
			return;
		}
		x--;
	}
	
	public static void moveRight(){
		if(x > 8){
			return;
		}
		x++;
	}
	
	public static void moveUp(){
		if(y < 1){
			return;
		}
		y--;
	}
	
	public static void moveDown(){
		if(y > 18){
			return;
		}
		y++;
	}
	
	public static int getX(){
		return x;
	}
	
	public static int getY(){
		return y;
	}

	public static boolean isSquare(int p_x,int p_y){
		return shape.getShape()[p_x][p_y];
	}
}
