package vetris;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.scene.paint.Color;

public class ColorManager {
	private static Color color;
	private static Color darkColor;
	private static int colorNumber;

	public static Color getColor() {
		return color;
	}

	public static Color getDarkColor() {
		return darkColor;
	}

	public static void nextColor() {
		colorNumber++;
		if (colorNumber > 2) {
			colorNumber = 0;
		}
		updateColor();
	}

	private static void updateColor() {
		if (colorNumber == 0) {
			color = Color.rgb(255, 63, 63);
			darkColor = Color.rgb(127, 0, 0);
		} else if (colorNumber == 1) {
			color = Color.rgb(63, 255, 63);
			darkColor = Color.rgb(0, 127, 0);
		} else if (colorNumber == 2) {
			color = Color.rgb(63, 63, 255);
			darkColor = Color.rgb(0, 0, 127);
		}
	}

	public static void loadColor() {
		try {
			DataInputStream in = new DataInputStream(
					new FileInputStream(new File(System.getProperty("user.home") + "/.vetris/color")));
			colorNumber = in.readByte();
			in.close();
		} catch (FileNotFoundException e) {
			colorNumber = 0;
			saveColor();
		} catch (IOException e) {
			colorNumber = 0;
			saveColor();
		}
		updateColor();
	}

	public static void saveColor() {
		try {
			File file = new File(System.getProperty("user.home") + "/.vetris");
			file.mkdirs();
			DataOutputStream out = new DataOutputStream(
					new FileOutputStream(new File(System.getProperty("user.home") + "/.vetris/color")));
			out.writeByte(colorNumber);
			out.close();
		} catch (IOException e) {
			return;
		}
	}
}
