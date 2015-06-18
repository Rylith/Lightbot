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
		int j=0;
		
		while (j<l.size()) {
			try {
				Thread.sleep(300);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			//traitement
			if(!(l.get(j) instanceof For)){
				l.get(j).executer();
				j++;
			}
			else
			{	
				int res = l.get(j).executer();
				j++;
				for(int i=0; i<res; i++){
					l.get(j).executer();
				}
				j++;
			}
		}
		return 0;
	}

}
