package lightbot;

import java.io.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
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
			document = sxb.build(new File("test.xml"));
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
}
