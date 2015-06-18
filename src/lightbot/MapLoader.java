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
	
	public MapLoader(){
		
		//On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try {
			//document = sxb.build(new File("test2.xml"));
			document = sxb.build(new File("test2.xml"));
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
	
	public void character(Character robb, Character robs){
		int x = Integer.parseInt(racine.getChild("character").getAttributeValue("pos_x"));
		int y = Integer.parseInt(racine.getChild("character").getAttributeValue("pos_y"));
		List<Element> listChar = racine.getChildren("character");
		for (int j = 0; j < listChar.size(); j++) {
			if(listChar.get(j).getAttributeValue("type").equals("basic")) {
				robb.update(new Vector2i(x,y));
				
				switch (listChar.get(j).getAttributeValue("orientation")){
				 case "up" : robb.setOrientation(Character.Orientation.Up); break;
				 case "right": robb.setOrientation(Character.Orientation.Right); break;
				 case "left": robb.setOrientation(Character.Orientation.Left); break;
				 default  : robb.setOrientation(Character.Orientation.Down); break;
				}
				String orders = listChar.get(j).getChild("orders").getText();
				String[] parts = orders.split(" ");
				System.out.println("Orders: ");
				for (int k = 0; k < parts.length; k++) {
					System.out.println(parts[k]);
				}
			} else {
				robs.update(new Vector2i(x,y));
				switch (listChar.get(j).getAttributeValue("orientation")){
				 case "Up" : robs.setOrientation(Character.Orientation.Up); break;
				 case "Right": robs.setOrientation(Character.Orientation.Right); break;
				 case "Left": robs.setOrientation(Character.Orientation.Left); break;
				 default  : robs.setOrientation(Character.Orientation.Down); break;
				}
				String orders = listChar.get(j).getChild("orders").getText();
			}
		}
	}
}
