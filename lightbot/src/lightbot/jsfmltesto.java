package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import lightbot.Button;

public class jsfmltesto {
 
    public static void main(String[] args) {
 
        RenderWindow fenetre = new RenderWindow();
        Texture textureTile = new Texture();
 
        fenetre.create(new VideoMode(640, 480), "Test d'affichage de map");
 
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte
        while (fenetre.isOpen()) {
 
            fenetre.clear(Color.CYAN); // On remplit la fenêtre avec la couleur
                                        // noire
            try {
                //Try to load the texture from file "jsfml.png"
            	textureTile.loadFromFile(Paths.get("tile.png"));

                //Texture was loaded successfully - retrieve and print size
                Vector2i size = textureTile.getSize();
            } catch(IOException ex) {
                //Ouch! something went wrong
                ex.printStackTrace();
            }
          //Create a sprite and make it use the logo texture
            Sprite tile1 = new Sprite(textureTile);
            Sprite tile2 = new Sprite(textureTile);
            
            //Set its origin to its center and put it at the center of the screen
            tile1.setOrigin(Vector2f.div(new Vector2f(textureTile.getSize()), 2));
            tile1.setPosition(400, 40);
            fenetre.draw(tile1);
        	tile2.setOrigin(Vector2f.div(new Vector2f(textureTile.getSize()), 2));
        	tile2.setPosition(tile1.getPosition().x-64,tile1.getPosition().y+32);
        	fenetre.draw(tile2);
            for (int i = 0; i < 3; i++) {
            	tile2.setOrigin(Vector2f.div(new Vector2f(textureTile.getSize()), 2));
            	tile2.setPosition(tile2.getPosition().x-64,tile2.getPosition().y+32);
            	fenetre.draw(tile2);
			}
            Button buttest = new Button("buttest.png",50,399);
            fenetre.draw(buttest.getSprite());
            
            fenetre.display();// On affiche notre fenetre et ce qu'on doit
                                // dessiner dessus (la couleur noire )
 
            // On gère les événements
            for (Event event : fenetre.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    fenetre.close();
                }
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                	Vector2i mouse_pos = Mouse.getPosition(fenetre);
                	if (buttest.clicked(mouse_pos)) {
                		System.out.println("J'ai cliqué sur test");
					}
                }
            }
        }
    }
}