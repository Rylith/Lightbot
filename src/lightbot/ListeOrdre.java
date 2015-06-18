package lightbot;

import java.util.LinkedList;

import org.jsfml.graphics.Color;

public class ListeOrdre extends Order {
	
	private LinkedList <Order> l;
	
	public ListeOrdre(Character p, Engine e, LinkedList <Order> l ){
		
		personne=p;
		engine=e;
		this.l=l;
		color= Color.WHITE;
	}
	
	@Override
	protected int executer() {
		// TODO Auto-generated method stub

		for (Order o : l) {
			try {
				Thread.sleep(300);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			//traitement
			o.executer();
		}
		return 0;
	}

}
