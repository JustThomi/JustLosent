package graphics;

import java.util.Random;

public class Screen {
    private int width, height;
    private final int mapSize = 64;
    // private final int tileSize = 4; // actual size: 16

    public int[] pixels;

    public int[] tiles = new int[mapSize * mapSize];
    private Random random = new Random();

    // TILESET
    public static SpriteSheet groundSheet = new SpriteSheet("../assets/tileset.png", 512);

    // TILE
    public static Sprite metalFlore = new Sprite(16, 0, 0, groundSheet);

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < mapSize * mapSize; i++) {
            tiles[i] = random.nextInt(0xFFFFFF);
        }
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            pixels[i] = 0;
        }
    }

    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            // int yy = y + yOffset;
            // if (y < 0 || y >= height)
            // break;
            for (int x = 0; x < width; x++) {
                // int xx = x + xOffset;
                // if (x < 0 || x >= width)
                // break;
                // int tileIndex = ((xx >> tileSize) & mapSize - 1) + ((yy >> tileSize) &
                // mapSize - 1) * mapSize;
                pixels[x + y * width] = metalFlore.pixels[(x & (metalFlore.SIZE - 1))
                        + (y & (metalFlore.SIZE - 1)) * metalFlore.SIZE];
            }
        }
    }
}
