package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;

import lightbot.Case;
import lightbot.MapLoader;
import lightbot.Lampadaire;

public class Map {

/** --------------- ATTRIBUTES --------------- */
	public Case m_map[][];
	
/** -------------- CONSTRUCTORS -------------- */
	public Map(){
		MapLoader ml = new MapLoader();
		Vector2i size = MapLoader.mapSize();
		m_map = new Case[size.x][size.y];
		createMap(ml);
	}
/** ---------------- METHODS ----------------- */
	public void createMap(MapLoader ml){
		for (int i = 0; i < this.m_map.length; i++) {
			int l = 0;
			List<Element> listCases = ml.getCasesLine(i);
			System.out.println("J'ai recup ma liste de cases");
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (listCases.size() > 0 && l < listCases.size()) {
					Element caseElement = (Element) listCases.get(l);
					if (Integer.parseInt(caseElement.getAttributeValue("pos_y")) == j) {
						switch (caseElement.getAttributeValue("type"))
						{
				           case "White":
				        	   System.out.println("White");
				        	   m_map[i][j] = new Case(new Vector2i(i,j), Integer.parseInt(caseElement.getAttributeValue("height")), Color.WHITE, "Case.png");
				           break;
				           case "Basic":
				        	   System.out.println("Basic");
				        	   m_map[i][j] = new Case(new Vector2i(i,j), Integer.parseInt(caseElement.getAttributeValue("height")), Color.WHITE, "Case.png");
				           break;
				           case "Lampe":
				        	   System.out.println("Lampe");
				        	   m_map[i][j] = new Case(new Vector2i(i,j), Integer.parseInt(caseElement.getAttributeValue("height")), Color.WHITE, "Case.png");
				        	   //Lampadaire(Vector2i position, int height, Color color, String tilePath, int value)
				        	   m_map[i][j].addObject(2, new Lampadaire(new Vector2i(i, j), Integer.parseInt(caseElement.getAttributeValue("height")), Color.WHITE, "Object.png"));
				           break;
						}
						l++;
					}
				}
			}
		}
	}
	
	public void drawMap(RenderWindow fenetre, Character robot){
		for (int i = 0; i < this.m_map.length; i++) {
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (this.m_map[i][j] != null) {
					this.m_map[i][j].drawCase(fenetre);
				}
				/*if (robot.getPosition().x == i && robot.getPosition().y == j) {
					robot.update(fenetre,new Vector2f(0, 0));
				}*/
			}
		}
	}
	
	public void testoMap(){
		for (int i = 0; i < this.m_map.length; i++) {
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (m_map[i][j] != null) {
					System.out.println("Case: " + i +","+j +" n'est pas nulle.");
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
		
		return this.m_map;
	}
}
