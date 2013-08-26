package networking.common;

import java.io.Serializable;

import game.GameState;

public abstract class Update implements Serializable{
	private static final long serialVersionUID = 1L;

	public abstract void doUpdate(GameState state) ;
}
