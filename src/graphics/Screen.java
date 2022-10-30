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

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < mapSize * mapSize; i++) {
            tiles[i] = random.nextInt(0xFFFFFF);
        }
    }

    public void clear() {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

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

                if (yAbsolute < 0)
                    yAbsolute = 0;

                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
}
