package lightbot;
import org.jsfml.system.Vector2f;


public class Engine {
	
	//private Personnage personne;
	
	private static final int encadrement = 5;
	private static final int l = 12;
	
	/** t
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchangée
	 * @return true si pas d'obstacles, false sinon
	 * @obstacle1: si les cases de depart et d'arrivee ont des hauteurs differentes
	 */
	private boolean isAbleToMove(Character clone){
		
		//il avance pas si la hauteur de la case dest est differente de celle de depart
		
		if(clone.getHeight() == getCurrentCase(clone).getHeight())
			
			return true;
		
		else 
			return false;	
		
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
	
	
	private void DeplacerClone(Character p, Character clone){
		
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
		
	}
	
	private void MoveCloneToThePeek(Character clone) {
		// TODO Auto-generated method stub
		
		while(getCurrentCase(clone).nextCase()!= null) //faire une fonction qui donne la prochaine case empilee sur une coordonnee x et null sinon
		{
		clone.setPosition(new Vector2f(clone.getPosition().x,(clone.getPosition().y - l)));
		
		} 
		
	
	}
	
	/**
	 * fait avancer le personnage d'une case en fonction de la direction
	 * @param p: le personnage a faire avancer
	 * @return un booleen : true si le move s'est bien passe, false sinon
	 */

	public boolean ExecMove(Character p){
		
		Character clone = null;
		DeplacerClone(p,clone);
		
		if(isAbleToMove(clone)){
			
			p=clone;
			return true;
		}
		else 
			return false;
		
	}
	
	
		
	public boolean ExecJump(Character p){
			
		Character clone = null;
		
		DeplacerClone(p,clone);
		
		if(isAbleToMove(clone)){
			
			return false;
		}
		else
		{
			//JUMP vers le haut
			//clone.setHeight(clone.getHeight()+ 1); //maj de l'attribut height
			
			int h= getCurrentCase(p).getHeight();
			
			MoveCloneToThePeek(clone); //on place le clone a la case au sommet
			
			//clone.setPosition(new Vector2f( clone.getPosition().x,(clone.getPosition().y - l)));//on place le clone a la case au sommet
			
			//JUMP VERS LE HAUT
			
			if(h < getCurrentCase(clone).getHeight()){
			
				if(getCurrentCase(clone).getHeight() - h != 1)
					
					return false;
				
				else{
					p=clone;
					return true;
				}
			}
			//JUMP VERS LE BAS 
			else 
				
			{
				if(h - getCurrentCase(clone).getHeight()!= 1)
					
					return false;
				
				else {
					p=clone;
					return true;
				}
			
			}
		}
			
	}
	
	
	
	
}
