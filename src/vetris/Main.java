package vetris;

import vetris.FallBlock;
import vetris.Grid;
import vetris.Key;
import vetris.Window;

public class Main {
	public static void main(String args[]){
		Window window = new Window();
		Key key = new Key();

		Grid.clear();
		FallBlock.reset();

		while(window.isVisible()){
			FallBlock.tick();

			if(key.leftHitted()){
				FallBlock.goLeft();
			}
			if(key.rightHitted()){
				FallBlock.goRight();
			}
			if(key.upHitted()){
				FallBlock.rotate();
			}
			window.repaint();
		}
		window.close();
	}
}