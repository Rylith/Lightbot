package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import Game.Engine;

public class Jump extends Order {

	
	public Jump(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public Jump(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}
	@Override
	public int executer() {
		// TODO Auto-generated method stub
		
		//if(color == personne.getColor() || color == Color.WHITE){
			
			if(!engine.ExecJump(personne)){
				try {
					throw new Exception("Impossible de jump");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
				
		//}
		/*else{
				try {
					throw new Exception("Pas possible de JUMP : couleurs differentes");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}*/
		return 0;
				
		}
	
	

}
