package lightbot;

import lightbot.Engine;


public abstract class Order  {
	
	/** --------------- ATTRIBUTES --------------- */
	
	protected Character personne;
	protected Engine engine;
	//protected Color color;
	
	/** ------------------METHODS------------------*/

	protected abstract void executer();
	
	
}
