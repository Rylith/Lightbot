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
import org.w3c.dom.NodeList;

import lightbot.Case;
import lightbot.MapLoader;
import lightbot.Lampadaire;

public class Map {

/** --------------- ATTRIBUTES --------------- */
	public Case m_map[][];
	
	private int m_lines;
	private int m_colonnes;
	
	private static final int d_lampe = 3;
	private static final int d_paint = 0;
	private static final int d_for = 1;
	private static final int d_pointeur = 2;
	private static final int d_charac = 4;
	
/** -------------- CONSTRUCTORS -------------- */
	public Map(Character robb, Character robs,String map_path){
		MapLoader ml = new MapLoader(map_path);
		Vector2i size = MapLoader.mapSize();
		this.m_lines = size.x;
		this.m_colonnes = size.y;
		m_map = new Case[size.x][size.y];
		ml.character(robb, robs);
		System.out.println("MAIN : " + robb.getPosition().x + " " + robb.getPosition().y);
		createMap(ml,robb,robs);
	}
/** ---------------- METHODS ----------------- */
	public void createMap(MapLoader ml, Character robb, Character robs){
		for (int i = 0; i < this.m_map.length; i++) {
			int l = 0;
			List<Element> listCases = ml.getCasesLine(i);
			
/*--------------------TRAITEMENT DE LA LISTE DE CASES--------------------*/		
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (listCases.size() > 0 && l < listCases.size()) {
					Element caseElement = (Element) listCases.get(l);
					if (Integer.parseInt(caseElement.getAttributeValue("pos_y")) == j) {
						int h = Integer.parseInt(caseElement.getAttributeValue("height"));
						/*------------------------------NEW VERSION------------------------------*/
						Vector2i posi = new Vector2i(i,j);
						m_map[i][j] = new Case(posi, h, "case.png");
						
						if (caseElement.getChild("paint") != null) {
							ColorMark painter = new ColorMark(posi, h, "case.png",Color.CYAN);
							if (caseElement.getChild("paint").getAttributeValue("color") == "magenta") {
								painter.setColor(Color.MAGENTA);
							}
							m_map[i][j].addObject(d_paint, painter);
						}
						
						if (caseElement.getChild("lampe") != null) {
				        	   m_map[i][j].addObject(d_lampe, new Lampadaire(posi, h, Color.WHITE, "Object.png"));
						}
						
						if (caseElement.getChild("pointeur") != null) {
							Pointeur pointer = new Pointeur(posi, h, Color.BLUE, "case.png");
							switch (caseElement.getChild("pointeur").getAttributeValue("color"))
							{
					           case "yellow":
					        	   pointer.setColor(Color.YELLOW);
					           break;
					           case "red":
					        	   pointer.setColor(Color.RED);
					           break;
					           case "green":
					        	   pointer.setColor(Color.GREEN);
					           break;
							}
							 m_map[i][j].addObject(d_pointeur, pointer);
						}
						
						if (caseElement.getChild("character") != null) {
							if (caseElement.getChild("character").getAttributeValue("type").equals("basic")) {
								robb.setHeight(h);
								 m_map[i][j].addObject(d_charac, robb);
							} else {
								robs.setHeight(h);
								m_map[i][j].addObject(d_charac, robs);
							}
						}
						
						if (caseElement.getChild("for") != null) {
							NumberMark repete = new NumberMark(posi, h, "case.png", 1);
							repete.setValue(Integer.parseInt(caseElement.getChild("for").getAttributeValue("number")));
							 m_map[i][j].addObject(d_for, repete);
						}
						
						/*------------------------------NEW VERSION------------------------------*/
						l++;
					}
				}
			}
/*--------------------TRAITEMENT DE LA LISTE DE CASES--------------------*/			
		}
	}
	
	public void drawMap(RenderWindow fenetre){
		for (int i = 0; i < this.m_map.length; i++) {
			for (int j = 0; j < this.m_map[i].length; j++) {
				if (this.m_map[i][j] != null) {
					this.m_map[i][j].drawCase(fenetre);
				}
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
				return (current_height == this.m_map[new_x][new_y].getHeight());
			}
			else return false;
	}
	
	public Case [][] get_m_mat(){
		
		return this.m_map;
	}
	
	public Vector2i getPointer(Color c){
		
		for(int i=0; i<m_map.length; i++){
			for(int j=0; j<m_map[i].length;j++){
				if(m_map[i][j]!=null)
					if((m_map[i][j].getMapDO().containsKey(d_pointeur)) && (m_map[i][j].getMapDO().get(d_pointeur).getColor()== c)){
						
						return m_map[i][j].getPosition();
					}
					
			}
		}

		return null;	
	}
	
	public Vector2f getMapSize() {
		return new Vector2f(250f - (m_lines-1) * 78/2f, 92);
		//return new Vector2f(250, 92);
	}
	
	public void setScale(Vector2f scaling) {
		for (int i = 0; i < m_map.length; i++) {
			for (int j = 0; j < m_map[i].length; j++) {
				if (m_map[i][j] != null) {
					m_map[i][j].setScaling(scaling);
				}
			}
		}
	}

}
