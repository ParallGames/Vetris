package vetris;

import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;

public class Key {

	private boolean leftPressed = false;
    private boolean left = false;
    
    private boolean rightPressed = false;
    private boolean right = false;
    
    private boolean upPressed = false;
    private boolean up = false;
    
    private boolean downPressed = false;
    private boolean down = false;
    
    private boolean enterPressed = false;
    private boolean enter = false;
    
    private boolean pPressed = false;
    private boolean p = false;
    
    public void reset(){
    	left = false;
    	right = false;
    	up = false;
    	down = false;
    	enter = false;
    	p = false;
    }
    
	Key(){
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (Key.class) {
                    if(ke.getID() == KeyEvent.KEY_PRESSED){
                        if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        	if(!leftPressed){
                        		leftPressed = true;
                        		left = true;
                        	}
                        }
                        if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        	if(!rightPressed){
                        		rightPressed = true;
                        		right = true;
                        	}
                        }
                        if(ke.getKeyCode() == KeyEvent.VK_UP){
                        	if(!upPressed){
                        		upPressed = true;
                        		up = true;
                        	}
                        }
                        if(ke.getKeyCode() == KeyEvent.VK_DOWN){
                        	if(!downPressed){
                        		downPressed = true;
                        		down = true;
                        	}
                        }
                        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                        	if(!enterPressed){
                        		enterPressed = true;
                        		enter = true;
                        	}
                        }
                        if(ke.getKeyCode() == KeyEvent.VK_P){
                        	if(!pPressed){
                        		pPressed = true;
                        		p = true;
                        	}
                        }
                    }else if(ke.getID() == KeyEvent.KEY_RELEASED){
                    	if(ke.getKeyCode() == KeyEvent.VK_LEFT){
                    		leftPressed = false;
                    	}
                    	if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    		rightPressed = false;
                    	}
                    	if(ke.getKeyCode() == KeyEvent.VK_UP){
                    		upPressed = false;
                    	}
                    	if(ke.getKeyCode() == KeyEvent.VK_DOWN){
                    		downPressed = false;
                    	}
                    	if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    		enterPressed = false;
                    	}
                    	if(ke.getKeyCode() == KeyEvent.VK_P){
                    		pPressed = false;
                    	}
                    }
                    return false;
                }
            }
        });
	}
	
	public boolean leftHitted(){
		if(left){
			left = false;
			return true;
		}
		return false;
	}
	
	public boolean rightHitted(){
		if(right){
			right = false;
			return true;
		}
		return false;
	}
	
	public boolean upHitted(){
		if(up){
			up = false;
			return true;
		}
		return false;
	}
	
	public boolean downHitted(){
		if(down){
			down = false;
			return true;
		}
		return false;
	}
	
	public boolean enterHitted(){
		if(enter){
			enter = false;
			return true;
		}
		return false;
	}
	
	public boolean pHitted(){
		if(p){
			p = false;
			return true;
		}
		return false;
	}
	
	public boolean downDown(){
		return downPressed;
	}
}