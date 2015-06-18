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
import org.jsfml.window.WindowStyle;
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
        	back_text.loadFromFile(Paths.get("ressource/Sprite/background.jpg"));

            //Texture was loaded successfully - retrieve and print size
            Vector2i size = back_text.getSize();
            //System.out.println("The texture is " + size.x + "x" + size.y);
        } catch(IOException ex) {
            //Ouch! something went wrong
            ex.printStackTrace();
        }
        Sprite back_sprite = new Sprite(back_text);
        //Vector2i screenSize = new Vector2i(1600,900);
        Vector2i screenSize = new Vector2i(1280,720);
        //Vector2i screenSize = new Vector2i(1440,900);
        //Vector2i screenSize = new Vector2i(1920,1080);
        fenetre.create(new VideoMode(screenSize.x,screenSize.y), "Prototype"); //,WindowStyle.FULLSCREEN);

        int frame = 3;
        int frameElec = 1;
        Clock animClock = new Clock();
        //Controler control = new Controler(screenSize);
        
        Button buttest = new Button("action.png", new Vector2f(50, 399), Button.ButtonType.Move, true);
        Button butturn = new Button("action.png", new Vector2f(50+72, 399), Button.ButtonType.TurnRight, true);
        Button butjump = new Button("action.png", new Vector2f(50+144, 399), Button.ButtonType.Jump, true);
        Button butallumer = new Button("action.png", new Vector2f(50+144+72, 399), Button.ButtonType.Light, true);
        Button buttelep = new Button("action.png", new Vector2f(50+288, 399), Button.ButtonType.UseP, true);

        
        Character robb = new Character(new Vector2i(0, 0), 1,Color.WHITE, "lightbot.png");
        Character robs = new Character(new Vector2i(0, 0), 1,Color.WHITE, "lightbot.png");
        robb.setOrientation(Character.Orientation.Right);
        robs.setOrientation(Character.Orientation.Right);
        Map testo = new Map(robb,robs,"test2.xml");
        Engine eng = new Engine(testo);
        //testo.setScale(new Vector2f(0.75f, 0.75f));
        
        Texture m_tileSet = new Texture();
		try {
			m_tileSet.loadFromFile(Paths.get("HautCarte.png"));
			m_tileSet.setSmooth(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sprite m_sprite = new Sprite(m_tileSet);
		m_sprite.setPosition(testo.getMapSize());
/*----------------------------------------Génération du robot----------------------------------------*/ 
        /*Character robot = new Character(new Vector2i(2,0), 1, Color.GREEN, "ressource/Sprite/lightbot.png");
        robot.setOrientation(Character.Orientation.Up);
        robot.setLimitOrder(0, 17);
        //robot.drawCharac(fenetre);
        control.init(robot,robot);*/
/*----------------------------------------Génération du robot----------------------------------------*/ 
        
        // Boucle principale qui s’exécute tant que la fenêtre est ouverte
        while (fenetre.isOpen()) {
        	fenetre.draw(back_sprite);

            fenetre.draw(buttest.getSprite());
            fenetre.draw(butturn.getSprite());
            fenetre.draw(butallumer.getSprite());
            
            testo.drawMap(fenetre);
            fenetre.draw(m_sprite);

            // On gère les événements
            fenetre.display();
            fenetre.clear();
            
            for (Event event : fenetre.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    // Si l'utilisateur clique sur la croix rouge alors on ferme
                    // la fenêtre
                    fenetre.close();
                }
                if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
                	Vector2i mouse_pos = Mouse.getPosition(fenetre);
                	
                	if (buttest.isClicked(mouse_pos)) {
                		Order ordreMove = new Move(robb, eng);
                		ordreMove.executer();
                	}
                	if (butturn.isClicked(mouse_pos)) {
                		Order orderRight = new TurnRight(robb);
                		orderRight.executer();
                	}
                	if (butallumer.isClicked(mouse_pos)) {
                		Order ordre_light= new Light(robb, eng, Color.WHITE);
                		ordre_light.executer();
                	}
                	if (butjump.isClicked(mouse_pos)) {
                		Order jump = new Jump(robb, eng);
                		jump.executer();
                	}
                	if (buttelep.isClicked(mouse_pos)) {
                		Order usep = new AccessPointer(robb, eng, Color.BLUE);
                		usep.executer();
                	}
                }
            }

        } 
    }
}