package lightbot;

import java.util.HashMap;

import lightbot.Button.ButtonType;

import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class Level {
	
	
	
/** --------------- ATTRIBUTES --------------- */	

	private final static int SIZELEVELX = 226;
	private final static int SIZELEVELY = 133;
	
	private HashMap<Integer, Button> m_listLevel;
	private HashMap<Integer, String> m_listXML;
	private int m_world;
	private int m_level;
	private Game m_game;

	
	
	
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de Level
	 * @param game
	 * @param tilePath
	 * @param position
	 * @param world : nombre de monde
	 * @param level : nombre de niveau par monde
	 */
	public Level(Game game, String tilePath, Vector2f position, int world, int level){
		m_game = game;
		for (int i = 0 ; i < world*level ; i++){
			Button button = new Button(tilePath, position, ButtonType.Level, false);
			button.getSprite().setTextureRect(new IntRect(((i%level)-1)*SIZELEVELX, (i/5)*SIZELEVELY, SIZELEVELX, SIZELEVELY));
			m_listLevel.put(i, button);
			}
	 }
	
	

/** ---------------- METHODS ----------------- */	
	
	
	/** Permet d'ajouter un fichier XML a la HashMap m_listXML
	 * @param chemin
	 */
	public void addXML(String chemin){
		int i = m_listXML.size();
		m_listXML.put(i-1, chemin);
	}
	
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @require la fenetre window doit etre ouverte
	 */
	public void launchLevel(){
		for (Event event : m_game.getWindow().pollEvents()) {
			
        	// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
            if (event.type == Event.Type.CLOSED) {
                m_game.getWindow().close();
            }
            
            // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un niveau
            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) { 
            	Vector2i mouse_pos = Mouse.getPosition(m_game.getWindow()); 
               	//Level1 : on charge le bon fichier XML
            	for (int i = 0; i < m_world*m_level; i++) {
                   	if (m_listLevel.get(i).isClicked(mouse_pos)) {  		
    	            		m_game.setMap(m_listXML.get(i));
    	            	}
            	}
            }
		}
	}
            
	
}
