package lightbot;

import lightbot.Engine;
import lightbot.Order;

public class Move extends Order {
	
	public Move(){
		
	}

	@Override
	protected boolean executer(Engine engine) {
		
		return engine.ExecMove(personne);
		
		
	}
	



}
