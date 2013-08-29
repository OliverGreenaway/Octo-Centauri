package state;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import util.ObjectImageStorage;

public class StructureType {
	private static Map<String, StructureType> types = new HashMap<String, StructureType>();

	public static Map<String, StructureType> getTypes() {return types;}

	private Image image;
	private int width;
	private int height;

	public Image getImage() {return image;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	public StructureType(Image image, int w, int h) {
		this.image = image;
		this.width = w;
		this.height = h;
	}

	static {
		types.put("Stockpile", new StructureType(ObjectImageStorage.getOrAdd(Crate.IMAGE), 1, 1));
		types.put("Tree", new StructureType(ObjectImageStorage.getOrAdd(Tree.IMAGE), 1, 1));
	}
}
