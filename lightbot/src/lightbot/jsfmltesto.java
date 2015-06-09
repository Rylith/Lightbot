package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
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
 
	public static void drawCases(RenderWindow fenetre){
		/*----------------------------------------Génération de cases----------------------------------------*/                        
        Case case1 = new Case(new Vector2f(125,175), 1, Color.CYAN, "tile.png");
        Case case2 = new Case(new Vector2f(125+64,175-32), 1, Color.CYAN, "tile.png");
        Case case3 = new Case(new Vector2f(125+64+64,175-32-32), 1, Color.CYAN, "tile.png");
        fenetre.draw(case3.getSprite());
        fenetre.draw(case2.getSprite());
        fenetre.draw(case1.getSprite());
/*----------------------------------------Génération de cases----------------------------------------*/ 
	}
    public static void main(String[] args) {
 
        RenderWindow fenetre = new RenderWindow();
        Texture textureTile = new Texture();
 
        fenetre.create(new VideoMode(640, 480), "Test d'affichage de map");

        Texture walkingRight = new Texture();
        try {
            walkingRight.loadFromFile(Paths.get("lightbot.png"));
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
        Sprite robot = new Sprite(walkingRight);
        //robot.setTextureRect(new IntRect(7*80, 0, 80, 100));
        robot.setTextureRect(new IntRect(0, 0, 80, 100));
        int frame = 3;
        Clock animClock = new Clock();
        
        robot.setPosition(85, 85);
        
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte
        while (fenetre.isOpen()) {

            try {
                //Try to load the texture from file "jsfml.png"
            	textureTile.loadFromFile(Paths.get("tile.png"));

                //Texture was loaded successfully - retrieve and print size
                Vector2i size = textureTile.getSize();
            } catch(IOException ex) {
                //Ouch! something went wrong
                ex.printStackTrace();
            }
           
            drawCases(fenetre);
            Button buttest = new Button("buttest.png",50,399);
            fenetre.draw(buttest.getSprite());
            fenetre.draw(robot);
            
            // On gère les événements
            for (Event event : fenetre.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    fenetre.close();
                }
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                	Vector2i mouse_pos = Mouse.getPosition(fenetre);
                	if (buttest.clicked(mouse_pos)) {
                		//robot.move(new Vector2f(16*4,16*-2));
                		int animCount = 0;
                		while (animCount < 17) {
                			if (animClock.getElapsedTime().asMilliseconds() >= 150) {
                           	 //Restart the clock
                               animClock.restart();

                               //Increase the frame counter by one
                               frame--;

                               if(frame == 0){
                               	frame = 3;
                               }
                               robot.move(new Vector2f(4,-2));
                               robot.setTextureRect(new IntRect(frame * 80, 0, 80, 100));
                               animCount++;
                               drawCases(fenetre);
                               fenetre.draw(robot);
                               fenetre.display();
                               fenetre.clear(Color.CYAN);
                           }        
						}
					}
                }
            } 
            fenetre.display();
            fenetre.clear(Color.CYAN);
        }
    }
}