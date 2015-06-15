package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

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
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	public Component(String tilePath,Vector2f position) {
		m_tileSet = new Texture();
		try {
        	m_tileSet.loadFromFile(Paths.get(tilePath));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
		m_sprite = new Sprite(m_tileSet);
		m_sprite.setPosition(position);	
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
	public Sprite getSprite(){
		return m_sprite;
	}
	
	/** Assigne une nouvelle visibilite au component */
	public void setSprite(Sprite sprite){
		m_sprite = sprite;
	}

	
	
	
	
	
}
