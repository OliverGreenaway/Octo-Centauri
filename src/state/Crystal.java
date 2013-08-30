package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Crystal extends Resource{

	public static final int SPREAD = 120;
	public static final int SPAWN = 100;
	public static final int MAX = 90;
	public static final int HALF = 60;
	public static final int MIN = 30;
	public static final int HARVEST = 4;
	public static final int REGEN_TIME = 10;

	private String lastImg;
	private boolean shouldOctoMine;

	@Override
	public void setImage(String image) {
		if(image != lastImg)
			super.setImage(image);
		lastImg = image;
	}

	private void updateImage() {
		if(amount < MIN) {
			setImage("Assets/EnvironmentObjects/ResourcesDepleted.png");
		} else if(amount < HALF) {
			setImage("Assets/EnvironmentObjects/ResourcesHalfDepleted.png");
		} else {
			setImage("Assets/EnvironmentObjects/Resources.png");
		}
	}

	public Crystal(int x, int y) {
		super(x, y, 1, 1, "Assets/EnvironmentObjects/Resources.png");
		this.amount = HALF;
		updateImage();
	}

	public int harvest() {
		amount = amount - HARVEST;
		updateImage();
		return HARVEST;
	}

	public ResourceType getResType() {
		return ResourceType.CRYSTAL;
	}

	int updateCount = 0;
	@Override
	public void update() {
		if(++updateCount >= REGEN_TIME) {
			updateCount = 0;
			amount++;
			updateImage();
		}

		if(amount < 0) {
			getWorld().removeStructure(this);
		} else if(amount > SPREAD) {
			Set<Tile> neighbours = new HashSet<Tile>();
			neighbours.add(getWorld().getTile(getX()-1, getY()));
			neighbours.add(getWorld().getTile(getX()+1, getY()));
			neighbours.add(getWorld().getTile(getX(), getY()+1));
			neighbours.add(getWorld().getTile(getX(), getY()-1));
			neighbours.remove(null);

			if(neighbours.size() > 0) {
				List<Tile> list = new ArrayList<Tile>(neighbours);
				Collections.shuffle(list);
				Tile t = list.get(0);
				if(t.getStructure() == null && t.getHeight() == getWorld().getTile(getX(),getY()).getHeight()) {
					getWorld().addStructure(new Crystal(t.getX(), t.getY()));
					amount = HALF;
				}
			}

			updateImage();
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
