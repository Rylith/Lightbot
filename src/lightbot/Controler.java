package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.text.Position;

import lightbot.Button.ButtonType;
import lightbot.Frame.FrameType;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;


public class Controler {


	// EMPLACEMENT RESSOURCES GRAPHIQUE
	private final static String TILEPATHACTION = "ressource/Sprite/action.png";
	private final static String TILEPATHFRAME = "ressource/Sprite/";
	private final static String TILEPATHBACKGROUND = "ressource/Sprite/background.jpg";
	
	// POSITION DES ORDRES //
	private final static Vector2f POSINITBUTTON = new Vector2f(58.0f,989.0f);
	private final static Vector2f SIZEBUTTONORDER = new Vector2f(70.0f,70.0f);
	private final static float DECALBUTTON = 46.0f;
	
	// POSITION DES ONGLETS DE SELECTION BOT //
	private final static Vector2f POSINITONGLETBOT = new Vector2f(1455.0f,14.0f);
	private final static Vector2f SIZEONGLETBOT = new Vector2f(84.0f,82.0f);
	
	// POSITION BOUTTON POINTEUR //
	private final static Vector2f POSINITBUTTONPOINTER = new Vector2f(809.0f,953.0f);
	private final static Vector2f SIZEBUTTONCOLOR = new Vector2f(15.0f,20.0f);
	private final static float DECALBUTTONPOINTER = 5.0f;
	
	// POSITION BOUTTON COULEUR //
	private final static Vector2f POSINITBUTTONCOLOR = new Vector2f(1442.0f,994.0f);
	
	// POSITION BOUTTON START / STOP //
	private final static Vector2f POSINITBUTTONSTARTSTOP = new Vector2f(1481.0f,989.0f);
	private final static Vector2f SIZEBUTTONSTARTSTOP = new Vector2f(140.0f,70.0f); 
	
	// POSITION FRAME //
	private final static Vector2f POSINITFRAMEMAIN = new Vector2f(1207.0f,142.0f);
	private final static Vector2f POSINITFRAMEORDER = new Vector2f(24.0f,949.0f); 
	private final static Vector2f SIZEFRAMEMAIN = new Vector2f(668.0f,312.0f);
	private final static Vector2f SIZEFRAMEPROC = new Vector2f(668.0f,216.0f);
	private final static Vector2f SIZEBACKMAIN = new Vector2f(688.0f,856.0f);
	private final static float DECALFRAME = 26.0f;
	private final static int NUMBERFRAME = 3;
	
	// POSITION CADRE ORDER //
	private final static Vector2f POSINITCADREMAIN = new Vector2f(1215.0f,180.0f);
	private final static Vector2f POSINITCADREP1 = new Vector2f(1215.0f,515.0f);
	private final static Vector2f POSINITCADREP2 = new Vector2f(1215.0f,755.0f);
	private final static Vector2f SIZECADREORDER = new Vector2f(70.0f,70.0f);
	private final static Vector2f DECALCADREORDER = new Vector2f(11.0f,16.0f);

	// POSITION BACK //
	private final static Vector2f POSINITBACKORDER = new Vector2f(24.0f,949.0f);
	private final static Vector2f POSINITBACKMAIN = new Vector2f(1197.0f,97.0f);	


/** --------------- ATTRIBUTES --------------- */        
        
	private HashMap<ButtonType, Button> m_listButton = new HashMap<Button.ButtonType, Button>();
	private HashMap<String, Frame> m_listFrame = new HashMap<String, Frame>();
	private Vector<Button> m_mainBasicBot = new Vector<Button>();
	private Vector<Button> m_p1BasicBot = new Vector<Button>();
	private Vector<Button> m_p2BasicBot = new Vector<Button>();
	private Vector<Button> m_mainSmartBot = new Vector<Button>();
	private Vector<Button> m_p1SmartBot = new Vector<Button>();
	private Vector<Button> m_p2SmartBot = new Vector<Button>();
	private Vector<Button> m_orderBasicBot = new Vector<Button>();
	private Vector<Button> m_orderSmartBot = new Vector<Button>();
	private Component m_backOrder;
	private Component m_backMain;
	private Sprite m_background;

