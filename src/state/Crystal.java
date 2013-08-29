package state;

public class Crystal extends Resource{

	public Crystal(int x, int y, int width, int height,String image) {
		super(x, y, width, height, image);
		this.amount = 50;
	}

	public int harvest() {
		if(amount < 10){
			return 0;
		}
		amount = amount - 10;
		if(amount < 10){
			setImage("Assets/EnvironmentObjects/ResourcesDepleted.png");
		}
		return 10;
	}

	public ResourceType getResType() {
		if(amount < 10)
			return null;
		else
			return ResourceType.CRYSTAL;
	}

	int updateCount = 0;
	@Override
	public void update() {
		if(++updateCount >= 30) {
			updateCount = 0;
			amount++;
			if(amount >= 10) {
				setImage("Assets/EnvironmentObjects/Resources.png");
			}
		}
	}

}
