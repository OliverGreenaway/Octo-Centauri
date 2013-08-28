package state;

public class Resource extends Structure {

	public Resource(int x, int y, int width, int height, String image) {
		super(x, y, width, height, image);
		// TODO Auto-generated constructor stub
	}

	public void harvest() {
		getWorld().removeStructure(this);
	}

}
