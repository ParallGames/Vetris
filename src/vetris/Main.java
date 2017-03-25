package vetris;

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
			if(key.pHitted()){
				Grid.pause();
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
					if(FallBlock.getY() > 18){
						break;
					}
					window.repaint();
				}
				Grid.setSquare(FallBlock.getX(),FallBlock.getY());
				Grid.update();
				FallBlock.reset();
			}
			
			if(Grid.isPause()){
				while(window.isVisible() && !key.pHitted()){
					window.repaint();
				}
				Grid.unpause();
			}

			if(Grid.isGameOver()){
				while(window.isVisible() && !key.enterHitted()){
					window.repaint();
				}
				Grid.reset();
				FallBlock.reset();
				key.reset();
			}
		}
		window.close();
	}
}
