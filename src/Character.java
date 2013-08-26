
public class Character {
	private int health;
	private String status;
	private int aPower;
	private int dPower;
	private String type;
	private int cost;

	public Character(String t){
		type = t;
		status = "free";
		if (t == "worker"){
			health = 50;
			aPower = 2;
			dPower = 1;
			cost = 50;
		}
		else if (t == "attacker"){
			health = 100;
			aPower = 10;
			dPower = 5;
			cost = 250;
		}
	}

	public int charCost(){
		return cost;
	}
}
