package state;

public abstract class Resource extends Structure {

	private static final long serialVersionUID = -9044185320248377571L;
	protected int amount;


	public Resource(int x, int y, int width, int height, String image) {
		super(x, y, width, height, image);
	}

	public int getAmount(){
		return amount;
	}

	public void setAmount(int amt){
		amount =  amount + amt;
	}

	public int harvest() {
		if(amount <= 10){

			getWorld().removeStructure(this);
		}
		amount =- 10;
		return 10;
	}

	public abstract ResourceType getResType();

}
