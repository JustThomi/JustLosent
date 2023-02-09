package graphics;

import java.util.Random;

import level.tile.Tile;

public class Screen {
    public int width, height;
    private final int mapSize = 64;

    public int[] pixels;

    public int[] tiles = new int[mapSize * mapSize];
    private static Random random = new Random();
    public int xOffset, yOffset;

    /**
     * @param width
     * @param height
     */
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < mapSize * mapSize; i++) {
            tiles[i] = random.nextInt(0xFFFFFF);
        }
    }

    /**
     * @return screen width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return screen height
     */
    public int getHeight() {
        return height;
    }

    /**
     * clears pixel buffer
     */
    public void clear() {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }
    }

    /**
     * Set screen offset
     * 
     * @param xOffset
     * @param yOffset
     */
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Render tiles
     * 
     * @param xtile
     * @param ytile
     * @param tile
     */
    public void renderTile(int xtile, int ytile, Tile tile) {
        xtile -= xOffset;
        ytile -= yOffset;

        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbsolute = y + ytile;

            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbsolute = x + xtile;

                if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height)
                    break;

                if (xAbsolute < 0)
                    xAbsolute = 0;

                int pixelColor = tile.sprite.pixels[x + y * tile.sprite.SIZE];

                if (pixelColor != 0xFFFF00FF)
                    pixels[xAbsolute + yAbsolute * width] = pixelColor;
            }
        }
    }

    /**
     * Render player sprite
     * 
     * @param xPlayer
     * @param yPlayer
     * @param sprite
     * @param flipped
     */
    public void renderPlayer(int xPlayer, int yPlayer, Sprite sprite, boolean flipped) {
        xPlayer -= xOffset;
        yPlayer -= yOffset;

        for (int y = 0; y < sprite.SIZE; y++) {
            int yAbsolute = y + yPlayer;

            for (int x = 0; x < sprite.SIZE; x++) {
                int xAbsolute = x + xPlayer;
                int xFlipped = x;

                if (flipped)
                    xFlipped = (sprite.SIZE - 1) - x;

                int pixelColor = sprite.pixels[xFlipped + y * sprite.SIZE];
                if (pixelColor != 0xFFFF00FF)
                    pixels[xAbsolute + yAbsolute * width] = pixelColor;
            }
        }
    }

    /**
     * Renders all sprites
     * 
     * @param xSprite
     * @param ySprite
     * @param sprite
     */
    public void renderSprite(int xSprite, int ySprite, Sprite sprite) {
        xSprite -= xOffset;
        ySprite -= yOffset;

        for (int y = 0; y < sprite.SIZE; y++) {
            int yAbsolute = y + ySprite;

            for (int x = 0; x < sprite.SIZE; x++) {
                int xAbsolute = x + xSprite;

                if (xAbsolute < -sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height)
                    break;

                if (xAbsolute < 0)
                    xAbsolute = 0;

                int pixelColor = sprite.pixels[x + y * sprite.SIZE];

                if (pixelColor != 0xFFFF00FF)
                    pixels[xAbsolute + yAbsolute * width] = pixelColor;
            }
        }
    }
}
