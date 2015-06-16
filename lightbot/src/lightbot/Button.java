package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JButton;

import lightbot.Character.Orientation;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Button extends Component{
	
	
/** --------------- ATTRIBUTES --------------- */	
	
	private final static int SIZEORDER = 70;
	private final static int SIZEPUSH = 70;
	
	
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
	}
	
	private ButtonType m_type; //indique le type de bouton
	private boolean m_order; //indique si le bouton represente un order
	private Color m_color; //indique la couleur du bouton
	private boolean m_active; //indique si le bouton est presse
	
	
/** -------------- CONSTRUCTORS -------------- */	
	
	public Button(String tilePath, Vector2f position, ButtonType type, boolean move) {
		super(tilePath, position);
		m_order = move;
		m_color = Color.WHITE;
		m_type = type;
		if (m_type == ButtonType.Move){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.TurnRight){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.TurnLeft){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.Jump){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.Light){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.For){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.PutP){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.UseP){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.RemoveColor){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.P1){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.P2){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			m_active = false;
		}
		else if (m_type == ButtonType.PushYellow){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.PushBlue){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.PushGreen){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH)); //Sprite bouton enfonce
			super.setVisibility(true); //Toujours visible
			m_active = true;
		}
		else if (m_type == ButtonType.PushRed){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.PushGrey){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH)); //Sprite bouton enfonce
			super.setVisibility(true); //Toujours visible
			m_active = true;
		}
		else if (m_type == ButtonType.PushMagenta){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.PushCyan){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
		else if (m_type == ButtonType.BasicBot){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER)); //Sprite bouton enfonce
			super.setVisibility(true); //Toujours visible
			m_active = true;
		}
		else if (m_type == ButtonType.SmartBot){
			super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
			super.setVisibility(true); //Toujours visible
			m_active = false;
		}
	}
	

/** ---------------- METHODS ----------------- */
	
	
	/** Indique si le bouton est clique 
	 */
	public boolean isClicked(Vector2i position){
		return super.getSprite().getGlobalBounds().contains(position.x, position.y);
	}
	
	
	/** Desactive/Active le bouton (mise a jours du sprite)
	 * 0 : Desactive
	 * 1 : Active
	 */
	public void ActiveButton(int i){
		if (i == 0) { //Bouton non enfonce
			if (m_type == ButtonType.PushYellow){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.PushBlue){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.PushGreen){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.PushRed){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.PushGrey){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.PushMagenta){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.PushCyan){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = false;
			}
			else if (m_type == ButtonType.BasicBot){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
			else if (m_type == ButtonType.SmartBot){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
				m_active = false;
			}
		}
		else if (i == 1){ //Bouton enfonce
			if (m_type == ButtonType.PushYellow){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.PushBlue){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.PushGreen){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.PushRed){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.PushGrey){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.PushMagenta){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.PushCyan){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEPUSH, SIZEPUSH));
				m_active = true;
			}
			else if (m_type == ButtonType.BasicBot){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
				m_active = true;
			}
			else if (m_type == ButtonType.SmartBot){
				super.getSprite().setTextureRect(new IntRect(0, 0, SIZEORDER, SIZEORDER));
				m_active = true;
			}
		}
		else {
			/* ERREUR */
		}
	}
	
	
	/** Mise a jours des sprites en fonction de la couleur des instructions
	 * Color.GREY / Color.MAGENTA / Color.CYAN
	 */
	public void setColor(Color c){
		m_color = c;
		if (m_color == Color.CYAN){
			/*TODO*/
		}
		else if (m_color == Color.MAGENTA) {
			/*TODO*/
		}
		else {
			/*TODO*/
		}
	}

	
}
