package lightbot;

import lightbot.Order;

public class Move extends Order {
	
	public Move(){
		
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
