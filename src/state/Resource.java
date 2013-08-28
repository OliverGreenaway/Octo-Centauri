package state;

public class Resource extends Structure {

	private static final long serialVersionUID = -9044185320248377571L;
	protected int amount;
	private boolean depleted = false;


	public Resource(int x, int y, int width, int height, String image) {
		super(x, y, width, height, image);
	}

	protected void depleted() {
		depleted = true;
	}

	public int getAmount(){
		return amount;
	}

	public void setAmount(int amt){
		amount =  amount + amt;
	}



	public void harvest() {
		if(amount <= 10){
			depleted();
			getWorld().removeStructure(this);
		}
		amount =- 10;
	}

}
