package networking.common;
/**
 * This update can be sent to the peer, to set the world seed in the peer.
 * Only holds a single seed.
 * @author muruphenr
 *
 */
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
