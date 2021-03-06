package DrawableObject;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Lampadaire extends DrawableObject {

	
/** --------------- ATTRIBUTES --------------- */
	
	private final static int SIZESPRITEX = 75;
	private final static int SIZESPRITEY = 105;
	
	private boolean m_active; //indique si le lampadaire est allumee
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la classe Lampadaire */
	public Lampadaire(Vector2i position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_active = false;
		this.getSprite().setTextureRect(new IntRect(75, 0, SIZESPRITEX, SIZESPRITEY));
		float tmp_x = 250 + 78/2;
		float tmp_y = 100 + (48-8)/2;
		float pos_x = tmp_x - SIZESPRITEX/2;
		float pos_y = tmp_y - SIZESPRITEY -5;
		float pox = pos_x + (position.y - position.x) * 78/2;
		float poy = pos_y + (position.x + position.y)*(48-8)/2 - (height-1)*8;
		
		Vector2f ancient = new Vector2f(this.getSprite().getGlobalBounds().height , this.getSprite().getGlobalBounds().width);
		Vector2f newS = new Vector2f(this.getSprite().getLocalBounds().height,this.getSprite().getLocalBounds().width);
		Vector2f diff = Vector2f.componentwiseDiv(ancient, newS);
		
		this.getSprite().setPosition(pox*diff.x, poy*diff.y);
	}
	
	
/** ---------------- METHODS ----------------- */	
	
	/** Retourne l'attribut m_active du lampadaire */
	public boolean getActive(){
		return m_active;
	}
	
	/** Assigne l'etat d'activation au lampadaire */
	public void setActive(boolean active){
		m_active = active;
		if (m_active) {
			this.getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else {
			this.getSprite().setTextureRect(new IntRect(75, 0, SIZESPRITEX, SIZESPRITEY));
		}
	}
	
	
}
