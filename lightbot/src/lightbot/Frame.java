package lightbot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

import lightbot.Button.ButtonType;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Frame extends Component {

	
/** --------------- ATTRIBUTES --------------- */
	
	private final static int SIZEMAINX = 70; 
	private final static int SIZEMAINY = 70;
	private final static int SIZEPROCX = 70;
	private final static int SIZEPROCY = 70;
	private final static int SIZELISTX = 70;
	private final static int SIZELISTY = 70;
	
	public enum FrameType{
		Main,
		P1,
		P2,
		OrderList,
	}
	
	private FrameType m_type; //indique le type de frame
	private Vector<Component> m_contain; //liste d'odre contenu dans la Frame
	private boolean m_active; //indique si la frame est active (on ne peut ajouter des ordres que dans la vue active)
	
	
/** -------------- CONSTRUCTORS -------------- */
	
	public Frame(String tilePath,Vector2f position, FrameType type) {
		super(tilePath,position);
		super.setVisibility(true); //Les frames sont visibles par default
		m_type = type;
		if (m_type == FrameType.Main){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEMAINX, SIZEMAINY));
			m_active = true;
		}
		else if (m_type == FrameType.P1){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPROCX, SIZEPROCY));
			m_active = false;
		}
		else if (m_type == FrameType.P2){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPROCX, SIZEPROCY));
			m_active = false;
		}
		else if (m_type == FrameType.OrderList){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZELISTX, SIZELISTY));
			m_active = false;
		}
	}
	
	
/** ---------------- METHODS ----------------- */	
	
	/** Ajout d'un component a la fin de la liste d'odre */
	public void addComponent(Component c){
		if (m_contain.isEmpty()) {
			c.setPosition(new Vector2f(this.getSprite().getPosition().x + 46.0f , this.getSprite().getPosition().y + 8.0f));
		}
		else {
			if(m_contain.size() % 8 == 1) {
				c.setPosition(new Vector2f(m_contain.get(m_contain.size()-8-1).getSprite().getPosition().x , m_contain.lastElement().getSprite().getPosition().y + 81.0f));
			}
			else {
				c.setPosition(new Vector2f(m_contain.lastElement().getSprite().getPosition().x + (m_contain.size()/8)*86.0f , m_contain.lastElement().getSprite().getPosition().y));
			}
		}
		m_contain.addElement(c);
	}
	
	
	/** Supprime le component a la fin de la liste d'ordre */
	public void popComponent(){
		m_contain.remove(m_contain.size()-1);
	}
	
	
	/** Draw l'ensemble des components contenu dans la liste d'ordre */
	public void draw(RenderWindow window){
		super.draw(window);
		for (int i = 0 ; i < m_contain.size() ; i++){
			m_contain.elementAt(i).draw(window);
		}
	}
	
	
	/** Indique si on a clique dans la frame */
	public boolean isClicked(Vector2i position){
		return super.getSprite().getGlobalBounds().contains(position.x, position.y);
	}
	
	
	/** Retourne l'etat d'activation de la frame */
	public boolean getActive(){
		return m_active;
	}
	
	
	/** Desactive/Active la frame
	 * 0 : Desactive
	 * 1 : Active
	 */
	public void ActiveFrame(int i){
		if (i == 0) {
			if (m_type == FrameType.Main){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEMAINX, SIZEMAINY));
				m_active = false;	
			}
			else if (m_type == FrameType.P1){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPROCX, SIZEPROCY));
				m_active = false;	
			}
			else if (m_type == FrameType.P2){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPROCX, SIZEPROCY));
				m_active = false;	
			}
		}
		else if (i == 1) {
			if (m_type == FrameType.Main){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEMAINX, SIZEMAINY));
				m_active = true;	
			}
			else if (m_type == FrameType.P1){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPROCX, SIZEPROCY));
				m_active = true;
			}
			else if (m_type == FrameType.P2){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPROCX, SIZEPROCY));
				m_active = true;
			}
		}
		else {
			/* ERREUR */
		}
	}
	
	
}
