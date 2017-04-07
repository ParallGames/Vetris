package vetris;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Key extends Group{

	private static boolean leftPressed = false;
	private static boolean left = false;

	private static boolean rightPressed = false;
	private static boolean right = false;

	private static boolean upPressed = false;
	private static boolean up = false;
    
	private static boolean downPressed = false;
	private static boolean down = false;

	private static boolean enterPressed = false;
	private static boolean enter = false;

	private static boolean pPressed = false;
	private static boolean p = false;

	public void reset(){
		left = false;
		right = false;
		up = false;
		down = false;
		enter = false;
		p = false;
	}

	Key(){
		this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(key.getCode() == KeyCode.LEFT){
				if(!leftPressed){
              		leftPressed = true;
              		left = true;
              	}
			}
		    if(key.getCode() == KeyCode.RIGHT){
		    	if(!rightPressed){
            		rightPressed = true;
            		right = true;
            	}
		    }
		    if(key.getCode() == KeyCode.UP){
            	if(!upPressed){
            		upPressed = true;
            		up = true;
            	}
		    }
		    if(key.getCode() == KeyCode.DOWN){
            	if(!downPressed){
            		downPressed = true;
            		down = true;
            	}
		    }
		    if(key.getCode() == KeyCode.ENTER){
            	if(!enterPressed){
            		enterPressed = true;
            		enter = true;
            	}
		    }
		    if(key.getCode() == KeyCode.P){
            	if(!pPressed){
            		pPressed = true;
            		p = true;
            	}
		    }
		});
		
		this.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
		      if(key.getCode() == KeyCode.LEFT){
		          leftPressed = false;
		      }
		      if(key.getCode() == KeyCode.RIGHT){
		    	  rightPressed = false;
		      }
		      if(key.getCode() == KeyCode.UP){
		    	  upPressed = false;
		      }
		      if(key.getCode() == KeyCode.DOWN){
		    	  downPressed = false;
		      }
		      if(key.getCode() == KeyCode.ENTER){
		    	  enterPressed = false;
		      }
		      if(key.getCode() == KeyCode.P){
		    	  pPressed = false;
		      }
		});
	}
	
	public static boolean leftHitted(){
		if(left){
			left = false;
			return true;
		}
		return false;
	}
	
	public static boolean rightHitted(){
		if(right){
			right = false;
			return true;
		}
		return false;
	}
	
	public static boolean upHitted(){
		if(up){
			up = false;
			return true;
		}
		return false;
	}
	
	public static boolean downHitted(){
		if(down){
			down = false;
			return true;
		}
		return false;
	}
	
	public static boolean enterHitted(){
		if(enter){
			enter = false;
			return true;
		}
		return false;
	}
	
	public static boolean pHitted(){
		if(p){
			p = false;
			return true;
		}
		return false;
	}
	
	public static boolean downDown(){
		return downPressed;
	}
}