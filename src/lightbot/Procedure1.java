package lightbot;
import org.jsfml.graphics.Color;

public class Procedure1 extends Order {
	
	
	public Procedure1(Character p){	
		
		personne=p;
		color=Color.WHITE;
	}
	
	public Procedure1(Character p,Color c){
		
		personne=p;
		color=c;
	
		
	}

	@Override
	protected int executer()  {
		
		// TODO Auto-generated method stub
		personne.activeProc(1);	
		return 0;
	}




}
