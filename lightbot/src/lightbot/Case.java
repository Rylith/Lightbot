package lightbot;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Case extends DrawableObject {

/** --------------- ATTRIBUTES --------------- */

	
	private int m_value; //valeur affichée sur la case

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class Case avec value */
	
	public Case(Vector2f position, int height, Color color, String tilePath, int value){
		super(position, height, color, tilePath);
		m_value = value;
		//TODO : initialise m_sprite en fonction de color & height
	}
	
	/** Constructeur de la class Case sans value */
	
	public Case(Vector2f position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_value = 1;
		//TODO : initialise m_sprite en fonction de color & height
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
