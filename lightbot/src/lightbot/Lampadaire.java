package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

public class Lampadaire extends DrawableObject {

	
/** --------------- ATTRIBUTES --------------- */
	
	private final static int SIZESPRITEX = 50;
	private final static int SIZESPRITEY = 50;
	
	private boolean m_active; //indique si le lampadaire est allumee
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	public Lampadaire(Vector2f position, int height, Color color, String tilePath, int value){
		super(position, height, color, tilePath);
		m_active = false;
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
