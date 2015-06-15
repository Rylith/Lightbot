package lightbot;

import lightbot.Character.Orientation;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

public class testEngine {
	
	public static void main (String[] args){

	Map teste = new Map(4);
	teste.get_m_mat()[0][1].setHeight(2);
	teste.get_m_mat()[0][2].setHeight(4);
	
	Engine MonEngine = new Engine (teste);
	Character p = new Character(new Vector2i(0,0), 1, Color.GREEN, "lightbot.png");
	p.setOrientation(Orientation.Right);
	Order ordre = new TurnLeft (p, Color.WHITE);
	System.out.println("Vla lorientation batard :" + p.getOrientation());
	for (int i = 0; i < 5; i++){
		ordre.executer();
		System.out.println("Vla lorientation batard :" + p.getOrientation());
	
	}
}
	
}
