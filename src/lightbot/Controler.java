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
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;


public class Controler {


	// EMPLACEMENT RESSOURCES GRAPHIQUE
	private final static String TILEPATHACTION = "ressource/Sprite/action.png";
	private final static String TILEPATHFRAME = "ressource/Sprite/";
	private final static String TILEPATHBACKGROUND = "ressource/Sprite/back.png";
	
	// POSITION BOUTTON MENU //
	private final static Vector2f POSINITBUTTONMENU = new Vector2f(0,0);
	
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
	
	// TAILLE INITIAL DE LA FENETRE
	private final static Vector2i SIZEINITWINDOW = new Vector2i(1920,1080);


/** --------------- ATTRIBUTES --------------- */        
        
	private HashMap<ButtonType, Button> m_listButton = new HashMap<Button.ButtonType, Button>();
	private HashMap<FrameType, Frame> m_listFrame = new HashMap<FrameType, Frame>();
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

	private Vector2i m_screenSize;
	private Vector2f m_decal;
	private Vector2f m_scale;
	
	
	
/** -------------- CONSTRUCTORS -------------- */

	/** Constructeur du Controler
	 * @param screenSize
	 * @param game
	 * @param window
	 */
	public Controler(Vector2i screenSize, Game game){
		m_game = game;
		reloadInterface(screenSize);
	}
	
	
	
/** ---------------- METHODS ----------------- */	
        
	
	/** Retourne le ButtonType d'un ordre 
	 * @param ordre
	 * @throws Exception 
	 */
	public ButtonType ButtonTypeToButton(Order ordre) throws Exception {
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
		m_decal = new Vector2f(((float)SIZEINITWINDOW.x) - m_screenSize.x,((float)SIZEINITWINDOW.y) - m_screenSize.y);
		m_scale = new Vector2f(m_screenSize.x / ((float)SIZEINITWINDOW.x), m_screenSize.y / ((float)SIZEINITWINDOW.y));
		m_background.setScale(m_scale);
		
		// BackOrder & BackMain
		m_backOrder = new Component(TILEPATHFRAME+"BackOrder.png",Vector2f.sub(POSINITBACKORDER,new Vector2f(0.0f,m_decal.y)));
		m_backOrder.setScale(m_scale);
		
		if (m_backOrder.getSprite().getPosition().y + m_backOrder.getSprite().getGlobalBounds().height < m_screenSize.y) {
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
		//Button b_stop = new Button(TILEPATHACTION, realInitPosButtonStartStop, ButtonType.Stop, false); //Bouton run
		//b_stop.setScale(m_scale);

		
		//Bouton Menu
		Button b_menu = new Button(TILEPATHACTION, POSINITBUTTONMENU, ButtonType.Menu, false); //Bouton Menu
		b_menu.setScale(m_scale);

		
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
		//m_listButton.put(ButtonType.Stop, b_stop);
		
		m_listButton.put(ButtonType.Menu, b_menu);
		
		m_listFrame.put(FrameType.Main, f_main);
		m_listFrame.put(FrameType.P1, f_p1);
		m_listFrame.put(FrameType.P2, f_p2);
		m_listFrame.put(FrameType.OrderList, f_orderList);
		init();
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
				System.out.println("Limit de " + currentChar.toString() + " pour la frame " + j + "est de : " + currentChar.getLimitOrder().get(j) );
				for (int i = 0 ; i < currentChar.getLimitOrder().get(j) ; i++) {
					if((i % 8 == 0) && i != 0 ) {
						pos = new Vector2f(realInitPosCadreMain.x,pos.y + realSizeCadreOrder.y + realDecalCadreMain.y);
					}
					else if (i != 0){
						pos = new Vector2f(pos.x + realSizeCadreOrder.x + realDecalCadreMain.x,pos.y);
					}
					Button cadre = new Button(TILEPATHACTION, pos , ButtonType.Cadre, false);
					cadre.setScale(realScale);
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
		m_listFrame.get(FrameType.Main).ActiveFrame(true);
		
		/*TODO*/
		// initialisation des ordres visibles du SmartBot
		/*TODO*/
	}
	
	
	/** Initialise les Ordres disponible pour le niveau charge
	 */
	private void initOrder() {
		/* TODO */
		for(ButtonType mapkey : m_listButton.keySet()) {
			if(mapkey.ordinal() > ButtonType.P2.ordinal()) {
				break;
			}
			m_listButton.get(mapkey).setVisibility(true);
		}
	}
	
	
	/** Dessine une HashMap de boutons
	 * @param hash
	 */
	private void drawButton(HashMap<ButtonType, Button> hash) {
		for (ButtonType mapKey : hash.keySet()) {
			hash.get(mapKey).draw(m_game.getWindow());
		}
	}
	
	
	/** Dessine un Vector de boutons
	 * @param vec
	 */
	private void drawButton(Vector<Button> vec) {
		for(int i = 0; i < vec.size(); i++) {
			vec.get(i).draw(m_game.getWindow());
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
		m_game.getWindow().draw(m_background);
		m_backMain.draw(m_game.getWindow());
		m_backOrder.draw(m_game.getWindow());
		
		/** On affiche les frames */
		for(FrameType mapkey : m_listFrame.keySet()) {
			m_listFrame.get(mapkey).draw(m_game.getWindow());
		}
		/** On affiche les boutons ayant leur attribut m_visible a true */
		drawButton(m_listButton);
		
		/** On affiche le bon nombre de cadre d'instructions dans main p1 et p2 a partir de getLimitOrder() */
		/** On affiche les instructions contenu dans le main, p1 et p2 du Bot actif */
		if(m_listButton.get(ButtonType.BasicBot).isActive()) {
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
		m_game.draw();
	}
	
	private Vector2f getRealPosOrderAt(int pos, FrameType frameType) {
		Vector2f realScale = m_backMain.getScale();
		Vector2f realDecalCadreMain = Vector2f.componentwiseMul(DECALCADREORDER, realScale);
		Vector2f realSizeCadreOrder = Vector2f.componentwiseMul(SIZECADREORDER, realScale);
		Vector2f realInitPosOrder = new Vector2f(0.0f,0.0f);
		switch(frameType.ordinal()) {
		case 0:
			realInitPosOrder = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREMAIN, POSINITBACKMAIN), realScale));
			break;
		case 1:
			realInitPosOrder = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREP1, POSINITBACKMAIN), realScale));
			break;
		case 2:
			realInitPosOrder = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREP2, POSINITBACKMAIN), realScale));
			break;
		}
		
		return Vector2f.add(realInitPosOrder,new Vector2f((realDecalCadreMain.x+realSizeCadreOrder.x)*(pos%8),(float) ((realDecalCadreMain.y+realSizeCadreOrder.y) * Math.floor(pos/8))));	
	}

	
	private void addOrderToFrame(Vector<Button> listOrder, FrameType frameType, ButtonType buttonType, Color color){
		int pos = 0;
		while(listOrder.get(pos).getType() != ButtonType.Cadre){
			pos++;
		}
		
		Button newOrder = new Button(TILEPATHACTION,getRealPosOrderAt(pos, frameType),buttonType,false);
		newOrder.setScale(m_backMain.getScale());
		newOrder.setColor(m_listButton.get(buttonType).getColor());
		newOrder.setColorPointeur(m_listButton.get(buttonType).getColorPointer());
		listOrder.set(pos, newOrder);
	}
	
	
	/** Ajoute l'ordre dans la frame active & dans m_listOrder du Character 
	 * @param type : type de bouton
	 * @param color : couleur du bouton
	 */
	private void addOrder(Button button, Color color) {
		//System.out.println("On ajoute l'ordre " + type.name());
		//si le BasicBot est active
		if (m_listButton.get(ButtonType.BasicBot).isActive()) { 
			//si main est active && on arrive a ajouter l'ordre
			//System.out.print("au BasicBot ");
			if (m_listFrame.get(FrameType.Main).isActive() && m_game.addOrder(0, "BasicBot", button, color)){ 
				//System.out.print("dans main\n");
				addOrderToFrame(m_mainBasicBot,FrameType.Main,button.getType(),color);
			}
			//si p1 est active && on arrive a ajouter l'ordre
			else if (m_listFrame.get(FrameType.P1).isActive() && m_game.addOrder(1, "BasicBot", button, color)){
				//System.out.print("dans p1\n");
				addOrderToFrame(m_p1BasicBot,FrameType.P1,button.getType(),color);
			}
			//si p2 est active && on arrive a ajouter l'ordre
			else if (m_listFrame.get(FrameType.P2).isActive() && m_game.addOrder(2, "BasicBot", button, color)){
				//System.out.print("dans p2");
				addOrderToFrame(m_p2BasicBot,FrameType.P2,button.getType(),color);
			}
		}
		//si le SmartBot est active
		else if (m_listButton.get(ButtonType.SmartBot).isActive()){ 
			//System.out.print("au SmartBot ");
			//si main est active && on arrive a ajouter l'ordre
			if (m_listFrame.get(FrameType.Main).isActive() && m_game.addOrder(0, "SmartBot", button, color)){
				//System.out.print("dans main");
				addOrderToFrame(m_mainSmartBot,FrameType.Main,button.getType(),color);
			}
			//si p1 est active && on arrive a ajouter l'ordre
			else if (m_listFrame.get(FrameType.P1).isActive() && m_game.addOrder(1, "SmartBot", button, color)){
				//System.out.print("dans p2");
				addOrderToFrame(m_p1SmartBot,FrameType.P1,button.getType(),color);
			}
			//si p2 est active && on arrive a ajouter l'ordre
			else if (m_listFrame.get(FrameType.P2).isActive() && m_game.addOrder(2, "SmartBot", button, color)){
				//System.out.print("dans p2");
				addOrderToFrame(m_p2SmartBot,FrameType.P2,button.getType(),color);

			}
		}
	}

	/**
	 * Met a jour les couleurs des ordres
	 * @param color
	 */
	private void updateColorOrder(Color color){
		for(ButtonType order : ButtonType.values()) {
			if (order.ordinal() > ButtonType.P2.ordinal()) {
				break;
			}
			m_listButton.get(order).setColor(color);
		}
	}
	
	/**
	 * Met a jour les couleurs des pointeurs
	 * @param color
	 */
	private void updateColorPointer(Color color) {
		for(ButtonType order : ButtonType.values()){
			if (order.ordinal() >= ButtonType.Paint.ordinal()) {
				break;
			} else if (order.ordinal() > ButtonType.For.ordinal()) {
				m_listButton.get(order).setColorPointeur(color);
			}
		}
	}
	
	
	private void removeOrder(Vector<Button> listOrder, int pos, FrameType frameType) {
		int next = pos + 1;
		//System.out.println("SizeListOrder : " + listOrder.size());
		while(pos < listOrder.size() && next < listOrder.size() && listOrder.get(pos).getType() != ButtonType.Cadre && listOrder.get(next).getType() != ButtonType.Cadre) {
			Button newBut = new Button(TILEPATHACTION,getRealPosOrderAt(pos, frameType),listOrder.get(next).getType(),false);
			newBut.setScale(m_backMain.getScale());
			newBut.setColor(listOrder.get(next).getColor());
			newBut.setColorPointeur(listOrder.get(next).getColorPointer());
			listOrder.set(pos, newBut);
			//System.out.println("On set " + listOrder.get(next).getType().name() + "a la pos " + pos);
			pos++;
			next++;
		}
		Button cadre = new Button(TILEPATHACTION,getRealPosOrderAt(pos, frameType),ButtonType.Cadre,false);
		cadre.setScale(m_backMain.getScale());
		listOrder.set(pos,cadre);
		//System.out.println("On set " + cadre.getType().name() + "a la pos " + pos);
	}
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @require la fenetre window doit etre ouverte
	 */
	public void supervise(){
		if(!m_game.getStateSimulation() && m_listButton.get(ButtonType.Run).isActive()) {
			System.out.println("Break SImulation");
			m_listButton.get(ButtonType.Run).ActiveButton(false);
		}
		for (Event event : m_game.getWindow().pollEvents()) {
			
        	// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
            if (event.type == Event.Type.CLOSED) {
                m_game.getWindow().close();
            }
            
            if (event.type == Event.Type.RESIZED) {
            	System.out.println("New Size x = " + event.asSizeEvent().size.x + " y = " + event.asSizeEvent().size.y);
            	m_game.setView(event.asSizeEvent().size);
            	reloadInterface(event.asSizeEvent().size);
            }
            
            if (event.type == Event.Type.KEY_PRESSED) {
            	if (event.asKeyEvent().key == Key.ESCAPE) {
            		m_game.getWindow().close();
            	}
            }
            
            // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un bouton ou d'une view
            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            	Vector2i mouse_pos = Mouse.getPosition(m_game.getWindow()); 
            	
            	if (m_listButton.get(ButtonType.Run).isActive()) {
            		
            		if (m_listButton.get(ButtonType.Run).isClicked(mouse_pos)) {        			
                		m_listButton.get(ButtonType.Run).ActiveButton(false);
                		// TODO -- Arret de la simulation
                		m_game.setStateSimulation(false);
                	}
            		//m_listButton.get(ButtonType.Run).ActiveButton(true);
            	} else {
	            	
	            	//Move : on ajoute l'ordre dans la view active & dans m_listOrder (Character)
	            	if (m_listButton.get(ButtonType.Move).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.Move), m_listButton.get(ButtonType.Move).getColor());
	            	}
	            	
	            	//TurnRight : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)	
	            	else if (m_listButton.get(ButtonType.TurnRight).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.TurnRight), m_listButton.get(ButtonType.TurnRight).getColor());
	            	}
	            	
	            	//TurnLeft : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.TurnLeft).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.TurnLeft), m_listButton.get(ButtonType.TurnLeft).getColor());
	            	}
	            	
	            	//Jump : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.Jump).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.Jump), m_listButton.get(ButtonType.Jump).getColor());
	            	}
	            	
	            	//Light : On ajoute l'ordre dans la frame active & dans m_listeOrder (Character)
	            	else if (m_listButton.get(ButtonType.Light).isClicked(mouse_pos)){
	            		addOrder(m_listButton.get(ButtonType.Light), m_listButton.get(ButtonType.Light).getColor());
	            	}
	            	
	            	//For : On ajoute l'ordre dans la frame active & dans m_listeOrder (Character)
	            	else if (m_listButton.get(ButtonType.For).isClicked(mouse_pos)){
	            		addOrder(m_listButton.get(ButtonType.For), m_listButton.get(ButtonType.For).getColor());
	            	}
	            	
	            	//putP : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.PutP).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.PutP), m_listButton.get(ButtonType.PutP).getColor());
	            	}
	            	
	            	//useP : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.UseP).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.UseP), m_listButton.get(ButtonType.UseP).getColor());
	            	}
	            	
	            	//paint : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.Paint).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.Paint), m_listButton.get(ButtonType.Paint).getColor());
	            	}
	            	
	            	//removeColor : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.RemoveColor).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.RemoveColor), m_listButton.get(ButtonType.RemoveColor).getColor());
	            	}
	            	
	            	//P1 : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.P1).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.P1), m_listButton.get(ButtonType.P1).getColor());
	            	}
	            	
	            	//P2 : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
	            	else if (m_listButton.get(ButtonType.P2).isClicked(mouse_pos)) { 
	            		addOrder(m_listButton.get(ButtonType.P2), m_listButton.get(ButtonType.P2).getColor());
	            	}
	            	
	            	//BasicBot : on passe le BasicBot en actif et le SmartBot en inactif
	            	else if (m_listButton.get(ButtonType.BasicBot).isClicked(mouse_pos)) { 
	            		m_listButton.get(ButtonType.BasicBot).ActiveButton(true);
	            		m_listButton.get(ButtonType.SmartBot).ActiveButton(false);
	            		m_listFrame.get(FrameType.Main).ActiveFrame(true);
	            		m_listFrame.get(FrameType.P1).ActiveFrame(false);
	            		m_listFrame.get(FrameType.P2).ActiveFrame(false);
	            	}
	            	
	            	//SmartBot : on passe le SmartBot en actif et le BasicBot en inactif
	            	else if (m_listButton.get(ButtonType.SmartBot).isClicked(mouse_pos)) { 
	            		m_listButton.get(ButtonType.BasicBot).ActiveButton(false);
	            		m_listButton.get(ButtonType.SmartBot).ActiveButton(true);
	            		m_listFrame.get(FrameType.Main).ActiveFrame(true);
	            		m_listFrame.get(FrameType.P1).ActiveFrame(false);
	            		m_listFrame.get(FrameType.P2).ActiveFrame(false);
	            	}
	            	
	            	//Yellow : on active le bouton et on desactive les autres (blue, green, red)
	            	else if (m_listButton.get(ButtonType.PushYellow).isClicked(mouse_pos)) { 
	            		m_listButton.get(ButtonType.PushYellow).ActiveButton(true);
	            		m_listButton.get(ButtonType.PushBlue).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushGreen).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushRed).ActiveButton(false);
	            		updateColorPointer(m_listButton.get(ButtonType.PushYellow).getColor());
	            		m_listButton.get(ButtonType.PutP).setColorPointeur(Color.YELLOW);
	            		m_listButton.get(ButtonType.UseP).setColorPointeur(Color.YELLOW);
	            	}
	            	
	            	//Blue : on active le bouton et on desactive les autres (yellow, green, red)
	            	else if (m_listButton.get(ButtonType.PushBlue).isClicked(mouse_pos)) { 
	            		m_listButton.get(ButtonType.PushYellow).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushBlue).ActiveButton(true);
	            		m_listButton.get(ButtonType.PushGreen).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushRed).ActiveButton(false);
	            		updateColorPointer(m_listButton.get(ButtonType.PushBlue).getColor());
	            		m_listButton.get(ButtonType.PutP).setColorPointeur(Color.BLUE);
	            		m_listButton.get(ButtonType.UseP).setColorPointeur(Color.BLUE);
	            	}
	            	
	            	//Green : on active le bouton et on desactive les autres (yellow, blue, red)
	            	else if (m_listButton.get(ButtonType.PushGreen).isClicked(mouse_pos)) { 
	            		m_listButton.get(ButtonType.PushYellow).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushBlue).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushGreen).ActiveButton(true);
	            		m_listButton.get(ButtonType.PushRed).ActiveButton(false);
	            		updateColorPointer(m_listButton.get(ButtonType.PushGreen).getColor());
	            		m_listButton.get(ButtonType.PutP).setColorPointeur(Color.GREEN);
	            		m_listButton.get(ButtonType.UseP).setColorPointeur(Color.GREEN);
	            	}
	            	 
	        		//Red : on active le bouton et on desactive les autres (yellow, blue, green)
	            	else if (m_listButton.get(ButtonType.PushRed).isClicked(mouse_pos)) {
	            		m_listButton.get(ButtonType.PushYellow).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushBlue).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushGreen).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushRed).ActiveButton(true);
	            		updateColorPointer(m_listButton.get(ButtonType.PushRed).getColor());
	            		m_listButton.get(ButtonType.PutP).setColorPointeur(Color.RED);
	            		m_listButton.get(ButtonType.UseP).setColorPointeur(Color.RED);
	            	}
	
	        		//Grey : on active le bouton et on desactive les autres (magenta, cyan)
	            	else if (m_listButton.get(ButtonType.PushGrey).isClicked(mouse_pos)) { 
	            		m_listButton.get(ButtonType.PushGrey).ActiveButton(true);
	            		m_listButton.get(ButtonType.PushMagenta).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushCyan).ActiveButton(false);
	            		updateColorOrder(m_listButton.get(ButtonType.PushGrey).getColor());
	            	}
	            	 
	        		//Magenta : on active le bouton et on desactive les autres (grey, cyan)
	            	else if (m_listButton.get(ButtonType.PushMagenta).isClicked(mouse_pos)) {
	            		m_listButton.get(ButtonType.PushGrey).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushMagenta).ActiveButton(true);
	            		m_listButton.get(ButtonType.PushCyan).ActiveButton(false);
	            		updateColorOrder(m_listButton.get(ButtonType.PushMagenta).getColor());
	            	}
	            	 
	        		//Cyan : on active le bouton et on desactive les autres (grey, magenta)
	            	else if (m_listButton.get(ButtonType.PushCyan).isClicked(mouse_pos)) {
	            		m_listButton.get(ButtonType.PushGrey).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushMagenta).ActiveButton(false);
	            		m_listButton.get(ButtonType.PushCyan).ActiveButton(true);
	            		updateColorOrder(m_listButton.get(ButtonType.PushCyan).getColor());
	            	}
	
	        		//Run : on active le Run qui se change alors en Stop
	            	else if (m_listButton.get(ButtonType.Run).isClicked(mouse_pos)){
	            		m_listButton.get(ButtonType.Run).ActiveButton(true);
	            		// TODO -- Action du Run
	            		m_game.setStateSimulation(true);
	            	}
	
	        		//Main : on active la frame main et on desactive p1 et p2
	            	else if (m_listFrame.get(FrameType.Main).isClicked(mouse_pos) && !m_listFrame.get(FrameType.Main).isActive()) {
	            		m_listFrame.get(FrameType.Main).ActiveFrame(true);
	            		//System.out.println("Main " + m_listFrame.get(FrameType.Main).isActive());
	            		m_listFrame.get(FrameType.P1).ActiveFrame(false);
	            		m_listFrame.get(FrameType.P2).ActiveFrame(false);
	            	}
	
	        		//P1 : on active la frame p1 et on desactive main et p2
	            	else if (m_listFrame.get(FrameType.P1).isClicked(mouse_pos) && !m_listFrame.get(FrameType.P1).isActive()) {
	            		m_listFrame.get(FrameType.Main).ActiveFrame(false);
	            		m_listFrame.get(FrameType.P1).ActiveFrame(true);
	            		m_listFrame.get(FrameType.P2).ActiveFrame(false);
	            	}
	
	        		//P2 : on active la frame p2 et on desactive main et p1
	            	else if (m_listFrame.get(FrameType.P2).isClicked(mouse_pos) && !m_listFrame.get(FrameType.P2).isActive()) {
	            		m_listFrame.get(FrameType.Main).ActiveFrame(false);
	            		m_listFrame.get(FrameType.P1).ActiveFrame(false);
	            		m_listFrame.get(FrameType.P2).ActiveFrame(true);
	            	}
	            	
	            	//On supprime un ordre dans la frame Main
	            	else if (m_listFrame.get(FrameType.Main).isActive()) {
	            		if (m_listButton.get(ButtonType.BasicBot).isActive()) {
	            			for(int i = 0; i < m_mainBasicBot.size(); i++){
	            				if (m_mainBasicBot.get(i).isClicked(mouse_pos)) {
	            					m_game.removeOrder(FrameType.Main.ordinal(),ButtonType.BasicBot.name(),i);
	            					removeOrder(m_mainBasicBot,i,FrameType.Main);
	            				}
	            			}
	            		} else {
	            			for(int i = 0; i < m_mainSmartBot.size(); i++){
	            				if (m_mainSmartBot.get(i).isClicked(mouse_pos)) {
	            					m_game.removeOrder(FrameType.Main.ordinal(),ButtonType.SmartBot.name(),i);
	            					removeOrder(m_mainSmartBot,i,FrameType.Main);
	            				}
	            			}
	            		}
	            	}
	            	
	            	// On supprime un ordre dans la frame P1
	            	else if (m_listFrame.get(FrameType.P1).isActive()) {
	            		if (m_listButton.get(ButtonType.BasicBot).isActive()) {
	            			for(int i = 0; i < m_p1BasicBot.size(); i++){
	            				if (m_p1BasicBot.get(i).isClicked(mouse_pos)) {
	            					m_game.removeOrder(FrameType.P1.ordinal(),ButtonType.BasicBot.name(),i);
	            					removeOrder(m_p1BasicBot,i,FrameType.P1);
	            				}
	            			}
	            		} else {
	            			for(int i = 0; i < m_p1SmartBot.size(); i++){
	            				if (m_p1SmartBot.get(i).isClicked(mouse_pos)) {
	            					m_game.removeOrder(FrameType.P1.ordinal(),ButtonType.SmartBot.name(),i);
	            					removeOrder(m_p1SmartBot,i,FrameType.P1);
	            				}
	            			}
	            		}
	            	}
	            	
	            	// On supprime un ordre dans la frame P2
	            	else if (m_listFrame.get(FrameType.P2).isActive()) {
	            		if (m_listButton.get(ButtonType.BasicBot).isActive()) {
	            			for(int i = 0; i < m_p2BasicBot.size(); i++){
	            				if (m_p2BasicBot.get(i).isClicked(mouse_pos)) {
	            					m_game.removeOrder(FrameType.P2.ordinal(),ButtonType.BasicBot.name(),i);
	            					removeOrder(m_p2BasicBot,i,FrameType.P2);
	            				}
	            			}
	            		} else {
	            			for(int i = 0; i < m_p2SmartBot.size(); i++){
	            				if (m_p2SmartBot.get(i).isClicked(mouse_pos)) {
	            					m_game.removeOrder(FrameType.P2.ordinal(),ButtonType.SmartBot.name(),i);
	            					removeOrder(m_p2SmartBot,i,FrameType.P2);
	            				}
	            			}
	            		}
	            	}
            	}
            }
		}
	}
}

