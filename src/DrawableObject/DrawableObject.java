package DrawableObject;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public abstract class DrawableObject {


/** --------------- ATTRIBUTES --------------- */
	
	private Vector2i m_position;
	private int m_height;
	private Color m_color;
	private String m_tilePath; //chemin vers la texture de l'objet
	private Texture m_tileSet; //texture de l'objet
	protected Sprite m_sprite; //sprite de l'objet parmis tous les sprites de la texture
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class DrawableObject 
	 * @info Penser a recadrer le sprite et a lui allouer une position
	 * [non inclus dans ce constructeur]
	 */
	public DrawableObject(Vector2i position, int height, Color color, String tilePath){
		m_position = position;
		m_height = height;
		m_color = color;
		m_tilePath = tilePath;
		m_tileSet = new Texture();
		try {
			m_tileSet.loadFromFile(Paths.get(tilePath));
			m_tileSet.setSmooth(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_sprite = new Sprite(m_tileSet);
	}
	
	
/** ---------------- METHODS ----------------- */
	
	/** Retourne la position de l'objet */
	public Vector2i getPosition(){
		return m_position;
	}
	
	/** Assigne une position a l'objet
	 * @param Vector2f position */
	public void setPosition(Vector2i position){
		m_position = position;
	}
	
	/** Retourne la hauteur de l'objet */
	public int getHeight(){
		return m_height;
	}
	
	/** Assigne la hauteur a l'objet 
	 * @param int hauteur*/
	public void setHeight(int height){
		m_height = height;
	}
	
	/** Retourne la couleur de l'objet */
	public Color getColor(){
		return m_color;
	}
	
	/** Assigne la couleur a l'objet */
	public void setColor(Color color){
		m_color = color;
	}
	
	/** Retourne le sprite de l'objet */
	public Sprite getSprite(){
		return m_sprite;
	}
	
	/** Assigne le sprite a l'objet */
	public void setSprite(Sprite sprite){
		m_sprite = sprite;
	}
	
	/** Dessine le DrawableObject dans la fenetre */
	public void draw(RenderWindow window, Vector2i position){
		float pos_x = 250 +  position.y * 41f - position.x * 41f;
		float pos_y = 100 + position.y * 20.5f + position.x * 20.5f;
		this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		window.draw(this.getSprite());
	}
	
	/** Met a jours la position du sprite */
	public void update(RenderWindow window, Vector2f dep){
		this.getSprite().move(dep);
	}
	
	public void setScaling(Vector2f scaling) {
		this.getSprite().setScale(scaling);
		//this.getSprite().s
		/*Vector2f ancient = new Vector2f(this.getSprite().getGlobalBounds().height , this.getSprite().getGlobalBounds().width);
		Vector2f newS = new Vector2f(this.getSprite().getLocalBounds().height,this.getSprite().getLocalBounds().width);
		Vector2f diff = Vector2f.componentwiseDiv(ancient, newS);
		this.getSprite().setPosition(this.getSprite().getPosition().x*diff.x, this.getSprite().getPosition().y * diff.y);
		*/
	}
	
	public void setPath(String n_path) {
		m_tilePath = n_path;
		m_tileSet = new Texture();
		try {
			m_tileSet.loadFromFile(Paths.get(m_tilePath));
			m_tileSet.setSmooth(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_sprite = new Sprite(m_tileSet);
	}
}