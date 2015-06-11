package lightbot;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Case extends DrawableObject {

/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 130;
	private final static int SIZESPRITEY = 75;
	
	private int m_value; //valeur affichée sur la case

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	
	/** Constructeur de la class Case avec value */
	
	public Case(Vector2i position, int height, Color color, String tilePath, int value){
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
	
	public Case(Vector2i position, int height, Color color, String tilePath){
		super(position, height, color, tilePath);
		m_value = 1;
		/*switch (getHeight())
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
        }*/
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
	
	public void drawCase(RenderWindow fenetre){
		float empile_y = -20.5f;
		float decaleLine = this.getPosition().x * -41;
		float pos_x_graph = 250 + decaleLine + this.getPosition().y*41 ;
		float pos_y_graph = 100 + this.getPosition().y * 20.5f + this.getPosition().x *20.5f;
		super.draw(fenetre, pos_x_graph, pos_y_graph);
		for(int i = 2; i <= this.getHeight();++i){
			//pos_y_graph = pos_y_graph + i*empile_y;
			super.draw(fenetre, pos_x_graph, (pos_y_graph + i*empile_y));
			/*System.out.println("Val du i: " + i);
			System.out.println("pos_x: " + pos_x_graph + " Pos y: " + (pos_y_graph + i*empile_y));*/
		}
	}
}
