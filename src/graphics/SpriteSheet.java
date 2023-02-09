package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private String path;
    public final int SIZE;
    public int[] pixels;

    // TILESET
    public static SpriteSheet groundSheet = new SpriteSheet("/assets/tileset.png",
            512);

    /**
     * @param path
     * @param size
     */
    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    /**
     * Loads sprite
     */
    private void load() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            int width = image.getWidth();
            int height = image.getHeight();

            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
