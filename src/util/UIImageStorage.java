package util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class UIImageStorage {
	private static Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	public static void add(String key) {
		try{
		if (!images.containsKey(key)) {
			BufferedImage img = ImageIO.read(new FileInputStream(
					"Assets/UI/Toggles" + key + ".png"));
			images.put(key, img);
		}
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Image Not Found x02", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public static BufferedImage get(String key){
		return images.get(key);
	}
}
