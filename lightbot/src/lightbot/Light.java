package lightbot;

import org.jsfml.graphics.Color;

public class Light extends Order {

	public Light (Character p){
		
		personne = p;
		color= Color.WHITE;
		
	}
	
	public Light(Character p, Color c){
		
		personne=p;
		color= c;
	}

	
	@Override
	protected void executer() {
		// TODO Auto-generated method stub
		
		if(personne.getColor() == color)
		{
			if(!engine.ExecLight(personne)){
				try {
					throw new Exception("Erreur lors de l'allumage de la case");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		}
		else {
			try {
				throw new Exception("Pas possible d'allumer : couleurs  differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
			
	
	
	}
}
