package lightbot;

import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

import java.util.List;
import java.util.Iterator;

public class MapLoader {
	
	static org.jdom2.Document document;
	static Element racine;
	
	public MapLoader(String map_path){
		
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(map_path));
		}
		catch(Exception e) {
			
		}
		//On initialise un nouvel éléent racine avec l'element racine du doc
		racine = document.getRootElement();
		
	}
	
	static Vector2i mapSize(){
		int x = Integer.parseInt(racine.getChild("size").getChild("lines").getText());
		int y = Integer.parseInt(racine.getChild("size").getChild("colonnes").getText());
		return new Vector2i(x,y);
	}
	
	static List<Element> getCasesLine(int line) {
		 List<Element> listLines = racine.getChildren("line");
		 Element lineElement = (Element) listLines.get(line);
		 return lineElement.getChildren("case");
	}
	
	static String getOrders(){
		return racine.getChild("character").getChild("orders").getText();
	}
	
	public void character(Character robb, Character robs, Vector2i b_pos_init, Vector2i s_pos_init) {
		List<Element> listChar = racine.getChildren("character");
		for (int j = 0; j < listChar.size(); j++) {
			if(listChar.get(j).getAttributeValue("type").equals("basic")) {
				b_pos_init = new Vector2i(Integer.parseInt(listChar.get(j).getAttributeValue("pos_x")),Integer.parseInt(listChar.get(j).getAttributeValue("pos_y")));
				robb.update(b_pos_init);
				
				switch (listChar.get(j).getAttributeValue("orientation")){
				 case "up" : robb.setOrientation(Character.Orientation.Up); break;
				 case "right": robb.setOrientation(Character.Orientation.Right); break;
				 case "left": robb.setOrientation(Character.Orientation.Left); break;
				 default  : robb.setOrientation(Character.Orientation.Down); break;
				}
				robb.setLimitOrder(0,Integer.parseInt(listChar.get(j).getChild("main").getText()));
				robb.setLimitOrder(1,Integer.parseInt(listChar.get(j).getChild("proc1").getText()));
				robb.setLimitOrder(2,Integer.parseInt(listChar.get(j).getChild("proc2").getText()));
				
			} else {
				s_pos_init = new Vector2i(Integer.parseInt(listChar.get(j).getAttributeValue("pos_x")),Integer.parseInt(listChar.get(j).getAttributeValue("pos_y")));
				robs.update(s_pos_init);
				switch (listChar.get(j).getAttributeValue("orientation")){
				 case "up" : robs.setOrientation(Character.Orientation.Up); break;
				 case "right": robs.setOrientation(Character.Orientation.Right); break;
				 case "left": robs.setOrientation(Character.Orientation.Left); break;
				 default  : robs.setOrientation(Character.Orientation.Down); break;
				}
				robs.setLimitOrder(0,Integer.parseInt(listChar.get(j).getChild("main").getText()));
				robs.setLimitOrder(1,Integer.parseInt(listChar.get(j).getChild("proc1").getText()));
				robs.setLimitOrder(2,Integer.parseInt(listChar.get(j).getChild("proc2").getText()));
			}
		}
	}
	
	public void getPossibleOrders(List<Button.ButtonType> b_possible, List<Button.ButtonType> s_possible) {
		List<Element> listChar = racine.getChildren("character");
		for (int j = 0; j < listChar.size(); j++) {
			if(listChar.get(j).getAttributeValue("type").equals("basic")) { 
				String orders = listChar.get(j).getChild("orders").getText();
				String parts[] = orders.split(" ");
				for (int i = 0; i < parts.length; i++) {
					switch (parts[i]){
						 case "move": b_possible.add(Button.ButtonType.Move); break;
						 case "turnr": b_possible.add(Button.ButtonType.TurnRight); break;
						 case "turnl": b_possible.add(Button.ButtonType.TurnLeft); break;
						 case "jump": b_possible.add(Button.ButtonType.Jump); break;
						 case "light": b_possible.add(Button.ButtonType.Light); break;
						 case "for": b_possible.add(Button.ButtonType.For); break;
						 case "paint": b_possible.add(Button.ButtonType.Paint); break;
						 case "douche": b_possible.add(Button.ButtonType.RemoveColor); break;
						 case "p1": b_possible.add(Button.ButtonType.P1); break;
						 case "p2": b_possible.add(Button.ButtonType.P2); break;
						 case "p_pointeur": b_possible.add(Button.ButtonType.PutP); break;
						 case "a_pointeur": b_possible.add(Button.ButtonType.UseP); break;
					}
				}
			} else {
			String orders = listChar.get(j).getChild("orders").getText();
			String parts[] = orders.split(" ");
				for (int i = 0; i < parts.length; i++) {
					switch (parts[i]) {
						 case "move": b_possible.add(Button.ButtonType.Move); break;
						 case "turnr": b_possible.add(Button.ButtonType.TurnRight); break;
						 case "turnl": b_possible.add(Button.ButtonType.TurnLeft); break;
						 case "jump": b_possible.add(Button.ButtonType.Jump); break;
						 case "light": b_possible.add(Button.ButtonType.Light); break;
						 case "for": b_possible.add(Button.ButtonType.For); break;
						 case "paint": b_possible.add(Button.ButtonType.Paint); break;
						 case "douche": b_possible.add(Button.ButtonType.RemoveColor); break;
						 case "p1": b_possible.add(Button.ButtonType.P1); break;
						 case "p2": b_possible.add(Button.ButtonType.P2); break;
						 case "p_pointeur": b_possible.add(Button.ButtonType.PutP); break;
						 case "a_pointeur": b_possible.add(Button.ButtonType.UseP); break;
					}
				}	
			}
		}
	}
}
