package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

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
	
	
	        
/** --------------- ATTRIBUTES --------------- */        
        
	private Vector<Button> m_listButton;
	private Vector<Frame> m_listFrame;
	private Vector<Button> m_MainBasicBot;
	private Vector<Button> m_P1BasicBot;
	private Vector<Button> m_P2BasicBot;
	private Vector<Button> m_MainSmartBot;
	private Vector<Button> m_P1SmartBot;
	private Vector<Button> m_P2SmartBot;
	private Vector<Button> m_OrderBasicBot;
	private Vector<Button> m_OrderSmartBot;
	
	
/** -------------- CONSTRUCTORS -------------- */

	/** Constructeur du Controler
	 * construit la liste des boutons et la liste des frames
	 */
	public Controler(){
		// Boutons representants les Ordres
		Button b_move = new Button("chemin.png", position, ButtonType.Move, true); //Avancer
		Button b_turnRight = new Button("chemin.png", position, ButtonType.TurnRight, true); //Tourner droite
		Button b_turnLeft = new Button("chemin.png", position, ButtonType.TurnLeft, true); //Tourner gauche
		Button b_jump = new Button("chemin.png", position, ButtonType.Jump, true); //Sauter
		Button b_light = new Button("chemin.png", position, ButtonType.Light, true); //Allumer
		Button b_for = new Button("chemin.png", position, ButtonType.For, true); //For
		Button b_putP = new Button("chemin.png", position, ButtonType.PutP, true); //Poser pointeur
		Button b_useP = new Button("chemin.png", position, ButtonType.UseP, true); //Utiliser pointeur
		Button b_paint = new Button("chemin.png", position, ButtonType.Paint, true); //Se peindre
		Button b_removeColor = new Button("chemin.png", position, ButtonType.RemoveColor, true); //Se laver
		Button b_p1 = new Button("chemin.png", position, ButtonType.P1, true); //P1
		Button b_p2 = new Button("chemin.png", position, ButtonType.P2, true); //P2
		// Boutons representants les Bots
		Button b_basicBot = new Button("chemin.png", position, ButtonType.BasicBot, false); //SmartBot [presse]
		Button b_smartBot = new Button("chemin.png", position, ButtonType.SmartBot, false); //BasicBot [non presse]
		// Boutons representants les couleurs des pointeurs et instructions
		Button b_yellow = new Button("chemin.png", position, ButtonType.PushYellow, false); //Bouton jaune [presse]
		Button b_blue = new Button("chemin.png", position, ButtonType.PushBlue, false); //Bouton bleue [non presse]
		Button b_green = new Button("chemin.png", position, ButtonType.PushGreen, false); //Bouton vert [non presse]
		Button b_red = new Button("chemin.png", position, ButtonType.PushRed, false); //Bouton rouge [non presse]
		Button b_grey = new Button("chemin.png", position, ButtonType.PushGrey, false); //Bouton gris [presse]
		Button b_magenta = new Button("chemin.png", position, ButtonType.PushMagenta, false); //Bouton magenta [non presse]
		Button b_cyan = new Button("chemin.png", position, ButtonType.PushCyan, false); //Bouton cyan [non presse]
		
		//Boutons run
		Button b_run = new Button("chemin.png" , position, ButtonType.Run, false); //Bouton run
		
		// Frames
		Frame f_main = new Frame("chemin.png", position, FrameType.Main); //Main
		Frame f_p1 = new Frame("chemin.png", position, FrameType.P1); //P1
		Frame f_p2 = new Frame("chemin.png", position, FrameType.P2); //P2
		Frame f_orderList = new Frame("chemin.png", position, FrameType.OrderList); //OrderList
		
		m_listButton.addElement(b_move);
		m_listButton.addElement(b_turnRight);
		m_listButton.addElement(b_turnLeft);
		m_listButton.addElement(b_jump);
		m_listButton.addElement(b_putP);
		m_listButton.addElement(b_useP);
		m_listButton.addElement(b_paint);
		m_listButton.addElement(b_removeColor);
		m_listButton.addElement(b_p1);
		m_listButton.addElement(b_p2);
		m_listButton.addElement(b_basicBot);
		m_listButton.addElement(b_smartBot);
		m_listButton.addElement(b_yellow);
		m_listButton.addElement(b_blue);
		m_listButton.addElement(b_green);
		m_listButton.addElement(b_red);
		m_listButton.addElement(b_grey);
		m_listButton.addElement(b_magenta);
		m_listButton.addElement(b_cyan);
		m_listButton.addElement(b_run);
		
		m_listFrame.addElement(f_main);
		m_listFrame.addElement(f_p1);
		m_listFrame.addElement(f_p2);
		m_listFrame.addElement(f_orderList);
		
	}
	
	