	private Game m_game;
	private RenderWindow m_window;
	
	private Vector2i m_screenSize;
	private Vector2f m_decal;
	private Vector2f m_scale;
	
	
	
/** -------------- CONSTRUCTORS -------------- */

	/** Constructeur du Controler
	 * @param screenSize
	 * @param game
	 * @param window
	 */
	public Controler(Vector2i screenSize, Game game, RenderWindow window){
		m_game = game;
		m_window = window;
		reloadInterface(screenSize);
	}
	
	
	
/** ---------------- METHODS ----------------- */	
        
	
	/** Retourne le ButtonType d'un ordre 
	 * @param ordre
	 * @throws Exception 
	 */
	public ButtonType getBoutonType(Order ordre) throws Exception {
		switch (ordre.toString()) {
		case "Move" :
			return ButtonType.Move;
		case "TurnRight" :
			return ButtonType.TurnRight;
		case "TurnLeft" :
			return ButtonType.TurnLeft;
		case "Jump" :
			return ButtonType.Jump;
		case "DoubleJump" :
			return ButtonType.Jump;
		case "Light" :
			return ButtonType.Light;
		case "For" :
			return ButtonType.For;
		case "MallocPointer" :
			return ButtonType.PutP;
		case "AccessPointer" :
			return ButtonType.UseP;
		case "getColor" :
			return ButtonType.Paint;
		case "RemoveColor" :
			return ButtonType.RemoveColor;
		case "Procedure1" :
			return ButtonType.P1;
		case "Procedure2" :
			return ButtonType.P2;
		default :
				throw new Exception();
		}
	}
	
	
	
