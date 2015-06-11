package lightbot;

import org.jsfml.graphics.Color;

import lightbot.Order;

public class Move extends Order {
	
	public Move(Character p){	
		
		personne=p;	
		color=Color.WHITE;
	}
	
	public Move(Character p, Color c){
		
		personne=p;
		color=c;
		
	}

	@Override
	protected void executer() {
		if (color == personne.getColor()){
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
