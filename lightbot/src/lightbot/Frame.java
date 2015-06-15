package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Frame extends Component {

/** --------------- ATTRIBUTES --------------- */
	
	private boolean m_contain; //indique si la frame a un contenu dynamique
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	public Frame(String tilePath,Vector2f position, boolean contain) {
		super(tilePath,position);
		m_contain = contain;
	}
	
	
/** ---------------- METHODS ----------------- */	
	
	/** Ajout un bouton a la fin de la liste dans la view
	 * possible que si m_contain == true
	 */
	
	/** Supprime le bouton a la fin de la liste dans la view 
	 * possible que si m_contain == true
	 */
	
	/** Mise a jour des frames main p1 & p2 a partir de getListOrder() de character */
	
	
	
	
}
