package logic;

import state.GameObject;


public class Logic {

	private Map<String, List<String>> menus;

	public Logic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Right-clicking brings up the context menu.
	 * @param object - the item or tile selected
	 * @return - stuff to go in the context menu
	 */
	public Menu rightClick(GameObject object){
		if(object instanceof GroundTile){
			return new Menu("Move here");
		}
		else if (object instanceof Building){
			if(object instanceof Barracks){

			}
			if(object instanceof Mill){

			}
		}
		else{
			System.out.println("Was not a game object.");
		}
	}


	/**
	 * Left-clicking selects an object or character.
	 */
	public void leftClick(GameObject object){
	if(object instanceof GroundTile){

		}
		else if (object instanceof Building){
			if(object instanceof Barracks){

			}
			if(object instanceof Mill){

			}
		}
	}
	else{
		System.out.println("Was not a game object.");
	}
}
