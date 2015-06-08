package lightbot;

import lightbot.Engine;
import lightbot.Order;

public class TurnRight extends Order {


protected boolean executer(Engine engine){
	
	return engine.ExecRight(personne);
		
}

}