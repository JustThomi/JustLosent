package level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import level.tile.Tile;

public class MainLevel extends Level {

    public MainLevel(String path) {
        super(path);
        loadLevel(path);
    }

    @Override
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            this.width = image.getWidth();
            this.height = image.getHeight();
            // init pixel array with image size
            tiles = new int[width * height];

            image.getRGB(0, 0, width, height, tiles, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Failed to load image");
        }

    }

    // 0xFF8800 (orange) - ground
    // 0xFF0000 (red) - wall
    // 0xFFFF00 (yellow) - cracked ground
    // to be continued

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.voidTile;
        if (tiles[x + y * width] == 0xFFFF8800)
            return Tile.ground;
        else if (tiles[x + y * width] == 0xFFFFFF00)
            return Tile.crackedGround;
        else if (tiles[x + y * width] == 0xFFFF0000)
            return Tile.wall;
        else if (tiles[x + y * width] == 0xFFFF4400)
            return Tile.sideWall;

        return Tile.voidTile;
    }
}
