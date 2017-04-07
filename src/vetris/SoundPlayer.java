package vetris;

import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundPlayer {	
	private static void play(String name){
		new Thread(){
			public void run(){
				try{
					BufferedInputStream bufferedIn = new BufferedInputStream(SoundPlayer.class.getResourceAsStream("/resources/sounds/"+name+".wav"));
					AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
					DataLine.Info info = new DataLine.Info(Clip.class,ais.getFormat());
					Clip clip = (Clip)AudioSystem.getLine(info);
					clip.open(ais);
					clip.start();
					clip.close();
					ais.close();
					bufferedIn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void playMove(){
		play("Move");
	}

	public static void playShock(){
		play("Shock");
	}

	public static void playGameOver(){
		play("GameOver");
	}

	public static void playLine(){
		play("Line");
	}
}