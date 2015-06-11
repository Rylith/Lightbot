package lightbot;

import org.jsfml.graphics.Color;

public class getColor extends Order {

	public getColor(Character p, Color c){
		
		personne=p;
		color=c;
		
	}

//	compare la couleur de l'ordre a la couleur du personnage, si mm couleur, on execute l'ordre sinon lever excepetion
	
	@Override
	protected void executer() {
		
		// TODO Auto-generated method stub
		if (color == personne.getColor()){
			
			 //execute the order ?? which order?
			
		}
			
		else{
			
			try {
				throw new Exception("Pas possible d'executer l'ordre : couleurs differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
		
	}
	
	
}
