package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;


public class Component {

	
/** --------------- ATTRIBUTES --------------- */
	
	private String m_tilePath; 
	private Texture m_tileSet; 
	private Sprite m_sprite;
	private Vector2f m_position;
	private boolean m_visible; // indique si le component sera dessine dans la fenetre
	private Vector2f m_scale;
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	public Component(String tilePath,Vector2f position) {
		m_scale = new Vector2f(1.0f,1.0f);
		m_tileSet = new Texture();
		try {
        	m_tileSet.loadFromFile(Paths.get(tilePath));
        	m_tileSet.setSmooth(true);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
		m_sprite = new Sprite(m_tileSet);
		m_sprite.setPosition(position);	
		m_position = position;
		m_visible = false;
	}
	
	public Component(String tilePath) {
		m_scale = new Vector2f(1.0f,1.0f);
		m_tileSet = new Texture();
		try {
        	m_tileSet.loadFromFile(Paths.get(tilePath));
        	m_tileSet.setSmooth(true);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
		m_sprite = new Sprite(m_tileSet);
		m_visible = false;
	}

	
/** ---------------- METHODS ----------------- */
	
	/** Retourne le sprite du component */
	public Sprite getSprite(){
		return m_sprite;
	}
	
	/** Assigne un nouveau sprite au component */
	public void setSprite(Sprite sprite){
		m_sprite = sprite;
	}
	
	/** Retourne la visibilite du component */
	public boolean getVisibility(){
		return m_visible;
	}
	
	/** Assigne une nouvelle visibilite au component */
	public void setVisibility(boolean visible){
		m_visible = visible;
	}
	
	/** Assigne une nouvelle position au component */
	public void setPosition(Vector2f position){
		m_position = position;
		m_sprite.setPosition(position);	
	}
	
	public Vector2f getScale(){
		return m_scale;
	}
	
	public void setScale(Vector2f scale) {
		m_scale = scale; 
		m_sprite.setScale(m_scale);
	}
	
	public void draw(RenderWindow window){
		window.draw(getSprite());	
	}
	
	
	
	
}
