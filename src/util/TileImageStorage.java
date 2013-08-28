package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TileImageStorage {
	private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	private Map<String, Color> colors = new HashMap<String, Color>();

	public void add(String key) {
		try {
			if (!images.containsKey(key)) {
				BufferedImage img = ImageIO.read(new FileInputStream(
						"Assets/EnvironmentTiles/" + key + ".png"));
				images.put(key, img);

				int r = 0;
				int g = 0;
				int b = 0;
				int count = 0;
				for (int i = 0; i < img.getWidth(); i++) {
					for (int j = 0; j < img.getHeight(); j++) {
						Color temp = new Color(img.getRGB(i, j), true);
						if (temp.getAlpha() > 0) {

							r += temp.getRed();
							b += temp.getBlue();
							g += temp.getGreen();
							count++;
						}
					}
				}
				Color storedColor = new Color((int) (r / count),
						(int) (g / count), (int) (b / count));
				colors.put(key, storedColor);

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Image Not Found x02",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public BufferedImage get(String key) {
		return images.get(key);
	}

	public Color getColor(String key) {
		return colors.get(key);
	}
}
