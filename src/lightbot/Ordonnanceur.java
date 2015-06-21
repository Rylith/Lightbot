package lightbot;

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
	 * Crée un ordonnanceur pour le jeu
	 * @param game Le jeu en cour
	 */
	public Ordonnanceur(Game game) {
		this.nbCoups = 0;
		this.pGame = game;
		this.pStacks = new LinkedList<Stack<Iterator<Order>>>();
		for(String mapKey : pGame.getCharacter().keySet()) {
			List<Order> main = pGame.getCharacter(mapKey).getListOrder().get(0);
			Stack<Iterator<Order>> wStack = new Stack<Iterator<Order>>();
			wStack.push(main.iterator());
			this.pStacks.add(wStack);
		}
	}
	
	/**
	 * Effectue une étape dans la liste d'ordre des bots actifs
	 * @return si une action a été effectué ou non
	 */
	public boolean step() {
		//Character c_bot;
		String b_name = "SmartBot";
		boolean action_done = false;
		for(Stack<Iterator<Order>> wStack : this.pStacks) {
			if (stepOne(wStack, b_name)) {
				action_done = true;
			}
			b_name = "BasicBot";
		}
		return action_done;
	}
	
	/**
	 * Tente d'exécuter l'action suivante d'un bot. Vérifie si le bot à la bonne couleur, s'il est actif etc.
	 * @param b_stack La pile contenant les iterators sur les procédures du bot courant
	 * @param c_bot String identifiant le bot courant
	 * @return Si une action a été exécuté
	 */
	public boolean stepOne(Stack<Iterator<Order>> b_stack, String c_bot) {
		/* Recherche du premier élément non null dans la pile */
		while (!b_stack.isEmpty() && !b_stack.peek().hasNext()) {
			b_stack.pop();
		}
		/* Retourne faux si la pile est vide */
		if (b_stack.isEmpty()) {
			if (c_bot.equals("SmartBot")) {
				pGame.getCharacter().get("BasicBot").setActif(true);
			}
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
			if (wIt.hasNext()  && pGame.getCharacter(c_bot).getActif()) {
				Order wAction = wIt.next();
				
				if ((wAction.getColor() == Color.WHITE || wAction.getColor() == pGame.getCharacter(c_bot).getColor())) {
					if (wAction instanceof Procedure1) {
						b_stack.push(pGame.getCharacter(c_bot).getListOrder().get(1).iterator());
						return stepOne(b_stack, c_bot);
					} else if (wAction instanceof For) {
						wAction.executer();
						wAction = wIt.next();
						if(wAction != null) {
							List<Order> for_list = new LinkedList<Order>();
							for (int i = 0; i < pGame.getEngine().get_nb_for(); i++) {
								for_list.add(i, wAction);
							}
							b_stack.push(for_list.iterator());
							return stepOne(b_stack, c_bot);
						}
					}else if (wAction instanceof Procedure2){
						b_stack.push(pGame.getCharacter(c_bot).getListOrder().get(2).iterator());
						return stepOne(b_stack, c_bot);
					} else {
						wAction.executer();
						this.nbCoups++;
						return true;
					}
				} else {
					this.nbCoups++;
					return true;
				}
			} else {
				System.out.println("Pas d'action suivante");
			}
			
		}
		return false;
	}
}
