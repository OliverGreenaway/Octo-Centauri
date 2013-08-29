package util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TileImageStorage {
	private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	public void add(String key) {
		try{
		if (!images.containsKey(key)) {
			BufferedImage img = ImageIO.read(new FileInputStream(
					"Assets/EnvironmentTiles/" + key + ".png"));
			images.put(key, img);
		}
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Image Not Found x02", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public BufferedImage get(String key){
		return images.get(key);
	}
}
