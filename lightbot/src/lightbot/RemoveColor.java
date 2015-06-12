package lightbot;

import org.jsfml.graphics.Color;

public class RemoveColor extends Order {

	public RemoveColor(Character p){	
		
		personne=p;
		color=Color.WHITE;
	}
	
	public RemoveColor(Character p, Color c){
		
		personne=p;
		color=c;
		
	}
	@Override
	protected int executer() {
		
		// TODO Auto-generated method stub
		//if (color == personne.getColor() || color == Color.WHITE){
			if(personne.getColor()!= Color.WHITE)
				personne.setColor(Color.WHITE);		
		//}
		return 0;
		
	}

	
	
}
