package lightbot;

import java.util.HashMap;

import org.jsfml.graphics.RenderWindow;

public class Game {


/** --------------- ATTRIBUTES --------------- */	

	private HashMap<String, Character> m_character = new HashMap<String, Character>(); // "BasicBot" | "SmartBot"
	private RenderWindow m_window;
	private Engine m_engine;
	
	
/** -------------- CONSTRUCTORS -------------- */		
	
	
/** ---------------- METHODS ----------------- */
	
	/** Retourne la HashMap de character */
	public HashMap<String, Character> getCharacter(){
		return m_character;
	}
	
	/** Retourne la fenetre */
	public RenderWindow getWindow(){
		return m_window;
	}
	
	/** Retourne engine */
	public Engine getEngine(){
		return m_engine;
	}
	
	/** Ajoute un ordre dans m_listOrder du Character
	 * @param proc  0 : main | 1 : p1 | 2 : P2 
	 * @param car  0 : BasicBot | 1 : SmartBot
	 * @param order  ordre a ajouter
	 */
	public void addOrder(int proc, int car,  Order order){
		m_character.get(car).addOrder(proc, order);
	}
	

	
}
