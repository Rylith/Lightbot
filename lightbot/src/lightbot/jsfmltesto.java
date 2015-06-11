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
import lightbot.Character;

public class jsfmltesto {
 
	public static void drawCase(RenderWindow fenetre,Case case1){                      
        fenetre.draw(case1.getSprite());
	}
    public static void main(String[] args) {
 
        RenderWindow fenetre = new RenderWindow();
        
        Texture back_text = new Texture();
        try {
            //Try to load the texture from file "jsfml.png"
        	back_text.loadFromFile(Paths.get("background.jpg"));

            //Texture was loaded successfully - retrieve and print size
            Vector2i size = back_text.getSize();
            System.out.println("The texture is " + size.x + "x" + size.y);
        } catch(IOException ex) {
            //Ouch! something went wrong
            ex.printStackTrace();
        }
        Sprite back_sprite = new Sprite(back_text);
        
        fenetre.create(new VideoMode(640, 480), "Prototype");

        int frame = 3;
        int frameElec = 1;
        Clock animClock = new Clock();
        Button buttest = new Button("ButAvancer.png",50,399);
        Button butturn = new Button("ButTournerDroite.png",50+0.5f*72,399);
        Button butallumer = new Button("buttest.png",50+72,399);
        
        Map testo = new Map();
        
/*----------------------------------------Génération du robot----------------------------------------*/ 
        Character robot = new Character(new Vector2i(2,0), 1, Color.GREEN, "lightbot.png");
        robot.setOrientation(Character.Orientation.Up);
        robot.drawCharac(fenetre);
/*----------------------------------------Génération du robot----------------------------------------*/ 
        boolean anti_cligno = true;
        
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte
        while (fenetre.isOpen()) {
        	anti_cligno = true;
        	fenetre.draw(back_sprite);
            fenetre.draw(buttest.getSprite());
            fenetre.draw(butturn.getSprite());
            fenetre.draw(butallumer.getSprite());
            testo.drawMap(fenetre,robot);
            robot.update(fenetre,new Vector2f(0, 0));
            
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
               //TESTER SI CASE DEVANT DISPO
                		
                		if (testo.caseAccess(robot.getPosition(), robot.getOrientation())) {

                		int animCount = 0;
                		while (animCount < 16) {
                			if (animClock.getElapsedTime().asMilliseconds() >= 50) {
                           	 //Restart the clock
                               animClock.restart();

                               //Increase the frame counter by one
                               frame--;

                               if(frame == 0){
                               	frame = 3;
                               }
                               switch (robot.getOrientation())
                       			{
                                   case Up:
                                	   robot.update(fenetre, new Vector2f(2.5625f,-1.28125f));
                                   break;
                                   case Down:
                                	   robot.update(fenetre, new Vector2f(-2.5625f,1.28125f));
                                   break;
                                   case Left:
                                	   robot.update(fenetre, new Vector2f(-2.5625f,-1.28125f));
                                   break;
                                   case Right:
                                	   robot.update(fenetre, new Vector2f(2.5625f,1.28125f));
                                   break;
                               }
                               
                               robot.getSprite().setTextureRect(new IntRect(frame * 80, robot.getSprite().getTextureRect().top, 80, 100));
                               animCount++;
                               testo.drawMap(fenetre,robot);
                               fenetre.draw(buttest.getSprite());
                               fenetre.draw(butturn.getSprite());
                               fenetre.draw(butallumer.getSprite());
                              // fenetre.draw(robot.getSprite());
                               fenetre.display();
                               fenetre.clear(Color.WHITE);
                				}    
                			}
                		robot.updatePostion();
                		}
                		
					}
                	if (butturn.clicked(mouse_pos)) {
                		//robot.setOrientation(Character.Orientation.Right);
                		  switch (robot.getOrientation())
                 			{
                             case Up:
                            	 robot.setOrientation(Character.Orientation.Right);
                             break;
                             case Down:
                            	 robot.setOrientation(Character.Orientation.Left);
                             break;
                             case Left:
                            	 robot.setOrientation(Character.Orientation.Up);
                             break;
                             case Right:
                            	 robot.setOrientation(Character.Orientation.Down);
                             break;
                         }
					}
                	if (butallumer.clicked(mouse_pos)) {
                		int animCount = 0;
                		while (animCount < 16) {
                			if (animClock.getElapsedTime().asMilliseconds() >= 50) {
                           	 //Restart the clock
                               animClock.restart();

                               //Increase the frame counter by one
                               frameElec--;

                               if(frameElec < 0){
                               	frameElec = 1;
                               }
                               
                               robot.getSprite().setTextureRect(new IntRect(6*80 + frameElec * 80, robot.getSprite().getTextureRect().top, 80, 100));
                               animCount++;
                               testo.drawMap(fenetre,robot);
                               fenetre.draw(buttest.getSprite());
                               fenetre.draw(butturn.getSprite());
                               fenetre.draw(butallumer.getSprite());
                               //fenetre.draw(robot.getSprite());
                               fenetre.display();
                               fenetre.clear(Color.WHITE);
                           }       
						}
                		robot.getSprite().setTextureRect(new IntRect(0, robot.getSprite().getTextureRect().top, 80, 100));
					}
                }
                anti_cligno = false;
            } 
            if (anti_cligno) {
                fenetre.display();
                fenetre.clear();
			}
        }
    }
}