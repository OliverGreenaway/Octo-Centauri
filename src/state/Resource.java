package state;

public class Resource extends Structure {

	private static final long serialVersionUID = -9044185320248377571L;
	private int amount;
	private boolean depleted = false;


	public Resource(int x, int y, int width, int height, String image) {
		super(x, y, width, height, image);
	}

	public void setAmount(int idx){
		if(amount <= 10){
			depleted();
		}
		amount =- idx;
	}

	private void depleted() {
		depleted = true;
	}

	public int getAmount(){
		return amount;
	}


}
