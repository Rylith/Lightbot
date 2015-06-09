package lightbot;

import lightbot.Engine;


public abstract class Order  {
	
	/** --------------- ATTRIBUTES --------------- */
	
	protected Character personne;
	
	/** ------------------METHODS------------------*/

	protected abstract boolean executer(Engine engine);

}
