package lightbot;

import org.jsfml.graphics.Color;

public class Procedure2 extends Order{
	
	
	public Procedure2(Character p){	
		
		personne=p;
		color=Color.WHITE;
	}
	
	public Procedure2(Character p,Color c){
		
		personne=p;
		color=c;
	}

	@Override
	protected int executer()  {
		
		// TODO Auto-generated method stub
		personne.activeProc(2);	
		return 0;
	}



}