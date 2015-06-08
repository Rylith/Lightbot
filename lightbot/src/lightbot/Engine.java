package lightbot;

import lightbot.Map;
import lightbot.Personnage;

public class Engine {
	
	//private Personnage personne;
	
	private Map monde;
	
	public Engine(Map m){
			
		this.setMonde(m);
	}

	
	
	public boolean ExecMove(Personnage p){
		
		Personnage clone;
		clone=p;
		float a,b,c,d;
		a = p.getPosition().x - getCurrentCase(p).sprite.getSpriteSize.x;
		
		switch(clone.getDirection()){
		
		case Up:  clone.setPosition()
		}
		
	}
	
	public boolean ExecLeft( Personnage p){

		p.setDirection(p.getDirectionFromLeft(p.getDirection()));
		
			return true;
			
		// TODO Auto-generated method stub	
		
	}
	public boolean ExecRight( Personnage p){

		p.setDirection(p.getDirectionFromRight(p.getDirection()));
		
			return true;
			
		// TODO Auto-generated method stub	
		
	}

	public Map getMonde() {
		return monde;
	}

	public void setMonde(Map monde) {
		this.monde = monde;
	}


}
