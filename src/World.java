
public class World {
	private int[][] tiles;
	private Player[] players;
	private Item[] items;
	
	public World(){
		tiles = new int[50][50];
		players = new Player[10];
		items = new Item[50];
	}
	
	public void tiles(){
		
	}
	
	public void players(){
		for (int i = 0; i<10; i++){
			while (players[i] != null)
				players[i].resource();
		}
	}
	
	public void items(){
		
	}
}
