package lightbot;

import java.util.HashMap;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class ColorMark extends DrawableObject{

	
/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
		
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class ColorMark
	 * @throws Exception 
	 */
	public ColorMark(Vector2i position, int height, String tilePath, Color color) {
		super(position, height, color, tilePath);
		if (color == Color.CYAN){
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (color == Color.MAGENTA) {
			getSprite().setTextureRect(new IntRect(SIZESPRITEX, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else {
			try {
				throw new Exception("La couleur de la tache ne peut être que magenta ou cyan");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
/** ---------------- METHODS ----------------- */	

	
	
}
