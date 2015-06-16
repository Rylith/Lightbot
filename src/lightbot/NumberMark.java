package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2i;

public class NumberMark extends DrawableObject {

	
/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
	
	private int m_value;
		
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class ColorMark
	 * @throws Exception 
	 */
	public NumberMark(Vector2i position, int height, String tilePath, int value) throws Exception{
		super(position, height, Color.BLACK tilePath);
		if (color == Color.CYAN){
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
			//setposition du sprite ??
		}
		else if (color == Color.MAGENTA) {
			getSprite().setTextureRect(new IntRect(SIZESPRITEX, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
			//setposition du sprite ??
		}
		else {
			throw new Exception("La couleur de la tache de peut être que magenta ou cyan");
		}
		
	}
	
	
	
/** ---------------- METHODS ----------------- */	

	
	
	
}
