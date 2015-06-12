package lightbot;

import org.jsfml.graphics.Color;

public class MallocPointer extends Order {
	
	public MallocPointer(Character p, Engine e){	
		
		personne=p;
		engine=e;
		color=Color.WHITE;
	}
	
	public MallocPointer(Character p, Engine e, Color c){
		
		personne=p;
		engine = e;
		color=c;
		
	}


	@Override
	protected int executer() {
		// TODO Auto-generated method stub
		if(!engine.ExecMalloc(personne)){
			try {
				throw new Exception("Impossible de poser un pointeur");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		return 0;
	}

}
