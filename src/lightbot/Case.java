package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Case extends DrawableObject {

/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 82;
	private final static int SIZESPRITEY = 82;
	
	private int m_value; //valeur affichée sur la case
	private HashMap<Integer,DrawableObject> m_mapDrawableObject ;

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class Case avec value */
	
	public Case(Vector2i position, int height, Color color, String tilePath, int value){
		super(position, height, color, tilePath);
		m_mapDrawableObject = new HashMap<Integer,DrawableObject>();
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
		m_mapDrawableObject = new HashMap<Integer,DrawableObject>();
		
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
		float pos_x = 250 +  position.y * 41f - position.x * 41f;
		float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
		
		this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		
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
	public HashMap getMapDO(){
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
	
	public void drawCase(RenderWindow fenetre){
		for (int i = 0; i < this.getHeight(); i++) {
			this.getSprite().move(0, -20.5f);
			fenetre.draw(this.getSprite());
		}
		float pos_x = 250 +  this.getPosition().y * 41f - this.getPosition().x * 41f;
		float pos_y = 100 + this.getPosition().y * 20.5f + this.getPosition().x * 20.5f;
		
		this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		if (m_mapDrawableObject.get(2) != null){
			fenetre.draw(m_mapDrawableObject.get(2).getSprite());
		}
	}

}
