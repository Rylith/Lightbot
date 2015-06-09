package lightbot;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public abstract class DrawableObject {


/** --------------- ATTRIBUTES --------------- */
	
	private Vector2f m_position;
	private int m_height;
	private Color m_color;
	private String m_tilePath; //chemin vers la texture de l'objet
	private Texture m_tileSet; //texture de l'objet
	private Sprite m_sprite; //sprite de l'objet parmis tous les sprites de la texture
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class DrawableObject
	 * @param Vector2f position, int height, Color color, String tilePath */
	
	public DrawableObject(Vector2f position, int height, Color color, String tilePath){
		m_position = position;
		m_height = height;
		m_color = color;
		m_tilePath = tilePath;
		m_tileSet = new Texture();
		try {
			m_tileSet.loadFromFile(Paths.get(tilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		m_sprite = new Sprite(m_tileSet);
		m_sprite.setOrigin(Vector2f.div(new Vector2f(m_tileSet.getSize()), 2));
		m_sprite.setPosition(position);
	}
	
	
/** ---------------- METHODS ----------------- */
	
	
	/** Retourne la position de l'objet */
	public Vector2f getPosition(){
		return m_position;
	}
	
	/** Assigne une position a l'objet
	 * @param Vector2f position */
	public void setPosition(Vector2f position){
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
	
	
	/*
	public void draw(RenderWindow window){
		TODO 
	}
	
	public void update(RenderWindow){
		TODO
	}
	*/
	
}
