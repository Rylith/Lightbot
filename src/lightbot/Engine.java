package lightbot;

import java.util.Vector;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;


public class Engine {
	
	
	private Map monde;
	private int nb_for;
	
	
	public Engine (Map m){
		
		monde = m;
		nb_for=1;
		
	}
	/** 
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchangee
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
			
			if(mat.length > x && mat[0].length > y && x >=0 && y >=0) { //test segmentation fault
				if (!(monde.get_m_mat()[x][y].getMapDO().containsKey(0))){
				//test la difference de hauteur
				
				int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight();
				int destination_height =  mat[x][y].getHeight();
				
				return (source_height == destination_height);
				}
				else return false;
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
			
			getCurrentCase(p).getMapDO().remove(0);
			updatePostion(p);
			getCurrentCase(p).getMapDO().put(0,p);
			return true;
		}
		else 
			return false;
		
	}
	
	/**
	 * fait jumper le personnage d'une case vers le haut ou d'une case vers le bas
	 * @param p: le personnage a fair jumper
	 * @return true si le jump s'est bien pass� false sinon
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
	 * @return true si le jump s'est bien passe false sinon
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
		
		Lampadaire l;
		
		if(getCurrentCase(personne).getMapDO().containsKey(2)){
			
			l=(Lampadaire) getCurrentCase(personne).getMapDO().get(2);
			
			l.setActive(!l.getActive());
			
			getCurrentCase(personne).getMapDO().put(2,l);
				
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
	 * 
	 */
	public boolean ExecFor(Character personne) {
		// TODO Auto-generated method stub
		
		if(getCurrentCase(personne).getValue() > 1 && getCurrentCase(personne).getValue() <= 6){ //tests  conditions for
			
			set_nb_for(getCurrentCase(personne).getValue());
			return true;
		}
		
		else{
			//set_nb_for(1);
			return false;
		}
	}
	
	public int get_nb_for() {
	
		// TODO Auto-generated method stub
		return nb_for;
	}
	public void set_nb_for(int val){
		
		nb_for=val;
	}
	
	public boolean ExecMalloc(Character personne, Color cptr) {
		// TODO Auto-generated method stub
		Vector <Pointeur> l;
		l= personne.getPointerList();
		boolean pursue = true;
		if(!l.isEmpty()){
			
		
			for(int i=0; i<l.size() && pursue; i++)
			{
				if(l.get(i).getColor()== cptr){
					
					//poser pointeur:
						l.get(i).setActive(true); // on active le pointeur avant de le poser sur la case
						personne.setPointeur(l.get(i).getColor(), personne.getPosition()); //change la position du pointeur 
						getCurrentCase(personne).addObject(1,l.get(i)); //ajoute le pointeur a la liste d'objets de la case
						personne.RemoveFromPtrList(l.get(i)); //supprimer le pointeur de la liste du perso
						pursue = false;
				}
			}
			
			if(!pursue)
				return true;
			else
				return false;
		}
		else
			return false;
		
		
	}

	public boolean ExecAccess(Character personne, Color color_ptr)  {
		// TODO Auto-generated method stub
		
		//il faut que le smartbot passe sa liste de pointeurs au basic bot apres son passage pour q ca marche
		
		Vector <Pointeur> l;
		l= personne.getPointerList();
		
		if(!l.isEmpty()){
			Pointeur p= personne.getPointeur(color_ptr);
			if(p==null || !p.getActive())
				return false;
			
			else{
				personne.setPosition(p.getPosition());
				return true;
			}
		}
		else
			return false;
	}
	
}