package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import org.jsfml.graphics.Color;
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
	
	private Orientation m_orientation;
	private boolean m_fall; //indique si le personnage chute
	private boolean m_death; //indique si le personnage est mort
	
	private Vector<Vector<Order>> m_listOrder;
	private Vector<int> m_limitOrder; //vector contenant la taille limite de chacune des listes dans ListOrder
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
	
	/** Assigne l'orientation du personnage & met le Sprite
	 * @param orientation du personnage : Right Left Up Down
	 * @return void (mise a jour de m_orientation & m_Texture
	 */
	public void setOrientation(Orientation orientation){
		m_orientation = orientation;
		switch (m_orientation)
		{
            case Right:
            	// mise à jours de m_sprite par décalage de m_tileSet
            break;
            case Left:
            	// mise à jours de m_sprite par décalage de m_tileSet
        		
            break;
            case Up:
            	// mise à jours de m_sprite par décalage de m_tileSet
            break;
            case Down:
            	// mise à jours de m_sprite par décalage de m_tileSet
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
	
	/** Retourne le pointeur correspondant a la couleur fournit en parametre */
	public Pointeur getPointeur(Color color){
    	m_listOrder.
    	//size() : renvoie le nombre d'éléments 
    	//indexOf(Object) : renvoie l'indice de l'objet
    	//insertElementAt(Object, int) : insère l'objet à l'indice indiqué 
    }
	
	/** Assigne le pointeur correspondant a la couleur au vecteur de Pointeur fournit en parametre */
	public void setPointeur(Color color, Vector2f position){
    	m_listOrder.;
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
