package lightbot;
import java.util.Vector;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import Character.Orientation;


public class Case {

/** --------------- ATTRIBUTES --------------- */

	private int m_height;
	private Color m_color;
	private Vector2f m_position;
	private int m_number;
	private String tilePath;
	private Texture tileTexture;
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class case : color + number*/
	
	public Case(float x, float y, int height, Color color, int number){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = color;
		m_number = number;
		titlePath = //chemin;
		titleTexture = //chemin;
	}
	
	/** Constructeur de la class case : color */
	
	public Case(float x, float y, int height, Color color){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = color;
		m_number = 1;
		titlePath = //chemin;
		titleTexture = //chemin;
	}
	
	/** Constructeur de la class case : number */
	
	public Case(float x, float y, int height, int number){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = Color.WHITE;
		m_number = number;
		titlePath = //chemin;
		titleTexture = //chemin;
	}
	
	/** Constructeur de la class case default */
	
	public Case(float x, float y, int height){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = Color.WHITE;
		m_number = 1;
		titlePath = //chemin;
		titleTexture = //chemin;
	}

	
/** ---------------- METHODS ----------------- */	

	/** Retourne les coordonnees de la case */
	public Vector2f getPosition(){
		return m_position;
	}
	
	/** Assigne les coordonnees de la case */
	public void setPosition(Vector2f position){
		m_position = position;
	}
	
	/** Retourne la hauteur de la case */
	public int getHeight(){
		return m_height;
	}
	
	/** Retourne le nombre de la case */
	public int getHeight(){
		return m_height;
	}
	
	/** Retourne le nombre de la case */
	public int getNumber(){
		return m_number;
	}
	
	/** Retourne la couleur de la case */
	public Color getColor(){
		return m_color;
	}
	
	/** Assigne la couleur a la case */
	public void setColor(Color color){
		m_color = color;
	}
	

	
	 
	/** Retourne le nombre d'ordres dans la liste d'ordre */
	public void nbrOrder (){
    	ListOrder.size();
    }
    
	
	/** Ajout de la case dans la fenetre */
	public void draw(){
		/*TODO*/
	}
	

}

	
}
