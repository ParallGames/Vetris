package vetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import vetris.FallBlock;
import vetris.Grid;

public class Panel extends JPanel{

	Panel(){
		this.setPreferredSize(new Dimension(320,640));
	}

	public void paintComponent(Graphics g){
		g.setColor(new Color(127,127,127));
		g.fillRect(0,0,this.getWidth(),this.getHeight());

		g.setColor(new Color(255,63,63));
		for(byte x = 0;x < 10;x++){
			for(byte y = 0;y < 20;y++){
				if(Grid.getSquare(x,y)){
					g.fillRect(x*32,y*32,32,32);
				}
			}
		}

		for(int x = 0;x < 4;x++){
			for(int y = 0;y < 4;y++){
				if(FallBlock.isSquare(x,y)){
					g.fillRect(FallBlock.getShapeX(x)*32,FallBlock.getShapeY(y)*32,32,32);
				}
			}
		}
		
	}
}
