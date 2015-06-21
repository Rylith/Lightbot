package lightbot;

import org.jsfml.graphics.Color;

import lightbot.Engine;


public abstract class Order  {
	
	/** --------------- ATTRIBUTES --------------- */
	
	protected Character personne;
	protected Engine engine;
	protected Color color;
	
	/** ------------------METHODS------------------*/

	protected abstract int executer();
	
	public Color getColor() {
		return color;
	}
}
