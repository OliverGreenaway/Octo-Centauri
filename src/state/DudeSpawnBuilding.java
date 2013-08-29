package state;

public class DudeSpawnBuilding extends Structure {
	public DudeSpawnBuilding(int x, int y) {
		super(x, y, 1, 1, "Assets/EnvironmentObjects/Stalagmite.png");
	}

	@Override
	public void update() {
		Tile t = getWorld().getTile(getX(), getY());
		if(getWorld().isDudeSpawningEnabled() && t.getDude() == null)
			getWorld().addDude(new Dude(getWorld(), getX(), getY(), 1, 1, "Assets/Characters/Man.png"));
	}
}
