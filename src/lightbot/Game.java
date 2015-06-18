package lightbot;

import java.util.HashMap;

import lightbot.Button.ButtonType;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;

public class Game {


/** --------------- ATTRIBUTES --------------- */	

	private HashMap<String, Character> m_character = new HashMap<String, Character>(); // "BasicBot" | "SmartBot"
	private RenderWindow m_window;
	private Engine m_engine;
	
	
/** -------------- CONSTRUCTORS -------------- */		
	
	/** Constructeur de Game
	 * @param window
	 * @param engine
	 * @param basicbot
	 */
	public Game(RenderWindow window, Engine engine, Character basicbot){
		m_window = window;
		m_engine = engine;
		m_character.put("BasicBot", basicbot);
	}
	
	
	/** Constructeur de Game
	 * @param window
	 * @param engine
	 * @param basicbot
	 * @param smartbot
	 */
	public Game(RenderWindow window, Engine engine, Character basicbot, Character smartbot){
		m_window = window;
		m_engine = engine;
		m_character.put("BasicBot", basicbot);
		m_character.put("SmartBot", smartbot);
	}
	
	
/** ---------------- METHODS ----------------- */
	
	/** Retourne la HashMap de character 
	 * @return m_character
	 */
	public HashMap<String, Character> getCharacter(){
		return m_character;
	}
	
	
	/** Retourne la fenetre
	 * @return m_window
	 */
	public RenderWindow getWindow(){
		return m_window;
	}
	
	
	/** Retourne engine
	 * @return m_engine
	 */
	public Engine getEngine(){
		return m_engine;
	}
	
	
	/** Creer un order a partir d'un ButtonType
	 * @param type
	 * @param car
	 * @param color
	 * @return Order
	 * @throws Exception 
	 */
	public Order ButtonToButtonType(ButtonType type, Character car, Color color) throws Exception{
		if (type == ButtonType.Move){
			Order order = new  Move(car, getEngine(), color);
			return order;
		}
		else if (type == ButtonType.TurnRight){
			Order order = new  TurnRight(car, color);
			return order;
		}
		else if (type == ButtonType.TurnLeft){
			Order order = new  TurnLeft(car, color);
			return order;
		}
		else if (type == ButtonType.Jump){
			Order order = new  Jump(car, getEngine(), color);
			return order;
		}
		else if (type == ButtonType.Light){
			Order order = new  Light(car, getEngine(), color);
			return order;
		}
		else if (type == ButtonType.For){
			Order order = new  For(car, getEngine(), color);
			return order;
		}
		else if (type == ButtonType.PutP){
			Order order = new  MallocPointer(car, getEngine(), color);
			return order;
		}		
		else if (type == ButtonType.UseP){
			Order order = new  AccessPointer(car, getEngine(), color);
			return order;
		}
		else if (type == ButtonType.Paint){
			Order order = new  getColor(car, getEngine(), color);
			return order;
		}
		else if (type == ButtonType.RemoveColor){
			Order order = new  RemoveColor(car, color);
			return order;
		}
		else if (type == ButtonType.P1){
			Order order = new  Procedure1(car, color);
			return order;
		}
		else if (type == ButtonType.P2){
			Order order = new  Procedure2(car, color);
			return order;
		}
		else {
			throw new Exception();
		}
	}
	
	
	/** Ajoute un ordre dans m_listOrder du Character
	 * @param proc  0 : main | 1 : p1 | 2 : P2 
	 * @param car  "BasicBot" : BasicBot | "SmartBot" : SmartBot
	 * @param order  ordre a ajouter
	 */
	public boolean addOrder(int proc, String car,  ButtonType type, Color color){
		if (m_character.get(car).getListOrder().get(proc).size() < m_character.get(car).getLimitOrder().get(proc)){
			Order order = null;
			try {
				order = ButtonToButtonType(type, m_character.get(car), color);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m_character.get(car).addOrder(proc, order);
			return true;
		}
		else { //la limite d'ordre est atteinte
			return false;
		}	
	}
	
	/*public void animation(Order ordre) {
		
	}*/
	
}
