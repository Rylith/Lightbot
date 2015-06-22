package Game;

import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JButton;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import DrawableObject.Character.Orientation;

public class Button extends Component{
	
	
	
/** --------------- ATTRIBUTES --------------- */	
	
	private final static int SIZEORDER = 70;
	
	private final static int SIZEPUSHX = 15;
	private final static int SIZEPUSHY = 20;
	
	private final static int SIZEBOTX = 85;
	private final static int SIZEBOTY = 83;
	
	private final static int SIZEMENUX = 149;
	private final static int SIZEMENUY = 83;
	
	private final static int SIZEONX = 140;
	private final static int SIZEONY = 70;
	
	
	public enum ButtonType{
		Move,
		TurnRight,
		TurnLeft,
		Jump,
		Light,
		For,
		PutP,
		UseP,
		Paint,
		RemoveColor,
		P1,
		P2,
		PushYellow,
		PushBlue,
		PushGreen,
		PushRed,
		PushGrey,
		PushMagenta,
		PushCyan,
		BasicBot,
		SmartBot,
		Run,
		Stop,
		Cadre,
		Level,
		Menu,
	}
	
	private ButtonType m_type; //indique le type de bouton
	private boolean m_order; //indique si le bouton represente un order
	private Color m_color; //indique la couleur du bouton
	private Color m_colorPointeur = Color.GREEN;
	private boolean m_active; //indique si le bouton est presse
	private boolean m_enable;
	
	
/** -------------- CONSTRUCTORS -------------- */	
	
