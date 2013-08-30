package state;

import java.util.Random;



public class OctoSpawnBuilding extends Structure {

	private final int SPAWN_DELAY = 50;
	private Random random = new Random();

	public OctoSpawnBuilding(int x, int y) {
		super(x, y, 1, 1, "Assets/EnvironmentTiles/SpawnPoints/SpawnOctoWater1.png");
	}

	int delay;
	@Override
	public void update() {
		Tile t = getWorld().getTile(getX(), getY());
		if(getWorld().isDudeSpawningEnabled() && t.getDude() == null) {
			if(delay <= 0) {
				if(new Random().nextBoolean())
					getWorld().addDude(new Octodude(getWorld(),getX()-1+random.nextInt(2) ,getY()-1+random.nextInt(2)));

				delay = SPAWN_DELAY;
			} else {
				delay--;
			}
		}
	}

	@Override
	public boolean isAttackable(){
		return false;
	}
}
