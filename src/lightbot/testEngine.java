package lightbot;

import java.util.Vector;

import lightbot.Character.Orientation;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

public class testEngine {
	
	public static void main (String[] args){

		
	Map teste = new Map();
	
	//teste.get_m_mat()[0][1].setHeight(2);
	//teste.get_m_mat()[0][2].setHeight(4);
	
	Engine MonEngine = new Engine (teste);
	
	Character p = new Character(new Vector2i(0,0), 1, Color.GREEN, "lightbot.png");
	
	/* ========================TESTS DE TURNLEFT==============================
	 System.out.println("la couleur bleu?  :" +Color.BLUE);
	p.setOrientation(Orientation.Right);
	//System.out.println("Vla lorientation batard :" + p.getOrientation());
	
	Order ordre = new TurnLeft (p, Color.WHITE);
	System.out.println("Vla lorientation batard :" + p.getOrientation());
	for (int i = 0; i < 5; i++){
		ordre.executer();
		System.out.println("Vla lorientation batard :" + p.getOrientation());
	
	}
*/
	/* +++++ TESTS DU LIGHT ++++++++++++++++++++++++++++++++++++++++++++++++++
	 
	 
	p.setOrientation(Orientation.Right);
	//System.out.println("Vla lorientation batard :" + p.getOrientation());
	
	Order ordre = new Light (p, MonEngine, Color.WHITE);
	Lampadaire l;
	l=(Lampadaire) teste.get_m_mat()[0][0].getMapDO().get(2);
	System.out.println("la lampe est allum�e avant execution?  :" + l.getActive() );
	ordre.executer();
	System.out.println("la lampe est allum�e ? :" + l.getActive());
	ordre.executer();
	System.out.println("la lampe est allumee ? :" + l.getActive());
	
	*/
	/*===================TESTS POSE POINTEUR=================================
	 */
	Order ordre = new MallocPointer (p, MonEngine, Color.WHITE, Color.BLUE );
	
	//Pointeur ptr;
	//ptr = (Pointeur) teste.get_m_mat()[0][0].getMapDO().get(1);
	
	System.out.println("le personnage a le pointeur bleu avec lui?  :" + (p.getPointeur(Color.BLUE)!=null) );
	System.out.println("la case contient un pointeur?  :" + teste.get_m_mat()[0][0].getMapDO().containsKey(1)  );
	ordre.executer();
	
	System.out.println("la case contient un  apres execution?  :" + teste.get_m_mat()[0][0].getMapDO().containsKey(1)  );
	System.out.println("la couleur bleu?  :" +Color.BLUE);
	System.out.println("la case contient le pointeur bleu?  :" + (teste.get_m_mat()[0][0].getMapDO().get(1).getColor()== Color.BLUE));
	System.out.println("en effet la couleur sur la case est ?  :" + teste.get_m_mat()[0][0].getMapDO().get(1).getColor());
	System.out.println("le personnage a  toujours le pointeur bleu avec lui  :" + (p.getPointeur(Color.BLUE)!=null) );
	
	ordre.executer();
	
	System.out.println("la case contient un pointeur?  :" + teste.get_m_mat()[0][0].getMapDO().containsKey(1)  );
	System.out.println("la case contient le pointeur bleu?  :" + (teste.get_m_mat()[0][0].getMapDO().get(1).getColor()== Color.BLUE));
	
	System.out.println("le personnage a  toujours le pointeur bleu avec lui  :" + (p.getPointeur(Color.BLUE)!=null) );
	 
	
}
	
}
