package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tree extends Resource{

	public static int SPREAD = 120;
	public static final int MAX = 90;
	public static final int HALF = 60;
	public static final int MIN = 30;
	public static final int HARVEST = 4;
	public static int REGEN_TIME = 10;



	private String lastImg;
	private boolean shouldOctoMine;

	@Override
	public void setImage(String image) {
		if(image != lastImg)
			super.setImage(image);
		lastImg = image;
	}



	public Tree(int x, int y) {

		super(x, y, 1, 1, "Assets/EnvironmentObjects/DarkTree.png");
		this.amount = HALF;

	}

	public int harvest() {
		amount = amount - HARVEST;
		return HARVEST;

	}

	public ResourceType getResType() {
		return ResourceType.WOOD;
	}

	int updateCount = 0;
	@Override
	public void update() {
		if(++updateCount >= REGEN_TIME) {
			updateCount = 0;
			amount++;
		}

		if(amount < 0) {
			getWorld().removeStructure(this);
		} else if(amount > SPREAD) {
			Set<Tile> neighbours = new HashSet<Tile>();
			Tile temp;

			temp = getWorld().getTile(getX()+-1, getY());
			neighbours.add(temp);

			temp = getWorld().getTile(getX()+1, getY());
			neighbours.add(temp);

			temp = (getWorld().getTile(getX(), getY()+1));

			neighbours.add(temp);

			temp = (getWorld().getTile(getX(), getY()-1));
			neighbours.add(temp);

			neighbours.remove(null);

			if(neighbours.size() > 0) {
				List<Tile> list = new ArrayList<Tile>(neighbours);
				Collections.shuffle(list);
				Tile t = list.get(0);
				if(t.getStructure() != null){ return; }
				if(t.getHeight() != getWorld().getTile(getX(),getY()).getHeight()){ return; }
				if(t.getImageName().equals("Water")){ return; }
				getWorld().addStructure(new Tree(t.getX(), t.getY()));
			}

			amount = HALF;
		}

		if(amount >= MAX)
			shouldOctoMine = true;
		if(amount <= MIN/2)
			shouldOctoMine = false;
	}

	public boolean shouldOctoMine() {
		return shouldOctoMine;
	}

}
