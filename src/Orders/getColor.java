package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import Game.Engine;

public class getColor extends Order {

	public getColor(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public getColor(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}
	
	@Override
	public int executer() {
		
		// TODO Auto-generated method stub
		
		//if (color == personne.getColor() || color == Color.WHITE){
			if (!engine.ExecGetColor(personne)){
				try {
					throw new Exception("Pas possible d'executer l'ordre : Aucune couleur sur la case");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		//}
			
		/*else{
			
			try {
				throw new Exception("Impossible de changer de couleur : couleurs action/robot differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return 0;
					
		
	}
	
	
}
