package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Case extends DrawableObject {

/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 50;
	private final static int SIZESPRITEY = 50;
	
	private int m_value; //valeur affichée sur la case
	private HashMap<Integer,DrawableObject> m_mapDrawableObject ;

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class Case avec value */
	
	public Case(Vector2i position, int height, Color color, String tilePath, int value){
		super(position, height, color, tilePath);
		m_value = value;
		if (getColor() == Color.WHITE) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.BLUE) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.MAGENTA) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 1*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.CYAN) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 2*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.BLACK) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 3*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.GREEN) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 4*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.RED) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 5*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		
	}
	
	/** Constructeur de la class Case sans value */
	
	public Case(Vector2i position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_value = 1;
		if (getColor() == Color.WHITE) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.BLUE) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.MAGENTA) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 1*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.CYAN) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 2*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.BLACK) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 3*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.GREEN) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 4*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
		else if (getColor() == Color.RED) {
			getSprite().setTextureRect(new IntRect(m_value*SIZESPRITEX, 5*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		}
	}

	
/** ---------------- METHODS ----------------- */	


	/** Retourne le nombre de la case */
	public int getValue(){
		return m_value;
	}
	
	/** Assigne le nombre a la case */
	public void setValue(int value){
		m_value = value;
	}
	
	/** Retourne true si la position est contenue dans la case (false sinon) */
	public boolean isContain(Vector2f position){
		return getSprite().getLocalBounds().contains(position);
	}
	
	/** Retourne la HashMap de DrawableObject present sur la case */
	public HashMap <Integer,DrawableObject> getMapDO(){
		return m_mapDrawableObject;
	}
	
	/***/
	public void addObject(int depth, DrawableObject object){
		m_mapDrawableObject.put(depth, object);		
	}
	
	/***/	
	public void delObject(int depth){
		m_mapDrawableObject.remove(depth);		
	}

}
