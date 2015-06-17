package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
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
	
	// POSITION DES ORDRES //
	private final static Vector2f POSINITBUTTON = new Vector2f(58.0f,989.0f);
	private final static Vector2f SIZEBUTTONORDER = new Vector2f(70.0f,70.0f);
	private final static float DECALBUTTON = 46.0f;
	
	// POSITION DES ONGLETS DE SELECTION BOT //
	private final static Vector2f POSINITONGLETBOT = new Vector2f(1455.0f,14.0f);
	private final static Vector2f SIZEONGLETBOT = new Vector2f(84.0f,82.0f);
	
	// POSITION BOUTTON POINTEUR //
	private final static Vector2f POSINITBUTTONPOINTER = new Vector2f(1016.0f,953.0f);
	private final static Vector2f SIZEBUTTONCOLOR = new Vector2f(20.0f,15.0f);
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
	private final static Vector2f POSINITCADREP1 = new Vector2f(1215.0f,526.0f);
	private final static Vector2f POSINITCADREP2 = new Vector2f(1215.0f,764.0f);
	private final static Vector2f SIZECADREORDER = new Vector2f(70.0f,70.0f);
	private final static Vector2f DECALCADREORDER = new Vector2f(11.0f,11.0f);

	// POSITION BACK //
	private final static Vector2f POSINITBACKORDER = new Vector2f(24.0f,949.0f);
	private final static Vector2f POSINITBACKMAIN = new Vector2f(1197.0f,97.0f);	


/** --------------- ATTRIBUTES --------------- */        
        
	private Vector<Button> m_listButton = new Vector<Button>();
	private Vector<Frame> m_listFrame = new Vector<Frame>();
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

	private Game m_game;
	private RenderWindow m_window;

	private Vector2i m_screenSize;
	private Vector2f m_decal;
	private Vector2f m_scale;
	
	
	
/** -------------- CONSTRUCTORS -------------- */

	/** Constructeur du Controler
	 * construit la liste des boutons et la liste des frames
	 */
	public Controler(Vector2i screenSize){
		reloadInterface(screenSize);
	}
	
	
