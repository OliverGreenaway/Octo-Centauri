package state;
import java.util.Queue;
import java.util.ArrayList;

public class Player {
	private ArrayList<Character> characters;
	private int resources;
	private Queue<Character> cQueue;
	private String type;
	private String pName;
	
	public Player(String name){
		pName = name;
	}

	public Player(){
		characters = new ArrayList<Character>();
		resources = 500;
		type = "worker";
		newChar(type, 20, 25);
		type = "attacker";
		newChar(type, 13, 18);
	}

	public void newChar(String t, int x, int y){
		Character character = new Character(t, x, y);
		resources = resources - character.charCost();
		characters.add(character);
		cQueue.offer(character);
	}

	public int resource(){
		return resources;
	}
	
	public String name(){
		return pName;
	}
}
