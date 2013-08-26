import java.util.Queue;
import java.util.ArrayList;

public class Player {
	private ArrayList<Character> characters;
	private int resources;
	private Queue<Character> cQueue;
	private String type;

	public Player(){
		characters = new ArrayList<Character>();
		resources = 500;
		type = "worker";
		newChar(type);
		type = "attacker";
		newChar(type);
	}

	public void newChar(String t){
		Character character = new Character(t);
		resources = resources - character.charCost();
		characters.add(character);
		cQueue.offer(character);
	}

	public int resource(){
		return resources;
	}
}
