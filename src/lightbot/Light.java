package lightbot;

import org.jsfml.graphics.Color;

public class Light extends Order {

	public Light(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public Light(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}

	
	@Override
	protected int executer() {
		// TODO Auto-generated method stub
		
	//	if(personne.getColor() == color || color == Color.WHITE)
		//{
			if(!engine.ExecLight(personne)){
				try {
					throw new Exception("Erreur lors de l'allumage de la case");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		//}
		/*else {
			try {
				throw new Exception("Pas possible d'allumer : couleurs  differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}*/
		return 0;
			
	
	
	}
}
