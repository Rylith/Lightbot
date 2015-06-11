package lightbot;

import org.jsfml.graphics.Color;

import lightbot.Order;
import lightbot.Character.Orientation;

public class TurnRight extends Order {


	public TurnRight(Character p){
		
		p=personne;
		color= Color.WHITE;
	}
	
	public TurnRight(Character p, Color c){
		
		p=personne;
		color= c;
	}
	protected void executer(){
		
		if(color == personne.getColor()){
			
			switch(personne.getOrientation())
			{
				case Up:  personne.setOrientation(Orientation.Right);
				case Down:  personne.setOrientation(Orientation.Left); 
				case Left: personne.setOrientation(Orientation.Up); 
				case Right: personne.setOrientation(Orientation.Down);
				default:
			
			}
		}
		else{
			try {
				throw new Exception("Pas possible de tourner à droite : couleurs differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
			
			
		//return true;
		
		//return engine.ExecLeft(personne);
	
			
	}

}