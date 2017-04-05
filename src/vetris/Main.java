package vetris;

import com.sun.javafx.application.PlatformImpl;

public class Main {
	public static void main(String args[]){
		PlatformImpl.startup(() -> {});
		SoundPlayer.loadSounds();
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
				while(window.isVisible() && FallBlock.fall()){
					window.repaint();
				}
				SoundPlayer.playShock();
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
				SoundPlayer.playGameOver();
				while(window.isVisible() && !key.enterHitted()){
					window.repaint();
				}
				Grid.reset();
				FallBlock.reset();
				key.reset();
			}
		}
		window.close();
		PlatformImpl.exit();
	}
}
