package lightbot;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class Case {

/** --------------- ATTRIBUTES --------------- */

	private int m_height;
	private Color m_color;
	private Vector2f m_position;
	private int m_number;
	private String sourceTexture /* = chemin */;
	private Texture m_Texture;
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class case : color + number*/
	
	public Case(float x, float y, int height, Color color, int number){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = color;
		m_number = number;
		m_Texture = new Texture();
		try {
			m_Texture.loadFromFile(Paths.get(sourceTexture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Constructeur de la class case : color */
	
	public Case(float x, float y, int height, Color color){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = color;
		m_number = 1;
		m_Texture = new Texture();
		try {
			m_Texture.loadFromFile(Paths.get(sourceTexture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Constructeur de la class case : number */
	
	public Case(float x, float y, int height, int number){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = Color.WHITE;
		m_number = number;
		m_Texture = new Texture();
		try {
			m_Texture.loadFromFile(Paths.get(sourceTexture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Constructeur de la class case default */
	
	public Case(float x, float y, int height){
		m_position = new Vector2f(x,y);
		m_height = height;
		m_color = Color.WHITE;
		m_number = 1;
		m_Texture = new Texture();
		try {
			m_Texture.loadFromFile(Paths.get(sourceTexture));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
/** ---------------- METHODS ----------------- */	

	/** Donne la position du centre de la case a partir d'une position en haut a gauche */
	private Vector2f centerPosition(Vector2f position){
		Sprite tile = new Sprite(m_Texture);
		m_position = new Vector2f(position.x + (tile.getScale().x /2), position.y + (tile.getScale().y / 2));
		return m_position;
	}
	
	/** Retourne les coordonnees de la case */
	public Vector2f getPosition(){
		return m_position;
	}
	
	/** Centre les coordonnees et les assigne les coordonnees de la case */
	public void setPosition(Vector2f position){
		m_position = centerPosition(position);
	}
	
	/** Retourne la hauteur de la case */
	public int getHeight(){
		return m_height;
	}
	
	/** Retourne le nombre de la case */
	public int getNumber(){
		return m_number;
	}
	
	/** Assigne le nombre a la case */
	public void setNumber(int number){
		m_number = number;
	}
	
	/** Retourne la couleur de la case */
	public Color getColor(){
		return m_color;
	}
	
	/** Assigne la couleur a la case */
	public void setColor(Color color){
		m_color = color;
	}

	
}
