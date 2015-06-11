package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import lightbot.Case;

public class Map {

/** --------------- ATTRIBUTES --------------- */
	public Case m_map[][];
	
/** -------------- CONSTRUCTORS -------------- */
	public Map(int taille){
		m_map = new Case[taille][taille];
		createMap();
	}
/** ---------------- METHODS ----------------- */
	
	public void createMap(){
		for (int i = 0; i < this.m_map.length; i++) {
			for (int j = 0; j < this.m_map[i].length; j++) {
				this.m_map[i][j] = new Case(new Vector2i(i,j), 1, Color.WHITE, "case.png");
			}
		}
	}
	
	public void drawMap(RenderWindow fenetre){
		for (int i = 0; i < this.m_map.length; i++) {
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (this.m_map[i][j] != null) {
					this.m_map[i][j].drawCase(fenetre);
				}
				else {
				System.out.println("Fuck");
				}
			}
		}
	}
	
	public void testoMap(){
		for (int i = 0; i < this.m_map.length; i++) {
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (m_map[i][j] == null) {
					System.out.println("Case: " + i +","+j +" est nulle.");
				}
			}
		}
	}
	
	public boolean caseAccess(Vector2i pos , Character.Orientation orient) {
		int new_x = pos.x;
		int new_y =pos.y;
			
			switch (orient)
				{
	           case Up:
	        	   new_x = new_x-1;
	           break;
	           case Down:
	        	   new_x = new_x+1;
	           break;
	           case Left:
	        	   new_y = new_y-1;
	           break;
	           case Right:
	        	   new_y = new_y+1;
	           break;
				}
			System.out.println("New_x: " + new_x + "New_y: " + new_y);
			System.out.println("Length: " + this.m_map.length + "	Length colonne: " + this.m_map[0].length); 
			if(this.m_map.length > new_x && this.m_map[0].length > new_y && new_x >=0 && new_y >=0) {
				int current_height = this.m_map[pos.x][pos.y].getHeight();
				System.out.println("Bijour");
				return (current_height == this.m_map[new_x][new_y].getHeight());
			}
			else return false;
	}
	
	public Case [][] get_m_mat(){
		
		return this.get_m_mat();
	}
}
