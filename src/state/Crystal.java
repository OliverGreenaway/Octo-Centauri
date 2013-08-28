package state;

public class Crystal extends Resource{

	public Crystal(int x, int y, int width, int height,String image) {
		super(x, y, width, height, image);
		this.amount = 500;
	}

	public int harvest() {
		if(amount < 10){
			depleted();
			setImage("Assets/EnvironmentObjects/ResourcesDepleted.png");
			return 0;//Error state where attempting to mine a depleted resoruce
		}
		amount = amount - 10;
		return 10;
	}

}
