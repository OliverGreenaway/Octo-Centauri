package state;

public class Crystal extends Resource{

	public Crystal(int x, int y, int width, int height,String image) {
		super(x, y, width, height, image);
		this.amount = 50;
	}

	public void harvest() {
		if(amount <= 10){
			depleted();
			setImage("Assets/EnvironmentObjects/ResourcesDepleted.png");
		}
		amount = amount - 10;
	}

	public ResourceType getResType() {
		if(amount < 10)
			return null;
		else
			return ResourceType.CRYSTAL;
	}

}
