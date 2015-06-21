package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import Game.Engine;


public abstract class Order  {
	
	/** --------------- ATTRIBUTES --------------- */
	
	protected Character personne;
	protected Engine engine;
	protected Color color;
	
	/** ------------------METHODS------------------*/

	public abstract int executer();
	
	public Color getColor() {
		return color;
	}
}
