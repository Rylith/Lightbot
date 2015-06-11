package lightbot;

public class Move extends Order {
	
	public Move(){}

	@Override
	public void executer(Engine e1) {
		
		e1.tryMove(character);
		
	}

}
