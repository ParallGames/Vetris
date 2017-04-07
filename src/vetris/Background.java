package vetris;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Background extends Group{
	Background(){
		Rectangle rectangle = new Rectangle(160,0,320,640);
		rectangle.setFill(Color.rgb(127,127,127));
		this.getChildren().add(rectangle);
		
		rectangle = new Rectangle(520,40,80,560);
		rectangle.setFill(Color.rgb(127,127,127));
		this.getChildren().add(rectangle);
		
		rectangle = new Rectangle(40,520,80,80);
		rectangle.setFill(Color.rgb(127,127,127));
		this.getChildren().add(rectangle);
		
		Text text = new Text(8,32,"Score");
		text.setFont(new Font("Noto Mono",32));
		text.setFill(Color.rgb(255,63,63));
		this.getChildren().add(text);
		
		text = new Text(8,128,"Record");
		text.setFont(new Font("Noto Mono",32));
		text.setFill(Color.rgb(255,63,63));
		this.getChildren().add(text);
	}
}