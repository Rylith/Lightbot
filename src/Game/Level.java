package Game;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

import Game.Button.ButtonType;


public class Level {
	
	
	// TAILLE INITIAL DE LA FENETRE
	private final static Vector2i SIZEINITWINDOW = new Vector2i(1920,1080);
	
	// BACKGROUND
	private static String TILEPATHBACKGROUNG = "ressource/Sprite/back.png";
	
	//LOGO
	private static String TILEPATHLOGO = "ressource/Sprite/Logo.png";
	private static String TILEPATHLOGOXL = "ressource/Sprite/LogoXL.png";
	private static String TILEPATHCLICK = "ressource/Sprite/ClickToLaunch.png";

	// LEVEL
	private static Vector2f POSINITLEVEL = new Vector2f(400,50);
	private static Vector2f POSINITBACK = new Vector2f(0.0f,0);
	private static Vector2f POSINIT = new Vector2f(50.0f,500.0f);
	//private static float DECAL = 100;
	
	private final static Vector2f DECALEVEL = new Vector2f(20.0f,30.0f) ;
	private final static Vector2f SIZELEVEL = new Vector2f(226.0f,133.0f);
	//private final static float SIZELEVELX = 226;
	//private final static float SIZELEVELY = 133;
	
	
	
/** --------------- ATTRIBUTES --------------- */	

	private HashMap<Integer, Button> m_listLevel = new HashMap<Integer, Button>();
	private HashMap<Integer, String> m_listXML = new HashMap<Integer, String>();
	private int m_currentLevel;
	private int m_world;
	private int m_level;
	private String m_tilePath;
	private Game m_game;
	private Vector2i m_screenSize;
	private Vector2f m_decal;
	private Vector2f m_scale;
	
	private static Sprite m_background = null;
	private static Sprite m_logo = null;
	private static Sprite m_click = null;
	
