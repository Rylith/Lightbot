package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import Game.Engine;

public class For extends Order {
	
	public For(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public For(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}

	@Override
	public int executer() {
		// TODO Auto-generated method stub
		
		
		//if(personne.getColor() == color || color == Color.WHITE)
		//{
			if(!engine.ExecFor(personne)){
				
				try {
					throw new Exception("Erreur lors de l'execution du for");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		
		return engine.get_nb_for();
	}
}
