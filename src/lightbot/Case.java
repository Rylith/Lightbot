package lightbot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import lightbot.Button.ButtonType;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Case extends DrawableObject {

	
/** --------------- ATTRIBUTES --------------- */

	private final static int SIZESPRITEX = 78;
	private final static int SIZESPRITEY = 49;
	private final static int SIZESPRITEE = 8;
	
	private int m_value; //valeur de la case
	private HashMap<Integer,DrawableObject> m_mapDrawableObject ;

	
	
/** -------------- CONSTRUCTORS -------------- */
	
	/** Constructeur de la class Case 
	 * @info : par defaut la couleur est a WHITE et la valeur a 1
	 */
	public Case(Vector2i position, int height, String tilePath){
		super(position, height, Color.WHITE, tilePath);
		m_mapDrawableObject = new HashMap<Integer,DrawableObject>();
		m_value = 1;
		//Generation aleatoire de la case parmis 5 possibilite de cases differentes
		int i = (int)((Math.random()*(5-0)));
		getSprite().setTextureRect(new IntRect(i*SIZESPRITEX, SIZESPRITEY, SIZESPRITEX, SIZESPRITEY));
		float pos_x = 250 +  this.getPosition().y * SIZESPRITEX/2 - this.getPosition().x * SIZESPRITEX/2;
		float pos_y = 100 + (this.getPosition().x + this.getPosition().y) * ((SIZESPRITEY - SIZESPRITEE)/2);
		this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
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
	
	/** Retourne la HashMap de DrawableObject present sur la case */
	public HashMap <Integer,DrawableObject> getMapDO(){
		return m_mapDrawableObject;
	}
	
	/** Permet d'ajouter un DrawableObjet sur la case */
	public void addObject(int depth, DrawableObject object){
		m_mapDrawableObject.put(depth, object);		
	}
	
	/** Permet de supprimer un DrawableObjet de la case */	
	public void delObject(int depth){
		m_mapDrawableObject.remove(depth);		
	}
	
	/** Dessine la case dans la fenetre */
	public void drawCase(RenderWindow fenetre) {
		
		Vector2f ancient = new Vector2f(this.getSprite().getGlobalBounds().height , this.getSprite().getGlobalBounds().width);
		Vector2f newS = new Vector2f(this.getSprite().getLocalBounds().height,this.getSprite().getLocalBounds().width);
		Vector2f diff = Vector2f.componentwiseDiv(ancient, newS);
		
		for (int i = 0; i < this.getHeight(); i++) {
			this.getSprite().move(0, - SIZESPRITEE*diff.x);
			fenetre.draw(this.getSprite());
		}
		
		float pos_x = 250 + this.getPosition().y * (SIZESPRITEX*diff.x)/2 - this.getPosition().x * (SIZESPRITEX*diff.x)/2;
		float pos_y = 100 +(this.getPosition().x + this.getPosition().y) * (((SIZESPRITEY*diff.y) - (SIZESPRITEE*diff.y))/2);

		this.getSprite().setPosition(new Vector2f(pos_x,pos_y));
		
		int obd = 0;
		int testo = 0;
		if (m_mapDrawableObject.size() > 0) {
			while (obd < m_mapDrawableObject.size()) {
				DrawableObject dob = m_mapDrawableObject.get(testo);
				if (dob != null){
					//dob.getSprite().setPosition(this.getSprite().getPosition().x * diff.x, this.getSprite().getPosition().y * diff.y);
					fenetre.draw(dob.getSprite());
					obd++;
				}
				testo++;
			}
		}
	}

	
	public void setScaling(Vector2f scaling) {
		super.setScaling(scaling);
		//super.getSprite().setPosition(Vector2f.sub(new Vector2f(this.getSprite().getGlobalBounds().height , this.getSprite().getGlobalBounds().width),new Vector2f(this.getSprite().getLocalBounds().height,this.getSprite().getLocalBounds().width)));
		Vector2f ancient = new Vector2f(this.getSprite().getGlobalBounds().height , this.getSprite().getGlobalBounds().width);
		Vector2f newS = new Vector2f(this.getSprite().getLocalBounds().height,this.getSprite().getLocalBounds().width);
		Vector2f.sub(ancient, newS);
		Vector2f diff = Vector2f.sub(ancient, newS);
		this.getSprite().setPosition(Vector2f.sub(this.getSprite().getPosition(), diff));
		for (Integer mapKey : m_mapDrawableObject.keySet()) {
			m_mapDrawableObject.get(mapKey).setScaling(scaling);
		}

	}
	
}
