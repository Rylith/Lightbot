package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
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
	
	private String tilePath;
	private Texture tileTexture;
	
	private boolean m_fall; //indique si le personnage chute
	private boolean m_death; //indique si le personnage est mort
	
	private Vector<Vector<Order>> m_listOrder;
	private Vector<Integer> m_limitOrder; //vector contenant la taille limite de chacune des listes dans ListOrder
	private Vector<Pointeur> m_listPointeur; //vector contenant les objets de types pointeurs
	private Vector<Boolean> m_currentProc; //vector indiquant la procedure active
	private Color color;

	
/** -------------- CONSTRUCTORS -------------- */
	
		
	/** Constructeur de la class Character 
	 * @info Penser a donner l'orientation (pour allouer le bon sprite) + Donner la position du Sprite 
	 * [non inclus dans ce constructeur]
	 */
	public Character(Vector2i position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_listOrder = new Vector<Vector<Order>>();
		m_limitOrder = new Vector<Integer>();
		for(int i=0; i<3; i++){
			m_limitOrder.add(0);
		}
		m_listPointeur = new Vector<Pointeur>();
		m_currentProc = new Vector<Boolean>();
		m_listPointeur= new Vector <Pointeur>();
		m_listPointeur.add(new Pointeur(new Vector2i(0,0), 1, Color.BLUE, "case.png"));
		m_listPointeur.add(new Pointeur(new Vector2i(0,0), 1, Color.GREEN, "case.png"));
		m_listPointeur.add(new Pointeur(new Vector2i(0,0), 1, Color.YELLOW, "case.png"));
		m_listPointeur.add(new Pointeur(new Vector2i(0,0), 1, Color.RED, "case.png"));
		m_sprite.scale(0.75f,0.75f);
	}

/** ---------------- METHODS ----------------- */	


	/** Retourne la liste d'ordre du character */
	public Vector<Vector<Order>> getListOrder(){
		return m_listOrder;
	}
	
	/** Retourne la liste de pointeur du caractere */
	public Vector<Pointeur> getPointerList(){
		return  this.m_listPointeur;
	}
	
	/** Supprime le pointeur de la liste de pointeur du character */
	public void RemoveFromPtrList(Pointeur p){
		this.m_listPointeur.remove(p);
	}
	
	/** Retourne la limite d'ordre de chaque procedures du character */
	public Vector<Integer> getLimitOrder(){
		return m_limitOrder;
	}
	
	/** Assigne la limite d'ordre de la procedure
	 */
	public void setLimitOrder(int proc, int nbr){
		m_limitOrder.set(proc, nbr);
	}
	
	/** Active la procedure courante du character
	 * 0 : main
	 * 1 : P1
	 * 2 : p2
	 */
	public void activeProc(int i){
		for(int j = 0; j < 3; j++){
			m_currentProc.set(j, false);
		}
		m_currentProc.set(i, true);	
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
	 * @param int : Numero de procedure (0:main 1:Proc1 2:Proc3)
	 * @param Order : Ordre a ajouter
	 */
	public void addOrder (int numprocedure, Order odr){
		m_listOrder.elementAt(numprocedure).addElement(odr);
    }
    
    /** Supprime l'ordre a la fin de la liste d'ordre 
     * @param int : Numero de procedure (0:main 1:Proc1 2:Proc3)
     */
	public void delOrder (int numprocedure){
		int lastElem;
		lastElem = m_listOrder.size();
		m_listOrder.elementAt(numprocedure).removeElementAt(lastElem);
	}
	
	/** Retourne le pointeur correspondant a la couleur fournit en parametre */
	public Pointeur getPointeur(Color color) {
    	for (int i = 0; i < m_listPointeur.size(); i++){
    		if (m_listPointeur.elementAt(i).getColor() == color){
    			return m_listPointeur.elementAt(i);
    		}
    	}
    	try {
			throw new Exception("Aucun pointeur de cette couleur n'a ete trouve");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    	
	/** Assigne la position au pointeur correspondant a la couleur fournit en parametre */
	public void setPointeur(Color color, Vector2i position){
    	for (int i = 0; i< m_listPointeur.size(); i++){
    		if (m_listPointeur.elementAt(i).getColor() == color){
    			m_listPointeur.elementAt(i).setPosition(position);
    			break;
    		}
    	}
	}
	
	/** Dessine le caractere dans la fenetre */
	public void drawCharac(RenderWindow window){
		//POSTION ROBOT
		//case3.getPosition().x+82/2-30,case3.getPosition().y+82/4-60
		
		//POSTIONNEMENT DES CASES
		//250 + decaleLine + this.getPosition().y*41 , 100 + this.getPosition().y * 20.5f + this.getPosition().x *20.5f
		
		float pos_x_graph = 250 + this.getPosition().x * -41 + this.getPosition().y * 41 + 82/2-30;
		float pos_y_graph = 100 + this.getPosition().y * 20.5f + this.getPosition().x *20.5f - 2*20.5f;
		//System.out.println("Pos x: " + this.getPosition().x + " Pos y: " + this.getPosition().y);
		super.draw(window,(new Vector2i((int)pos_x_graph,(int)pos_y_graph)));
		
	}
	
	/** Met a jours la position du sprite */
	public void update(RenderWindow window, Vector2f dep){
		super.update(window, dep);
		window.draw(this.getSprite());
	}
	

}
