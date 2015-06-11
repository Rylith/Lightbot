package lightbot;

import org.jsfml.graphics.Color;


public class Engine {
	
	
	private Map monde;
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
			
			p.updatePostion();
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
			p.updatePostion(); //on place le clone a la case au sommet
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
			p.updatePostion(); //on place le clone a la case au sommet
			return true;
		}
	}
	
	public boolean ExecLight(Character personne) {
		
		// TODO Auto-generated method stub
		Case [][] mat;
		mat= monde.get_m_mat();
		Lampadaire l;
		
		int x = personne.getPosition().x;
		int y =personne.getPosition().y;
		
		//if (mat[x][y].getColor() == Color.BLUE )
		return (allumerLampadaire (!mat[x][y].object_list.get(l).isOn()));
			
	}
	
	

	
	
}
