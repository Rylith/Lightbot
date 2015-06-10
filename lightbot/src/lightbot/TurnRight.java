package lightbot;

import lightbot.Order;
import lightbot.Character.Orientation;

public class TurnRight extends Order {


	public TurnRight(Character p){
		
		p=personne;
	}
	protected void executer(){
		
		switch(personne.getOrientation())
		{
			case Up:  personne.setOrientation(Orientation.Right);
			case Down:  personne.setOrientation(Orientation.Left); 
			case Left: personne.setOrientation(Orientation.Up); 
			case Right: personne.setOrientation(Orientation.Down);
			default:
		
		}
		//return true;
		
		//return engine.ExecLeft(personne);
	
			
	}

}