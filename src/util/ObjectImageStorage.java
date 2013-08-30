package util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
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
			try {
				i = ImageIO.read(new File(path));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			add(path, i);
		}
		return i;
	}
}
