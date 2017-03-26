package vetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	private int gEnergy = 0;
	private int alpha = 255;

	Panel(){
		this.setPreferredSize(new Dimension(640,640));
	}

	public void paintComponent(Graphics g){
		g.setColor(new Color(31,31,31));
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		g.setColor(new Color(127,127,127));
		g.fillRect(160,0,320,640);
		g.fillRect(520,40,80,560);
		
		g.drawString("Bonsoir", 0, 0);
		
		g.setColor(new Color(255,63,63));
		for(byte x = 0;x < 10;x++){
			for(byte y = 0;y < 20;y++){
				if(Grid.getSquare(x,y)){
					g.fillRect(x*32+160,y*32,32,32);
				}
			}
		}

		if(Grid.getEnergy()*56 > gEnergy){
			gEnergy++;
		}else if(Grid.getEnergy()*56 < gEnergy){
			gEnergy--;
		}
		
		if(FallBlock.getY()*32 > FallBlock.gY){
			FallBlock.gY+=3200/Grid.getSpeed();
		}else if(FallBlock.getY()*32 < FallBlock.gY){
			FallBlock.gY-=3200/Grid.getSpeed();
		}
		if(Math.abs(FallBlock.gY-FallBlock.getY()*32) < 3200/Grid.getSpeed()){
			FallBlock.gY = FallBlock.getY()*32;
		}
		
		if(FallBlock.getX()*32 > FallBlock.gX){
			FallBlock.gX+=4;
		}else if(FallBlock.getX()*32 < FallBlock.gX){
			FallBlock.gX-=4;
		}
		
		g.fillRect(520,600-gEnergy,80,gEnergy);

		g.setFont(new Font("Courier",Font.BOLD,32));
		g.drawString("Score",8,32);
		g.drawString(String.valueOf(Grid.getScore()),8,64);
		g.drawString("Record",8,128);
		g.drawString(String.valueOf(Grid.getRecord()),8,160);

		for(int x = 0;x < 4;x++){
			for(int y = 0;y < 4;y++){
				if(FallBlock.isSquare(x,y)){
					if(Grid.getSquare(FallBlock.getX()+x,FallBlock.getY()+y)){
						g.setColor(new Color(255,0,0));
					}else{
						g.setColor(new Color(63,63,63));
					}
					g.fillRect(FallBlock.gX+x*32+160,FallBlock.gY + y*32,32,32);
				}
			}
		}
		
		if(Grid.isPause() || Grid.isGameOver()){
			alpha+=2;
			if(alpha > 200){
				alpha = 200;
			}
		}else{
			alpha-=2;
			if(alpha < 0){
				alpha = 0;
			}
		}
		g.setColor(new Color(0,0,0,alpha));
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		if(Grid.isPause() && alpha == 200){
			g.setColor(new Color(63,63,63));
			g.fillRect(224,224,192,192);
			
			g.setColor(new Color(255,31,31));
			g.fillRect(256,256,32,128);
			g.fillRect(352,256,32,128);
			
		}

		if(Grid.isGameOver() && alpha == 200){
			g.setColor(new Color(255,31,31));
			g.setFont(new Font("Courier",Font.BOLD,100));
			g.drawString("Game Over",50,200);

			g.setFont(new Font("Courier",Font.BOLD,50));
			g.drawString("Score:"+String.valueOf(Grid.getScore()),160,400);
			g.drawString("Record:"+String.valueOf(Grid.getRecord()),160,500);
		}
	}
}
