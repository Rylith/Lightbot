package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import DrawableObject.Character.Orientation;


public class TurnLeft extends Order {
	


	public TurnLeft (Character p){
		personne=p;
		color= Color.WHITE;
		
	}
	

	public TurnLeft (Character p, Color c){
		
		personne=p;
		color= c;
		
	}
	public int executer(){
		
		//if(color == personne.getColor() || color == Color.WHITE) {
			
			switch(personne.getOrientation())
			{
				case Up:  personne.setOrientation(Orientation.Left);break;
				case Down:  personne.setOrientation(Orientation.Right); break;
				case Left: personne.setOrientation(Orientation.Down); break;
				case Right: personne.setOrientation(Orientation.Up);break;
			
			}
		//return true;
		//}
		/*else{
			try {
				throw new Exception("Pas possible de tourner � gauche : couleurs differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}*/
		return 0;
			
		//return engine.ExecLeft(personne);
	
			
	}
	
	
	
	
}