	/** Constructeur de Button
	 * @param tilePath
	 * @param position
	 * @param type
	 * @param move
	 */
	public Button(String tilePath, Vector2f position, ButtonType type, boolean move) {
		super(tilePath, position);
		m_order = move;
		m_color = Color.WHITE;
		m_type = type;
		m_enable = false;
		if (m_type == ButtonType.Cadre){
			super.getSprite().setTextureRect(new IntRect(2*SIZEONX, 3*SIZEORDER, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.Move){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.TurnRight){
			super.getSprite().setTextureRect(new IntRect(SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.TurnLeft){
			super.getSprite().setTextureRect(new IntRect(2*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.Jump){
			super.getSprite().setTextureRect(new IntRect(3*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.Light){
			super.getSprite().setTextureRect(new IntRect(4*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.For){
			super.getSprite().setTextureRect(new IntRect(5*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.Paint){
			super.getSprite().setTextureRect(new IntRect(6*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.RemoveColor){
			super.getSprite().setTextureRect(new IntRect(7*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.P1){
			super.getSprite().setTextureRect(new IntRect(8*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.P2){
			super.getSprite().setTextureRect(new IntRect(9*SIZEORDER, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.PutP){
			super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, 0, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
			m_active = false;
		}
		else if (m_type == ButtonType.UseP){
			super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, 0, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
			m_active = false;
		}
		else if (m_type == ButtonType.PushMagenta){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
			super.setVisibility(true); //Toujours visible
			m_color = Color.MAGENTA;
			m_active = false; 
		}
		else if (m_type == ButtonType.PushGrey){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY)); //Sprite bouton enfonce
			super.setVisibility(true); //Toujours visible
			m_color = Color.WHITE;
			m_active = true;
		}
		else if (m_type == ButtonType.PushCyan){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 2*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
			super.setVisibility(true); //Toujours visible
			m_color = Color.CYAN;
			m_active = false;
		}
		else if (m_type == ButtonType.PushGreen){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 3*SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY)); //Sprite bouton enfonce
			super.setVisibility(true); //Toujours visible
			m_color = Color.GREEN;
			m_active = true;
		}
		else if (m_type == ButtonType.PushYellow){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 4*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
			super.setVisibility(true); //Toujours visible
			m_color = Color.YELLOW;
			m_active = false;
		}
		else if (m_type == ButtonType.PushRed){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 5*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
			super.setVisibility(true); //Toujours visible
			m_color = Color.RED;
			m_active = false;
		}
		else if (m_type == ButtonType.PushBlue){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 6*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
			super.setVisibility(true); //Toujours visible
			m_color = Color.BLUE;
			m_active = false;
		}
		else if (m_type == ButtonType.BasicBot){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX + 2*SIZEBOTX), 3*SIZEORDER, SIZEBOTX, SIZEBOTY)); //Sprite bouton enfonce
			super.setVisibility(true); //Toujours visible
			m_active = true;
		}
		else if (m_type == ButtonType.SmartBot){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX + 3*SIZEBOTX), 3*SIZEORDER, SIZEBOTX, SIZEBOTY));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.Run){
			super.getSprite().setTextureRect(new IntRect(0, 3*SIZEORDER, SIZEONX, SIZEONY));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.Stop){
			super.getSprite().setTextureRect(new IntRect(SIZEONX, 3*SIZEORDER, SIZEONX, SIZEONY));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.Level){
			m_active = false;
		}
		else if (m_type == ButtonType.Menu){
			super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX + 4*SIZEBOTX), 3*SIZEORDER, SIZEMENUX, SIZEMENUY));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		
		this.getSprite().setPosition(position);
	}
	

/** ---------------- METHODS ----------------- */
	
	
	/** Retourne la couleur de l'objet
	 * @return m_color
	 */
	public Color getColor() {
		return m_color;
	}
	
	public Color getColorPointer(){
		return m_colorPointeur;
	}
	
	public ButtonType getType(){
		return m_type;
	}
	
	public void enable() {
		m_enable = true;
	}
	
	public void disable() {
		m_enable = false;
	}
	
	/** Indique si l'on a clique sur le sprite du bouton 
	 * @param position
	 * @return boolean
	 */
	public boolean isClicked(Vector2i position){
		return super.getSprite().getGlobalBounds().contains(position.x, position.y);
	}
	
	
	/** Indique si le bouton est pressse (active)
	 * @return m_active
	 */
	public boolean isActive(){
		return m_active;
	}
		
	
	/** Desactive/Active le bouton (mise a jours du sprite)
	 * @param i : false si bouton non enfonce | true si bouton enfonce
	 */
	public void ActiveButton(boolean i) {
		if (i == false) { //Bouton non enfonce
			if (m_type == ButtonType.PushMagenta){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
				m_active = false; 
			}
			else if (m_type == ButtonType.PushGrey){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY)); 
				m_active = false;
			}
			else if (m_type == ButtonType.PushCyan){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 2*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
				m_active = false;
			}
			else if (m_type == ButtonType.PushGreen){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 3*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY)); 
				m_active = false;
			}
			else if (m_type == ButtonType.PushYellow){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 4*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
				m_active = false;
			}
			else if (m_type == ButtonType.PushRed){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 5*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
				m_active = false;
			}
			else if (m_type == ButtonType.PushBlue){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 6*SIZEPUSHX), 3*SIZEORDER, SIZEPUSHX, SIZEPUSHY));
				m_active = false;
			}
			else if (m_type == ButtonType.BasicBot){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX), 3*SIZEORDER, SIZEBOTX, SIZEBOTY)); 
				m_active = false;
			}
			else if (m_type == ButtonType.SmartBot){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX + 3*SIZEBOTX), 3*SIZEORDER, SIZEBOTX, SIZEBOTY));
				m_active = false;
			}
			else if (m_type == ButtonType.Run){
				super.getSprite().setTextureRect(new IntRect(0, 3*SIZEORDER, SIZEONX, SIZEONY));
				super.setVisibility(true); //Toujours visible
				m_active = false;
			}
			else if (m_type == ButtonType.Stop){
				super.getSprite().setTextureRect(new IntRect(SIZEONX, 3*SIZEORDER, SIZEONX, SIZEONY));
				super.setVisibility(true); //Toujours visible
				m_active = false;
			}
		}
		else if (i == true){ //Bouton enfonce
			if (m_type == ButtonType.PushMagenta){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY));
				m_active = true; 
			}
			else if (m_type == ButtonType.PushGrey){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY)); 
				m_active = true;
			}
			else if (m_type == ButtonType.PushCyan){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 2*SIZEPUSHX) ,(3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY));
				m_active = true;
			}
			else if (m_type == ButtonType.PushGreen){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 3*SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY)); 
				m_active = true;
			}
			else if (m_type == ButtonType.PushYellow){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 4*SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY));
				m_active = true;
			}
			else if (m_type == ButtonType.PushRed){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 5*SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY));
				m_active = true;
			}
			else if (m_type == ButtonType.PushBlue){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 6*SIZEPUSHX), (3*SIZEORDER + SIZEPUSHY), SIZEPUSHX, SIZEPUSHY));
				m_active = true;
			}
			else if (m_type == ButtonType.BasicBot){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX + 2*SIZEBOTX), 3*SIZEORDER, SIZEBOTX, SIZEBOTY)); 
				m_active = true;
			}
			else if (m_type == ButtonType.SmartBot){
				super.getSprite().setTextureRect(new IntRect((2 * SIZEONX + SIZEORDER + 7*SIZEPUSHX + SIZEBOTX), 3*SIZEORDER, SIZEBOTX, SIZEBOTY));
				m_active = true;
			}
			else if (m_type == ButtonType.Run){ // Le bouton Run devient un bouton Stop
				//m_type = ButtonType.Stop;
				super.getSprite().setTextureRect(new IntRect(SIZEONX, 3*SIZEORDER, SIZEONX, SIZEONY));
				super.setVisibility(true); //Toujours visible
				m_active = true;
			}
			else if (m_type == ButtonType.Stop){ // Le bouton Stop devient un bouton Run
				m_type = ButtonType.Run;
				super.getSprite().setTextureRect(new IntRect(0, 3*SIZEORDER, SIZEONX, SIZEONY));
				super.setVisibility(true); //Toujours visible
				m_active = true;
			}
		}
	}
	
	
	/** Mise a jours des sprites en fonction de la couleur des instructions
	 * @param c : Color.GREY / Color.MAGENTA / Color.CYAN
	 */
	public void setColor(Color c){
		m_color = c;
		if (m_color == Color.CYAN){
			if (m_type == ButtonType.Move){
				super.getSprite().setTextureRect(new IntRect(0, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.TurnRight){
				super.getSprite().setTextureRect(new IntRect(SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.TurnLeft){
				super.getSprite().setTextureRect(new IntRect(2*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Jump){
				super.getSprite().setTextureRect(new IntRect(3*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Light){
				super.getSprite().setTextureRect(new IntRect(4*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.For){
				super.getSprite().setTextureRect(new IntRect(5*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Paint){
				super.getSprite().setTextureRect(new IntRect(6*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.RemoveColor){
				super.getSprite().setTextureRect(new IntRect(7*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.P1){
				super.getSprite().setTextureRect(new IntRect(8*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.P2){
				super.getSprite().setTextureRect(new IntRect(9*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.PutP){
				super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
				setColorPointeur(m_colorPointeur);
				m_active = false;
			}
			else if (m_type == ButtonType.UseP){
				super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
				setColorPointeur(m_colorPointeur);
				m_active = false;
			}
		}
		else if (m_color == Color.MAGENTA) {
			if (m_type == ButtonType.Move){
				super.getSprite().setTextureRect(new IntRect(0, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.TurnRight){
				super.getSprite().setTextureRect(new IntRect(SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.TurnLeft){
				super.getSprite().setTextureRect(new IntRect(2*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Jump){
				super.getSprite().setTextureRect(new IntRect(3*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Light){
				super.getSprite().setTextureRect(new IntRect(4*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.For){
				super.getSprite().setTextureRect(new IntRect(5*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Paint){
				super.getSprite().setTextureRect(new IntRect(6*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.RemoveColor){
				super.getSprite().setTextureRect(new IntRect(7*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.P1){
				super.getSprite().setTextureRect(new IntRect(8*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.P2){
				super.getSprite().setTextureRect(new IntRect(9*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.PutP){
				super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
				setColorPointeur(m_colorPointeur);
				m_active = false;
			}
			else if (m_type == ButtonType.UseP){
				super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
				setColorPointeur(m_colorPointeur);
				m_active = false;
			}
		}
		else {
			if (m_type == ButtonType.Move){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.TurnRight){
				super.getSprite().setTextureRect(new IntRect(SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.TurnLeft){
				super.getSprite().setTextureRect(new IntRect(2*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Jump){
				super.getSprite().setTextureRect(new IntRect(3*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Light){
				super.getSprite().setTextureRect(new IntRect(4*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.For){
				super.getSprite().setTextureRect(new IntRect(5*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.Paint){
				super.getSprite().setTextureRect(new IntRect(6*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.RemoveColor){
				super.getSprite().setTextureRect(new IntRect(7*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.P1){
				super.getSprite().setTextureRect(new IntRect(8*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.P2){
				super.getSprite().setTextureRect(new IntRect(9*SIZEORDER, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.PutP){
				super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, 0, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
				setColorPointeur(m_colorPointeur);
				m_active = false;
			}
			else if (m_type == ButtonType.UseP){
				super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, 0, SIZEORDER, SIZEORDER)); //Sprite Pointeur vert
				setColorPointeur(m_colorPointeur);
				m_active = false;
			}
		}
	}
	
	
	/** Mise a jours des sprites en fonction de la couleur des pointeurs
	 * @param colorPointeur : Color.YELLOW , Color.RED , Color.BLUE , Color.GREEN
	 */
	public void setColorPointeur(Color colorPointeur){
		m_colorPointeur = colorPointeur;
		if (getColor() == Color.MAGENTA) {
			if (m_colorPointeur == Color.YELLOW){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(11*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(15*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
			}
			else if (m_colorPointeur == Color.RED){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(12*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(16*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
			}
			else if (m_colorPointeur == Color.BLUE){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(13*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(17*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
			}
			else { //Pointeur vert
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
			}
		}
		else if (getColor() == Color.CYAN){
			if (m_colorPointeur == Color.YELLOW){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(11*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(15*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
			else if (m_colorPointeur == Color.RED){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(12*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(16*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
			else if (m_colorPointeur == Color.BLUE){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(13*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(17*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
			else { //Pointeur vert
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER)); 
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, 2*SIZEORDER, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
		}
		else { // Instruction grise
			if (m_colorPointeur == Color.YELLOW){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(11*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(15*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
			else if (m_colorPointeur == Color.RED){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(12*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(16*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
			else if (m_colorPointeur == Color.BLUE){
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(13*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(17*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
			else { //Pointeur vert
				if (m_type == ButtonType.PutP){
					super.getSprite().setTextureRect(new IntRect(10*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
				else if (m_type == ButtonType.UseP){
					super.getSprite().setTextureRect(new IntRect(14*SIZEORDER, 0, SIZEORDER, SIZEORDER));
					m_active = false;
				}
			}
		}
	}
}