	private boolean m_makechoice;
	private boolean m_launch;
	


	
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
		m_currentLevel = 0;
		m_makechoice = false;
		m_launch = false;
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
		System.out.println("Ajout du fichier "+ chemin +  " pour le Level : " + i);
		
	}
	
	
	/** Dessine l'interface de choix de niveaux 
	 */
	public void drawLogo(){
		m_screenSize = m_game.getWindow().getSize();
		// Ajuste le background a la taille de la fenetre
		if(m_background == null) {
			Texture background = new Texture();
			try {
				background.loadFromFile(Paths.get(TILEPATHBACKGROUNG));
				m_background = new Sprite(background);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		m_scale = new Vector2f(m_screenSize.x / ((float)SIZEINITWINDOW.x), m_screenSize.y / ((float)SIZEINITWINDOW.y));
		m_decal = new Vector2f(((float)SIZEINITWINDOW.x) - m_screenSize.x,((float)SIZEINITWINDOW.y) - m_screenSize.y);
		m_background.setScale(m_scale);
		
		
		Vector2f realScale = m_background.getScale();

		// Generation du logo
		if (m_logo == null) {
			Texture logo = new Texture();
			try {
				logo.loadFromFile(Paths.get(TILEPATHLOGOXL));
				m_logo = new Sprite(logo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		m_logo.setPosition(new Vector2f(10,0));
		m_logo.setScale(realScale);
		
		
		// Generation du texte
		if(m_click == null) {
			Texture click = new Texture();
			try {
				click.loadFromFile(Paths.get(TILEPATHCLICK));
				m_click = new Sprite(click);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Vector2f realPosInitLogo = new Vector2f(m_logo.getPosition().x + 10, m_logo.getPosition().y + m_logo.getGlobalBounds().height + 10);
		m_click.setPosition(realPosInitLogo);
		m_click.setScale(realScale);
			
        // On affiche le background
		m_game.getWindow().draw(m_background);
		
		// On affiche le logo
		m_game.getWindow().draw(m_logo);
		
		// On affiche le texte
		m_game.getWindow().draw(m_click);
		
	}
	
	
	
	/** Dessine l'interface de choix de niveaux 
	 */
	public void draw(){
		
        // On affiche le background
		m_game.getWindow().draw(m_background);
		
		// On affiche le logo
		m_game.getWindow().draw(m_logo);
		
		// On affiche les boutons
		for(Integer mapkey : m_listLevel.keySet()) {
			m_listLevel.get(mapkey).draw(m_game.getWindow());
		}
		
	}
	
	
	
	/** Reload l'interface de choix des niveaux
	 * @param screenSize
	 */
	public void reload() {
		m_screenSize = m_game.getWindow().getSize();		
		
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
		//m_scale = new Vector2f(m_screenSize.x / ((float)SIZEINITWINDOW.x), m_screenSize.y / ((float)SIZEINITWINDOW.y));
		m_background.setScale(m_scale);
		System.out.println("m_scale " + m_scale);
		System.out.println("BackGround Scale : " + m_background.getScale());
		
		
		//Vector2f realScale = m_background.getScale();
		Vector2f realInitPosLevel = Vector2f.add(m_background.getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITLEVEL, POSINITBACK), m_scale));
		Vector2f position = realInitPosLevel;
		
	
		// Generation du logo
		Texture logo = new Texture();
		try {
			logo.loadFromFile(Paths.get(TILEPATHLOGO));
			m_logo = new Sprite(logo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_logo.setPosition(new Vector2f(10,0));
		m_logo.setScale(m_scale);
		
		Vector2f realSizeLevel = Vector2f.componentwiseMul(SIZELEVEL, m_scale);
		Vector2f realDecal = Vector2f.componentwiseMul(DECALEVEL, m_scale);
		
		// Generation des boutons Levels
		for (int i = 0 ; i < m_world*m_level ; i++){
			if (i == 0) {
				position = new Vector2f(realInitPosLevel.x + realDecal.x , realInitPosLevel.y + realDecal.y);
				Button button = new Button(m_tilePath, position, ButtonType.Level, false);
				button.getSprite().setTextureRect(new IntRect(((i%m_level))*(int)SIZELEVEL.x, (i/m_level)*(int)SIZELEVEL.y, (int)SIZELEVEL.x, (int)SIZELEVEL.y));
				button.setScale(m_scale);
				m_listLevel.put(i, button);
				//System.out.println("Creation bouton Level : " + i);
			}
			else if((i % m_level == 0) && i != 0 ) {
				position = new Vector2f(realInitPosLevel.x , position.y + realSizeLevel.x + realDecal.y);
				Button button = new Button(m_tilePath, position, ButtonType.Level, false);
				button.getSprite().setTextureRect(new IntRect(((i%m_level))*(int)SIZELEVEL.x, (i/m_level)*(int)SIZELEVEL.y, (int)SIZELEVEL.x, (int)SIZELEVEL.y));
				button.setScale(m_scale);
				m_listLevel.put(i, button);
				//System.out.println("Creation bouton Level : " + i);
				}
			else {
				position = new Vector2f(position.x + realSizeLevel.x + realDecal.x , position.y);
				Button button = new Button(m_tilePath, position, ButtonType.Level, false);
				button.getSprite().setTextureRect(new IntRect(((i%m_level))*(int)SIZELEVEL.x, (i/m_level)*(int)SIZELEVEL.y, (int)SIZELEVEL.x, (int)SIZELEVEL.y));
				button.setScale(m_scale);
				m_listLevel.put(i, button);
				//System.out.println("Creation bouton Level : " + i);
				}
	        }
	/*} else {
			Texture logo = new Texture();
			try {
				logo.loadFromFile(Paths.get(TILEPATHLOGO));
				m_logo = new Sprite(logo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m_logo.setPosition(new Vector2f(10,0));
			m_logo.setScale(m_scale);
		}*/
	}
	
	
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @require la fenetre window doit etre ouverte
	 */
	public void launchGame(){
		m_game.getWindow().clear();
		drawLogo();
		m_game.getWindow().display();
		for (Event event : m_game.getWindow().pollEvents()) {
			
			// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
            if (event.type == Event.Type.CLOSED) {
                m_game.getWindow().close();
            }
            
            // Si l'utilisateur modifie la taille de la fenetre : on reload l'interface
            if (event.type == Event.Type.RESIZED) {
            	System.out.println("New Size x = " + event.asSizeEvent().size.x + " y = " + event.asSizeEvent().size.y);
            	m_game.setView(event.asSizeEvent().size);
            	//reload();
            }
            
            // Si l'utilisateur clique sur la touche echape : on ferme la fenetre
            if (event.type == Event.Type.KEY_PRESSED) {
            	if (event.asKeyEvent().key == Key.ESCAPE) {
            		m_game.getWindow().close();
            	}
            }
            
            // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un niveau
            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED && getLaunch() == false) {
            	setLaunch(true);
            }
		}
	}
	
	
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @require la fenetre window doit etre ouverte
	 */
	public void launchLevel(){
		m_game.getWindow().clear();
		draw();
		m_game.getWindow().display();
		for (Event event : m_game.getWindow().pollEvents()) {
			// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
            if (event.type == Event.Type.CLOSED) {
                m_game.getWindow().close();
            }
            
            // Si l'utilisateur modifie la taille de la fenetre : on reload l'interface
            if (event.type == Event.Type.RESIZED) {
            	System.out.println("New Size x = " + event.asSizeEvent().size.x + " y = " + event.asSizeEvent().size.y);
            	m_game.setView(event.asSizeEvent().size);
            	reload();
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
                   			System.out.println("On tente de lancer le niveau: " + m_listXML.get(i) + ". Size de la list " + m_listXML.size() + " i = " + i);
                   			System.out.println("Chemin xml : " + m_listXML.get(i));
    	            		m_game.setMap(m_listXML.get(i));
    	            		m_currentLevel = i;
    	            		System.out.println("Lancement du niveau : " + i);
    	            		setMakeChoice(true);
    	            		//m_game.getWindow().close();
    	            	}
            	}
            }
		}
	}


	public void nextLevel() {
		m_currentLevel++;
		m_game.setMap(m_listXML.get(m_currentLevel));
	}

	public boolean getMakeChoice() {
		return m_makechoice;
	}

	public void setMakeChoice(boolean m_makechoice) {
		this.m_makechoice = m_makechoice;
	}
	
	public boolean getLaunch(){
		return m_launch;
	}
	
	public void setLaunch(boolean launch) {
		this.m_launch =launch;
	}
            
	
}
