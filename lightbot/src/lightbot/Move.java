package lightbot;

import org.jsfml.graphics.Color;

import lightbot.Order;

public class Move extends Order {
	
	public Move(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public Move(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}

	@Override
	protected void executer() {
		if (color == personne.getColor() || color == Color.WHITE){
			if(!engine.ExecMove(personne)) {
				try {
					throw new Exception("Pas possible d'avancer");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	



}
