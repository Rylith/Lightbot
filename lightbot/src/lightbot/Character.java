package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Character extends DrawableObject{


/** --------------- ATTRIBUTES --------------- */
	
	public enum Orientation{
		Right,
		Left,
		Up,
		Down,
	}
	
	private Vector<Ordre> ListOrder;
	private Vector2f m_position; //Position du centre du personnage (calcule automatiquement)
	private Orientation m_orientation;
	private Texture m_Texture;
	
	private static String sourcePathRight /* = chemin*/ ;
	private static String sourcePathLeft /* = chemin*/ ;
	private static String sourcePathUp /* = chemin*/ ;
	private static String sourcePathDown /* = chemin*/ ;
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class Character 
	 * @param coordonne x du haut gauche de l'image, coordonnee y du haut gauche de l'image et orientation du personnage */
	public Character(float x, float y, Orientation o){
		ListOrder = new Vector<Ordre>();
		m_orientation = o;
		switch (m_orientation)
		{
            case Right:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathRight));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
            case Left:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathLeft));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
            case Up:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathUp));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
            case Down:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathDown));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
        }
		Vector2f position = new Vector2f(x,y);
		m_position = centerPosition(position);
	}

	
/** ---------------- METHODS ----------------- */	

	/** Donne la position du centre du personnage a partir d'une position en haut a gauche */
	private Vector2f centerPosition(Vector2f position){
		Sprite tile = new Sprite(m_Texture);
		m_position = new Vector2f(position.x + (tile.getScale().x /2), position.y + (tile.getScale().y / 2));
		return m_position;
	}
	
	/** Retourne les coordonnees du centre du personnage */
	public Vector2f getPosition(){
		return m_position;
	}
	
	/** Centre les coordonnees et les assigne au personnage */
	public void setPosition(Vector2f position){
		m_position = centerPosition(position);
	}
	
	/** Retourne l'orientation du personnage */
	public Orientation getOrientation(){
		return m_orientation;
	}
	
	/** Assigne l'orientation du personnage 
	 * @param orientation du personnage : Right Left Up Down
	 * @return void (mise a jour de m_orientation & m_Texture
	 */
	public void setOrientation(Orientation orientation){
		m_orientation = orientation;
		switch (m_orientation)
		{
            case Right:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathRight));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
            case Left:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathLeft));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
            case Up:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathUp));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
            case Down:
            	m_Texture = new Texture();
        		try {
        			m_Texture.loadFromFile(Paths.get(sourcePathDown));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            break;
        }
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
	
	/** Retourne le Sprite du personnage */
	public Sprite getSprite(){
        return new Sprite(m_Texture);
	}
	
	/** Retourne la case sur laquelle se trouve le personnage */
	//public Case getCurrentCase(){
	//	return /*TODO*/
		
	//}
	

	

}
