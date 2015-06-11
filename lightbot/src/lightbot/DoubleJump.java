package lightbot;

import org.jsfml.graphics.Color;

public class DoubleJump extends Order{

	

	public DoubleJump(Character p){
		
		personne= p;
		color= Color.WHITE;

	}
	
	public DoubleJump( Character p, Color c){
		
		personne=p;
		color=c;
	}
	
	@Override
	protected void executer() {
		// TODO Auto-generated method stub
		if(personne.getColor() == color){
			
		
			if(!engine.ExecDoubleJump(personne)){
				try {
					throw new Exception("Pas possible de double jump");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		}
		
		else{
			try {
				throw new Exception("Pas possible de double JUMP : couleurs differentes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
			
	}
	
}
