package lightbot;

import org.jsfml.graphics.Color;

public class RemoveColor extends Order {

	public RemoveColor(Character p, Engine e){	
		
		personne=p;
		engine=e;
		
		color=Color.WHITE;
	}
	
	public RemoveColor(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}
	@Override
	protected void executer() {
		
		// TODO Auto-generated method stub
		if (color == personne.getColor() || color == Color.WHITE){
			if(personne.getColor()!= Color.WHITE)
				personne.setColor(Color.WHITE);		
		}
		
	}

	
	
}
