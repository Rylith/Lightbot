package lightbot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import lightbot.Button.ButtonType;
import lightbot.Frame.FrameType;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

public class Level {
	
	
	// TAILLE INITIAL DE LA FENETRE
	private final static Vector2i SIZEINITWINDOW = new Vector2i(1920,1080);
	
	// BACKGROUND
	private static String TILEPATHBACKGROUNG = "ressource/Sprite/back.png";
	
	//TITLE
	private static String TILEPATHTITLE = "ressource/Sprite/Menu.png";
	private final static float DECALTITLE = 100 ;

	// LEVEL
	private static Vector2f POSINITLEVEL = new Vector2f(100,50);
	private static Vector2f POSINITBACK = new Vector2f(0,0);
	
	private final static float DECALEVEL = 2 ;
	private final static float SIZELEVELX = 226;
	private final static float SIZELEVELY = 133;
	
	
	
/** --------------- ATTRIBUTES --------------- */	

	private HashMap<Integer, Button> m_listLevel = new HashMap<Integer, Button>();
	private HashMap<Integer, String> m_listXML = new HashMap<Integer, String>();
	private int m_world;
	private int m_level;
	private String m_tilePath;
	private Game m_game;
	private Vector2i m_screenSize;
	private Vector2f m_decal;
	private Vector2f m_scale;
	
	private static Sprite m_background;
	private static Sprite m_title;
	
	private boolean m_makechoice;
	


	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de Level
	 * @param game
	 * @param tilePath
	 * @param world : nombre de monde
	 * @param level : nombre de niveau par monde
	 */
	public Level(Game game, String tilePath, int world, int level, Vector2i screenSize){
	
		m_game = game;
		m_world = world;
		m_level = level;
		m_tilePath = tilePath;
		m_screenSize = screenSize;
		m_makechoice = false;
		
		
	}
	
	

/** ---------------- METHODS ----------------- */	
	
	
	/** Getter de la HashMap de Level
	 * @return m_listLevel
	 */
	public HashMap<Integer, Button> getListLevel(){
		return m_listLevel;
	}
	
	
	/** Getter de la HashMap de fichier XML
	 * @return m_listXML
	 */
	public HashMap<Integer, String> getListXML(){
		return m_listXML;
	}
	
	/** Getter du nombre de monde
	 * 
	 */
	public int getWorld(){
		return m_world;
	}
	
	
	/** Getter du nombre de niveau
	 * 
	 */
	public int getLevel(){
		return m_level;
	}
	
	
	/** Permet d'ajouter un fichier XML a la HashMap m_listXML
	 * @param chemin
	 */
	public void addXML(String chemin){
		int i = m_listXML.size();
		m_listXML.put(i, chemin);
		System.out.println("Ajout du fichier .xml du Level : " + i);
		
	}
	
	
	
	/** Dessine l'interface de choix de niveaux 
	 */
	public void draw(){
		
        // On affiche le background
		m_game.getWindow().draw(m_background);
		
		// On affiche le titre
		m_game.getWindow().draw(m_title);
		
		// On affiche les boutons
		for(Integer mapkey : m_listLevel.keySet()) {
			m_listLevel.get(mapkey).draw(m_game.getWindow());
		}
		
	}
	
	
	
	/** Reload l'interface de choix des niveaux
	 * @param screenSize
	 */
	public void reload(Vector2i screenSize) {
		
		m_screenSize = screenSize;		
		m_listLevel.clear();
		m_listXML.clear();
		
		// Ajuste le background a la taille de la fenetre
		Texture background = new Texture();
		try {
			background.loadFromFile(Paths.get(TILEPATHBACKGROUNG));
			m_background = new Sprite(background);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_decal = new Vector2f(((float)SIZEINITWINDOW.x) - m_screenSize.x,((float)SIZEINITWINDOW.y) - m_screenSize.y);
		m_scale = new Vector2f(m_screenSize.x / ((float)SIZEINITWINDOW.x), m_screenSize.y / ((float)SIZEINITWINDOW.y));
		m_background.setScale(m_scale);
		
		
		Vector2f realScale = m_background.getScale();
		Vector2f realInitPosLevel = Vector2f.add(m_background.getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITLEVEL, POSINITBACK), realScale));
		Vector2f position = realInitPosLevel;
		
		
		// Generation du titre
		Texture title = new Texture();
		try {
			title.loadFromFile(Paths.get(TILEPATHTITLE));
			m_title = new Sprite(title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_title.setPosition(realInitPosLevel);
		
		
		
		// Generation des boutons Levels
		for (int i = 0 ; i < m_world*m_level ; i++){
			if (i == 0) {
				position = new Vector2f(realInitPosLevel.x , realInitPosLevel.y + DECALTITLE);
				Button button = new Button(m_tilePath, position, ButtonType.Level, false);
				button.getSprite().setTextureRect(new IntRect(((i%m_level))*(int)SIZELEVELX, (i/m_level)*(int)SIZELEVELY, (int)SIZELEVELX, (int)SIZELEVELY));
				button.setScale(realScale);
				m_listLevel.put(i, button);
				//System.out.println("Creation bouton Level : " + i);
			}
			else if((i % m_level == 0) && i != 0 ) {
				position = new Vector2f(realInitPosLevel.x , position.y + SIZELEVELY + DECALEVEL);
				Button button = new Button(m_tilePath, position, ButtonType.Level, false);
				button.getSprite().setTextureRect(new IntRect(((i%m_level))*(int)SIZELEVELX, (i/m_level)*(int)SIZELEVELY, (int)SIZELEVELX, (int)SIZELEVELY));
				button.setScale(realScale);
				m_listLevel.put(i, button);
				//System.out.println("Creation bouton Level : " + i);
				}
			else {
				position = new Vector2f(position.x + SIZELEVELX + DECALEVEL , position.y);
				Button button = new Button(m_tilePath, position, ButtonType.Level, false);
				button.getSprite().setTextureRect(new IntRect(((i%m_level))*(int)SIZELEVELX, (i/m_level)*(int)SIZELEVELY, (int)SIZELEVELX, (int)SIZELEVELY));
				button.setScale(realScale);
				m_listLevel.put(i, button);
				//System.out.println("Creation bouton Level : " + i);
				}
			
	        }
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
            
            // Si l'utilisateur modifie la taille de la fenetre : on reload l'interface
            if (event.type == Event.Type.RESIZED) {
            	System.out.println("New Size x = " + event.asSizeEvent().size.x + " y = " + event.asSizeEvent().size.y);
            	m_game.setView(event.asSizeEvent().size);
            	reload(event.asSizeEvent().size);
            }
            
            // Si l'utilisateur clique sur la touche echape : on ferme la fenetre
            if (event.type == Event.Type.KEY_PRESSED) {
            	if (event.asKeyEvent().key == Key.ESCAPE) {
            		m_game.getWindow().close();
            	}
            }
            
            // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un niveau
            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED && getMakeChoice() == false) {
            	Vector2i mouse_pos = Mouse.getPosition(m_game.getWindow()); 
            	for (int i = 0; i < m_world*m_level; i++) {
                   	if (m_listLevel.get(i).isClicked(mouse_pos)) {  		
    	            		m_game.setMap(m_listXML.get(i));
    	            		System.out.println("Lancement du niveau : " + i);
    	            		setMakeChoice(true);
    	            		//m_game.getWindow().close();
    	            	}
            	}
            }
		}
	}



	public boolean getMakeChoice() {
		return m_makechoice;
	}



	public void setMakeChoice(boolean m_makechoice) {
		this.m_makechoice = m_makechoice;
	}
            
	
}
