package lightbot;


public class Engine {
	
	
	private Map monde;
	/** 
	 * 
	 * @param clone: le clone deplace a la case de destination, hauteur inchangée
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
	 * @return true si le jump s'est bien passé false sinon
	 */
		
	public boolean ExecJump(Character p){
		
		if(!isAbleToJump(p)){
			
			return false;
		}
		else
			p.updatePostion(); //on place le clone a la case au sommet
			return true;
		
	}

	public boolean ExecDoubleJump(Character p) {
		// TODO Auto-generated method stub
		
		if(!isAbleToDoubleJump(p)){
			
			return false;
		}
		else
			p.updatePostion(); //on place le clone a la case au sommet
			return true;
	}

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
					
					return (destination_height - source_height >= 1 || destination_height - source_height <= 2  );
				
				else if ( source_height > destination_height ) //JUMP VERS LE BAS
						
					return (source_height - destination_height >= 1 || source_height - destination_height <=2 );
				
				else //JUMP VERS UNE CASE DE MM HAUTEUR
					
					return false;
				
			}
			
			else return false;
	}
	
	
}
