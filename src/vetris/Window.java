package vetris;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application{

	private Panel panel = new Panel();
	private Foreground foreground = new Foreground();
	
	private Group root = new Group();
	
	private Key key = new Key();
	
	private static long time = System.nanoTime();
	public static int fps = 60;
	private static long interval = 1_000_000_000 / fps;

	public synchronized void start(Stage primaryStage){

		Grid.reset();
		FallBlock.reset();
		
		Scene scene = new Scene(root,640,640);
		scene.setFill(Color.rgb(31, 31, 31));
		
		root.getChildren().add(key);
		key.requestFocus();
		root.getChildren().add(new Background());
		root.getChildren().add(panel);
		root.getChildren().add(foreground);
		
		primaryStage.setTitle("Vetris");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(){
			public void run(){
				while(primaryStage.isShowing()){
					FallBlock.tick();
					
					if(Key.leftHitted()){
						FallBlock.goLeft();
					}
					if(Key.rightHitted()){
						FallBlock.goRight();
					}
					if(Key.upHitted()){
						FallBlock.rotate();
					}
					if(Key.pHitted()){
						Grid.pause();
					}
					Window.this.repaint();
					
					if(Key.enterHitted() && Grid.hasEnoughEnergy()){
						FallBlock.tinyShape();
						while(primaryStage.isShowing()){
							if(Key.leftHitted()){
								FallBlock.moveLeft();
							}
							if(Key.rightHitted()){
								FallBlock.moveRight();
							}
							if(Key.upHitted()){
								FallBlock.moveUp();
							}
							if(Key.downHitted()){
								FallBlock.moveDown();
							}
							if(Key.enterHitted() && !FallBlock.collision()){
								break;
							}
							Window.this.repaint();
						}
						while(primaryStage.isShowing() && FallBlock.fall()){
							Window.this.repaint();
						}
						SoundPlayer.playShock();
						Grid.setSquare(FallBlock.getX(),FallBlock.getY());
						Grid.update();
						FallBlock.reset();
					}
					
					if(Grid.isPause()){
						while(primaryStage.isShowing() && !Key.pHitted()){
							Window.this.repaint();
						}
						Grid.unpause();
						key.reset();
					}

					if(Grid.isGameOver()){
						SoundPlayer.playGameOver();
						while(primaryStage.isShowing() && !Key.enterHitted()){
							Window.this.repaint();
						}
						Grid.reset();
						FallBlock.reset();
						key.reset();
					}
				}
			}
		}.start();
	}
	public void repaint(){
		panel.update();
		foreground.update();
		
		long sleep = time - System.nanoTime() + interval;

		if(sleep > 0){
			try{
				Thread.sleep(sleep/1000000,(int) (sleep%1000000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		time = System.nanoTime();
	}
}