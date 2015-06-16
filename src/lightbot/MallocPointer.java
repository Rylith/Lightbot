package lightbot;

import org.jsfml.graphics.Color;

public class MallocPointer extends Order {
	
	private Color color_ptr;
	
	public MallocPointer(Character p, Engine e, Color cptr){	
		
		personne=p;
		engine=e;
		color=Color.WHITE;
		setColor_ptr(cptr);
	}
	
	public MallocPointer(Character p, Engine e, Color c, Color cptr){
		
		personne=p;
		engine = e;
		color=c;
		setColor_ptr(cptr);
		
	}


	@Override
	protected int executer() {
		// TODO Auto-generated method stub
		if(!engine.ExecMalloc(personne, getColor_ptr())){
			try {
				throw new Exception("Impossible de poser un pointeur");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		return 0;
	}

	public Color getColor_ptr() {
		return color_ptr;
	}

	public void setColor_ptr(Color color_ptr) {
		this.color_ptr = color_ptr;
	}

}