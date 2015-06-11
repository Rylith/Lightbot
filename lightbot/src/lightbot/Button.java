package lightbot;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

public class Button {
	
	private Sprite button_sprite;
	
	public Button(String texture_file,float x, float y) {
		Texture textureButton = new Texture();
		try {
            //Try to load the texture from file "jsfml.png"
        	textureButton.loadFromFile(Paths.get(texture_file));
        	
        } catch(IOException ex) {
            //Ouch! something went wrong
            ex.printStackTrace();
        }
		this.button_sprite = new Sprite(textureButton);
		this.button_sprite.scale(0.5f, 0.5f);
		this.button_sprite.setPosition(x, y);
	}
	
	public boolean clicked(Vector2i pos){
		return this.button_sprite.getGlobalBounds().contains(pos.x, pos.y);
	}
	
	public Sprite getSprite(){
		return this.button_sprite;
	}
}
