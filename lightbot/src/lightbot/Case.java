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

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
	
	private int m_value; //valeur de la case
	private HashMap<Integer,DrawableObject> m_mapDrawableObject ;

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class Case 
	 * @info : par defaut la couleur est a WHITE et la valeur a 1
	 */
	public Case(Vector2i position, int height, String tilePath){
		super(position, height, Color.WHITE, tilePath);
		m_value = 1;
		getSprite().setTextureRect(new IntRect((int)((Math.random()*(5-0)+1) * SIZESPRITEX), SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
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
	
	/** Permet d'ajouter un DrawableObjet sur la case */
	public void addObject(int depth, DrawableObject object){
		m_mapDrawableObject.put(depth, object);		
	}
	
	/** Permet de supprimer un DrawableObjet de la case */	
	public void delObject(int depth){
		m_mapDrawableObject.remove(depth);		
	}

}
