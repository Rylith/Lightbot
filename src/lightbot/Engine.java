package lightbot;

import java.util.LinkedList;
import java.util.Vector;

import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2i;


public class Engine {
	
	
	private Map monde;
	private int nb_for;
	private LinkedList <Lampadaire> liste_lampadaire; 
	
	private static int d_lampe = 0;
	private static int d_paint = 1;
	private static int d_for = 2;
	private static int d_pointeur = 3;
	private static int d_charac = 4;
	
	
	public Engine (Map m){
		
		monde = m;
		nb_for=1;
		liste_lampadaire= new LinkedList<Lampadaire>();
		
	}
	
	/**
	 * ajoute un lampadaire � la liste 
	 * 
	 */
	
	private void  addLampadaire (Lampadaire l){
		liste_lampadaire.add(l);
	}
	
	private boolean LampadairesAllumes(){
		
		for(int i=0; i<liste_lampadaire.size(); i++){
			if(!liste_lampadaire.get(i).getActive())
				return false;
		}
		return true;
	}
	/** 
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchangee
	 * @return true si pas d'obstacles, false sinon
	 * @obstacle1: si les cases de depart et d'arrivee ont des hauteurs differentes
	 * @obstacle2: 
	 */
	private boolean isAbleToMove(Character p) {
		
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
				if (!(monde.get_m_mat()[x][y] == null)) {

					if (!(monde.get_m_mat()[x][y].getMapDO().containsKey(d_charac))) {
					//test la difference de hauteur
					
					int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight();
					int destination_height =  mat[x][y].getHeight();
					
					return (source_height == destination_height);
					}
					else return false;
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
				if (!(monde.get_m_mat()[x][y].getMapDO().containsKey(0))){
					//teste la difference de hauteur :
					
					int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight();
					
					int destination_height =  mat[x][y].getHeight();
					
					if(source_height < destination_height)   //JUMP VERS LE HAUT 
						
						return (destination_height - source_height == 1);
					
					else if (source_height > destination_height) //JUMP VERS LE BAS
							
						return true;
					
					else //JUMP VERS UNE CASE DE MM HAUTEUR
						
						return false;
				} else return false;
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
				if (!(monde.get_m_mat()[x][y].getMapDO().containsKey(0))){
					//teste la difference de hauteur :
					
					int source_height = mat[p.getPosition().x][p.getPosition().y].getHeight(); 
					
					int destination_height =  mat[x][y].getHeight();
					
					if(source_height < destination_height)   //JUMP VERS LE HAUT 
						
						return (destination_height - source_height == 1 || destination_height - source_height == 2  );
					
					else if ( source_height > destination_height ) //JUMP VERS LE BAS
							
						return true;
					
					else //JUMP VERS UNE CASE DE MM HAUTEUR
						
						return false;
				} else return false;
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
			
			getCurrentCase(p).delObject(d_charac);
			updatePostion(p);
			getCurrentCase(p).addObject(d_charac, p);
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
			
			getCurrentCase(p).delObject(d_charac);
			updatePostion(p); //on place le clone a la case au sommet
			p.setHeight(getCurrentCase(p).getHeight());
			getCurrentCase(p).addObject(d_charac, p);
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
			
			getCurrentCase(p).delObject(d_charac);
			updatePostion(p); //on place le clone a la case au sommet
			p.setHeight(getCurrentCase(p).getHeight());
			getCurrentCase(p).addObject(d_charac, p);
			return true;
		}
	}
	
	public boolean ExecLight(Character personne) {
		
		// TODO Auto-generated method stub
		
		Lampadaire l;
		
		if(getCurrentCase(personne).getMapDO().containsKey(d_lampe)){
			
			l=(Lampadaire) getCurrentCase(personne).getMapDO().get(d_lampe);
			
			l.setActive(!l.getActive());
			
			getCurrentCase(personne).addObject(d_lampe,l);
				
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
		p.update(new Vector2i(new_x,new_y));
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
						getCurrentCase(personne).addObject(d_pointeur,l.get(i)); //ajoute le pointeur a la liste d'objets de la case
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
		
				if (monde.getPointer(color_ptr)!=null){

					getCurrentCase(personne).delObject(d_charac);
					personne.setPosition(monde.getPointer(color_ptr));
					getCurrentCase(personne).addObject(d_charac,personne);

					return true;
				} 
				else 
					return false;
			
		
	}
	
	
}
