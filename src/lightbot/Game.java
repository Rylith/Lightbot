package lightbot;

import java.util.HashMap;
import java.util.LinkedList;

import lightbot.Button.ButtonType;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

public class Game {


	private static String TILEPATHBasicBot = "ressource/Sprite/lightbot.png";
	private static String TILEPATHSmartBot = "ressource/Sprite/lightbot-smart.png";
	
/** --------------- ATTRIBUTES --------------- */	

	private HashMap<String, Character> m_character = new HashMap<String, Character>(); // "BasicBot" | "SmartBot"
	private RenderWindow m_window;
	private Engine m_engine;
	private Map m_map;
	private boolean m_runSimulation = false;
	private Level m_level;

	
/** -------------- CONSTRUCTORS -------------- */		
	
	
	public Game(RenderWindow window){
		m_window = window;
		m_character.put("BasicBot", new Character(new Vector2i(0, 0), 1,Color.WHITE, TILEPATHBasicBot));
		m_character.put("SmartBot", new Character(new Vector2i(0, 0), 1,Color.WHITE, TILEPATHSmartBot));

		//m_map = new Map(m_character.get("BasicBot"),m_character.get("SmartBot"),LEVELPATH);
		//m_engine = new Engine(m_map);
		m_map = new Map();
		m_engine = new Engine();
		
		//TODO A CHANGER
		//getCharacter("BasicBot").setLimitOrder(0, 18);
		//getCharacter("BasicBot").setLimitOrder(1, 3);
		//getCharacter("BasicBot").setLimitOrder(2, 4);
		//getCharacter("SmartBot").setLimitOrder(0, 7);
	}
	
	
	/** Constructeur de Game
	 * @param window
	 * @param engine
	 * @param basicbot
	 */
	public Game(RenderWindow window, Character basicbot){
		m_window = window;
		m_map = new Map();
		m_engine = new Engine(m_map);
		m_character.put("BasicBot", basicbot);
	}

	
	/** Constructeur de Game
	 * @param window
	 * @param engine
	 * @param basicbot
	 * @param smartbot
	 */
	public Game(RenderWindow window, Character basicbot, Character smartbot){
		m_window = window;
		m_map = new Map();
		m_engine = new Engine(m_map);
		m_character.put("SmartBot", smartbot);
		m_character.put("BasicBot", basicbot);
		
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
	
	public void setMap(String mapPath){
		m_map.setLevel(m_character.get("BasicBot"), m_character.get("SmartBot"), mapPath);
		m_engine.setMap(m_map);
	}
	
	/** Retourne engine
	 * @return m_engine
	 */
	public Engine getEngine(){
		return m_engine;
	}

	
	public boolean levelIsCompleted(){
		return m_map.isCompleted();
	}
	
	
	/** Creer un order a partir d'un Button
	 * @param type
	 * @param car
	 * @param color
	 * @return Order
	 * @throws Exception 
	 */
	public Order ButtonToOrder(Button button, Character car, Color color) throws Exception{
		if (button.getType() == ButtonType.Move){
			Order order = new  Move(car, getEngine(), color);
			return order;
		}
		else if (button.getType() == ButtonType.TurnRight){
			Order order = new  TurnRight(car, color);
			return order;
		}
		else if (button.getType() == ButtonType.TurnLeft){
			Order order = new  TurnLeft(car, color);
			return order;
		}
		else if (button.getType() == ButtonType.Jump){
			Order order = new  Jump(car, getEngine(), color);
			return order;
		}
		else if (button.getType() == ButtonType.Light){
			Order order = new  Light(car, getEngine(), color);
			return order;
		}
		else if (button.getType() == ButtonType.For){
			Order order = new  For(car, getEngine(), color);
			return order;
		}
		else if (button.getType() == ButtonType.PutP){
			Color cptr = button.getColorPointer();
			Order order = new  MallocPointer(car, getEngine(), color, cptr);
			return order;
		}		
		else if (button.getType() == ButtonType.UseP){
			Color cptr = button.getColorPointer();
			Order order = new  AccessPointer(car, getEngine(), color, cptr);
			return order;
		}
		else if (button.getType() == ButtonType.Paint){
			Order order = new  getColor(car, getEngine(), color);
			return order;
		}
		else if (button.getType() == ButtonType.RemoveColor){
			Order order = new  RemoveColor(car, color);
			return order;
		}
		else if (button.getType() == ButtonType.P1){
			Order order = new  Procedure1(car, color);
			return order;
		}
		else if (button.getType() == ButtonType.P2){
			Order order = new  Procedure2(car, color);
			return order;
		}
		else {
			throw new Exception();
		}
	}
	
	
	/** Ajoute un ordre dans m_listOrder du Character
	 * @param proc  0 : main | 1 : p1 | 2 : P2 
	 * @param character  "BasicBot" : BasicBot | "SmartBot" : SmartBot
	 * @param button  bouton correspondant a l'ordre a ajouter
	 * @param color couleur de l'instruction
	 */
	public boolean addOrder(int proc, String character,  Button button, Color color){
		//System.out.println("Size listOrdre " + proc + " = " + m_character.get(character).getListOrder().get(proc).size() + " | LimitOrder = " + m_character.get(character).getLimitOrder().get(proc));
		if (m_character.get(character).getListOrder().get(proc).size() < m_character.get(character).getLimitOrder().get(proc)){
			Order order = null;
			try {
				order = ButtonToOrder(button, m_character.get(character), color);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m_character.get(character).addOrder(proc, order);
			System.out.println("On ajoute " + order.toString() + "a la proc" + proc);
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
	
	public void resetGame() {
		//int i = 0;
		for(String mapKey : m_character.keySet()) {
			System.out.println("Reset Pos Char with Map: " + mapKey + " " + m_map.getPosInit());
			m_engine.deleteCharacter(m_character.get(mapKey));
			m_character.get(mapKey).setOrientation(m_map.getOrientationInit().get(mapKey));
			m_character.get(mapKey).setHeight(m_map.getHeightInit().get(mapKey));
			m_character.get(mapKey).update(m_map.getPosInit().get(mapKey));
			m_character.get(mapKey).setColor(Color.WHITE);
			m_engine.resetCharacter(m_character.get(mapKey));
			//m_character.get(mapKey).setPosition(m_map.getPosInit().get(mapKey));
			//System.out.println("Reset Pos Char : " + mapKey + " " + m_character.get(mapKey).getPosition());
			//i++;
		}
			//m_map.getPosInit()
	}
	
	public void setStateSimulation(boolean state) {
		m_runSimulation = state;
		if(!m_runSimulation) {
			resetGame();
		}
	}
	
	public boolean getStateSimulation() {
		return m_runSimulation;
	}


	public Level getLevel() {
		return m_level;
	}


	public void setLevel(Level m_level) {
		this.m_level = m_level;
	}
	
}
