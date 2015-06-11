package lightbot;

public class Light extends Order {

	public Light (Character p){
		
		personne = p;
		
	}

	@Override
	protected void executer() {
		// TODO Auto-generated method stub
		if(!engine.ExecLight(personne)){
			try {
				throw new Exception("Erreur lors de l'allumage de la case");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
				
	
	
	}
}
