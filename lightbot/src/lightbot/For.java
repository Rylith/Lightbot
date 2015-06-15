package lightbot;

import org.jsfml.graphics.Color;

public class For extends Order {
	
	public For(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public For(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}
/**
 * usage : tester si retour > 1 (for possible), si retour = 1 for pas possible 
 */
	@Override
	protected int executer() {
		// TODO Auto-generated method stub
		
		
		//if(personne.getColor() == color || color == Color.WHITE)
		//{
			if(!engine.ExecFor(personne)){
				
				try {
					throw new Exception("Erreur lors de l'execution du for");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		//}
		/*else {
			try {
				throw new Exception("Pas possible d'utiliser le for : couleurs  differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}*/
		return engine.get_nb_for();
	}
}
