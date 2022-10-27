package graphics;

import java.util.Random;

import level.tile.Tile;

public class Screen {
    private int width, height;
    private final int mapSize = 64;
    // private final int tileSize = 4; // actual size: 16

    public int[] pixels;

    public int[] tiles = new int[mapSize * mapSize];
    private Random random = new Random();

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

    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yy = y - yOffset;
            if (yy < 0 || yy >= height)
                continue;

            for (int x = 0; x < width; x++) {
                int xx = x - xOffset;
                if (xx < 0 || xx >= width)
                    continue;

                pixels[xx + yy * width] = Sprite.ground.pixels[(x & (Sprite.ground.SIZE - 1))
                        + (y & (Sprite.ground.SIZE - 1)) * Sprite.ground.SIZE];
            }
        }
    }

    public void renderTile(int xtile, int ytile, Tile tile) {
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                // later
            }
        }
    }
}
