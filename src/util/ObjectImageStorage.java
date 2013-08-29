package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ObjectImageStorage {
	private static transient Map<String, Image> images = new HashMap<String, Image>();

	public static void add(String key, Image image) {
		if (!images.containsKey(key)) {
			images.put(key, image);
		}
	}

	public static Image get(String key){
		return images.get(key);
	}

	public static Image getOrAdd(String path) {
		Image i = get(path);
		if(i == null) {
			i = new ImageIcon(path).getImage();
			add(path, i);
		}
		return i;
	}
}