	/** Reload l'interface
	 * @param screenSize
	 */
	public void reloadInterface(Vector2i screenSize) {
		m_screenSize = screenSize;
		Texture background = new Texture();
		try {
			background.loadFromFile(Paths.get(TILEPATHBACKGROUND));
			m_background = new Sprite(background);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_decal = new Vector2f(1920.0f - m_screenSize.x,1080.0f - m_screenSize.y);
		m_scale = new Vector2f(m_screenSize.x / 1920.0f, m_screenSize.y / 1080.0f);
		
		m_background.setScale(m_scale);
		// BackOrder & BackMain
		m_backOrder = new Component(TILEPATHFRAME+"BackOrder.png",Vector2f.sub(POSINITBACKORDER,new Vector2f(0.0f,m_decal.y)));
		m_backOrder.setScale(m_scale);
		
		if (m_backOrder.getSprite().getPosition().y + m_backOrder.getSprite().getLocalBounds().height < m_screenSize.y) {
			m_backOrder.getSprite().setPosition(new Vector2f(m_backOrder.getSprite().getPosition().x,m_backOrder.getSprite().getPosition().y + ( m_screenSize.y - (m_backOrder.getSprite().getPosition().y + m_backOrder.getSprite().getGlobalBounds().height))-10));
		}
		
		Vector2f scaleFrame;
		float scaleFramex;
		float scaleFramey;
		if (POSINITBACKMAIN.y + SIZEBACKMAIN.y > (m_screenSize.y - (m_screenSize.y - m_backOrder.getSprite().getPosition().y))){
			scaleFramey = (m_screenSize.y-(m_screenSize.y-m_backOrder.getSprite().getPosition().y))/(POSINITBACKMAIN.y + SIZEBACKMAIN.y);
		} else {
			scaleFramey = m_scale.y;
		}
		if (POSINITBACKMAIN.x + SIZEBACKMAIN.x > m_screenSize.x) {
			scaleFramex = m_screenSize.x/(POSINITBACKMAIN.x + SIZEBACKMAIN.x);
		} else {
			scaleFramex = m_scale.x;
		}
		scaleFrame = new Vector2f(scaleFramex,scaleFramey);
		m_backMain = new Component(TILEPATHFRAME+"BackAllProc.png",Vector2f.sub(POSINITBACKMAIN,new Vector2f(m_decal.x,20-m_scale.y)));
		m_backMain.setScale(scaleFrame);
		if(m_backMain.getSprite().getPosition().x + m_backMain.getSprite().getGlobalBounds().width < m_screenSize.x) {
			m_backMain.getSprite().setPosition(new Vector2f(m_backMain.getSprite().getPosition().x + (m_screenSize.x - (m_backMain.getSprite().getPosition().x + m_backMain.getSprite().getGlobalBounds().width)-10),m_backMain.getSprite().getPosition().y));
			
		}
		if(m_backMain.getSprite().getPosition().y + m_backMain.getSprite().getGlobalBounds().height > m_backOrder.getSprite().getPosition().y ) {
			Vector2f newScaleFrame = new Vector2f(1.0f,m_backOrder.getSprite().getPosition().y / (m_backMain.getSprite().getPosition().y + m_backMain.getSprite().getGlobalBounds().height));
			scaleFrame = new Vector2f(scaleFrame.x, scaleFrame.y - (1-newScaleFrame.y));
			m_backMain.setScale(scaleFrame);
		}

		
		// Frames
		Vector2f realInitFrameMain = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITFRAMEMAIN, POSINITBACKMAIN), scaleFrame));
		float realDecalFrame = DECALFRAME * scaleFrame.y;
		Vector2f realSizeFrameMain = Vector2f.componentwiseMul(SIZEFRAMEMAIN, scaleFrame);
		Vector2f realSizeFrameProc = Vector2f.componentwiseMul(SIZEFRAMEPROC, scaleFrame);
		Frame f_main = new Frame(TILEPATHFRAME+"BackProc.png", realInitFrameMain, FrameType.Main); //Main
		f_main.setScale(scaleFrame);
		Frame f_p1 = new Frame(TILEPATHFRAME+"BackProc.png", new Vector2f(realInitFrameMain.x , realInitFrameMain.y + realSizeFrameMain.y +  realDecalFrame), FrameType.P1); //P1
		f_p1.setScale(scaleFrame);
		Frame f_p2 = new Frame(TILEPATHFRAME+"BackProc.png", new Vector2f(realInitFrameMain.x , realInitFrameMain.y + realSizeFrameMain.y + realSizeFrameProc.y + (realDecalFrame * 2)), FrameType.P2); //P2
		f_p2.setScale(scaleFrame);
		Frame f_orderList = new Frame(TILEPATHFRAME+"BackOrder.png",m_backOrder.getSprite().getPosition(), FrameType.OrderList); //OrderList
		f_orderList.setScale(m_scale);
		
		
		//Boutons representant les ordres
		Vector2f realInitPosButton = Vector2f.add(m_backOrder.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITBUTTON, POSINITBACKORDER), m_scale));
		float realDecalButton = DECALBUTTON * m_scale.x;
		Vector2f realSizeButton = Vector2f.componentwiseMul(SIZEBUTTONORDER, m_scale);
		Button b_move = new Button(TILEPATHACTION, realInitPosButton, ButtonType.Move, true); //Avancer
		b_move.setScale(m_scale);
		Button b_turnRight = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + realSizeButton.x + realDecalButton ,realInitPosButton.y), ButtonType.TurnRight, true); //Tourner droite
		b_turnRight.setScale(m_scale);
		Button b_turnLeft = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 2),realInitPosButton.y), ButtonType.TurnLeft, true); //Tourner gauche
		b_turnLeft.setScale(m_scale);
		Button b_jump = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 3),realInitPosButton.y), ButtonType.Jump, true); //Sauter
		b_jump.setScale(m_scale);
		Button b_light = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 4),realInitPosButton.y), ButtonType.Light, true); //Allumer
		b_light.setScale(m_scale);
		Button b_for = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 5),realInitPosButton.y), ButtonType.For, true); //For
		b_for.setScale(m_scale);
		Button b_putP = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 6),realInitPosButton.y), ButtonType.PutP, true); //Poser pointeur
		b_putP.setScale(m_scale);
		Button b_useP = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 7),realInitPosButton.y), ButtonType.UseP, true); //Utiliser pointeur
		b_useP.setScale(m_scale);
		Button b_paint = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 8),realInitPosButton.y), ButtonType.Paint, true); //Se peindre
		b_paint.setScale(m_scale);
		Button b_removeColor = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 9),realInitPosButton.y), ButtonType.RemoveColor, true); //Se laver
		b_removeColor.setScale(m_scale);
		Button b_p1 = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 10),realInitPosButton.y), ButtonType.P1, true); //P1
		b_p1.setScale(m_scale);
		Button b_p2 = new Button(TILEPATHACTION, new Vector2f(realInitPosButton.x + ((realSizeButton.x + realDecalButton) * 11),realInitPosButton.y), ButtonType.P2, true); //P2
		b_p2.setScale(m_scale);
				
		// Boutons representants les Bots
		Vector2f realInitPosOngletBot = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITONGLETBOT, POSINITBACKMAIN), m_scale));
		Vector2f realSizeOngletBot = Vector2f.componentwiseMul(SIZEONGLETBOT, m_scale);
		Button b_basicBot = new Button(TILEPATHACTION, realInitPosOngletBot, ButtonType.BasicBot, false); //SmartBot [presse]
		b_basicBot.setScale(m_scale);
		Button b_smartBot = new Button(TILEPATHACTION, new Vector2f(realInitPosOngletBot.x + realSizeOngletBot.x, realInitPosOngletBot.y), ButtonType.SmartBot, false); //BasicBot [non presse]
		b_smartBot.setScale(m_scale);
		
		// Boutons representants les couleurs des pointeurs et instructions
		Vector2f realInitPosButtonPointer = Vector2f.add(m_backOrder.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITBUTTONPOINTER, POSINITBACKORDER), m_scale));
		Vector2f realSizeButtonPointer = Vector2f.componentwiseMul(SIZEBUTTONCOLOR, m_scale);
		float realDecalButtonPointer = DECALBUTTONPOINTER * m_scale.x;
		Button b_green = new Button(TILEPATHACTION, realInitPosButtonPointer, ButtonType.PushGreen, false); //Bouton vert [presse]
		b_green.setScale(m_scale);
		Button b_yellow = new Button(TILEPATHACTION, new Vector2f(realInitPosButtonPointer.x + realSizeButtonPointer.x + realDecalButtonPointer , realInitPosButtonPointer.y), ButtonType.PushYellow, false); //Bouton jaune [non presse]
		b_yellow.setScale(m_scale);
		Button b_red = new Button(TILEPATHACTION, new Vector2f(realInitPosButtonPointer.x + ((realSizeButtonPointer.x + realDecalButtonPointer) * 2) , realInitPosButtonPointer.y), ButtonType.PushRed, false); //Bouton rouge [non presse]
		b_red.setScale(m_scale);
		Button b_blue = new Button(TILEPATHACTION, new Vector2f(realInitPosButtonPointer.x + ((realSizeButtonPointer.x + realDecalButtonPointer) * 3) , realInitPosButtonPointer.y), ButtonType.PushBlue, false); //Bouton bleue [non presse]
		b_blue.setScale(m_scale);
		
		Vector2f realInitPosButtonColor = Vector2f.add(m_backOrder.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITBUTTONCOLOR, POSINITBACKORDER), m_scale));
		Vector2f realSizeButtonColor = Vector2f.componentwiseMul(SIZEBUTTONCOLOR, m_scale);
		Button b_grey = new Button(TILEPATHACTION, realInitPosButtonColor, ButtonType.PushGrey, false); //Bouton gris [presse]
		b_grey.setScale(m_scale);
		Button b_magenta = new Button(TILEPATHACTION, new Vector2f(realInitPosButtonColor.x , realInitPosButtonColor.y + realSizeButtonColor.y ), ButtonType.PushMagenta, false); //Bouton magenta [non presse]
		b_magenta.setScale(m_scale);
		Button b_cyan = new Button(TILEPATHACTION, new Vector2f(realInitPosButtonColor.x , realInitPosButtonColor.y + (realSizeButtonColor.y * 2)), ButtonType.PushCyan, false); //Bouton cyan [non presse]
		b_cyan.setScale(m_scale);
		
		//Boutons run
		Vector2f realInitPosButtonStartStop = Vector2f.add(m_backOrder.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITBUTTONSTARTSTOP, POSINITBACKORDER), m_scale));
		Button b_run = new Button(TILEPATHACTION, realInitPosButtonStartStop, ButtonType.Run, false); //Bouton run
		b_run.setScale(m_scale);
		

		
		// Clear des Vector
		m_listButton.clear();
		m_listFrame.clear();
		m_mainBasicBot.clear(); 
		m_p1BasicBot.clear();
		m_p2BasicBot.clear();
		m_mainSmartBot.clear();
		m_p1SmartBot.clear();
		m_p2SmartBot.clear();
		m_orderBasicBot.clear();
		m_orderSmartBot.clear();
		
		m_listButton.put(ButtonType.Move, b_move);
		m_listButton.put(ButtonType.TurnRight, b_turnRight);
		m_listButton.put(ButtonType.TurnLeft, b_turnLeft); 
		m_listButton.put(ButtonType.Jump, b_jump); 
		m_listButton.put(ButtonType.Light, b_light); 
		m_listButton.put(ButtonType.For, b_for); 
		m_listButton.put(ButtonType.PutP, b_putP);
		m_listButton.put(ButtonType.UseP, b_useP); 
		m_listButton.put(ButtonType.Paint, b_paint);
		m_listButton.put(ButtonType.RemoveColor, b_removeColor);
		m_listButton.put(ButtonType.P1, b_p1);
		m_listButton.put(ButtonType.P2, b_p2);
		
		m_listButton.put(ButtonType.BasicBot, b_basicBot);
		m_listButton.put(ButtonType.SmartBot, b_smartBot);
		
		m_listButton.put(ButtonType.PushGreen, b_green); 
		m_listButton.put(ButtonType.PushYellow, b_yellow); 
		m_listButton.put(ButtonType.PushRed, b_red);
		m_listButton.put(ButtonType.PushBlue,b_blue);
		
		m_listButton.put(ButtonType.PushGrey, b_grey); 
		m_listButton.put(ButtonType.PushMagenta, b_magenta); 
		m_listButton.put(ButtonType.PushCyan, b_cyan);
		m_listButton.put(ButtonType.Run, b_run);
		
		m_listFrame.put("main", f_main);
		m_listFrame.put("p1", f_p1);
		m_listFrame.put("p2", f_p2);
		m_listFrame.put("orderlist", f_orderList);
	}
	
	
	
	/** Initialisation des cadres dans les frames et les ordres visible selon le Level
	 *
	 */
	public void init(){
		Vector2f realScale = m_backMain.getScale();
		Vector2f realInitPosCadreMain = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREMAIN, POSINITBACKMAIN), realScale));
		Vector2f realInitPosCadreP1 = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREP1, POSINITBACKMAIN), realScale));
		Vector2f realInitPosCadreP2 = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREP2, POSINITBACKMAIN), realScale));
		Vector2f realDecalCadreMain = Vector2f.componentwiseMul(DECALCADREORDER, realScale);
		Vector2f realSizeCadreOrder = Vector2f.componentwiseMul(SIZECADREORDER, realScale);
		Vector2f pos = realInitPosCadreMain;
		Character currentChar;
		for (int g = 0; g < 2 ; g++) {
			for (int j = 0; j < NUMBERFRAME; j++) {
				switch(j) {
				case 0:
					pos = new Vector2f(realInitPosCadreMain.x,realInitPosCadreMain.y);
					break;
				case 1:
					pos = new Vector2f(realInitPosCadreP1.x,realInitPosCadreP1.y);
					break;
				case 2:
					pos = new Vector2f(realInitPosCadreP2.x,realInitPosCadreP2.y);
					break;
				}
				if (g == 0 ) {
					currentChar = m_game.getCharacter().get("BasicBot"); 	
				} else {
					currentChar = m_game.getCharacter().get("SmartBot");
				}
				// initialisation des cadres dans le main du BasicBot
				// initialisation des cadres dans p1 du BasicBot
				// initialisation des cadres dans P2 du BasicBot
				// initialisation des cadres dans le main du SmartBot
				// initialisation des cadres dans p1 du SmartBot
				// initialisation des cadres dans p2 du SmartBot
				for (int i = 0 ; i < currentChar.getLimitOrder().get(j) ; i++) {
					if((i % 8 == 0) && i != 0 ) {
						pos = new Vector2f(realInitPosCadreMain.x,pos.y + realSizeCadreOrder.y + realDecalCadreMain.y);
					}
					else if (i != 0){
						pos = new Vector2f(pos.x + realSizeCadreOrder.x + realDecalCadreMain.x,pos.y);
					}
					Button cadre = new Button(TILEPATHACTION, pos , ButtonType.Cadre, false);
					cadre.setScale(m_scale);
					if(g == 0) {
						switch(j) {
						case 0:
							m_mainBasicBot.addElement(cadre); //ajout un cadre
							break;
						case 1:
							m_p1BasicBot.addElement(cadre); //ajout un cadre
							break;
						case 2:
							m_p2BasicBot.addElement(cadre); //ajout un cadre
							break;
						}
					} else {
						switch(j) {
						case 0:
							m_mainSmartBot.addElement(cadre); //ajout un cadre
							break;
						case 1:
							m_p1SmartBot.addElement(cadre); //ajout un cadre
							break;
						case 2:
							m_p2SmartBot.addElement(cadre); //ajout un cadre
							break;
						}
					}
				}
			}
		}
		// initialisation des ordres visibles du BasicBot
		initOrder();
		m_listFrame.get(0).ActiveFrame(false);
		
		/*TODO*/
		// initialisation des ordres visibles du SmartBot
		/*TODO*/
	}
	
	
	/** Initialise les Ordres disponible pour le niveau charge
	 */
	private void initOrder() {
		/* TODO */
		for(int i = 0; i < 13;i++){
			m_listButton.get(i).setVisibility(true);
		}
	}
	
	
	/** Dessine une HashMap de boutons
	 * @param hash
	 */
	private void drawButton(HashMap<ButtonType, Button> hash) {
		Collection<Button> col = hash.values();
		Button Tab[] = new Button[hash.size()];
		col.toArray(Tab);
		for(int i = 0; i < hash.size(); i++) {
			Tab[i].draw(m_window);
		}
	}
	
	/** Dessine un Vector de boutons
	 * @param vec
	 */
	private void drawButton(Vector<Button> vec) {
		for(int i = 0; i < vec.size(); i++) {
			vec.get(i).draw(m_window);
		}
	}
	
	
	/**
	 * Notify pour l'interface
	 */
	//public void notify(){
		
	//}
	
	/** Update le visuel de l'interface 
	 */
	public void update(){
		
        /** On affiche les components de fond */
		m_window.draw(m_background);
		m_backMain.draw(m_window);
		m_backOrder.draw(m_window);
		
		/** On affiche les frames */
		for(int i = 0; i < m_listFrame.size();i++) {
			m_listFrame.get(i).draw(m_window);
		}
		/** On affiche les boutons ayant leur attribut m_visible a true */
		drawButton(m_listButton);
		
		/** On affiche le bon nombre de cadre d'instructions dans main p1 et p2 a partir de getLimitOrder() */
		/** On affiche les instructions contenu dans le main, p1 et p2 du Bot actif */
		if(m_listButton.get(13).isActive()) {
			drawButton(m_mainBasicBot);
			drawButton(m_p1BasicBot);
			drawButton(m_p2BasicBot);
			drawButton(m_orderBasicBot);
		} else {
			drawButton(m_mainSmartBot);
			drawButton(m_p1SmartBot);
			drawButton(m_p2SmartBot);
			drawButton(m_orderSmartBot);
		}
	}
	
	
	/** Ajoute l'ordre dans la frame active & dans m_listOrder du Character 
	 * @param type
	 * @param order : l'ordre correspondant au bouton
	 */
	private void addOrder(ButtonType type, Order order) {
		//si le BasicBot est active
		if (m_listButton.get(ButtonType.BasicBot).isActive()) { 
			//si main est active
			if (m_listFrame.get("Main").isActive()){ 
				m_mainBasicBot.addElement(m_listButton.get(type));
				m_game.addOrder(0, 0, order);
			}
			//si p1 est active
			else if (m_listFrame.get("P1").isActive()){
				m_p1BasicBot.addElement(m_listButton.get(type));
				m_game.addOrder(1, 0, order);
			}
			//si p2 est active
			else if (m_listFrame.get("P2").isActive()){ 
				m_p2BasicBot.addElement(m_listButton.get(type));
				m_game.addOrder(2, 0, order);
			}
		}
		//si le SmartBot est active
		else if (m_listButton.get(ButtonType.SmartBot).isActive()){ 
			//si main est active
			if (m_listFrame.get("Main").isActive()){ 
				m_mainSmartBot.addElement(m_listButton.get(type));
				m_game.addOrder(0, 0, order);
			}
			//si p1 est active
			else if (m_listFrame.get("P1").isActive()){
				m_p1SmartBot.addElement(m_listButton.get(type));
				m_game.addOrder(1, 0, order);
			}
			//si p2 est active
			else if (m_listFrame.get("P2").isActive()){ 
				m_p2SmartBot.addElement(m_listButton.get(type));
				m_game.addOrder(2, 0, order);
			}
		}
	}

	
	
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @require la fenetre window doit etre ouverte
	 */
		
	/* TODO : SmartBot & BasicBot + Affichage dans la fenetre des changement */
		
	public void supervise(){
		for (Event event : m_window.pollEvents()) {
			
        	// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
            if (event.type == Event.Type.CLOSED) {
                m_window.close();
            }
            
            // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un bouton ou d'une view
            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            	Vector2i mouse_pos = Mouse.getPosition(m_window); 
            	
            	//Move : on ajoute l'ordre dans la view active & dans m_listOrder (Character)
            	if (m_listButton.get(0).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.Move, order1);
            	}
            	
            	//TurnRight : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)	
            	else if (m_listButton.get(1).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.TurnRight, order1);
            	}
            	
            	//TurnLeft : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(2).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.TurnLeft, order1);
            	}
            	
            	//Jump : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(3).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.Jump, order1);
            	}
            	
            	//putP : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(4).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.PutP, order1);
            	}
            	
            	//useP : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(5).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.UseP, order1);
            	}
            	
            	//paint : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(6).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.Paint, order1);
            	}
            	
            	//removeColor : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(7).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.RemoveColor, order1);
            	}
            	
            	//P1 : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(8).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.P1, order1);
            	}
            	
            	//P2 : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            	else if (m_listButton.get(9).isClicked(mouse_pos)) { 
            		Order order1 = null;
            		if (m_listButton.get(ButtonType.BasicBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("BasicBot"), m_game.getEngine());
            		}
            		else if (m_listButton.get(ButtonType.SmartBot).isActive()){
            			order1 = new Move(m_game.getCharacter().get("SmartBot"), m_game.getEngine());
            		}
            		else {
          
            		}
            		addOrder(ButtonType.P2, order1);
            	}
            	
            	//BasicBot : on charge les procedures du BasicBot & on passe le BasicBot en actif
            	else if (m_listButton.get(10).isClicked(mouse_pos)) { 
            		
            		/*TODO*/
            	}
            	
            	//SmartBot : on charge les procedures du SmartBot & on passe le BasicBot en non actif
            	else if (m_listButton.get(11).isClicked(mouse_pos)) { 
            		
            		/*TODO*/
            	}
            	
            	else if (m_listButton.get(12).isClicked(mouse_pos)) { 
            		//Yellow : on active le bouton et on desactive les autres (blue, green, red)
            		m_listButton.get(12).ActiveButton(true);
            		m_listButton.get(13).ActiveButton(false);
            		m_listButton.get(14).ActiveButton(false);
            		m_listButton.get(15).ActiveButton(false);
            		//on affiche les pointeurs yellow
            	}
            	
            	else if (m_listButton.get(13).isClicked(mouse_pos)) { 
            		//Blue : on active le bouton et on desactive les autres (yellow, green, red)
            		m_listButton.get(12).ActiveButton(false);
            		m_listButton.get(13).ActiveButton(true);
            		m_listButton.get(14).ActiveButton(false);
            		m_listButton.get(15).ActiveButton(false);
            		//on affiche les pointeurs blue
            	}
            	
            	else if (m_listButton.get(14).isClicked(mouse_pos)) { 
            		//Green : on active le bouton et on desactive les autres (yellow, blue, red)
            		m_listButton.get(12).ActiveButton(false);
            		m_listButton.get(13).ActiveButton(false);
            		m_listButton.get(14).ActiveButton(true);
            		m_listButton.get(15).ActiveButton(false);
            	}
            	
            	else if (m_listButton.get(15).isClicked(mouse_pos)) { 
            		//Red : on active le bouton et on desactive les autres (yellow, blue, green)
            		m_listButton.get(12).ActiveButton(false);
            		m_listButton.get(13).ActiveButton(false);
            		m_listButton.get(14).ActiveButton(false);
            		m_listButton.get(15).ActiveButton(true);
            		//on affiche les pointeurs red
            	}
            	
            	else if (m_listButton.get(16).isClicked(mouse_pos)) { 
            		//Grey : on active le bouton et on desactive les autres (magenta, cyan)
            		m_listButton.get(16).ActiveButton(true);
            		m_listButton.get(17).ActiveButton(false);
            		m_listButton.get(18).ActiveButton(false);
            		//on affiche les instructions grey
            	}
            	
            	else if (m_listButton.get(17).isClicked(mouse_pos)) { 
            		//Magenta : on active le bouton et on desactive les autres (grey, cyan)
            		m_listButton.get(16).ActiveButton(false);
            		m_listButton.get(17).ActiveButton(true);
            		m_listButton.get(18).ActiveButton(false);
            		//on affiche les instructions magenta
            	}
            	
            	else if (m_listButton.get(18).isClicked(mouse_pos)) { 
            		//Cyan : on active le bouton et on desactive les autres (grey, magenta)
            		m_listButton.get(16).ActiveButton(false);
            		m_listButton.get(17).ActiveButton(false);
            		m_listButton.get(18).ActiveButton(true);
            		//on affiche les instructions magenta
            	}
            	
            	else if (m_listButton.get(19).isClicked(mouse_pos)){
            		//Run : on active le Run qui se change alors en Stop
            		m_listButton.get(19).ActiveButton(true);
            		//on affiche le stop
            	}
            	
            	else if (m_listFrame.get(0).isClicked(mouse_pos)) {
            		//Main : on active la frame main et on desactive p1 et p2
            		m_listFrame.get(0).ActiveFrame(true);
            		m_listFrame.get(1).ActiveFrame(false);
            		m_listFrame.get(2).ActiveFrame(false);
            		//on affiche le main active avec ses composants
            	}
            	
            	else if (m_listFrame.get(1).isClicked(mouse_pos)) {
            		//P1 : on active la frame p1 et on desactive main et p2
            		m_listFrame.get(0).ActiveFrame(false);
            		m_listFrame.get(1).ActiveFrame(true);
            		m_listFrame.get(2).ActiveFrame(false);
            		//on affiche P1 active avec ses composants
            	}
            	
            	else if (m_listFrame.get(2).isClicked(mouse_pos)) {
            		//P2 : on active la frame p2 et on desactive main et p1
            		m_listFrame.get(0).ActiveFrame(false);
            		m_listFrame.get(1).ActiveFrame(false);
            		m_listFrame.get(2).ActiveFrame(true);
            		//on affiche P2 active avec ses composants
            	}
            }
		}
	}
}

