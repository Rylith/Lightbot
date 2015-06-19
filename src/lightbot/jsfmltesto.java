package lightbot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

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
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import lightbot.Button;
import lightbot.Character;

public class jsfmltesto {
	
	public static void drawCase(RenderWindow fenetre,Case case1){                      
        fenetre.draw(case1.getSprite());
	}
	
	private final static String LEVELPATH = "test2.xml";
	
    public static void main(String[] args) {
 
        RenderWindow fenetre = new RenderWindow();
        
        /*Texture back_text = new Texture();
        try {
            //Try to load the texture from file "jsfml.png"
        	back_text.loadFromFile(Paths.get("ressource/Sprite/background.jpg"));

            //Texture was loaded successfully - retrieve and print size
            Vector2i size = back_text.getSize();
            //System.out.println("The texture is " + size.x + "x" + size.y);
        } catch(IOException ex) {
            //Ouch! something went wrong
            ex.printStackTrace();
        }*/
        //Sprite back_sprite = new Sprite(back_text);
        //Vector2i screenSize = new Vector2i(900,600);
        //Vector2i screenSize = new Vector2i(1600,900);
        Vector2i screenSize = new Vector2i(1280,720);
        //Vector2i screenSize = new Vector2i(1440,900);
        //Vector2i screenSize = new Vector2i(1920,1080);
        fenetre.create(new VideoMode(screenSize.x,screenSize.y), "Prototype"); //,WindowStyle.FULLSCREEN);

        int frame = 3;
        int frameElec = 1;
        Clock animClock = new Clock();

        Character rob = new Character(new Vector2i(0, 0), 1,Color.WHITE, "lightbot.png");
        rob.setOrientation(Character.Orientation.Right);
        //Map testo = new Map(rob);
        //Engine eng = new Engine(testo);
        Game game = new Game(fenetre);
        game.setMap(LEVELPATH);
        //Animation animate = new Animation:
        Controler control = new Controler(screenSize,game);

/*----------------------------------------Génération du robot----------------------------------------*/ 
        /*Character robot = new Character(new Vector2i(2,0), 1, Color.GREEN, "ressource/Sprite/lightbot.png");
        robot.setOrientation(Character.Orientation.Up);
        robot.setLimitOrder(0, 17);
        //robot.drawCharac(fenetre);
        control.init(robot,robot);*/
/*----------------------------------------Génération du robot----------------------------------------*/ 
        
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte
        while (game.getWindow().isOpen()) {
        	//fenetre.draw(back_sprite);

            //fenetre.draw(buttest.getSprite());
            //fenetre.draw(butturn.getSprite());
            //fenetre.draw(butallumer.getSprite());
        	control.update();
            //testo.drawMap(fenetre);
        	
            // On gère les événements
            control.supervise();
    		while(game.getStateSimulation()) {
    			System.out.println("Start Simulation");
    			for(String mapKey : game.getCharacter().keySet()) {
    				boolean orderExist;
    				if(game.getCharacter(mapKey).getListOrder().get(0).size() > 0) {
    					orderExist = true;
    				} else {
    					orderExist = false;
    				}
    				//Vector<Vector<Order>> listOrder = game.getCharacter(mapKey).getListOrder();
    				int currentSimulation = 0;
    				int backProcP1 = 0;
    				int backProcP2 = 0;
    				int currentMain = 0;
    				int currentP1 = -1;
    				int currentP2 = -1;
    				int nbFor = 0;
    				while(orderExist) {
    					
    					switch(game.getCharacter(mapKey).getProcActive()) {
    					case 0:
    						currentSimulation = currentMain;
    						break;
    					case 1:
    						currentSimulation = currentP1;
    						break;
    					case 2:
    						currentSimulation = currentP2;
    					}
    					System.out.println("Current Simu = " + currentSimulation + " Main : " + currentMain + " P1 : " + currentP1 + " P2 : " + currentP2 + " BackProcP1 : " + backProcP1 + " BackProcP2" + backProcP2);
    					game.getWindow().display();
			        	game.getWindow().clear();
						Clock clock = new Clock();
						clock.restart();
						//System.out.println("CurrentSimu : " + currentSimulation);
						System.out.println("Proc Active : " + game.getCharacter(mapKey).getProcActive());
						//System.out.println("List proc active " + game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size());
						if(game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).get(currentSimulation) instanceof Procedure1) {
							backProcP1 = game.getCharacter(mapKey).getProcActive();
							currentP1 = -1;
							switch(backProcP1) {
							case 0:
								currentMain++;
								break;
							case 1:
								currentP1++;
								break;
							case 2:
								currentP2++;
								break;
							}
						} else if (game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).get(currentSimulation) instanceof Procedure2) {
							backProcP2 = game.getCharacter(mapKey).getProcActive();
							currentP2 = -1;
							switch(backProcP2) {
							case 0:
								currentMain++;
								break;
							case 1:
								currentP1++;
								break;
							case 2:
								currentP2++;
								break;
							}
						} else if (game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).get(currentSimulation) instanceof For) {
							nbFor = game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).get(currentSimulation).executer();
							switch(game.getCharacter(mapKey).getProcActive()) {
							case 0:
								currentMain++;
								break;
							case 1:
								
								currentP1++;
								break;
							case 2:
								currentP2++;
								break;
							}
						}
						System.out.println("On execute : " + game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).get(currentSimulation).toString());
						game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).get(currentSimulation).executer();
    					control.update();
						control.supervise();
						if(!game.getStateSimulation()) {
							break;
						}
						while(clock.getElapsedTime().asSeconds() < 0.5f ) {
						}
    					
						if(nbFor > 0) {
							nbFor--;
						} else {
	    					switch(game.getCharacter(mapKey).getProcActive()) {
	    					case 0:
	    						currentMain++;
	    						if(currentMain >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	    							orderExist = false;
	    						}
	    						//System.out.println("Size : " + game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size());
	    						break;
	    					case 1:
	    						currentP1++;
	    						if(currentP1 >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	    							currentP1 = -1;
	    							System.out.println("Fin de P1 on revient en " + backProcP1);
	    							game.getCharacter(mapKey).activeProc(backProcP1);
	    							if(currentMain >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	        							orderExist = false;
	        						}
	    						}
	    						break;
	    					case 2:
	    						currentP2++;
	    						if(currentP2 >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	    							currentP2 = -1;
	    							System.out.println("Fin de P2 on revient en " + backProcP2);
	    							game.getCharacter(mapKey).activeProc(backProcP2);
	    							if(currentMain >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	        							orderExist = false;
	        						}
	    							if(currentP1 >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	        							currentP1 = -1;
	        							System.out.println("Fin de P1 on revient en " + backProcP1);
	        							game.getCharacter(mapKey).activeProc(backProcP1);
	        							if(currentMain >= game.getCharacter(mapKey).getListOrder().get(game.getCharacter(mapKey).getProcActive()).size()) {
	            							orderExist = false;
	            						}
	        						}
	    						}
	    					}
	    				}
    				}
    			}
    			System.out.println("End of Simulation");
    			//if(game.levelIsCompleted()){
    			//	System.out.println("FELICITATION !!");
    			//}
    			game.setStateSimulation(false);
    			/*for(String mapKey : game.getCharacter().keySet()) {
    				ListeOrdre listorder = new ListeOrdre(game.getCharacter(mapKey),game.getEngine(), game.getCharacter(mapKey).getListOrder());
    				
    			}*/
    		}
    		game.getWindow().display();
        	game.getWindow().clear();
            /*for (Event event : fenetre.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    fenetre.close();
                }
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                	Vector2i mouse_pos = Mouse.getPosition(fenetre);
                	
                	if (buttest.isClicked(mouse_pos)) {
                		Order ordreMove = new Move(rob, eng);
                		ordreMove.executer();
                	}
                	if (butturn.isClicked(mouse_pos)) {
                		Order orderRight = new TurnRight(rob);
                		orderRight.executer();
                	}
                	if (butallumer.isClicked(mouse_pos)) {
                		Order ordre_light= new Light(rob, eng, Color.WHITE);
                		ordre_light.executer();
                	}
                }
            }*/

        } 
    }
}