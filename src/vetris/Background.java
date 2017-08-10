package vetris;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Background extends Group {

	private Group updateGroup = new Group();

	Background() {
		updateSize();
	}

	public void update() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				updateGroup.getChildren().clear();

				Text text = new Text(Grid.getSquareSize(), Grid.getSquareSize(), "Score");
				text.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				text.setFill(ColorManager.getColor());
				updateGroup.getChildren().add(text);

				text = new Text(Grid.getSquareSize(), Grid.getSquareSize() * 4, "Record");
				text.setFont(new Font("Noto Mono", Grid.getSquareSize()));
				text.setFill(ColorManager.getColor());
				updateGroup.getChildren().add(text);
			}
		});
	}

	public void updateSize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Background.this.getChildren().clear();
				Rectangle rectangle = new Rectangle(Grid.getSquareSize() * 6 + Grid.getTranslate(), 0,
						Grid.getSquareSize() * 10, Grid.getSquareSize() * 20);
				rectangle.setFill(Color.rgb(127, 127, 127));
				Background.this.getChildren().add(rectangle);

				rectangle = new Rectangle(Grid.getSquareSize() * 17 + Grid.getTranslate() * 2, Grid.getSquareSize(),
						Grid.getSquareSize() * 4, Grid.getSquareSize() * 18);
				rectangle.setFill(Color.rgb(127, 127, 127));
				Background.this.getChildren().add(rectangle);

				rectangle = new Rectangle(Grid.getSquareSize(), Grid.getSquareSize() * 15, Grid.getSquareSize() * 4,
						Grid.getSquareSize() * 4);
				rectangle.setFill(Color.rgb(127, 127, 127));
				Background.this.getChildren().add(rectangle);

				Background.this.getChildren().add(updateGroup);

				Background.this.update();
			}
		});
	}
}
