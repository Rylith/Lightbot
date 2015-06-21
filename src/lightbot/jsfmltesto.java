package lightbot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Stack;
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
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.Event;

import lightbot.Button;
import lightbot.Character;

public class jsfmltesto {
	
	public static void drawCase(RenderWindow fenetre,Case case1){                      
        fenetre.draw(case1.getSprite());
	}
	
	private final static String LEVELPATH1 = "test2.xml";
	private final static String LEVELPATH2 = "level/maloc_lvl_1.xml";
	private final static String LEVELPATH3 = "level/maloc_lvl_2.xml";
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
        fenetre.create(new VideoMode(screenSize.x,screenSize.y), "LightBot",WindowStyle.CLOSE); //,WindowStyle.FULLSCREEN);

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
        level.reload(screenSize);
        
        level.addXML(LEVELPATH1);
        level.addXML(LEVELPATH2);
        level.addXML(LEVELPATH3);
        //System.out.println("Chemin : " + level.getListXML().get(0));

        
        game.setLevel(level); //On ajoute le level dans game pour pouvoir revenir au choix des niveaux plus tard
        
        game.getWindow().setFramerateLimit(30);
        
        level.drawLogo();
    	game.getWindow().display();
        game.getWindow().clear();
        

        while ((level.getLaunch() == false)) {
    		level.launchGame();
        }
        
        level.reload(screenSize);
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
	    				}
	    				game.setStateSimulation(false);
	    			}
	    	game.getWindow().clear();
        }
    }
}