/** ---------------- METHODS ----------------- */	
        
	
	
	
	public void reloadInterface(Vector2i screenSize) {
		m_screenSize = screenSize;
		m_decal = new Vector2f(1920.0f - m_screenSize.x,1080.0f - m_screenSize.y);
		m_scale = new Vector2f(m_screenSize.x / 1920.0f, m_screenSize.y / 1080.0f);
		
		
		// BackOrder & BackMain
		m_backOrder = new Component(TILEPATHFRAME+"BackOrder.png",Vector2f.sub(POSINITBACKORDER,new Vector2f(0.0f,m_decal.y)));
		m_backOrder.setScale(m_scale);
		
		if (m_backOrder.getSprite().getPosition().y + m_backOrder.getSprite().getLocalBounds().height < m_screenSize.y) {
			m_backOrder.getSprite().setPosition(new Vector2f(m_backOrder.getSprite().getPosition().x,m_backOrder.getSprite().getPosition().y + ( m_screenSize.y - (m_backOrder.getSprite().getPosition().y + m_backOrder.getSprite().getLocalBounds().height))+10));
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
		
		m_listButton.addElement(b_move); //#0
		m_listButton.addElement(b_turnRight); //#1
		m_listButton.addElement(b_turnLeft); //#2
		m_listButton.addElement(b_jump); //#3
		m_listButton.addElement(b_light); //#4
		m_listButton.addElement(b_for); //#5
		m_listButton.addElement(b_putP); //#6
		m_listButton.addElement(b_useP); //#7 
		m_listButton.addElement(b_paint); //#8
		m_listButton.addElement(b_removeColor); //#9
		m_listButton.addElement(b_p1); //#10
		m_listButton.addElement(b_p2); //#11 
		
		m_listButton.addElement(b_basicBot); //#12
		m_listButton.addElement(b_smartBot); //#13
		
		m_listButton.addElement(b_green); //#14
		m_listButton.addElement(b_yellow); //#15
		m_listButton.addElement(b_red); //#16
		m_listButton.addElement(b_blue); //#17
		
		m_listButton.addElement(b_grey); //#18
		m_listButton.addElement(b_magenta); //#19
		m_listButton.addElement(b_cyan); //#20
		m_listButton.addElement(b_run); //#21
		
		m_listFrame.addElement(f_main); //#0
		m_listFrame.addElement(f_p1); //#1
		m_listFrame.addElement(f_p2); //#2
		m_listFrame.addElement(f_orderList); //#3
	}
	
	
	/** Retourne le ButtonType d'un ordre 
	 * @throws Exception */
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
	
	
	
/** ---------- Supervision de deux Bots ----------------- */
	
	
	/** Initialisation des cadres dans les frames et les ordres visible selon le Level
	 * version avec deux bots
	 */
	public void init(Character BasicBot, Character SmartBot/*, Map map*/){

		Vector2f realInitPosCadreMain = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREMAIN, POSINITBACKMAIN), m_scale));
		Vector2f realInitPosCadreP1 = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREP1, POSINITBACKMAIN), m_scale));
		Vector2f realInitPosCadreP2 = Vector2f.add(m_backMain.getSprite().getPosition(), Vector2f.componentwiseMul(Vector2f.sub(POSINITCADREP2, POSINITBACKMAIN), m_scale));
		Vector2f realdDecalCadre = Vector2f.componentwiseMul(DECALCADREORDER, m_scale);
		Vector2f realSizeCadreOrder = Vector2f.componentwiseMul(SIZECADREORDER, m_scale);
		Vector2f pos = realInitPosCadreMain;
		Character currentChar;
		for (int g = 0; g < 2 ; g++) {
			pos = realInitPosCadreMain;
			for (int j = 0; j < NUMBERFRAME; j++) {
				switch(j) {
				case 0:
					pos = new Vector2f(pos.x,realInitPosCadreMain.y + (realdDecalCadre.y * j));
					break;
				case 1:
					pos = new Vector2f(pos.x,realInitPosCadreP1.y + (realdDecalCadre.y * j));
					break;
				case 2:
					pos = new Vector2f(pos.x,realInitPosCadreP2.y + (realdDecalCadre.y * j));

					break;
				}
				if (g == 0 ) {
					currentChar = BasicBot; 	
				} else {
					currentChar = SmartBot;
				}
				// initialisation des cadres dans le main du BasicBot
				// initialisation des cadres dans p1 du BasicBot
				// initialisation des cadres dans P2 du BasicBot
				// initialisation des cadres dans le main du SmartBot
				// initialisation des cadres dans p1 du SmartBot
				// initialisation des cadres dans p2 du SmartBot
				for (int i = 0 ; i < currentChar.getLimitOrder().get(j) ; i++) {
					if(i % 7 == 1 && i != 1 ) {
						pos = new Vector2f(realInitPosCadreMain.x,pos.y + realSizeCadreOrder.y + realdDecalCadre.y);
					}
					else if (i != 0){
						pos = new Vector2f(pos.x + realSizeCadreOrder.x + realdDecalCadre.x,pos.y);
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
		m_listFrame.get(0).ActiveFrame(0);
		
		/*TODO*/
		// initialisation des ordres visibles du SmartBot
		/*TODO*/
	}
	
	/**
	 * Initialise les Ordres disponible pour le niveau charge
	 */
	private void initOrder() {
		/* TODO */
		for(int i = 0; i < 13;i++){
			m_listButton.get(i).setVisibility(true);
		}
	}
	
	
	/**
	 * Dessine un vecteur de boutton
	 * @param vec
	 * @param window
	 */
	private void drawButton(Vector<Button> vec, RenderWindow window) {
		for(int i = 0; i < vec.size(); i++) {
			vec.get(i).draw(window);
		}
	}
	
	
	/** Update le visuel de l'interface 
	 * @param window courante, Character actif
	 */
	public void update(RenderWindow window){
		
        /** On affiche les components de fond */
		m_backMain.draw(window);
		m_backOrder.draw(window);
		
		/** On affiche les frames */
		for(int i = 0; i < m_listFrame.size();i++) {
			m_listFrame.get(i).draw(window);
		}
		/** On affiche les boutons ayant leur attribut m_visible a true */
		drawButton(m_listButton,window);
		
		/** On affiche le bon nombre de cadre d'instructions dans main p1 et p2 a partir de getLimitOrder() */
		/** On affiche les instructions contenu dans le main, p1 et p2 du Bot actif */
		if(m_listButton.get(13).isActive()) {
			drawButton(m_mainBasicBot, window);
			drawButton(m_p1BasicBot, window);
			drawButton(m_p2BasicBot, window);
			drawButton(m_orderBasicBot, window);
		} else {
			drawButton(m_mainSmartBot, window);
			drawButton(m_p1SmartBot, window);
			drawButton(m_p2SmartBot, window);
			drawButton(m_orderSmartBot, window);
		}
	}
	
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @param window
	 * @require la fenetre window doit etre ouverte
	 */
	public void supervise(){
		for (Event event : window.pollEvents()) {
			
		
        	// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
            if (event.type == Event.Type.CLOSED) {
                window.close();
            }
            
            // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un bouton ou d'une view
            if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
            	Vector2i mouse_pos = Mouse.getPosition(window); 
            	if (m_listButton.get(0).isClicked(mouse_pos)) { 
            		//Move : on ajoute l'ordre dans la view active & dans m_listOrder (Character)
            		if (m_listButton.get(10).getActive()){ //si le BasicBot est active
	            		if (m_listFrame.get(0).getActive()){ //si main est active
	            			m_mainBasicBot.addElement(ButtonType.Move);
	            			game.addOrder(0, ButtonType.Move);
	           
	            		}
	            		else if (m_listFrame.get(1).getActive()){ //si p1 est active
	            			m_p1BasicBot.addElement(ButtonType.Move);
	            		}
	            		else if (m_listFrame.get(2).getActive()){ //si p2 est active
							m_p2BasicBot.addElement(ButtonType.Move);
						}
            		} else if (m_listButton.get(11).getActive()){ //si le SmartBot est active
	            }
            	else if (m_listButton.get(1).isClicked(mouse_pos)) { 
            		//TurnRight : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(2).isClicked(mouse_pos)) { 
            		//TurnLeft : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(3).isClicked(mouse_pos)) { 
            		//Jump : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(4).isClicked(mouse_pos)) { 
            		//putP : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(5).isClicked(mouse_pos)) { 
            		//useP : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(6).isClicked(mouse_pos)) { 
            		//paint : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(7).isClicked(mouse_pos)) { 
            		//removeColor : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(8).isClicked(mouse_pos)) { 
            		//P1 : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(9).isClicked(mouse_pos)) { 
            		//P2 : on ajoute l'ordre dans la frame active & dans m_listOrder (Character)
            		/*TODO*/
            	}
            	else if (m_listButton.get(10).isClicked(mouse_pos)) { 
            		//BasicBot : on charge les procedures du BasicBot & on passe le BasicBot en actif
            		/*TODO*/
            	}
            	else if (m_listButton.get(11).isClicked(mouse_pos)) { 
            		//SmartBot : on charge les procedures du SmartBot & on passe le BasicBot en non actif
            		/*TODO*/
            	}
            	else if (m_listButton.get(12).isClicked(mouse_pos)) { 
            		//Yellow : on active le bouton et on desactive les autres (blue, green, red)
            		m_listButton.get(12).ActiveButton(1);
            		m_listButton.get(13).ActiveButton(0);
            		m_listButton.get(14).ActiveButton(0);
            		m_listButton.get(15).ActiveButton(0);
            		//on affiche les pointeurs yellow
            	}
            	else if (m_listButton.get(13).isClicked(mouse_pos)) { 
            		//Blue : on active le bouton et on desactive les autres (yellow, green, red)
            		m_listButton.get(12).ActiveButton(0);
            		m_listButton.get(13).ActiveButton(1);
            		m_listButton.get(14).ActiveButton(0);
            		m_listButton.get(15).ActiveButton(0);
            		//on affiche les pointeurs blue
            	}
            	else if (m_listButton.get(14).isClicked(mouse_pos)) { 
            		//Green : on active le bouton et on desactive les autres (yellow, blue, red)
            		m_listButton.get(12).ActiveButton(0);
            		m_listButton.get(13).ActiveButton(0);
            		m_listButton.get(14).ActiveButton(1);
            		m_listButton.get(15).ActiveButton(0);
            	}
            	else if (m_listButton.get(15).isClicked(mouse_pos)) { 
            		//Red : on active le bouton et on desactive les autres (yellow, blue, green)
            		m_listButton.get(12).ActiveButton(0);
            		m_listButton.get(13).ActiveButton(0);
            		m_listButton.get(14).ActiveButton(0);
            		m_listButton.get(15).ActiveButton(1);
            		//on affiche les pointeurs red
            	}
            	else if (m_listButton.get(16).isClicked(mouse_pos)) { 
            		//Grey : on active le bouton et on desactive les autres (magenta, cyan)
            		m_listButton.get(16).ActiveButton(1);
            		m_listButton.get(17).ActiveButton(0);
            		m_listButton.get(18).ActiveButton(0);
            		//on affiche les instructions grey
            	}
            	else if (m_listButton.get(17).isClicked(mouse_pos)) { 
            		//Magenta : on active le bouton et on desactive les autres (grey, cyan)
            		m_listButton.get(16).ActiveButton(0);
            		m_listButton.get(17).ActiveButton(1);
            		m_listButton.get(18).ActiveButton(0);
            		//on affiche les instructions magenta
            	}
            	else if (m_listButton.get(18).isClicked(mouse_pos)) { 
            		//Cyan : on active le bouton et on desactive les autres (grey, magenta)
            		m_listButton.get(16).ActiveButton(0);
            		m_listButton.get(17).ActiveButton(0);
            		m_listButton.get(18).ActiveButton(1);
            		//on affiche les instructions magenta
            	}
            	else if (m_listButton.get(19).isClicked(mouse_pos)){
            		//Run : on active le Run qui se change alors en Stop
            		m_listButton.get(19).ActiveButton(1);
            		//on affiche le stop
            	}
            	else if (m_listFrame.get(0).isClicked(mouse_pos)) {
            		//Main : on active la frame main et on desactive p1 et p2
            		m_listFrame.get(0).ActiveFrame(1);
            		m_listFrame.get(1).ActiveFrame(0);
            		m_listFrame.get(2).ActiveFrame(0);
            		//on affiche le main active avec ses composants
            	}
            	else if (m_listFrame.get(1).isClicked(mouse_pos)) {
            		//P1 : on active la frame p1 et on desactive main et p2
            		m_listFrame.get(0).ActiveFrame(0);
            		m_listFrame.get(1).ActiveFrame(1);
            		m_listFrame.get(2).ActiveFrame(0);
            		//on affiche P1 active avec ses composants
            	}
            	else if (m_listFrame.get(2).isClicked(mouse_pos)) {
            		//P2 : on active la frame p2 et on desactive main et p1
            		m_listFrame.get(0).ActiveFrame(0);
            		m_listFrame.get(1).ActiveFrame(0);
            		m_listFrame.get(2).ActiveFrame(1);
            		//on affiche P2 active avec ses composants
            		}	
            	}
            }
		}
	}	
}
