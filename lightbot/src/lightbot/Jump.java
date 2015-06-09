package lightbot;

public class Jump extends Order {

	
	
	@Override
	protected void executer() {
		// TODO Auto-generated method stub
		if(!engine.ExecJump(personne)){
			try {
				throw new Exception("Pas possible de jump");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
				
	
	}

}
