package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;


public class Engine {
	
	
	private Map monde;
	private int nb_for;
	
	
	public Engine (Map m){
		
		monde = m;
		nb_for=0;
		
	}
	/** 
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchang�e
	 * @return true si pas d'obstacles, false sinon
	 * @obstacle1: si les cases de depart et d'arrivee ont des hauteurs differentes
	 * @obstacle2: 
	 */
	private boolean isAbleToMove(Character p){
		
		Case [][] mat;
		mat= monde.get_m_mat();
		
		int x = p.getPosition().x;
		int y =p.getPosition().y;
			
			switch (p.getOrientation())
				{
	           case Up:
	        	   x = x-1;
	           break;
	           case Down:
	        	   x = x+1;
	           break;
	           case Left:
	        	   y = y-1;
	           break;
	           case Right:
	        	   y = y+1;
	           break;
				}
			
			if(mat.length > x && mat[0].length > y && x >=0 && y >=0) { //teste segmentation fault
				
				//teste la difference de hauteur
				
				int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight();
				int destination_height =  mat[x][y].getHeight();
				
				return (source_height == destination_height);
			}
			else return false;
	}
	/**
	 * teste si le personnage peut jumper vers la prochaine case
	 * @param p le personnage
	 * @return true si jump ok, false sinon
	 */
	private boolean isAbleToJump(Character p){
		
		Case [][] mat;
		mat= monde.get_m_mat();
		
		int x = p.getPosition().x;
		int y =p.getPosition().y;
			
			switch (p.getOrientation())
				{
	           case Up:
	        	   x = x-1;
	           break;
	           case Down:
	        	   x = x+1;
	           break;
	           case Left:
	        	   y = y-1;
	           break;
	           case Right:
	        	   y = y+1;
	           break;
				}
			
			if(mat.length > x && mat[0].length > y && x >=0 && y >=0) { //teste segmentation fault
				
				//teste la difference de hauteur :
				
				int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight();
				
				int destination_height =  mat[x][y].getHeight();
				
				if(source_height < destination_height)   //JUMP VERS LE HAUT 
					
					return (destination_height - source_height == 1);
				
				else if (source_height > destination_height) //JUMP VERS LE BAS
						
					return (source_height - destination_height == 1);
				
				else //JUMP VERS UNE CASE DE MM HAUTEUR
					
					return false;
				
			}
			
			else return false;
	}
	/**
	 * teste si le personnage pourra double jumper vers la prochaine case
	 * @param p: le personnage
	 * @return true si double jump possible, false sinon
	 * @details double jump ok si difference de hauteur = 1 (jump normal)
	 */
	
	private boolean isAbleToDoubleJump(Character p) {
		// TODO Auto-generated method stub

		Case [][] mat;
		mat= monde.get_m_mat();
		
		int x = p.getPosition().x;
		int y =p.getPosition().y;
			
			switch (p.getOrientation())
				{
	           case Up:
	        	   x = x-1;
	           break;
	           case Down:
	        	   x = x+1;
	           break;
	           case Left:
	        	   y = y-1;
	           break;
	           case Right:
	        	   y = y+1;
	           break;
				}
			
			if(mat.length > x && mat[0].length > y && x >=0 && y >=0) { //teste segmentation fault
				
				//teste la difference de hauteur :
				
				int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight(); 
				
				int destination_height =  mat[x][y].getHeight();
				
				if(source_height < destination_height)   //JUMP VERS LE HAUT 
					
					return (destination_height - source_height == 1 || destination_height - source_height == 2  );
				
				else if ( source_height > destination_height ) //JUMP VERS LE BAS
						
					return (source_height - destination_height == 1 || source_height - destination_height ==2 );
				
				else //JUMP VERS UNE CASE DE MM HAUTEUR
					
					return false;
				
			}
			
			else return false;
	}
	
	/**
	 * fait avancer le personnage d'une case en fonction de la direction
	 * @param p: le personnage a faire avancer
	 * @return un booleen : true si le move s'est bien passe, false sinon
	 */

	public boolean ExecMove(Character p){
		
		if(isAbleToMove(p)){
			
			updatePostion(p);
			return true;
		}
		else 
			return false;
		
	}
	
	/**
	 * fait jumper le personnage d'une case vers le haut ou d'une case vers le bas
	 * @param p: le personnage a fair jumper
	 * @return true si le jump s'est bien passé false sinon
	 */
		
	public boolean ExecJump(Character p){
		
		if(!isAbleToJump(p)){
			
			return false;
		}
		else{
			updatePostion(p); //on place le clone a la case au sommet
			p.setHeight(getCurrentCase(p).getHeight());
			return true;
		}
	}
	/**
	 * fait jumper le personnage d'une case ou de deux au max vers le haut ou vers le bas
	 * @param p: le personnage a fair jumper
	 * @return true si le jump s'est bien pass� false sinon
	 */	

	public boolean ExecDoubleJump(Character p) {
		// TODO Auto-generated method stub
		
		if(!isAbleToDoubleJump(p)){
			
			return false;
		}
		else{
			updatePostion(p); //on place le clone a la case au sommet
			p.setHeight(getCurrentCase(p).getHeight());
			return true;
		}
	}
	
	public boolean ExecLight(Character personne) {
		
		// TODO Auto-generated method stub
		Case [][] mat;
		mat= monde.get_m_mat();
	//	Lampadaire l;
		
		int x = personne.getPosition().x;
		int y =personne.getPosition().y;
		
		if (mat[x][y].getColor() == Color.BLUE ){
			
			//traitement
			return true;
		}
		
		else
			return false;
	
			
	}
	
	

	public void updatePostion(Character p){
		int new_x = p.getPosition().x;
		int new_y =p.getPosition().y;
		
		switch (p.getOrientation())
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
		p.setPosition(new Vector2i(new_x,new_y));
	}
	
	public Case getCurrentCase(Character p){
		int x = p.getPosition().x;
		int y = p.getPosition().y;
		
		return monde.get_m_mat()[x][y];
	}
	public boolean ExecGetColor(Character p) {
		if (!(getCurrentCase(p).getColor() == Color.WHITE)){
			p.setColor(getCurrentCase(p).getColor());
			return true;
		} 
		else {
			return false;
		}
	}
	/**
	 * retourne la valeur de la case courante
	 * @param personne: le character
	 * @return true si for possible false sinon
	 * @obstacleaufor: si valeur case == 1
	 */
	public boolean ExecFor(Character personne) {
		// TODO Auto-generated method stub
		
		if(getCurrentCase(personne).getValue()> 1){ //tests  conditions for
			
			set_nb_for(getCurrentCase(personne).getValue());
			return true;
		}
		
		else	
			return false;
	}
	
	public int get_nb_for() {
	
		// TODO Auto-generated method stub
		return nb_for;
	}
	public void set_nb_for(int val){
		
		nb_for=val;
	}
}
