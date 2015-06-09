package lightbot;
import org.jsfml.system.Vector2f;


public class Engine {
	
	//private Personnage personne;
	
	private static final int encadrement;
	
	/** t
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchangée
	 * @return true si pas d'obstacles, false sinon
	 * @obstacle1: si les cases de depart et d'arrivee ont des hauteurs differentes
	 */
	private boolean isAbleToMove(Character clone){
		
		//il avance pas si la hauteur de la case dest est differente de celle de depart
		
		if(clone.getHeight() != getCurrentCase(clone).getHeight())
			
			return true;
		
		else 
			return false;	
		
	}
	/** t
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchangée
	 * @return true si pas d'obstacles au jump, false sinon
	 * @obstacle1: si les cases de depart et d'arrivee sont de meme hauteur
	 * @obstacle2 : 
	 */
	
	private boolean isAbleToJump(Character clone){
		
		
	}
	
	private boolean encadrementOK(float x, float y, Character p){
		
		return  (
				
				p.getPosition().x >= x- encadrement &&
				p.getPosition().x <= x+ encadrement &&
				p.getPosition().y >= y- encadrement &&
				p.getPosition().y <= y+ encadrement );
			
		
	}
	/**
	 * 
	 * @param p: le personnage sur la case
	 * @return la case ou est le personnage
	 */
	private Case getCurrentCase(Character p) {
		// TODO Auto-generated method stub
		float ideal_x, ideal_y;
		
		//for i allant de 1 a la fin de toutes les cases
		
			if(t[i].isContain(p.getPosition()))
				
				if(encadrementOK(ideal_x, ideal_y,p))
					return t[i];
				else
					return null;
			else
				return null;
		
	}
	
	/**
	 * fait avancer le personnage d'une case en fonction de la direction
	 * @param p: le personnage a faire avancer
	 * @return un booleen : true si le move s'est bien passe, false sinon
	 */

	public boolean ExecMove(Character p){
		
		Character clone;
		Vector2f temp;
		
		clone=p;
		float a,b,c,d;
		
		a = p.getPosition().x - getCurrentCase(p).getSprite().getTexture().getSize().x; 
		b =  p.getPosition().x + getCurrentCase(p).getSprite().getTexture().getSize().x;
		c =  p.getPosition().y - getCurrentCase(p).getSprite().getTexture().getSize().y;
		d = p.getPosition().y + getCurrentCase(p).getSprite().getTexture().getSize().y;
		
		switch(clone.getOrientation()){
		
		case Up: temp = new Vector2f(a,c); clone.setPosition(temp); break;
		case Down: temp = new Vector2f(b,d); clone.setPosition(temp); break;
		case Right: temp = new Vector2f(b,c); clone.setPosition(temp); break;
		case Left: temp = new Vector2f(a,c); clone.setPosition(temp); break;
		default: 
		
		}
		
		if(isAbleToMove(clone)){
			
			p=clone;
			return true;
		}
		else 
			return false;
		
	}
		

	
	/*
	public boolean ExecLeft( Character p){

		p.setDirection(p.getDirectionFromLeft(p.getOrientation()));
		
			return true;
			
		// TODO Auto-generated method stub	
		
	}
	public boolean ExecRight( Character p){

		p.setDirection(p.getDirectionFromRight(p.getDirection()));
		
			return true;
			
		// TODO Auto-generated method stub	
		
	}

	public Map getMonde() {
		return monde;
	}

	public void setMonde(Map monde) {
		this.monde = monde;
		*/
	}


}