/** ---------------- METHODS ----------------- */	
        
	
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
	public void init(Character BasicBot, Character SmartBot){
		// initialisation des cadres dans le main du BasicBot
		for (int i = 0 ; i < BasicBot.getLimitOrder().elementAt(0) ; i++) {
			m_MainBasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p1 du BasicBot
		for (int i = 0 ; i < BasicBot.getLimitOrder().elementAt(1) ; i++) {
			m_P1BasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans P2 du BasicBot
		for (int i = 0 ; i < BasicBot.getLimitOrder().elementAt(2) ; i++) {
			m_P2BasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans le main du SmartBot
		for (int i = 0 ; i < SmartBot.getLimitOrder().elementAt(0) ; i++) {
			m_MainSmartBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p1 du SmartBot
		for (int i = 0 ; i < SmartBot.getLimitOrder().elementAt(1) ; i++) {
			m_P1SmartBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p2 du SmartBot
		for (int i = 0 ; i < SmartBot.getLimitOrder().elementAt(2) ; i++) {
			m_P2SmartBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des ordres visibles du BasicBot
		/*TODO*/
		// initialisation des ordres visibles du SmartBot
		/*TODO*/
	}
	
	
	/** Initialisation des cadres dans les frames et les ordres visible selon le Level
	 * version avec un bot
	 */
	public void init(Character Bot){
		// initialisation des cadres dans le main du Bot
		for (int i = 0 ; i < Bot.getLimitOrder().elementAt(0) ; i++) {
			m_MainBasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p1 du Bot
		for (int i = 0 ; i < Bot.getLimitOrder().elementAt(1) ; i++) {
			m_P1BasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p2 du Bot
		for (int i = 0 ; i < Bot.getLimitOrder().elementAt(2) ; i++) {
			m_P2BasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des ordres visibles du Bot
		/*TODO*/
	}
	
	
	/** Update le visuel de l'interface 
	 * @param window courante, Character actif
	 */
	public void update(Window window, Character Bot){
		
        /** On affiche les components de fond */
		
		/** On affiche les frames */
		
		/** On affiche les boutons ayant leur attribut m_visible a true */
		
		/** On affiche le bon nombre de cadre d'instructions dans main p1 et p2 a partir de getLimitOrder() */

		/** On affiche les instructions contenu dans le main, p1 et p2 du Bot actif */
	}
	
	
	/** Traite tous les evenements qui ont ete emis dans la fenetre depuis la precedente iteration
	 * @param window
	 * @require la fenetre window doit etre ouverte
	 */
	public void supervise(Window window){
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
            		/*TODO*/
            		
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
	
	
	
	
	
/** ---------- Supervision d'un seul Bot ----------------- */
	
	
	
	
	/** Initialisation des cadres dans les frames et les ordres visible selon le Level
	 * version avec un bot
	 */
	public void init(Character Bot){
		// initialisation des cadres dans le main du Bot
		for (int i = 0 ; i < Bot.getLimitOrder().elementAt(0) ; i++) {
			m_MainBasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p1 du Bot
		for (int i = 0 ; i < Bot.getLimitOrder().elementAt(1) ; i++) {
			m_P1BasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des cadres dans p2 du Bot
		for (int i = 0 ; i < Bot.getLimitOrder().elementAt(2) ; i++) {
			m_P2BasicBot.addElement(new Button("chemin.png", position, ButtonType.Cadre, false)); //ajout un cadre
		}
		// initialisation des ordres visibles du Bot
		/*TODO*/
	}
	

	
}