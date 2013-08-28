package state;

public class Crate extends Structure {

	public Crate(int x, int y) {
		super(x, y, 1, 1, "Assets/EnvironmentObjects/Stalagmite.png");
	}

	public void dropoff(int storedResources, int type) {
		if(type == 0){
			getWorld().setCrystalResource(storedResources);
		}else if(type == 1){
			getWorld().setPlantResource(storedResources);
		}else if(type == 2){
			getWorld().setWoodResource(storedResources);
		}else{
			System.out.println("Type wrong");
		}
	}
}
