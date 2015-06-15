package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Lampadaire extends DrawableObject {

	
/** --------------- ATTRIBUTES --------------- */
	
	private final static int SIZESPRITEX = 75;
	private final static int SIZESPRITEY = 110;
	
	private boolean m_active; //indique si le lampadaire est allumee
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	public Lampadaire(Vector2i position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_active = false;
		this.getSprite().setTextureRect(new IntRect(75, 0, SIZESPRITEX, SIZESPRITEY));
		float pos_x = 250 +  position.y * 41f - position.x * 41f;
		float pos_y = 100 + position.y * 20.5f + position.x * 20.5f - this.getHeight()*60;
		this.getSprite().setPosition(pos_x, pos_y);
	}
	
	
/** ---------------- METHODS ----------------- */	
	
	/** Retourne l'attribut m_active du lampadaire */
	public boolean getActive(){
		return m_active;
	}
	
	/** Assigne l'etat d'activation au lampadaire */
	public void setActive(boolean active){
		m_active = active;
	}
	
	
}
