package lightbot;

public class DoubleJump extends Order{

	

	public DoubleJump(Character p){
		
		personne= p;

	}
	@Override
	protected void executer() {
		// TODO Auto-generated method stub
		if(!engine.ExecDoubleJump(personne)){
			try {
				throw new Exception("Pas possible de double jump");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
				
	
	}
}
