package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import Game.Engine;

public class DoubleJump extends Order{

	

	public DoubleJump(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public DoubleJump(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}
	
	@Override
	public int executer() {
		// TODO Auto-generated method stub
		if(personne.getColor() == color || color == Color.WHITE){
			
		
			//if(!engine.ExecDoubleJump(personne)){
				try {
					throw new Exception("Pas possible de double jump");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		//}
		
		/*else{
			try {
				throw new Exception("Pas possible de double JUMP : couleurs differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}*/
		return 0;
			
	}
	
}
