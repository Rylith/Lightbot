package lightbot;

public class Jump extends Order {

	
	
	@Override
	protected boolean executer(Engine engine) {
		// TODO Auto-generated method stub
		return engine.ExecJump(personne);
	
	}

}
