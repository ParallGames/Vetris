package vetris;

import vetris.FallBlock;
import vetris.Grid;
import vetris.Key;
import vetris.Window;

public class Main {
	public static void main(String args[]){
		Window window = new Window();
		Key key = new Key();

		Grid.reset();
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

			if(Grid.isGameOver()){
				while(window.isVisible() && !key.enterHitted()){
					window.repaint();
				}
				Grid.reset();
				FallBlock.reset();
			}
		}
		window.close();
	}
}