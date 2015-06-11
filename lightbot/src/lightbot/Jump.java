package lightbot;

import org.jsfml.graphics.Color;

public class Jump extends Order {

	
	public Jump(Character p){
		
		personne=p;
		color= Color.WHITE;
		
	}
	
	public Jump(Character p, Color c){
		
		personne=p;
		color=c;
	}
	@Override
	protected void executer() {
		// TODO Auto-generated method stub
		
		if(color == personne.getColor()){
			
			if(!engine.ExecJump(personne)){
				try {
					throw new Exception("Pas possible de jump");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
				
		}
		else{
				try {
					throw new Exception("Pas possible de JUMP : couleurs differentes");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
				
		}
	
	

}
