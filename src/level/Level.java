package level;

import graphics.Screen;
import level.tile.Tile;
import java.util.Random;

public class Level {
    protected String path;
    protected int width, height;
    public int[] tiles;
    private static final Random RNG = new Random();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new int[width * height];

        generateLevel();
    }

    public Level(String path) {
        this.path = path;
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = RNG.nextInt(4);
            }
        }
    }

    protected void loadLevel(String path) {
    }

    protected void time() {
    }

    public void update() {
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.voidTile;
        if (tiles[x + y * width] == 0)
            return Tile.ground;
        else if (tiles[x + y * width] == 1)
            return Tile.crackedGround;
        else if (tiles[x + y * width] == 2)
            return Tile.chippedGround;
        return Tile.ground;
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);

        // left most border
        int x0 = xScroll >> 4;
        // right most border
        int x1 = (xScroll + screen.width + 16) >> 4; // 16 is tile size
        // top most border
        int y0 = yScroll >> 4;
        // bot most border
        int y1 = (yScroll + screen.height + 16) >> 4; // 16 is tile size

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

}
