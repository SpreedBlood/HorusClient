package sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class SpriteStorage {

    private Map<String, BufferedImage> images;

    public SpriteStorage() {
        this.images = new HashMap<>();
        this.addSprite("tile.png");
        this.addSprite("tileoutline.png");
        this.addSprite("avatar.png");
    }

    public BufferedImage getSprite(String name) {
        return images.get(name);
    }

    private void addSprite(String name) {
        try {
            BufferedImage image = ImageIO.read(new File(name));
            if (image != null)
                images.put(name, image);
            else
                System.out.println("Sprite: " + name + " does not exist!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static SpriteStorage instance;

    public static SpriteStorage getInstance() {
        if (instance == null)
            instance = new SpriteStorage();
        return instance;
    }

}
