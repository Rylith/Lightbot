package lightbot;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;


public abstract class Object extends DrawableObject {
	
	
/** --------------- ATTRIBUTES --------------- */	
	
	private int m_height;
	private Color m_color;

	
	
/** -------------- CONSTRUCTORS -------------- */

	
	
/** ---------------- METHODS ----------------- */
	
	public abstract Sprite getSprite();
	
}
