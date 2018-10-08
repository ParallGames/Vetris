package vetris;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Save {
	public static final String PATH = System.getProperty("user.home") + "/.vetris";

	public static int loadScore() {
		int score;
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(new File(PATH + "/score")));
			score = in.readInt();
			in.close();
		} catch (FileNotFoundException e) {
			saveScore(0);
			return 0;
		} catch (IOException e) {
			return 0;
		}
		return score;
	}

	public static void saveScore(int score) {
		try {
			File file = new File(PATH);
			file.mkdirs();
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(PATH + "/score")));
			out.writeInt(score);
			out.close();
		} catch (IOException e) {
			return;
		}
	}
}
