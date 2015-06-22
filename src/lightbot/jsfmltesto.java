package lightbot;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

import DrawableObject.Case;
import DrawableObject.Character;
import Game.Button;
import Game.Controler;
import Game.Game;
import Game.Level;
import Game.Ordonnanceur;

public class jsfmltesto {
	
	public static void drawCase(RenderWindow fenetre,Case case1){                      
        fenetre.draw(case1.getSprite());
	}
	
	private final static String LEVELPATH1 = "level/for_lvl_1.xml";
	private final static String LEVELPATH2 = "level/for_lvl_2.xml";
	private final static String LEVELPATH3 = "level/for_lvl_3.xml";
	private final static String LEVELPATH4 = "level/test2.xml";
	private final static String LEVELPATH5 = "level/if_then_else_lvl_1.xml";
	private final static String LEVELPATH6 = "level/if_then_else_lvl_2.xml";
	private final static String LEVELPATH7 = "level/if_then_else_lvl_3.xml";
	private final static String LEVELPATH8 = "level/test2.xml";
	private final static String LEVELPATH9 = "level/use_pointeur_lvl_1.xml";
	private final static String LEVELPATH10 = "level/use_pointeur_lvl_2.xml";
	private final static String LEVELPATH11= "level/use_pointeur_lvl_3.xml";
	private final static String LEVELPATH12 = "level/test2.xml";
	private final static String LEVELPATH13 = "level/maloc_lvl_1.xml";
	private final static String LEVELPATH14 = "level/maloc_lvl_2.xml";
	private final static String LEVELPATH15 = "level/malloc_lvl_3.xml";
	private final static String LEVELPATH16 = "level/test2.xml";



	private final static String TILEPATHLEVEL = "ressource/Sprite/Level.png";


	
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
        fenetre.create(new VideoMode(screenSize.x,screenSize.y), "LightBot"/*,WindowStyle.CLOSE*/); //,WindowStyle.FULLSCREEN);

/*        int frame = 3;
        int frameElec = 1;
        Clock animClock = new Clock();*/

        //Character rob = new Character(new Vector2i(0, 0), 1,Color.WHITE, "lightbot.png");
        //rob.setOrientation(Character.Orientation.Right);
        //Map testo = new Map(rob);
        //Engine eng = new Engine(testo);
        
        
        Game game = new Game(fenetre);
        
 
        
//ADD BY CORALIE:
/*---------------------------Génération de l'interface des levels------------------------------------*/ 

        Level level = new Level(game, TILEPATHLEVEL, 4, 4, screenSize);
        //level.reload();     
        level.addXML(LEVELPATH1);
        level.addXML(LEVELPATH2);
        level.addXML(LEVELPATH3);
        level.addXML(LEVELPATH4);
        level.addXML(LEVELPATH5);
        level.addXML(LEVELPATH6);
        level.addXML(LEVELPATH7);
        level.addXML(LEVELPATH8);
        level.addXML(LEVELPATH9);
        level.addXML(LEVELPATH10);
        level.addXML(LEVELPATH11);
        level.addXML(LEVELPATH12);
        level.addXML(LEVELPATH13);
        level.addXML(LEVELPATH14);
        level.addXML(LEVELPATH15);
        level.addXML(LEVELPATH16);
        
        //System.out.println("Chemin : " + level.getListXML().get(0));

        game.setLevel(level); //On ajoute le level dans game pour pouvoir revenir au choix des niveaux plus tard
        
        game.getWindow().setFramerateLimit(30);
        
        game.getWindow().clear();
        level.drawLogo();
    	game.getWindow().display();
        
        

        while ((level.getLaunch() == false) && game.getWindow().isOpen()) {
    		level.launchGame();
        }
        
        level.reload();
        game.getWindow().clear();
        level.draw();
    	game.getWindow().display();
    	

        Clock bug_clock = new Clock();
 

        while ((level.getMakeChoice() == false)) {
        	bug_clock.restart();
            while(bug_clock.getElapsedTime().asSeconds() < 0.5f){
    			
    		}
        	level.launchLevel();
        }
        
        System.out.println("Chargement de l'interface");

/*--------------------Génération de l'interface de jeux et de son controleur-------------------------*/ 
        	
        //Animation animate = new Animation:
        Controler control = new Controler(screenSize,game);
        
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte


        while (game.getWindow().isOpen()) {

        	control.update();
        	
            // On gère les événements
            control.supervise();

    		game.getWindow().display();
    		
	    			while(game.getStateSimulation()) {
	    				System.out.println("Start Simulation : " + game.getCharacter("BasicBot").getPosition());
	    				
	    				Ordonnanceur ordo = new Ordonnanceur(game);
	    				for(String mapKey : game.getCharacter().keySet()) {
	    					boolean orderExist;
	    					if(game.getCharacter(mapKey).getListOrder().get(0).size() > 0) {
	    						orderExist = true;
	    					} else {
	    						orderExist = false;
	    					}
							Clock clock = new Clock();
	    					while(orderExist && game.getStateSimulation()) {
	    						clock.restart();
	    						game.getWindow().display();
	    			        	game.getWindow().clear();
	    						
	        					if (!ordo.step()){
	        						orderExist = false;
	        					}
	        					
	    						control.update();
	    						control.supervise();
	    						while(clock.getElapsedTime().asSeconds() < 0.5f){
	    							
	    						}
	    					}
	    				}
	    				System.out.println("End of Simulation");
	    				if(game.levelIsCompleted()){
	    					System.out.println("FELICITATION !!");
	    					control.completedLevel(); // On affiche un WIN
	    				}
	    				game.setStateSimulation(false);
	    			}
	    	game.getWindow().clear();
        }
    }
}
