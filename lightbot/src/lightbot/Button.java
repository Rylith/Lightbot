package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JButton;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Button extends Component{
	
	
/** --------------- ATTRIBUTES --------------- */	
	
	private String m_name; //indique l'ordre donne par le bouton
	private boolean m_move; //indique si le bouton peut etre deplace
	
	
/** -------------- CONSTRUCTORS -------------- */	
	
	public Button(String tilePath, Vector2f position, String name, boolean move) {
		super(tilePath, position);
		m_name = name;
		m_move = move;
	}
	

/** ---------------- METHODS ----------------- */
	
	/** Indique si le bouton est clique */
	public boolean clicked(Vector2i position){
		return getSprite().getGlobalBounds().contains(position.x, position.y);
	}
	

}
