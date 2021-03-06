package Orders;

import org.jsfml.graphics.Color;

import DrawableObject.Character;
import Game.Engine;

public class AccessPointer extends Order {

private Color color_ptr;
	
	public AccessPointer(Character p, Engine e, Color cptr){	
		
		personne=p;
		engine=e;
		color=Color.WHITE;
		setColor_ptr(cptr);
	}
	
	public AccessPointer(Character p, Engine e, Color c, Color cptr){
		
		personne=p;
		engine = e;
		color=c;
		setColor_ptr(cptr);
		
	}

	@Override
	public int executer()  {
		
		// TODO Auto-generated method stub
				if(!engine.ExecAccess(personne, getColor_ptr() )){
					try {
						throw new Exception("Impossible d'acceder au pointeur");
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