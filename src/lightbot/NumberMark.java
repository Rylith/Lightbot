package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class NumberMark extends DrawableObject {

	
/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
	
	private int m_value;
		
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class ColorMark
	 * @info value = 1 | 2 | 3 | 4 | 5
	 */
	public NumberMark(Vector2i position, int height, String tilePath, int value) {
		super(position, height, Color.BLACK, tilePath);
		m_value = value;
		if (m_value == 5){
			getSprite().setTextureRect(new IntRect(6*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (m_value == 4){
			getSprite().setTextureRect(new IntRect(5*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (value == 3) {
			getSprite().setTextureRect(new IntRect(4*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (value == 2){
			getSprite().setTextureRect(new IntRect(3*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else { //value == 1 
			getSprite().setTextureRect(new IntRect(2*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		
	}
	
	
	
/** ---------------- METHODS ----------------- */	

	
	
	
}
