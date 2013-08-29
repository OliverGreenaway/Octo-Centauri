package state;

public class Tree extends Resource{

	public static final String IMAGE = "Assets/EnvironmentObjects/DarkTree.png";

	public Tree(int x, int y) {
		super(x, y, 1, 1, IMAGE);
		this.amount = 200;
	}

	public ResourceType getResType() {
		return ResourceType.WOOD;
	}

}
