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
			
			if(key.enterHitted() && Grid.hasEnoughEnergy()){
				FallBlock.tinyShape();
				while(window.isVisible()){
					if(key.leftHitted()){
						FallBlock.moveLeft();
					}
					if(key.rightHitted()){
						FallBlock.moveRight();
					}
					if(key.upHitted()){
						FallBlock.moveUp();
					}
					if(key.downHitted()){
						FallBlock.moveDown();
					}
					if(key.enterHitted() && !FallBlock.collision()){
						break;
					}
					window.repaint();
				}
				while(window.isVisible()){
					FallBlock.moveDown();
					if(FallBlock.collision()){
						FallBlock.moveUp();
						break;
					}
					if(FallBlock.getShapeY(0) > 18){
						break;
					}
					window.repaint();
				}
				Grid.setSquare(FallBlock.getShapeX(0),FallBlock.getShapeY(0));
				Grid.update();
				FallBlock.reset();
			}

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