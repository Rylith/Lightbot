package lightbot;

import lightbot.Character.Orientation;
import lightbot.Engine;
import lightbot.Order;


public class TurnLeft extends Order {
	


	protected boolean executer(Engine engine){
		
		switch(personne.getOrientation())
		{
			case Up:  personne.setOrientation(Orientation.Left);
			case Down:  personne.setOrientation(Orientation.Right); 
			case Left: personne.setOrientation(Orientation.Down); 
			case Right: personne.setOrientation(Orientation.Up);
			default:
		
		}
		return true;
		
		//return engine.ExecLeft(personne);
	
			
	}
	
	
	
	
}
