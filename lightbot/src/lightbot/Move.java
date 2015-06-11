package lightbot;

import lightbot.Order;

public class Move extends Order {
	
	public Move(Character p){	
		
		personne=p;	
	}

	@Override
	protected void executer() {
		
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
