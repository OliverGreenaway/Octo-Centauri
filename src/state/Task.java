package state;
/**
 *
 * objects to be added to a queue, called by a dude. The dude then runs those methods
 *
 */
public class Task {
	private Tile tile;
	private String task;
	private String type;

	public Task(Tile t, String taskType, String block){
		this.tile = t;
		this.task = taskType;
		this.type = block;
	}

	//constructor for digging
	public Task(Tile t, String taskType) {
		this.tile = t;
		this.type = taskType;
		this.task = taskType;
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
