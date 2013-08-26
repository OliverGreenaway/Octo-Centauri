import java.awt.Image;
import java.util.ArrayList;

public class World {
	private Tile tiles;
	private Tile[][] worldTile;
	private ArrayList<Player> players;
	private Item[] items;
	
	public World(){
		worldTile = new Tile[50][50];
		tiles = new Tile();
		players = new ArrayList<Player>();
		items = new Item[50];
		Player player1 = new Player("Player1");
		players.add(player1);
		Player player2 = new Player("Player2");
		players.add(player2);
	}
	
	public Image tileImage(){
		return tiles.getImage();
	}
	
	public void players(){
		
	}
	
	public void items(){
		
	}
}
