package lightbot;

import java.util.LinkedList;
import java.util.Vector;

import lightbot.Character.Orientation;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;

public class testEngine {
	
	public static void main (String[] args){

		Character p = new Character(new Vector2i(0,0), 1, Color.GREEN, "lightbot.png");
		p.setOrientation(Orientation.Right);
		
		Map teste = new Map(p,p,"test2.xml");
	
		//teste.get_m_mat()[0][1].setHeight(2);
		//teste.get_m_mat()[0][2].setHeight(4);
	
		Engine MonEngine = new Engine (teste);
	
	
		
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
		System.out.println("la lampe est allumée avant execution?  :" + l.getActive() );
		ordre.executer();
		System.out.println("la lampe est allumée ? :" + l.getActive());
		ordre.executer();
		System.out.println("la lampe est allumee ? :" + l.getActive());
		
		*/
		/*===================TESTS POSE POINTEUR=================================
		 */
		Order ordre = new MallocPointer (p, MonEngine, Color.WHITE, Color.BLUE );
		
		//Pointeur ptr;
		//ptr = (Pointeur) teste.get_m_mat()[0][0].getMapDO().get(1);
		
		/*
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
		System.out.println("le personnage a  la position  :" + p.getPosition() );
		System.out.println("le personnage est dans la hashmap de la case :" +teste.get_m_mat()[0][0].getMapDO().containsKey(0));
	*/	Order ordre2= new Move(p, MonEngine, Color.WHITE);
		Order ordre1 = new AccessPointer (p, MonEngine, Color.WHITE, Color.BLUE );
		/*p.setOrientation(Orientation.Right);
		ordre2.executer();
		System.out.println("le personnage a  la position apres move :" + p.getPosition() );
		ordre1.executer();
		System.out.println("le personnage est dans la hashmap de la nvlle case :" +teste.get_m_mat()[p.getPosition().x][p.getPosition().y].getMapDO().containsKey(0));
		System.out.println("le personnage a  la position apres execAccess  :" + p.getPosition());
		*/
		
		//=================TESTS LISTE D'ORDRE=====================================
		Order ForOrder = new For (p, MonEngine, Color.WHITE);
		Order ordre3 = new TurnLeft(p,Color.WHITE);
		LinkedList <Order> l= new LinkedList <Order>();
		System.out.println("position initiale du perso  :" + p.getPosition());
		System.out.println("Vla lorientation initiale :" + p.getOrientation());
		System.out.println("Vla la couleur initiale du perso :" + p.getColor());
		l.add(ordre); //pose ptr
		l.add(ordre2); //move  0 - 1
		l.add(ordre1); // acess 0 - 0
		l.add(ordre2); // MOVE 0 - 1 
		l.add(ForOrder); // recup nb
		l.add(ordre3); // ??
		//l.add(ordre3);
		
		
	//==========================TESTS GET COLOR============================================
		Order GetColorO = new getColor(p, MonEngine,Color.WHITE);
		l.add(GetColorO);
		Order execAll = new ListeOrdre(p,MonEngine,l);
		execAll.executer();
		System.out.println("la case contient un pointeur?  :" + teste.get_m_mat()[0][0].getMapDO().containsKey(2));
		System.out.println("la case contient un pot de peinture?  :" + teste.get_m_mat()[0][1].getMapDO().containsKey(0));
		System.out.println("la case contient un nombre for?  :" + teste.get_m_mat()[0][1].getMapDO().containsKey(1));
		System.out.println("le nbre for sur la case?  :" + ((NumberMark) (teste.get_m_mat()[0][1].getMapDO().get(1))).getValue());
		
		System.out.println("la case contient quelle couleur?  :" + teste.get_m_mat()[0][1].getMapDO().get(0).getColor());
		System.out.println("le personnage a  la position apres execAccess  :" + p.getPosition());
		System.out.println("Vla lorientation batard :" + p.getOrientation());
		System.out.println("Vla la nvelle couleur du perso :" + p.getColor());
		System.out.println("couleur magenta :" + Color.MAGENTA);
		System.out.println(" couleur bleue :" + Color.BLUE);
		
		System.out.println(" test exec for" + ForOrder.executer());
		System.out.println("la couleur cyan :" + Color.CYAN);
		
}
		
}
