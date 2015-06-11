package lightbot;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class jsfmltesto {
 
    public static void main(String[] args) {
 
        RenderWindow fenetre = new RenderWindow();
 
        fenetre.create(new VideoMode(640, 480), "Ma premiere fenetre JSFML");
 
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte
        while (fenetre.isOpen()) {
 
            fenetre.clear(Color.BLACK); // On remplit la fenêtre avec la couleur
                                        // noire
 
            fenetre.display();// On affiche notre fenetre et ce qu'on doit
                                // dessiner dessus (la couleur noire )
 
            // On gère les événements
            for (Event event : fenetre.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    fenetre.close();
                }
            }
        }
    }
}