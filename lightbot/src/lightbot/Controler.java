package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

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
import org.jsfml.window.event.Event;

public class Controler {
	        
/** --------------- ATTRIBUTES --------------- */        
        
	private Vector<Component> m_listComponent;
	
/** -------------- CONSTRUCTORS -------------- */

	public Controler(){
		Button b_move = new Button("chemin.png", position, "Move", true); //Avancer
		Button b_turnRight = new Button("chemin.png", position, "TurnRight", true); //Tourner droite
		Button b_turnLeft = new Button("chemin.png", position, "TurnLeft", true); //Tourner gauche
		Button b_jump = new Button("chemin.png", position, "Jump", true); //Sauter
		Button b_putP = new Button("chemin.png", position, "putP", true); //Poser pointeur
		Button b_useP = new Button("chemin.png", position, "useP", true); //Utiliser pointeur
		Button b_paint = new Button("chemin.png", position, "paint", true); //Se peindre
		m_listComponent.addElement(b_move);
		m_listComponent.addElement(b_turnRight);
		m_listComponent.addElement(b_turnLeft);
		m_listComponent.addElement(b_jump);
		m_listComponent.addElement(b_putP);
		m_listComponent.addElement(b_useP);
		m_listComponent.addElement(b_paint);
	}
	
/** ---------------- METHODS ----------------- */	
        
	
	
	public void update(){
		
        /** On affiche l'ensemble des les boutons et des frames */
		
		
		
		/** On met a jours l'attribut m_visible des components a partir de getListOrderDispo() de Level ???
		 * + le bon nombre de cadre d'instructions dans main p1 et p2 a partir de getLimitOrder()
		 */
        
		
        
        /** On fait tourner le programme jusqu'à ce que la fenetre soit fermee */
        while (window.isOpen()) {        	
        	// On inspecte tous les evenements de la fenetre qui ont ete emis depuis la precedente iteration
        	for (Event event : window.pollEvents()) {
            	// Si l'utilisateur clique sur la croix rouge : on ferme la fenetre
                if (event.type == Event.Type.CLOSED) {
                    window.close();
                }
                // Si l'utilisateur clique on recupere la position et on regarde s'il s'agit d'un bouton ou d'une view
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                	Vector2i mouse_pos = Mouse.getPosition(window); 
                	
                	//Bouton BasicBot : on charge les procedures du BasicBot
                	
                	//Bouton SmartBot : on charge les procedures du SmartBot
                	
                	//View avec m_contain == true (main, p1 ou p2) : on active la view et on desactive les autres view
                	
                	//Bouton Order : on ajoute l'ordre dans la view active
                	
                	//Default : on ne fait rien

					
                }

   
        	}
        }
	}
}