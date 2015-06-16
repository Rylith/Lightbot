package lightbot;

import java.util.HashMap;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2i;

public class ColorMark extends DrawableObject{

	
/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
		
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class ColorMark
	 * @throws Exception 
	 */
	public ColorMark(Vector2i position, int height, String tilePath, Color color) throws Exception{
		super(position, height, color, tilePath);
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
