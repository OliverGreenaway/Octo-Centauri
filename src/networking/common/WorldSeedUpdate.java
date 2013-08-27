package networking.common;

public class WorldSeedUpdate extends Update{
	private static final long serialVersionUID = 1L;

	long seed ;

	public WorldSeedUpdate(long seed) {
		this.seed = seed ;
	}
	public long getSeed() {
		return this.seed ;
	}

}
