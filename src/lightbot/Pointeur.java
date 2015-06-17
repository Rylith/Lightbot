package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Pointeur extends DrawableObject{

/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
	
	private boolean m_active; //indique si le pointeur a ete place sur la map

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la classe Pointeur 
	 * @info : color = Color.BLUE | Color.YELLOW | Color. RED | Color.GREEN
	 */
	public Pointeur(Vector2i position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_active = false;
		if (color == Color.BLUE){
			getSprite().setTextureRect(new IntRect(7*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (color == Color.YELLOW) {
			getSprite().setTextureRect(new IntRect(8*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (color == Color.RED){
			getSprite().setTextureRect(new IntRect(9*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else if (color == Color.GREEN){
			getSprite().setTextureRect(new IntRect(10*SIZESPRITEX, 0, SIZESPRITEX, SIZESPRITEY));
			float pos_x = 250 +  position.y * 41f - position.x * 41f;
			float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
			this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		}
		else {
			try {
				throw new Exception("La couleur du pointeur ne peut être que blue, yellow, red ou green");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
/** ---------------- METHODS ----------------- */	
	
	
	/** Retourne l'attribut m_active du pointeur */
	public boolean getActive(){
		return m_active;
	}
	
	/** Assigne l'etat d'activation au pointeur */
	public void setActive(boolean active){
		m_active = active;
	}
	
}
