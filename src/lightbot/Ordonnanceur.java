package lightbot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.jsfml.graphics.Color;

public class Ordonnanceur {
	
	private int nbCoups;
	private Game pGame;
	
	/** Liste de pile d'itérateur d'Action. Utilisé pour conserver la position des bots dans les fonctions */
	List<Stack<Iterator<Order>>> pStacks;
	
	/**
	 * Liste contenant la dernière Action effectuée par chancun des Bots. Utilisée pour les affichages
	 * "Action courante"
	 */
	private List<Order> pPrev;

	public Ordonnanceur(Game game) {
		this.nbCoups = 0;
		this.pGame = game;
		this.pStacks = new LinkedList<Stack<Iterator<Order>>>();
		for(String mapKey : pGame.getCharacter().keySet()) {
			List<Order> main = pGame.getCharacter(mapKey).getListOrder().get(0);
			System.out.println("Le main ajouté fait: " + main.size());
			System.out.println("La p1 fait: " + pGame.getCharacter(mapKey).getListOrder().get(1).size());
			Stack<Iterator<Order>> wStack = new Stack<Iterator<Order>>();
			wStack.push(main.iterator());
			this.pStacks.add(wStack);
		}
		this.pPrev = new ArrayList<>();
	}
	
	public boolean step() {
		String b_name = "BasicBot";
		System.out.println("JE FAIS UNE STEP");
		boolean action_done = false;
		for(Stack<Iterator<Order>> wStack : this.pStacks) {
			if (stepOne(wStack, b_name)) {
				action_done = true;
			}
			b_name = "SmartBot";
		}
		return action_done;
	}
	
	public boolean stepOne(Stack<Iterator<Order>> b_stack, String c_bot) {
		/* Recherche du premier élément non null dans la pile */
		while (!b_stack.isEmpty() && !b_stack.peek().hasNext()) {
			System.out.println("POP");
			b_stack.pop();
		}
		/* Retourne faux si la pile est vide */
		if (b_stack.isEmpty()) {
			return false;
		}
		
		/* Récupère l'Iterator sans le retirer de la pile */
		Iterator<Order> wIt = b_stack.peek();
		if (wIt == null) {
			/* L'Iterator est null, ce qui est une erreur normalement impossible */
			System.err.println("Unexpected iterator null");
			return false;
		} else { 
			/* Vérifie que l'itérator à un élément suivant */
			if (wIt.hasNext()) {
				Order wAction = wIt.next();
				if (wAction.getColor() == Color.WHITE || wAction.getColor() == pGame.getCharacter("BasicBot").getColor()) {
					if (wAction instanceof Procedure1) {
						System.out.println("J'appele p1 " + pGame.getCharacter(c_bot).getListOrder().get(1).size() + "du bot: " + c_bot);
						wAction.executer();
						b_stack.push(pGame.getCharacter(c_bot).getListOrder().get(1).iterator());
						return stepOne(b_stack, c_bot);
					} else if (wAction instanceof For) {
						wAction.executer();
						wAction = wIt.next();
						if(wAction != null) {
							System.out.println("Mon for");
							for (int i = 0; i < pGame.getEngine().get_nb_for(); i++) {
								wAction.executer();
							}
						}
					}else {
						System.out.println("L'Ordo execute: " + wAction.toString());
						wAction.executer();
						this.nbCoups++;
						return true;
					}
				}
			}
		}
		return false;
	}
}
