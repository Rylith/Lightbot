package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Character extends DrawableObject{


/** --------------- ATTRIBUTES --------------- */
	
	private final static int SIZESPRITEX = 80;
	private final static int SIZESPRITEY = 100;
	
	public enum Orientation{
		Right,
		Left,
		Up,
		Down,
	}
	
	private Orientation m_orientation;
	private boolean m_fall; //indique si le personnage chute
	private boolean m_death; //indique si le personnage est mort
	
	private Vector<Vector<Order>> m_listOrder;
	private Vector m_limitOrder; //vector contenant la taille limite de chacune des listes dans ListOrder
	private Vector<Pointeur> m_listPointeur; //vector contenant les objets de types pointeurs

	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class Character 
	 * @param coordonne x du haut gauche de l'image, coordonnee y du haut gauche de l'image et orientation du personnage */
	public Character(Vector2f position, int height, Color color, String tilePath){
		super(position, 1, color, tilePath);
	}

	
/** ---------------- METHODS ----------------- */	


	/** Retourne m_listOrder */
	public Vector<Vector<Order>> getListOrder(){
		return m_listOrder;
	}
	
	/** Retourne l'orientation du personnage */
	public Orientation getOrientation(){
		return m_orientation;
	}
	
	/** Assigne l'orientation du personnage & assigne le bon le Sprite en consequence
	 * @param orientation du personnage : Right Left Up Down
	 * @return void (mise a jour de m_orientation & m_Texture
	 */
	public void setOrientation(Orientation orientation){
		m_orientation = orientation;
		switch (m_orientation)
		{
            case Up:
            	getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
            break;
            case Down:
            	getSprite().setTextureRect(new IntRect(0, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
            break;
            case Left:
            	getSprite().setTextureRect(new IntRect(0, 2*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
            break;
            case Right:
            	getSprite().setTextureRect(new IntRect(0, 3*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
            break;
        }
	}
	
	/** Ajout d'un ordre a la fin de la liste d'ordre passe en parametre
	 * @param int : Numero de procedure (1:main 2:Proc1 3:Proc3)
	 * @param Order : Ordre a ajouter
	 * */
	public void addOrder (int numprocedure, Order odr){
		m_listOrder.elementAt(numprocedure-1).addElement(odr);
    }
    
    /** Supprime l'ordre a la fin de la liste d'ordre 
     * @param int : Numero de procedure (1:main 2:Proc1 3:Proc3)
     * */
	public void delOrder (int numprocedure){
		int lastElem;
		lastElem = m_listOrder.size() - 1;
		m_listOrder.elementAt(numprocedure-1).removeElementAt(lastElem);
	}
	
	/** Retourne le pointeur correspondant a la couleur fournit en parametre 
	 * @throws Exception */
	public Pointeur getPointeur(Color color) throws Exception{
    	for (int i = m_listPointeur.size(); i > 0; i--){
    		if (m_listPointeur.elementAt(i).getColor() == color){
    			return m_listPointeur.elementAt(i);
    		}
    	}
    	throw new Exception("Aucun pointeur de cette couleur n'a ete trouve");
	}
    	
	/** Assigne la position au pointeur correspondant a la couleur fournit en parametre */
	public void setPointeur(Color color, Vector2f position){
    	for (int i = m_listPointeur.size(); i > 0; i--){
    		if (m_listPointeur.elementAt(i).getColor() == color){
    			m_listPointeur.elementAt(i).setPosition(position);
    			break;
    		}
    	}
	}
	

	/*
	public void draw(RenderWindow window){
		TODO 
	}
	
	public void update(RenderWindow){
		TODO
	}
	*/
	

}
