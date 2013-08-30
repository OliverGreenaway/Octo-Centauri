package state;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import util.ObjectImageStorage;

public abstract class StructureType {
	private static Map<String, StructureType> types = new HashMap<String, StructureType>();

	public static Map<String, StructureType> getTypes() {return types;}

	private Image image;
	private int width;
	private int height;

	public Image getImage() {return image;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	public abstract Structure create(World world, int x, int y);

	public StructureType(String image, int w, int h) {
		this.image = ObjectImageStorage.getOrAdd(image);
		this.width = w;
		this.height = h;
	}

	static {
		types.put("Stockpile", new StructureType(Crate.IMAGE, 1, 1) {
			@Override
			public Structure create(World world, int x, int y) {
				return new Crate(x, y);
			}
		});
		types.put("Tree", new StructureType("Assets/EnvironmentObjects/DarkTree.png", 1, 1) {
			@Override
			public Structure create(World world, int x, int y) {
				return new Tree(x, y);
			}
		});
		types.put("Stalagmite", new StructureType("Assets/EnvironmentObjects/Stalagmite.png",1,1) {
			@Override
			public Structure create(World world, int x, int y) {

				return new Structure(x,y,1,1,"Assets/EnvironmentObjects/Stalagmite.png");
			}
		});
		types.put("Ramp", new StructureType("Assets/EnvironmentTiles/EastFacePathRamp.png",1,1) {
			@Override
			public Structure create(World world, int x, int y){
				int h = world.getTile(x, y).getHeight();
				Tile t;
				Direction d = null;
				t = world.getTile(x-1, y); if(t != null && t.getHeight() == h+1) d = Direction.LEFT;
				t = world.getTile(x+1, y); if(t != null && t.getHeight() == h+1) d = Direction.RIGHT;
				t = world.getTile(x, y-1); if(t != null && t.getHeight() == h+1) d = Direction.UP;
				t = world.getTile(x, y+1); if(t != null && t.getHeight() == h+1) d = Direction.DOWN;
				if(d == null)
					return new Ramp(x,y,1,1,"PathRamp", Direction.LEFT);
				return new Ramp(x,y, 1, 1, "PathRamp", d);
			}
		});
	}
}
