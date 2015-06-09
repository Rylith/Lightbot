package lightbot;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Case extends DrawableObject {

/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 50;
	private final static int SIZESPRITEY = 50;
	
	private int m_value; //valeur affichée sur la case

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class Case avec value */
	
	public Case(Vector2f position, int height, Color color, String tilePath, int value){
		super(position, height, color, tilePath);
		m_value = value;
		if (m_value == 4 && getHeight() == 4) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 4 && getHeight() == 3) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 4 && getHeight() == 2) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 4 && getHeight() == 1) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 3 && getHeight() == 4) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 3 && getHeight() == 3) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 3 && getHeight() == 2) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 3 && getHeight() == 1) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 2 && getHeight() == 4) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 2 && getHeight() == 3) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 2 && getHeight() == 2) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 2 && getHeight() == 1) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 1 && getHeight() == 4) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 1 && getHeight() == 3) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else if (m_value == 1 && getHeight() == 2) {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
		else /*(m_value == 1 && getHeight() == 1)*/ {
			getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
		}
}
	
	/** Constructeur de la class Case sans value */
	
	public Case(Vector2f position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_value = 1;
		switch (getHeight())
		{
            case 4:
            	getSprite().setTextureRect(new IntRect(0, 0, SIZESPRITEX, SIZESPRITEY));
            break;
            case 3:
            	getSprite().setTextureRect(new IntRect(0, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
            break;
            case 2:
            	getSprite().setTextureRect(new IntRect(0, 2*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
            break;
            case 1:
            	getSprite().setTextureRect(new IntRect(0, 3*SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
            break;
        }
	}

	
/** ---------------- METHODS ----------------- */	


	/** Retourne le nombre de la case */
	public int getValue(){
		return m_value;
	}
	
	/** Assigne le nombre a la case */
	public void setValue(int value){
		m_value = value;
	}
	
	/** Retourne true si la position est contenue dans la case (false sinon) */
	public boolean isContain(Vector2f position){
		return getSprite().getLocalBounds().contains(position);
	}

}
