package state;

public class Plant extends Resource{

	public Plant(int x, int y, int width, int height, String image) {
		super(x, y, width, height, image);
		this.amount = 100;
	}

}
