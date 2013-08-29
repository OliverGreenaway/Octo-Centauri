package state;
/**
 *
 * objects to be added to a queue, called by a dude. The dude then runs those methods
 *
 */
public class Task {
	//build tiles
	//build items
	//if empty haul
	private Tile tile;
	private String task;
	private String type;

	public Task(Tile t, String taskType, String blockType){
		this.tile = t;
		this.task = task;
		this.type = blockType;
	}

	public Tile getTile() {
		return tile;
	}

	public String getTask() {
		return task;
	}

	public String getType() {
		return type;
	}


}
