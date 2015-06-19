package lightbot;

import java.util.HashMap;

import lightbot.Button.ButtonType;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

public class Game {


	private final static String LEVELPATH = "test2.xml";
	
/** --------------- ATTRIBUTES --------------- */	

	private HashMap<String, Character> m_character = new HashMap<String, Character>(); // "BasicBot" | "SmartBot"
	private RenderWindow m_window;
	private Engine m_engine;
	private Map m_map;
	
	
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
	
	
	public Game(RenderWindow window){
		m_window = window;
		m_character.put("BasicBot", new Character(new Vector2i(0, 0), 1,Color.WHITE, "lightbot.png"));
		m_character.put("SmartBot", new Character(new Vector2i(0, 0), 1,Color.WHITE, "lightbot.png"));
		m_map = new Map(m_character.get("BasicBot"),m_character.get("SmartBot"),LEVELPATH);
		m_engine = new Engine(m_map);
		getCharacter("BasicBot").setLimitOrder(0, 17);
		getCharacter("BasicBot").setLimitOrder(1, 3);
		getCharacter("SmartBot").setLimitOrder(0, 6);
	}
	
	/** Constructeur de Game
	 * @param window
	 * @param engine
	 * @param basicbot
	 * @param smartbot
	 */
	public Game(RenderWindow window, Engine engine, Character basicbot, Character smartbot){
		m_window = window;
		m_map = new Map(basicbot,smartbot,LEVELPATH);
		m_engine = new Engine(m_map);
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
	
	public Character getCharacter(String name){
		return m_character.get(name);
	}
	
	/** Retourne la fenetre
	 * @return m_window
	 */
	public RenderWindow getWindow(){
		return m_window;
	}
	
	public void draw(){
		m_map.drawMap(m_window);
	}
	
	public void setView(Vector2i size){
		View view = new View();
		view.setSize(new Vector2f(size.x,size.y));
		view.setCenter(new Vector2f(size.x/2.0f,size.y/2.0f));
		m_window.setView(view);
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
	public boolean addOrder(int proc, String character,  ButtonType type, Color color){
		//System.out.println("Size listOrdre " + proc + " = " + m_character.get(character).getListOrder().get(proc).size() + " | LimitOrder = " + m_character.get(character).getLimitOrder().get(proc));
		if (m_character.get(character).getListOrder().get(proc).size() < m_character.get(character).getLimitOrder().get(proc)){
			Order order = null;
			try {
				order = ButtonToButtonType(type, m_character.get(character), color);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m_character.get(character).addOrder(proc, order);
			//System.out.println("Order ajoute");
			return true;
		}
		else { //la limite d'ordre est atteinte
			//System.out.println("Limit Ordre atteinte");
			return false;
		}	
	}
	
	/*public void animation(Order ordre,Character rob) {
		int decale_y = 0;
		switch (rob.getOrientation())
		{
	        case Down:
	        	decale_y = 1;
	        break;
	        case Left:
	        	decale_y = 2;
	        break;
	        case Right:
	        	decale_y = 3;
	        break;
	        case Default:
	        break;
		}
		
		
		
	}*/
	public void removeOrder(int proc, String character, int posOrder) {
		if (posOrder >= 0 && posOrder < m_character.get(character).getListOrder().get(proc).size()) {
			m_character.get(character).removeOrder(proc, posOrder);
		}
	}
	
}
