package vetris;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Background extends Group {

	private static final Text score = new Text("Score");
	private static final Text record = new Text("Record");

	private static final Rectangle rectangles[] = new Rectangle[3];

	Background() {
		this.getChildren().add(score);
		this.getChildren().add(record);

		rectangles[0] = new Rectangle();
		rectangles[0].setTranslateY(0);
		rectangles[0].setFill(Color.rgb(127, 127, 127));
		this.getChildren().add(rectangles[0]);

		rectangles[1] = new Rectangle();
		rectangles[1].setFill(Color.rgb(127, 127, 127));
		this.getChildren().add(rectangles[1]);

		rectangles[2] = new Rectangle();
		rectangles[2].setFill(Color.rgb(127, 127, 127));
		this.getChildren().add(rectangles[2]);

		updateSize();
		updateColor();
	}

	public void updateSize() {
		score.setTranslateX(Grid.getSquareSize());
		score.setTranslateY(Grid.getSquareSize());
		score.setFont(Font.font("Noto Mono", Grid.getSquareSize()));

		record.setTranslateX(Grid.getSquareSize());
		record.setTranslateY(Grid.getSquareSize() * 4);
		record.setFont(Font.font("Noto Mono", Grid.getSquareSize()));

		rectangles[0].setTranslateX(Grid.getSquareSize() * 6 + Grid.getTranslate());
		rectangles[0].setWidth(Grid.getSquareSize() * 12);
		rectangles[0].setHeight(Grid.getSquareSize() * 20);

		rectangles[1].setTranslateX(Grid.getSquareSize() * 19 + Grid.getTranslate() * 2);
		rectangles[1].setTranslateY(Grid.getSquareSize());
		rectangles[1].setWidth(Grid.getSquareSize() * 4);
		rectangles[1].setHeight(Grid.getSquareSize() * 18);

		rectangles[2].setTranslateX(Grid.getSquareSize());
		rectangles[2].setTranslateY(Grid.getSquareSize() * 15);
		rectangles[2].setWidth(Grid.getSquareSize() * 4);
		rectangles[2].setHeight(Grid.getSquareSize() * 4);
	}

	public void updateColor() {
		score.setFill(ColorManager.getColor());
		record.setFill(ColorManager.getColor());
	}
}
