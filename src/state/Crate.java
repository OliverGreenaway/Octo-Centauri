package state;

public class Crate extends Structure {
	private static final long serialVersionUID = 2931626387167L;

	public static final String IMAGE = "Assets/EnvironmentObjects/Stockpile/StockpileFrontLeft.png";

	public Crate(int x, int y) {
		super(x, y, 1, 1, IMAGE);
	}

	public void dropoff(int storedResources, ResourceType storedResType) {
		World w = getWorld();
		if(storedResType == ResourceType.CRYSTAL){
			w.setCrystalResource(w.getCrystalResource() + storedResources);
			return;
		}else if (storedResType == ResourceType.PLANT) {
			w.setPlantResource(w.getPlantResource() + storedResources);
			return;
		}else if(storedResType == ResourceType.WOOD) {
			w.setWoodResource(w.getWoodResource() + storedResources);
			return;
		}
	}
}
