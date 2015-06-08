package lightbot;
import java.util.Vector;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class Character extends DrawableObject{


/** --------------- ATTRIBUTES --------------- */
	
	public enum Orientation{
		Right,
		Left,
		Up,
		Down,
	}
	
	private Vector<Ordre> ListOrder;
	private Vector2f m_position;
	private Orientation m_orientation;
	private String tilePath;
	private Texture tileTexture;
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class Character */
	public Character(float x, float y, Orientation o){
		ListOrder = new Vector();
		m_position = new Vector2f(x,y);
		m_orientation = o;
		tilePath = //chemin;
		tileTexture = //chemin;
	}

	
/** ---------------- METHODS ----------------- */	

	/** Retourne les coordonnees du personnage */
	public Vector2f getPosition(){
		return m_position;
	}
	
	/** Assigne les coordonnees au personnage */
	public void setPosition(Vector2f position){
		m_position = position;
	}
	
	/** Retourne l'orientation du personnage */
	public Orientation getOrientation(){
		return m_orientation;
	}
	
	/** Assigne l'orientation du personnage */
	public void setOrientation(Orientation orientation){
		m_orientation = orientation;
	}
	
	/** Ajout d'un ordre a la fin de la liste d'ordre */
	public void addOrder (Ordre odr){
    	ListOrder.addElement(odr);
    }
    
    /** Supprime l'ordre a la fin de la liste d'ordre */
	public void delOrder (Ordre odr){
		int lastElem;
		lastElem = ListOrder.size() - 1;
		ListOrder.removeElementAt(lastElem);
	}
	
	/** Retourne le n ieme ordre de la liste d'ordres du personnage
	 * @parametre : int n
	 * @return element d'indice n de ListOrder
	 * @careful : premier element n = 0 !*/
	public Ordre getOrder(int n){
		return ListOrder.elementAt(n);
	}
	 
	/** Retourne le nombre d'ordres dans la liste d'ordre */
	public void nbrOrder (){
    	ListOrder.size();
    }
    
	
	/** Retourne la case sur laquelle se trouve le personnage */
	public Case getCurrentCase(){
		return /*TODO*/
		
	}
	
	/** Ajout du personnage dans la fenetre */
	public void draw(){
		/*TODO*/
	}
	

}
