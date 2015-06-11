package lightbot;

import org.jsfml.graphics.Color;

public class getColor extends Order {

	public getColor(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public getColor(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}
	
	@Override
	protected void executer() {
		
		// TODO Auto-generated method stub
		if (color == personne.getColor() || color == Color.WHITE){
			if (!engine.ExecGetColor(personne)){
				try {
					throw new Exception("Pas possible d'executer l'ordre : Aucune couleur sur la case");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
			
		else{
			
			try {
				throw new Exception("Impossible de changer de couleur : couleurs action/robot differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
		
	}
	
	
}
