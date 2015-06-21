package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import DrawableObject.Character.Orientation;

public class TurnRight extends Order {


	public TurnRight(Character p){	
		
		personne=p;
		
		color=Color.WHITE;
	}
	
	public TurnRight(Character p, Color c){
		
		personne=p;
		color=c;
		
	}
	public int executer(){
		
		//if(color == personne.getColor() || color == Color.WHITE){
			
			switch(personne.getOrientation())
			{
				case Up:  
					personne.setOrientation(Orientation.Right);
					break;
				case Down:  
					personne.setOrientation(Orientation.Left); 
					break;
				case Left: 
					personne.setOrientation(Orientation.Up); 
					break;
				case Right: 
					personne.setOrientation(Orientation.Down);
					break;
			
			}
		//}
		/*else{
			try {
				throw new Exception("Pas possible de tourner � droite : couleurs differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}/*/
		return 0;
			
			
		//return true;
		
		//return engine.ExecLeft(personne);
	
			
	}

}