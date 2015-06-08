package lightbot;

import lightbot.Engine;
import lightbot.Personnage;

public abstract class Order  {
	
	//public int pas; // pas de deplacement
	
	protected Personnage personne;
	//protected Engine engine;
	

	protected abstract boolean executer(Engine engine);

}
